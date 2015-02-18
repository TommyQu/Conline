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

import domain.CoEmailLog;
/**
邮件发送日志
**/
public class CoEmailLogDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;

	/*
	 * 删除邮件发送日志
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
			App.outPutErrorLog("CoEmailLogDAO", exp.getMessage());
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
	 * 插入邮件发送日志
	 * 
	 * @param emailLog
	 * @return
	 */
	public boolean insert(CoEmailLog a) {
		boolean ans = true;
		if (a == null)
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		sql = "insert into CoCoursePlan (userId,sendAddress,fromAddress,sendTime,title,content,status,type) VALUES";
		sql += String.format("('%d','%s','%s','%s','%s','%s','%d','%s');",
				a.getUserId(),
				StringEscapeUtils.escapeSql(a.getSendAddress()),
				StringEscapeUtils.escapeSql(a.getFromAddress()),
				StringEscapeUtils.escapeSql(df.format(a.getSendTime())),
				StringEscapeUtils.escapeSql(a.getTitle()),
				StringEscapeUtils.escapeSql(a.getContext()),
				a.getStatus(),
				StringEscapeUtils.escapeSql(a.getType())
				);
		// App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoEmailLogDAO", exp.getMessage());
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
	 * 查询邮件发送日志
	 * 
	 * @param lmt
	 * @return
	 */
	public List<CoEmailLog> query(String lmt) {
		List<CoEmailLog> ans = new ArrayList<CoEmailLog>();
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
				CoEmailLog a = new CoEmailLog();
				a.setId(rst.getInt("id"));
				a.setUserId(rst.getInt("userId"));
				a.setSendAddress(rst.getString("sendAddress"));
				a.setFromAddress(rst.getString("fromAddress"));
				a.setSendTime(rst.getDate("sendTime"));
				a.setTitle(rst.getString("title"));
				a.setContext(rst.getString("content"));
				a.setStatus(rst.getInt("status"));
				a.setType(rst.getString("type"));
				ans.add(a);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoEmailLogDAO", exp.getMessage());
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
