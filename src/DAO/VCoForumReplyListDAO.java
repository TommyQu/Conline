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
import domain.CoForumReply;
import domain.VCoForumReplyList;

public class VCoForumReplyListDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 查询用户
	 * 
	 * @param lmt
	 * @return
	 */
	public List<VCoForumReplyList> query(String lmt) {
		List<VCoForumReplyList> ans = new ArrayList<VCoForumReplyList>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from VCoForumReplyList";
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
				VCoForumReplyList forumReply = new VCoForumReplyList();
				Date createTime=df.parse(rst.getString("createTime"));
				forumReply.setId(rst.getInt("id"));
				forumReply.setUserId(rst.getInt("id"));
				forumReply.setTopicId(rst.getInt("topicId"));
				forumReply.setUserName(rst.getString("userName"));
				forumReply.setContent(rst.getString("content"));
				forumReply.setCreateTime(createTime);
				forumReply.setAttachFileID(rst.getInt("attachFileId"));
				forumReply.setAttachFileName(rst.getString("attachFileName"));
				ans.add(forumReply);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("VCoForumReplyListDAO", exp.getMessage());
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
