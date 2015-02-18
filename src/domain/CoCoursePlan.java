//by 杨楠
package domain;

import java.util.Date;
/**
用户学习计划
**/
public class CoCoursePlan {
	private int userId = -1;/*用户ID*/
	private int chapterId = -1;/*课程ID*/
	private Date startTime = new Date();/*开始学习时间*/
	private int period = 0;/*学习周期*/
	private int status = 0;/*用户是否按期完成该课程*/
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getChapterId() {
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
