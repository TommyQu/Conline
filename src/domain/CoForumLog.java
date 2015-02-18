//By 陈帆

package domain;

import java.util.Date;
/**
	论坛管理日志
**/
public class CoForumLog {
	private int id=-1;				/*日志ID*/
	private int userId=-1;			/*操作人ID*/
	Date creatTime = new Date();	/*日志时间*/
	private String content="";			/*日志说明*/
	
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
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
