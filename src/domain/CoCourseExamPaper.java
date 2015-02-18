//by 杨楠
package domain;

import java.util.Date;

/**
试卷表信息
**/
public class CoCourseExamPaper {
	private int id = -1;/*试卷ID*/
	private String title = "";/*试卷标题*/
	private String description = "";/*描述*/
	private String configFile = "";/*
	   试卷配置信息文件
	   相对网页根目录的路径
	   文件为XML文件
	   包含题目列表等信息
	 */
	/**
	  由考卷系统统一修改这两个信息
	  其他的只要创建的时候设置成初始值
	*/
	private int attendCount = 0;/*参与人*/
	private double averageScore = 0;/*平均分*/
	private double averageUseTime = 0;/*平均用时，单位秒*/
	private Date createTime = new Date();/*创建试卷的日期*/
	private String createPerson = "";/*创建人*/
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
	public String getConfigFile() {
		return configFile;
	}
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}
	public int getAttendCount() {
		return attendCount;
	}
	public void setAttendCount(int attendCount) {
		this.attendCount = attendCount;
	}
	public double getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}
	public double getAverageUseTime() {
		return averageUseTime;
	}
	public void setAverageUseTime(double averageUseTime) {
		this.averageUseTime = averageUseTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	
}
