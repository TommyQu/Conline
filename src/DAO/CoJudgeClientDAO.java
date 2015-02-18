//by 杨楠
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import domain.CoJudgeClient;
/**
评测机信息表
**/
public class CoJudgeClientDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;

	/*
	 * 删除评测机信息表
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
			App.outPutErrorLog("CoJudgeClientDAO", exp.getMessage());
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
	 * 插入评测机信息表
	 * 
	 * @param judgeClient
	 * @return
	 */
	public boolean insert(CoJudgeClient a) {
		boolean ans = true;
		if (a == null)
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;
		sql = "insert into CoCoursePlan (ip,currentThread,maxJudgeThread,level,status) VALUES";
		sql += String.format("('%s','%d','%d','%d','%d');",
				StringEscapeUtils.escapeSql(a.getIp()),
				a.getCurrentThread(),
				a.getMaxJudgeThread(),
				a.getLevel(),
				a.getStatus()
				);
		// App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoJudgeClientDAO", exp.getMessage());
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
	 * 查询评测机信息表
	 * 
	 * @param lmt
	 * @return
	 */
	public List<CoJudgeClient> query(String lmt) {
		List<CoJudgeClient> ans = new ArrayList<CoJudgeClient>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoJudgeClient";
		sql+=" order by status desc";
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
				CoJudgeClient a = new CoJudgeClient();
				a.setIp(rst.getString("ip"));
				a.setCurrentThread(rst.getInt("currentThread"));
				a.setMaxJudgeThread(rst.getInt("maxJudgeThread"));
				a.setLevel(rst.getInt("level"));
				a.setStatus(rst.getInt("status"));
				ans.add(a);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoJudgeClientDAO", exp.getMessage());
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
