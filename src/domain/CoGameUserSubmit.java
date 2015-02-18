//By 陈帆

package domain;

import java.util.Date;

/**
用户提交
**/
public class CoGameUserSubmit {
	private int id = -1;/*用户提交ID*/
	private int userId = -1;/*提交用户ID*/
	private int pid = -1;/*题目ID*/
	private Date submitTime = new Date();
	private String submitFile = "";/*
	   提交答案所保存的文档
	*/
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
	private String extendJudgeFile = "";/*附加评测结果文件*/
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
	public String getSubmitFile() {
		return submitFile;
	}
	public void setSubmitFile(String submitFile) {
		this.submitFile = submitFile;
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
	public String getExtendJudgeFile() {
		return extendJudgeFile;
	}
	public void setExtendJudgeFile(String extendJudgeFile) {
		this.extendJudgeFile = extendJudgeFile;
	}
	
}
