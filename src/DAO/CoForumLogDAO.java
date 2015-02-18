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

import domain.CoForumLog;

public class CoForumLogDAO {
	public int queryStartPos=-1;
	public int queryStartCount=-1;
	/**
	 * 删除论坛管理日志
	 * @param lmt
	 * @return
	 */
	public boolean delete(String lmt){
		if(lmt==null || lmt.isEmpty())return false;	
		String sql="";
		Connection conn=App.getSqlConnection();	
		if(conn==null)return false;
		sql="delete from CoForumLog  WHERE "+lmt;
		boolean ans=true;
		Statement stmt;
		try{
			stmt=conn.createStatement();
			int count=stmt.executeUpdate(sql);
			ans= count==1;
		}
		catch(Exception exp){
			App.outPutErrorLog("CoForumLog", exp.getMessage());
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
	
	/**
	 * 插入日志
	 * @param userLog
	 * @return
	 */
	public boolean insert(CoForumLog forumLog){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean ans=true;
		if(forumLog==null )
			return false;		
		String sql="";
		Connection conn=App.getSqlConnection();	
		if(conn==null)return false;
		
		sql="insert into CoForumLog (userId,createTime,content) VALUES";
		
		sql+=String.format("('%d','%s','%s');",
				forumLog.getUserId(),
				StringEscapeUtils.escapeSql(df.format(forumLog.getCreatTime())),
				StringEscapeUtils.escapeSql(forumLog.getContent())
				);
	//	App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try{
			stmt=conn.createStatement();
			int count=stmt.executeUpdate(sql);
			ans= count==1;
		}
		catch(Exception exp){
			App.outPutErrorLog("CoForumLogDao", exp.getMessage());
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
	/**
	 * 查询日志
	 * @param lmt
	 * @return
	 */
public List<CoForumLog> query(String lmt) {
			List<CoForumLog> ans=new ArrayList<CoForumLog>();
			String sql="";
			Connection conn=App.getSqlConnection();	
			if(conn==null)return null;
			sql="select * from CoForumLog";
			if(lmt!=null && lmt.isEmpty()==false)
				sql+=" WHERE "+lmt;
			if(this.queryStartPos>=0 && this.queryStartCount>=0){
				sql+=String.format(" LIMIT %d,%d ", this.queryStartPos,this.queryStartCount);
			}
			else if(this.queryStartCount>=0){
				sql+= String.format(" LIMIT %d ", this.queryStartCount);
			}
			sql+=";";
			PreparedStatement pStmt;
			try{
				pStmt=conn.prepareStatement(sql);
				ResultSet rst=pStmt.executeQuery();
				if(rst==null)return ans;
				while(rst.next()){
					CoForumLog forumLog=new CoForumLog();
					forumLog.setId(rst.getInt("id"));
					forumLog.setUserId(rst.getInt("userId"));
					forumLog.setCreatTime(rst.getDate("createTime"));
					forumLog.setContent(rst.getString("content"));
					ans.add(forumLog);
				}
				rst.close();
				pStmt.close();
			}
			catch(Exception exp){
				App.outPutErrorLog("CoForumLogDao", exp.getMessage());
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
