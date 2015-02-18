//by 杨楠
package domain;
/**
学习资料
**/
public class CoCourseMaterial {
	private int fileId = -1;/*文件ID*/
	private int chapterId = -1;/*课程ID*/
	private String type = "";/*资源类型，如
	  代码
	  课件
	  FLASH
	  视频等
	*/
	private String description = "";/*资源简介*/
	private double averageComment = 0;/*平均评估*/
	private int comentCount = 0;/*评估人数*/
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public int getChapterId() {
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
