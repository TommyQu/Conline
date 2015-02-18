//by 杨楠
package domain;

public class CoCourseQuiz {
	private int chapterId = -1;/*章节ID*/
	private int examId = -1;/*试卷ID*/
	private double passScore = 60;/*
	   通过考试的分数
	*/
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
	public double getPassScore() {
		return passScore;
	}
	public void setPassScore(double passScore) {
		this.passScore = passScore;
	}	
}
