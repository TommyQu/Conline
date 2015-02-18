//by 杨楠
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import domain.CoCourseAssignment;
/**
课程作业
**/
public class CoCourseAssignmentDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;

	/*
	 * 删除课程作业
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
		sql = "delete from CoCourseAssignment WHERE " + lmt;
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseAssignmentDAO", exp.getMessage());
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
	 * 插入课程作业
	 * 
	 * @param courseAssignment
	 * @return
	 */
	public CoCourseAssignment insert(CoCourseAssignment a) {
		CoCourseAssignment ans = a;
		if (a == null)
			return null;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "insert into CoCourseAssignment(pid,chapterId,title,averageComment,comentCount) VALUES";
		sql += String.format("('%d','%d','%s','%f','%d');", a.getPid(),
				a.getChapterId(), StringEscapeUtils.escapeSql(a.getTitle()),
				a.getAverageComment(), a.getComentCount());
		//System.out.println(sql);
		// App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			if(count != 1)return null;
			
//			ResultSet rs = stmt.getGeneratedKeys();  
//			int id=-1;//保存生成的ID  
//			if (rs != null&&rs.next()) {  
//			    id=rs.getInt("id"); 
//			}  
//			ans.setid(id);
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseAssignmentDAO", exp.getMessage());
			return null;
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
	 * 查询课程作业
	 * 
	 * @param lmt
	 * @return
	 */
	public List<CoCourseAssignment> query(String lmt) {
		List<CoCourseAssignment> ans = new ArrayList<CoCourseAssignment>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoCourseAssignment";
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
				CoCourseAssignment a = new CoCourseAssignment();
				a.setPid(rst.getInt("pid"));
				a.setChapterId(rst.getInt("chapterId"));
				a.setTitle(rst.getString("title"));
				a.setAverageComment(rst.getDouble("averageComment"));
				a.setComentCount(rst.getInt("comentCount"));
				ans.add(a);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoCourseAssignmentDAO", exp.getMessage());
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
