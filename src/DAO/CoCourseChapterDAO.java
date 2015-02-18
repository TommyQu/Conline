//by 杨楠
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.CoCourseChapter;
import org.apache.commons.lang.*;

/**
 * 课程章节
 **/
public class CoCourseChapterDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;

	/*
	 * 删除课程章节
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
		sql = "delete from CoCourseChapter WHERE " + lmt;
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseChapterDAO", exp.getMessage());
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
	 * 插入课程章节
	 * 
	 * @param courseChapter
	 * @return
	 */
	public boolean insert(CoCourseChapter courseChapter) {
		boolean ans = true;
		if (courseChapter == null)
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;
		sql = "insert into CoCourseChapter (title,description,period,averagePeriod,studyCount,difficulty) VALUES";
		sql += String.format("('%s','%s','%d','%f','%d','%f');",
				StringEscapeUtils.escapeSql(courseChapter.getTitle()),
				StringEscapeUtils.escapeSql(courseChapter.getDescription()),
				courseChapter.getPeriod(),
				courseChapter.getAveragePeriod(),
				courseChapter.getStudyCount(),
				courseChapter.getDifficulty()
				);
		// App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseChapterDAO", exp.getMessage());
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
	 * 查询课程章节
	 * 
	 * @param lmt
	 * @return
	 */
	public List<CoCourseChapter> query(String lmt) {
		List<CoCourseChapter> ans = new ArrayList<CoCourseChapter>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoCourseChapter";
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
				CoCourseChapter courseChapter = new CoCourseChapter();
				courseChapter.setId(rst.getInt("id"));
				courseChapter.setTitle(rst.getString("title"));
				courseChapter.setDescription(rst.getString("description"));
				courseChapter.setPeriod(rst.getInt("period"));
				courseChapter.setAveragePeriod(rst.getDouble("averagePeriod"));
				courseChapter.setStudyCount(rst.getInt("studyCount"));
				courseChapter.setDifficulty(rst.getDouble("difficulty"));
				ans.add(courseChapter);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseChapterDAO", exp.getMessage());
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
