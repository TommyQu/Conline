//By 陈帆

package domain;

import java.util.Date;

/**
题目信息
**/
public class CoProblem {
	private int id=-1;					/*文件ID*/
	private String configFile="";		/*题目信息配置文件路径
	  采用XML文档配置*/
	private int status=0;				/*题目状态  0=正常
	1=待审批 
	2=禁用*/
	private String keyword="";			/*关键字或者设计的知识
	用'@'分开*/
	/**
	  统计信息
	  由评测服务器同意修改
	  WEB程序秩序提交默认值 
	**/
	private int submitCount=0;			/*提交次数*/
	private double averageScore =0;		/*平均分*/
	private double averageUseTime =0;	/*平均用时，单位 秒*/
	private String providerName ="";	/*提供者*/
	Date createTime = new Date();		/*创建时间*/
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConfigFile() {
		return configFile;
	}
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getSubmitCount() {
		return submitCount;
	}
	public void setSubmitCount(int submitCount) {
		this.submitCount = submitCount;
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
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
