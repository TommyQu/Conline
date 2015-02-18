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

import domain.CoForumTopic;
import domain.CoUserInfo;

public class CoForumTopicDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 删除论坛主题
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
		sql = "delete from CoForumTopic WHERE " + lmt;
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoForumTopicDAO", exp.getMessage());
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
	 * 插入论坛主题
	 * 
	 * @param forumTopic
	 * @return
	 */
	public boolean insert(CoForumTopic forumTopic) {
		boolean ans = true;
		if (forumTopic == null)
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;
		sql = "insert into CoForumTopic (userId,topic,content,keyword,level,status,accessCount,lastReplyTime,createTime,attachFileId,attachFileName) VALUES";
		sql += String.format("('%d','%s','%s','%s','%d','%d','%d','%s','%s','%d','%s');",
				forumTopic.getUserId(), 
				StringEscapeUtils.escapeSql(forumTopic.getTopic()), 
				StringEscapeUtils.escapeSql(forumTopic.getContent()), 
				StringEscapeUtils.escapeSql(forumTopic.getKeyword()), 
				forumTopic.getLevel(), 
				forumTopic.getStatus(), 
				forumTopic.getAccessCount(),
				StringEscapeUtils.escapeSql(df.format(forumTopic.getLastReplyTime())), 
				StringEscapeUtils.escapeSql(df.format(forumTopic.getCreateTime())), 
				forumTopic.getAttachFileID(),
				StringEscapeUtils.escapeSql(forumTopic.getAttachFileName()));
		// App.outPutErrorLog("reg user", sql);
		//System.out.println(sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoForumTopicDAO", exp.getMessage());
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
	 * 查询论坛主题
	 * @param lmt
	 * @return
	 */
	public List<CoForumTopic> query(String lmt) {
		List<CoForumTopic> ans = new ArrayList<CoForumTopic>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoForumTopic";
		if (lmt != null && lmt.isEmpty() == false)
			sql += " WHERE " + lmt;
		if (this.queryStartPos >= 0 && this.queryStartCount >= 0) {
			sql += String.format(" LIMIT %d,%d ", this.queryStartPos,
					this.queryStartCount);
		} else if (this.queryStartCount >= 0) {
			sql += String.format(" LIMIT %d ", this.queryStartCount);
		}
		sql += ";";
		//System.out.println(sql);
		PreparedStatement pStmt;
		try {
			pStmt = conn.prepareStatement(sql);
			ResultSet rst = pStmt.executeQuery();
			if (rst == null)
				return ans;
			while (rst.next()) {
				CoForumTopic forumTopic = new CoForumTopic();
				forumTopic.setId(rst.getInt("id"));
				forumTopic.setUserId(rst.getInt("userId"));
				forumTopic.setTopic(rst.getString("topic"));
				forumTopic.setContent(rst.getString("content"));
				forumTopic.setKeyword(rst.getString("keyword"));
				forumTopic.setLevel(rst.getInt("level"));
				forumTopic.setStatus(rst.getInt("status"));
				forumTopic.setReplyCount(rst.getInt("replyCount"));
				forumTopic.setAccessCount(rst.getInt("accessCount"));
				forumTopic.setLastReplyTime(rst.getDate("lastReplyTime"));
				forumTopic.setCreateTime(rst.getDate("createTime"));
				//System.out.println(rst.getDate("createTime"));
				forumTopic.setAttachFileID(rst.getInt("attachFileId"));
				forumTopic.setAttachFileName(rst.getString("attachFileName"));
				ans.add(forumTopic);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoForumTopicDAO", exp.getMessage());
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

	public boolean update(CoForumTopic cft){
		boolean ans=true;
		if(cft==null )
			return false;		
		String sql="";
		Connection conn=App.getSqlConnection();	
		if(conn==null)return false;
		sql="update CoForumTopic set ";
		//App.outPutErrorLog("reg user", sql);
		sql+=String.format("accessCount='%d'WHERE id=%d;",
				cft.getAccessCount(),
				cft.getId()
				);
		//App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try{
			stmt=conn.createStatement();
			int count=stmt.executeUpdate(sql);
			ans= count==1;
		}
		catch(Exception exp){
			App.outPutErrorLog("CoForumTopicDAO", exp.getMessage());
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
