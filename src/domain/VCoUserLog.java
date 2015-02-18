//By 陈帆

package domain;

import java.util.Date;
/**
用户操作日志表格
记录用户登入失败，关键操作等日志信息
扩展--->>视图vCoUserLog
**/
public class VCoUserLog {
	private int id=-1;				/*日志ID*/
	private int userId=-1;			/*用户ID*/
	private String logType="";		/*日志类型*/
	private String logContent="";	/*日志内容*/
    Date createTime=new Date();		/*创建日期*/
    private String name="";
    private int authority=10;
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
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority=authority;
	}
}
