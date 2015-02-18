//By 陈帆

package domain;
/**
用户游戏积分
**/
public class CoGameScore {
	private int id= -1;			/*用户ID,外键*/
	private double score =0;	/*总积分*/
	
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
	
}
