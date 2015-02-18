//by 杨楠
package domain;


/**
评测机信息表
**/
public class CoJudgeClient {
	
	private String ip = "";/*评测机IP地址*/
	private int currentThread = 0;/*当前评测线程数*/
	private int maxJudgeThread = 50;/*最大并发评测线程*/
	private int level = 1;/*
  	分配任务优先级
	0的时候表示不分配
*/
	private int status = 0;/*表示是否上线*/
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getCurrentThread() {
		return currentThread;
	}
	public void setCurrentThread(int currentThread) {
		this.currentThread = currentThread;
	}
	public int getMaxJudgeThread() {
		return maxJudgeThread;
	}
	public void setMaxJudgeThread(int maxJudgeThread) {
		this.maxJudgeThread = maxJudgeThread;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
