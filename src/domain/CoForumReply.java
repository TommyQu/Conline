//By 陈帆

package domain;

import java.util.Date;
/**
帖子回复
**/
public class CoForumReply {
	private int id=-1;					/*回复ID*/
	private int userId=-1;				/*回复用户的ID*/
	private int topicId=-1;				/*所属主题的ID*/
	private String content="";			/*回复内容*/
	Date createTime = new Date();		/*创建日期*/
	private int attachFileID =-1;		/*附件ID ，不做外键 
	 -1 表示没有附件
	 一个主题或者回复只能一个附件*/
	private String attachFileName="";	/*附件名*/
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date creatTime) {
		this.createTime = creatTime;
	}
	public int getAttachFileID() {
		return attachFileID;
	}
	public void setAttachFileID(int attachFileID) {
		this.attachFileID = attachFileID;
	}
	public String getAttachFileName() {
		return attachFileName;
	}
	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}
	
}
