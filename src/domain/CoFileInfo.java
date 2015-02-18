//By 陈帆

package domain;

import java.util.Date;

/**
文件信息表格
**/
public class CoFileInfo {
	private int id=-1;		 			/*文件ID*/
	private int userId=-1;				/*上传用户的ID*/
	private int authority=10;			/*文件的权限
	只有用户的权限大于等于文件的权限才能下载*/
	private String targetPage="";		/*目标页面
	如资源下载、课程PPT资源、课程视频资源*/
	private String fileName="";			/*文件名*/
	private long fileSize=10;			/*文件大小*/
	private String filePath="";			/*相对于网页更目录的路径*/
	private int downCount=0;			/*下载量*/
	Date createTime = new Date();		/*上传资源时间*/
	

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
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public String getTargetPage() {
		return targetPage;
	}
	public void setTargetPage(String targetPage) {
		this.targetPage = targetPage;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getDownCount() {
		return downCount;
	}
	public void setDownCount(int downCount) {
		this.downCount = downCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date creatTime) {
		this.createTime = creatTime;
	}
	
}
