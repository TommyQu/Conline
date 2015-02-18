//By 陈帆

package domain;


/**
天梯中题目列表
**/
public class CoGameProblem {
	private int id=-1;					/*题目ID，外键*/
	private double score=0;				/*题目分数*/
	private double posX=0;				/*在图像中的X坐标*/
	private double posY=0;				/*在图像中的Y坐标*/
	private String title="";			/*名称*/
	private String precondition="";		/*前置题目ID
	  格式
	@id@@id@*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public double getPosX() {
		return posX;
	}
	public void setPosX(double posX) {
		this.posX = posX;
	}
	public double getPosY() {
		return posY;
	}
	public void setPosY(double posY) {
		this.posY = posY;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrecondition() {
		return precondition;
	}
	public void setPrecondition(String precondition) {
		this.precondition = precondition;
	}
	
}
