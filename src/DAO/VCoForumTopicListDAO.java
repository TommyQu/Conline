//By 陈帆

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

import domain.CoForumTopic;
import domain.VCoForumTopicList;

public class VCoForumTopicListDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 查询论坛主题
	 * @param lmt
	 * @return
	 */
	public List<VCoForumTopicList> query(String lmt) {
		List<VCoForumTopicList> ans = new ArrayList<VCoForumTopicList>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from VCoForumTopicList";
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
				VCoForumTopicList forumTopic = new VCoForumTopicList();
				Date createTime=df.parse(rst.getString("createTime"));
				Date lastReplyTime=df.parse(rst.getString("lastReplyTime"));
				forumTopic.setId(rst.getInt("id"));
				forumTopic.setUserId(rst.getInt("userId"));
				forumTopic.setTopic(rst.getString("topic"));
				forumTopic.setContent(rst.getString("content"));
				forumTopic.setKeyword(rst.getString("keyword"));
				forumTopic.setLevel(rst.getInt("level"));
				forumTopic.setStatus(rst.getInt("status"));
				forumTopic.setReplyCount(rst.getInt("replyCount"));
				forumTopic.setAccessCount(rst.getInt("accessCount"));
				forumTopic.setLastReplyTime(lastReplyTime);
				forumTopic.setCreateTime(createTime);
				forumTopic.setAttachFileID(rst.getInt("attachFileId"));
				forumTopic.setAttachFileName(rst.getString("attachFileName"));
				forumTopic.setUserName(rst.getString("userName"));
				ans.add(forumTopic);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("VCoForumTopicDAO", exp.getMessage());
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
