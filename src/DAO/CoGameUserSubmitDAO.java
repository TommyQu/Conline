package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import domain.CoGameUserSubmit;

public class CoGameUserSubmitDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;

	/**
	 * 删除用户提交
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
		sql = "delete from CoGameUserSubmit WHERE " + lmt;
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoGameUserSubmit", exp.getMessage());
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
	 * 插入用户提交
	 * 
	 * @param userLog
	 * @return
	 */
	public boolean insert(CoGameUserSubmit gameSubmit) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		boolean ans = true;
		if (gameSubmit == null)
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;

		sql = "insert into CoGameUserSubmit (userId,pid,submitTime,submitFile,judgeStatus,status,score,judgeResult,extendJudgeFile) VALUES";

		sql += String
				.format("('%d','%d',%s','%s','%s','%d','%f','%s','%s');", 
						gameSubmit.getUserId(),
						gameSubmit.getPid(),
						StringEscapeUtils.escapeSql(df.format(gameSubmit.getSubmitTime())),
						StringEscapeUtils.escapeSql(gameSubmit.getSubmitFile()),
						StringEscapeUtils.escapeSql(gameSubmit.getJudgeStatus()),
						gameSubmit.getStatus(),
						gameSubmit.getScore(),
						StringEscapeUtils.escapeSql(gameSubmit.getJudgeResult()),
						StringEscapeUtils.escapeSql(gameSubmit.getExtendJudgeFile())
						
						);
		// App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoGameUserSubmitDao", exp.getMessage());
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
	 * 查询用户提交
	 * 
	 * @param lmt
	 * @return
	 */
	public List<CoGameUserSubmit> query(String lmt) {
		List<CoGameUserSubmit> ans = new ArrayList<CoGameUserSubmit>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoGameUserSubmit";
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
				CoGameUserSubmit gameSubmit = new CoGameUserSubmit();
				gameSubmit.setId(rst.getInt("id"));
				gameSubmit.setUserId(rst.getInt("userId"));
				gameSubmit.setPid(rst.getInt("pid"));
				gameSubmit.setSubmitTime(rst.getDate("submitTime"));
				gameSubmit.setSubmitFile(rst.getString("submitFile"));
				gameSubmit.setJudgeStatus(rst.getString("judgeStatus"));
				gameSubmit.setStatus(rst.getInt("status"));
				gameSubmit.setScore(rst.getDouble("score"));
				gameSubmit.setJudgeResult(rst.getString("judgeResult"));
				gameSubmit.setExtendJudgeFile(rst.getString("extendJudgeFile"));
				ans.add(gameSubmit);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoGameUserSubmitDao", exp.getMessage());
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
