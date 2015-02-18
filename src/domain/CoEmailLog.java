//by 杨楠
package domain;

import java.util.Date;
/**
邮件发送日志
**/
public class CoEmailLog {
	private int id=-1;/*邮件发送ID*/
	private int userId=-1;/*接受者用户ID
	 不设外键*/
	private String sendAddress="";/*接收地址*/
	private String fromAddress="";/*发送地址*/
	private Date sendTime = new Date();/*发送时间*/
	private String title="";/*主题*/
	private String content="";/*发送具体内容*/
	private int status= 0;/*
	   0 = 正常
	   其他表示具体错误信息
	 */
	private String type="";/*邮件类型
	  如系统发送激活邮件
	  回执邮件，提醒邮件等
	*/
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
	public String getSendAddress() {
		return sendAddress;
	}
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return content;
	}
	public void setContext(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
