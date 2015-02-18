//By 陈帆

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import domain.CoUserLog;

public class CoUserLogDAO {

	public int queryStartPos = -1;
	public int queryStartCount = -1;

	/**
	 * 删除日志
	 * 
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
		sql = "delete from CoUserLog  WHERE " + lmt;
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoUserLog", exp.getMessage());
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
	 * 插入日志
	 * 
	 * @param userLog
	 * @return
	 */
	public boolean insert(CoUserLog userLog) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean ans = true;
		if (userLog == null)
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;

		sql = "insert into CoUserLog (userId,logType,logContent,createTime) VALUES";

		sql += String
				.format("('%d','%s','%s','%s');", userLog.getUserId(),
						StringEscapeUtils.escapeSql(userLog.getLogType()),
						StringEscapeUtils.escapeSql(userLog.getLogContent()),
						StringEscapeUtils.escapeSql(df.format(userLog
								.getCreateTime())));
		// App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoUserLogDao", exp.getMessage());
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
	 * 查询用户
	 * 
	 * @param lmt
	 * @return
	 */
	public List<CoUserLog> query(String lmt) {
		List<CoUserLog> ans = new ArrayList<CoUserLog>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoUserLog";
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
				CoUserLog userLog = new CoUserLog();
				userLog.setId(rst.getInt("id"));
				userLog.setUserId(rst.getInt("userId"));
				userLog.setLogType(rst.getString("logType"));
				userLog.setLogContent(rst.getString("logContent"));
				userLog.setCreateTime(rst.getDate("createTime"));
				ans.add(userLog);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoUserLogDao", exp.getMessage());
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
