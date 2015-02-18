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
import domain.CoForumReply;

public class CoForumReplyDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;

	/**
	 * 删除帖子回复
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
		sql = "delete from CoForumReply WHERE " + lmt;
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoForumReply", exp.getMessage());
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
	 * 插入帖子回复
	 * 
	 * @param forumReply
	 * @return
	 */
	public boolean insert(CoForumReply forumReply) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean ans = true;
		if (forumReply == null)
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;

		sql = "insert into CoForumReply (userId,TopicId,content,createTime,attachFileId,attachFileName) VALUES";

		sql += String
				.format("('%d','%d','%s','%s','%d','%s');", 
				forumReply.getUserId(),
				forumReply.getTopicId(),
				StringEscapeUtils.escapeSql(forumReply.getContent()),
				StringEscapeUtils.escapeSql(df.format(forumReply.getCreateTime())),
				forumReply.getAttachFileID(),
				StringEscapeUtils.escapeSql(forumReply.getAttachFileName())
				);
				
		// App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoFormReplyDao", exp.getMessage());
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
	public List<CoForumReply> query(String lmt) {
		List<CoForumReply> ans = new ArrayList<CoForumReply>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoForumReply";
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
				CoForumReply forumReply = new CoForumReply();
				forumReply.setId(rst.getInt("id"));
				forumReply.setUserId(rst.getInt("id"));
				forumReply.setTopicId(rst.getInt("topicId"));
				forumReply.setContent(rst.getString("content"));
				forumReply.setCreateTime(rst.getDate("createTime"));
				forumReply.setAttachFileID(rst.getInt("attachFileId"));
				forumReply.setAttachFileName(rst.getString("attachFileName"));
				ans.add(forumReply);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoForumReplyDAO", exp.getMessage());
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
