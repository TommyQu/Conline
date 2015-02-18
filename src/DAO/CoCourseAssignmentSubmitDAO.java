//by 杨楠
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import domain.CoCourseAssignmentSubmit;
import domain.CoProblem;
/**
用户作业提交
**/
public class CoCourseAssignmentSubmitDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;
	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/*
	 * 删除用户作业提交
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
		sql = "delete from CoCourseAssignmentSubmit WHERE " + lmt;
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseAssignmentSubmitDAO", exp.getMessage());
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
	 * 插入用户作业提交
	 * 
	 * @param courseAssignmentSubmit
	 * @return
	 */
	public int insert(CoCourseAssignmentSubmit a) {
		boolean ans = true;
		if (a == null)
			return 0;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return 0;

		sql = "insert into CoCourseAssignmentSubmit (userId,chapterId,pid,submitTime,submitFile,judgeStatus,status,score,judgeResult,extendMessage,language) VALUES";
		sql += String.format("('%d','%d','%d','%s','%s','%s','%d','%f','%s','%s','%s');",
				a.getUserId(),
				a.getChapterId(),
				a.getPid(),
				StringEscapeUtils.escapeSql(df.format(a.getSubmitTime())),
				StringEscapeUtils.escapeSql(a.getSubmitFile()),
				StringEscapeUtils.escapeSql(a.getJudgeStatus()),
				a.getStatus(),
				a.getScore(),
				StringEscapeUtils.escapeSql(a.getJudgeResult()),
				StringEscapeUtils.escapeSql(a.getExtendMessage()),
				StringEscapeUtils.escapeSql(a.getLanguage())
				);
		// App.outPutErrorLog("reg user", sql);
		System.out.println(sql);
		Statement stmt;
		int id=0;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs=stmt.getGeneratedKeys();
			if(rs.next())
			{
				id=rs.getInt(1);
				//System.out.println(id);
			}
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseAssignmentSubmitDAO", exp.getMessage());
			return 0;
		} finally {
			try {// 关闭连接
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
			}
		}
		return id;
	}

	/**
	 * 查询用户作业提交
	 * 
	 * @param lmt
	 * @return
	 */
	public List<CoCourseAssignmentSubmit> query(String lmt) {
		List<CoCourseAssignmentSubmit> ans = new ArrayList<CoCourseAssignmentSubmit>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoCourseAssignmentSubmit ";
		if (lmt != null && lmt.isEmpty() == false)
			sql += " WHERE " + lmt ;
		sql += " order by id desc ";
		if (this.queryStartPos >= 0 && this.queryStartCount >= 0) {
			sql += String.format(" LIMIT %d,%d ", this.queryStartPos,
					this.queryStartCount);
		} else if (this.queryStartCount >= 0) {
			sql += String.format(" LIMIT %d ", this.queryStartCount);
		}
		sql += " ;";
		PreparedStatement pStmt;
		try {
			pStmt = conn.prepareStatement(sql);
			ResultSet rst = pStmt.executeQuery();
			if (rst == null)
				return ans;
			while (rst.next()) {
				CoCourseAssignmentSubmit a = new CoCourseAssignmentSubmit();
				Date submitTime=df.parse(rst.getString("submitTime"));
				a.setId(rst.getInt("id"));
				a.setUserId(rst.getInt("userId"));
				a.setChapterId(rst.getInt("chapterId"));
				a.setPid(rst.getInt("pid"));
				a.setSubmitTime(submitTime);
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
			App.outPutErrorLog("CoCourseAssignmentSubmitDAO", exp.getMessage());
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
	
	public boolean update(CoCourseAssignmentSubmit ccas){
		boolean ans=true;
		if(ccas==null )
			return false;		
		String sql="";
		Connection conn=App.getSqlConnection();	
		if(conn==null)return false;
		sql="update CoCourseAssignmentSubmit set ";
		//App.outPutErrorLog("reg user", sql);
		sql+=String.format("userId='%d',chapterId='%d',pid='%d',submitFile='%s',judgeStatus='%s',status='%d',score='%f',judgeResult='%s',extendMessage='%s',language='%s' WHERE id=%d;",
				ccas.getUserId(),
				ccas.getChapterId(),
				ccas.getPid(),
				StringEscapeUtils.escapeSql(ccas.getSubmitFile()),
				StringEscapeUtils.escapeSql(ccas.getJudgeStatus()),
				ccas.getStatus(),
				ccas.getScore(),
				StringEscapeUtils.escapeSql(ccas.getJudgeResult()),
				StringEscapeUtils.escapeSql(ccas.getExtendMessage()),
				StringEscapeUtils.escapeSql(ccas.getLanguage()),
				ccas.getId()
				);
		//App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try{
			stmt=conn.createStatement();
			int count=stmt.executeUpdate(sql);
			ans= count==1;
		}
		catch(Exception exp){
			App.outPutErrorLog("CoAssignmentSubmitDAO", exp.getMessage());
			return false;
		}
		finally{
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
