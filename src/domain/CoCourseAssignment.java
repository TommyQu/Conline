//by 杨楠
package domain;
/**
课程作业
**/
public class CoCourseAssignment {
	private int pid = -1;/*题目ID*/
	private int chapterId = -1;/*课程ID*/
	private String title = "";/*题目显示的标题*/
	private double averageComment = 0;/*平均评估*/
	private int comentCount = 0;/*评估人数*/
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getChapterId() {
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getAverageComment() {
		return averageComment;
	}
	public void setAverageComment(double averageComment) {
		this.averageComment = averageComment;
	}
	public int getComentCount() {
		return comentCount;
	}
	public void setComentCount(int comentCount) {
		this.comentCount = comentCount;
	}
}
