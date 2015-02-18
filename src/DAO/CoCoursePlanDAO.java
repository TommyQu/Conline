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

import domain.CoCoursePlan;
/**
用户学习计划
**/
public class CoCoursePlanDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;

	/*
	 * 删除用户学习计划
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
		sql = "delete from CoCoursePlan WHERE " + lmt;
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoCoursePlanDAO", exp.getMessage());
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
	 * 插入用户学习计划
	 * 
	 * @param coursePlan
	 * @return
	 */
	public boolean insert(CoCoursePlan a) {
		boolean ans = true;
		if (a == null)
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		sql = "insert into CoCoursePlan (userId,chapterId,startTime,period,status) VALUES";
		sql += String.format("('%d','%d','%s','%d','%d');",
				a.getUserId(),
				a.getChapterId(),
				StringEscapeUtils.escapeSql(df.format(a.getStartTime())),
				a.getPeriod(),
				a.getStatus()
				);
		// App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoCoursePlanDAO", exp.getMessage());
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
	 * 查询用户学习计划
	 * 
	 * @param lmt
	 * @return
	 */
	public List<CoCoursePlan> query(String lmt) {
		List<CoCoursePlan> ans = new ArrayList<CoCoursePlan>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoCoursePlan";
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
				CoCoursePlan a = new CoCoursePlan();
				a.setUserId(rst.getInt("userId"));
				a.setChapterId(rst.getInt("chapterId"));
				a.setStartTime(rst.getDate("startTime"));
				a.setPeriod(rst.getInt("period"));
				a.setStatus(rst.getInt("status"));
				ans.add(a);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoCoursePlanDAO", exp.getMessage());
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
