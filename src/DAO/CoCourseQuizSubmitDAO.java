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

import domain.CoCourseQuizSubmit;
/**
试卷提交信息
**/
public class CoCourseQuizSubmitDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;

	/*
	 * 删除试卷提交信息
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
		sql = "delete from CoCourseQuizSubmit WHERE " + lmt;
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseQuizSubmitDAO", exp.getMessage());
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
	 * 插入试卷提交信息
	 * 
	 * @param coursePlan
	 * @return
	 */
	public boolean insert(CoCourseQuizSubmit a) {
		boolean ans = true;
		if (a == null)
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		sql = "insert into CoCourseQuizSubmit (userId,chapterId,examId,submitTime,submitFile,status,judgeStatus,score,judgeResult,extendMessage,language) VALUES";
		sql += String.format("('%d','%d','%d','%s','%s','%d','%s','%f','%s','%s','%s');",
				a.getUserId(),
				a.getChapterId(),
				a.getExamId(),
				StringEscapeUtils.escapeSql(df.format(a.getSubmitTime())),
				StringEscapeUtils.escapeSql(a.getSubmitFile()),
				a.getStatus(),
				StringEscapeUtils.escapeSql(a.getJudgeStatus()),
				a.getScore(),
				StringEscapeUtils.escapeSql(a.getJudgeResult()),
				StringEscapeUtils.escapeSql(a.getExtendMessage()),
				StringEscapeUtils.escapeSql(a.getLanguage())	
				);
		// App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseQuizSubmitDAO", exp.getMessage());
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
	 * 查询试卷提交信息
	 * 
	 * @param lmt
	 * @return
	 */
	public List<CoCourseQuizSubmit> query(String lmt) {
		List<CoCourseQuizSubmit> ans = new ArrayList<CoCourseQuizSubmit>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoCourseQuizSubmit";
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
				CoCourseQuizSubmit a = new CoCourseQuizSubmit();
				a.setId(rst.getInt("id"));
				a.setUserId(rst.getInt("userId"));
				a.setChapterId(rst.getInt("chapterId"));
				a.setExamId(rst.getInt("examId"));
				a.setSubmitTime(rst.getDate("submitTime"));
				a.setSubmitFile(rst.getString("submitFile"));
				a.setJudgeStatus (rst.getString("judgeStatus"));
				a.setStatus(rst.getInt("status"));
				a.setScore(rst.getDouble("score"));
				a.setJudgeResult(rst.getString("judgeResult"));
				a.setExtendMessage(rst.getString("extendMessage"));
				a.setLanguage(rst.getString("language"));
				ans.add(a);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseQuizSubmitDAO", exp.getMessage());
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
