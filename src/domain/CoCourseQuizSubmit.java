//by 杨楠
package domain;

import java.util.Date;
/**
试卷提交信息
**/
public class CoCourseQuizSubmit {
	private int id = -1;/*用户提交ID*/
	private int userId = -1;/*提交用户ID*/
	private int chapterId = -1;/*章节ID*/
	private int examId = -1;/*试卷ID*/
	private Date submitTime = new Date();/*提交时间*/
	private String submitFile = "";/*
	   提交的答案所保存的文件
	   文件格式为XML
	   相对应于网站根路径
	 */
	private int status = 0;/*是否通过考试*/
	private String judgeStatus = "";/*
	   评测状态
	   pending
	   compiling
	   juding
	   running
	   judged
 */
	private double score = 0;/*得分*/
	private String judgeResult = "";/*评判结果*/
	private String extendMessage = "";/*附加评测结果文件*/
	private String language="";
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
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getJudgeStatus() {
		return judgeStatus;
	}
	public void setJudgeStatus(String judgeStatus) {
		this.judgeStatus = judgeStatus;
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
}
