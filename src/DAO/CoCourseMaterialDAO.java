//by 杨楠
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import domain.CoCourseMaterial;
/**
学习资料
**/
public class CoCourseMaterialDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;

	/*
	 * 删除学习资料
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
		sql = "delete from CoCourseMaterial WHERE " + lmt;
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseMaterialDAO", exp.getMessage());
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
	 * 插入学习资料
	 * 
	 * @param courseMaterial
	 * @return
	 */
	public boolean insert(CoCourseMaterial a) {
		boolean ans = true;
		if (a == null)
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;
		sql = "insert into CoCourseMaterial (fileId,chapterId,type,description,averageComment,comentCount) VALUES";
		sql += String.format("('%d','%d','%s','%s','%f','%d');",
				a.getFileId(),
				a.getChapterId(),
				StringEscapeUtils.escapeSql(a.getType()),
				StringEscapeUtils.escapeSql(a.getDescription()),
				a.getAverageComment(),
				a.getComentCount()
				);
		// App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseMaterialDAO", exp.getMessage());
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
	 * 查询学习资料
	 * 
	 * @param lmt
	 * @return
	 */
	public List<CoCourseMaterial> query(String lmt) {
		List<CoCourseMaterial> ans = new ArrayList<CoCourseMaterial>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoCourseMaterial";
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
				CoCourseMaterial a = new CoCourseMaterial();
				a.setFileId(rst.getInt("fileId"));
				a.setChapterId(rst.getInt("chapterId"));
				a.setType(rst.getString("type"));
				a.setDescription(rst.getString("description"));
				a.setAverageComment(rst.getDouble("averageComment"));
				a.setComentCount (rst.getInt("comentCount"));
				ans.add(a);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseMaterialDAO", exp.getMessage());
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
