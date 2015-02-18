//By 陈帆

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import domain.CoGameProblem;

public class CoGameProblemDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;

	/**
	 * 删除天梯题目
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
		sql = "delete from CoGameProblem  WHERE " + lmt;
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoGameProblem", exp.getMessage());
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
	 * 插入天梯题目
	 * 
	 * @param gameProblem
	 * @return
	 */
	public boolean insert(CoGameProblem gameProblem) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		boolean ans = true;
		if (gameProblem == null)
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;

		sql = "insert into CoGameProblem (id,score,posX,posY,title,precondition) VALUES";

		sql += String
				.format("('%d','%f','%f','%f','%s','%s');", 
						gameProblem.getId(),
						gameProblem.getScore(),
						gameProblem.getPosX(),
						gameProblem.getPosY(),
						StringEscapeUtils.escapeSql(gameProblem.getTitle()),
						StringEscapeUtils.escapeSql(df.format(gameProblem.getTitle()))
						);
		// App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoGameProblemDao", exp.getMessage());
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
	 * 查询天梯题目
	 * 
	 * @param lmt
	 * @return
	 */
	public List<CoGameProblem> query(String lmt) {
		List<CoGameProblem> ans = new ArrayList<CoGameProblem>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoGameProblem";
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
				CoGameProblem gameProblem = new CoGameProblem();
				gameProblem.setId(rst.getInt("id"));
				gameProblem.setScore(rst.getDouble("score"));
				gameProblem.setPosX(rst.getDouble("posX"));
				gameProblem.setPosY(rst.getDouble("posY"));
				gameProblem.setTitle(rst.getString("title"));
				gameProblem.setPrecondition(rst.getString("precondition"));
				ans.add(gameProblem);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoGameProblemDao", exp.getMessage());
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
