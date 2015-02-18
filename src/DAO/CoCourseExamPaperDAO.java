//by 杨楠
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import domain.CoCourseExamPaper;
/**
试卷表信息
**/
public class CoCourseExamPaperDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;

	/*
	 * 删除试卷表信息
	 * @param lmt
	 * @return
	 */
	public boolean delete(String lmt) {
		if (lmt == null || lmt.isEmpty())
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;
		sql = "delete from CoCourseExamPaper WHERE " + lmt;
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseExamPaperDAO", exp.getMessage());
			return false;
		} finally {
			try {// 关闭连接
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
			}
		}
		return ans;
	}

	/**
	 * 插入试卷表信息
	 * 
	 * @param courseExamPaper
	 * @return
	 */
	public boolean insert(CoCourseExamPaper a) {
		boolean ans = true;
		if (a == null)
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		sql = "insert into CoCourseExamPaper (title,description,configFile,attendCount,averageScore,averageUseTime,createTime,createPerson) VALUES";
		sql += String.format("('%s','%s','%s','%d','%f','%f','%s','%s');",
				StringEscapeUtils.escapeSql(a.getTitle()),
				StringEscapeUtils.escapeSql(a.getDescription()),
				StringEscapeUtils.escapeSql(a.getConfigFile()),
				a.getAttendCount(),
				a.getAverageScore(),
				a.getAverageUseTime(),
				StringEscapeUtils.escapeSql(df.format(a.getCreateTime())),
				StringEscapeUtils.escapeSql(a.getCreatePerson())
				);
		// App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseExamPaperDAO", exp.getMessage());
			return false;
		} finally {
			try {// 关闭连接
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
			}
		}
		return ans;
	}

	/**
	 * 查询试卷表信息
	 * 
	 * @param lmt
	 * @return
	 */
	public List<CoCourseExamPaper> query(String lmt) {
		List<CoCourseExamPaper> ans = new ArrayList<CoCourseExamPaper>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoCourseExamPaper";
		if (lmt != null && lmt.isEmpty() == false)
			sql += " WHERE " + lmt;
		if (this.queryStartPos >= 0 && this.queryStartCount >= 0) {
			sql += String.format(" LIMIT %d,%d ", this.queryStartPos,
					this.queryStartCount);
		} else if (this.queryStartCount >= 0) {
			sql += String.format(" LIMIT %d ", this.queryStartCount);
		}
		sql += ";";
		PreparedStatement pStmt;
		try {
			pStmt = conn.prepareStatement(sql);
			ResultSet rst = pStmt.executeQuery();
			if (rst == null)
				return ans;
			while (rst.next()) {
				CoCourseExamPaper a = new CoCourseExamPaper();
				a.setId(rst.getInt("id"));
				a.setTitle(rst.getString("title"));
				a.setDescription(rst.getString("description"));
				a.setConfigFile(rst.getString("configFile"));
				a.setAttendCount(rst.getInt("attendCount"));
				a.setAverageScore(rst.getDouble("averageScore"));
				a.setAverageUseTime(rst.getDouble("averageUseTime"));
				a.setCreateTime(rst.getDate("createTime"));
				a.setCreatePerson(rst.getString("createPerson"));
				ans.add(a);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseExamPaperDAO", exp.getMessage());
		} finally {
			try {// 关闭连接
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
			}
		}
		return ans;
	}
}
