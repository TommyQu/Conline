//by 杨楠
package domain;
/**
课程章节
**/
public class CoCourseChapter {
	private int id = -1;/*文件ID*/
	private String title="";/*章节名*/
	private String description = "";/*章节描述*/
	private int period = 0;/*学习周期
	  单位天
	*/
	private double averagePeriod = 0;/*学习平均周期
	 单位天
	*/
	private int studyCount = 0;/*学习人数*/
	private double difficulty = 0;/*难度,【0，,10】*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public double getAveragePeriod() {
		return averagePeriod;
	}
	public void setAveragePeriod(double averagePeriod) {
		this.averagePeriod = averagePeriod;
	}
	public int getStudyCount() {
		return studyCount;
	}
	public void setStudyCount(int studyCount) {
		this.studyCount = studyCount;
	}
	public double getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}
}
