//by 杨楠
package domain;

import java.util.Date;
/**
用户作业提交
**/
public class CoCourseAssignmentSubmit {
	private int id;/*用户提交ID*/
	private int userId;/*提交用户ID*/
	private int chapterId;/*章节ID*/
	private int pid;/*题目ID*/
	private Date submitTime = new Date();/*提交时间*/
	private String submitFile="";
	private String judgeStatus = "";/*
	   评测状态
	   pending
	   compiling
	   juding
	   running
	   judged
 */
	private int status = 0;/*
	   是否完成指定任务，1=是
	*/
	private double score = 0;/*得分*/
	private String judgeResult = "";/*评判结果*/
	private String extendMessage = "";/*附加评测结果文件*/
	private String language="c++";
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
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
	public int getChapterId() {
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public String getJudgeStatus() {
		return judgeStatus;
	}
	public void setJudgeStatus(String judgeStatus) {
		this.judgeStatus = judgeStatus;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getJudgeResult() {
		return judgeResult;
	}
	public void setJudgeResult(String judgeResult) {
		this.judgeResult = judgeResult;
	}
	public String getExtendMessage() {
		return extendMessage;
	}
	public void setExtendMessage(String extendMessage) {
		this.extendMessage = extendMessage;
	}
	public String getSubmitFile() {
		return submitFile;
	}
	public void setSubmitFile(String submitFile) {
		this.submitFile = submitFile;
	}
}
