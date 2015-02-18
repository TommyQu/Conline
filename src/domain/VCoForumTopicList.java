package domain;

import java.util.Date;

public class VCoForumTopicList {
	private int id=-1;					/*主题ID*/
	private int userId=-1;				/*发表用户ID*/
	private String topic="";			/*论坛主题*/
	private String content="";			/*发表内容*/
	private String keyword="";			/*主题关键字*/
	private int level=10;				/*优先级*/
	private int status=0;				/*状态 正常=0*/
	private int replyCount=0;		/*回复数
	统一由触发器管理
 	创建的时候可以不设置成0 ，有默认值了*/
	private int accessCount=0;			/*访问数*/
	Date lastReplyTime = new Date();	/*最后回复时间
	由触发器统一管理，
	程序只负责创建的时候填写当前时间
	*/
	Date createTime = new Date();		/*创建时间 */	
	private int attachFileID =-1;		/*附件ID,不做外键
 	-1表示没有附件
	一个主题或者回复只能有一个附件*/
	private String attachFileName="";	/*附件名*/
	private String userName="";
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
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public int getAccessCount() {
		return accessCount;
	}
	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}
	public Date getLastReplyTime() {
		return lastReplyTime;
	}
	public void setLastReplyTime(Date lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
