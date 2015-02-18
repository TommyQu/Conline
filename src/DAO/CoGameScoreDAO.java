//By 陈帆

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import domain.CoGameScore;


public class CoGameScoreDAO {

	public int queryStartPos = -1;
	public int queryStartCount = -1;

	/**
	 * 删除游戏积分
	 * 
	 * @param lmt
	 * @return
	 */
	public boolean delete(String lmt) {
		if (lmt == null || lmt.isEmpty())
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;
		sql = "delete from CoGameScore  WHERE " + lmt;
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoGameScore", exp.getMessage());
			return false;
		} finally {
			try {// 关闭连接
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
			}
		}
		return ans;
	}

	/**
	 * 插入游戏积分
	 * 
	 * @param gameScore
	 * @return
	 */
	public boolean insert(CoGameScore gameScore) {
		boolean ans = true;
		if (gameScore == null)
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;

		sql = "insert into CoGameScore (userId,logType,logContent,creatTime) VALUES";

		sql += String
				.format("('%d','%f');",
						gameScore.getId(),
						gameScore.getScore()
						);
		// App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoGameScoreDAO", exp.getMessage());
			return false;
		} finally {
			try {// 关闭连接
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
			}
		}
		return ans;
	}

	/**
	 * 查询游戏积分
	 * 
	 * @param lmt
	 * @return
	 */
	public List<CoGameScore> query(String lmt) {
		List<CoGameScore> ans = new ArrayList<CoGameScore>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoGameScore";
		if (lmt != null && lmt.isEmpty() == false)
			sql += " WHERE " + lmt;
		if (this.queryStartPos >= 0 && this.queryStartCount >= 0) {
			sql += String.format(" LIMIT %d,%d ", this.queryStartPos,
					this.queryStartCount);
		} else if (this.queryStartCount >= 0) {
			sql += String.format(" LIMIT %d ", this.queryStartCount);
		}
		sql += ";";
		PreparedStatement pStmt;
		try {
			pStmt = conn.prepareStatement(sql);
			ResultSet rst = pStmt.executeQuery();
			if (rst == null)
				return ans;
			while (rst.next()) {
				CoGameScore gameScore = new CoGameScore();
				gameScore.setId(rst.getInt("id"));
				gameScore.setScore(rst.getDouble("score"));
				ans.add(gameScore);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoGameScoreDao", exp.getMessage());
		} finally {
			try {// 关闭连接
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
			}
		}
		return ans;
	}
}
