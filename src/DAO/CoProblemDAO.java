//By 陈帆 

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;

import domain.CoProblem;
import domain.CoUserInfo;

public class CoProblemDAO {

	public int queryStartPos = -1;
	public int queryStartCount = -1;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 删除题目
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
		sql = "delete from CoProblem  WHERE " + lmt;
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoProblem", exp.getMessage());
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
	 * 插入问题  
	 * 
	 * @param userLog
	 * @return
	 */
	public int insert(CoProblem problem) {
		if (problem == null)
			return 0;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return 0;

		sql = "insert into CoProblem (configFile,status,keyword,submitCount,averageScore,averageUseTime,providerName,createTime) VALUES";

		sql += String
				.format("('%s','%d','%s','%d','%f','%f','%s','%s');", 
						StringEscapeUtils.escapeSql(problem.getConfigFile()),
						problem.getStatus(),
						StringEscapeUtils.escapeSql(problem.getKeyword()),
						problem.getSubmitCount(),
						problem.getAverageScore(),
						problem.getAverageUseTime(),
						StringEscapeUtils.escapeSql(problem.getProviderName()),
						StringEscapeUtils.escapeSql(df.format(problem.getCreateTime())));
		// App.outPutErrorLog("reg user", sql);
		Statement stmt;
		int id=0;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs=stmt.getGeneratedKeys();
			if(rs.next())
			{
				id=rs.getInt(1);
				//System.out.println(id);
			}
		} catch (Exception exp) {
			App.outPutErrorLog("CoProblem", exp.getMessage());
			return 0;
		} finally {
			try {// 关闭连接
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
			}
		}
		return id;
	}

	/**
	 * 查询题目
	 * 
	 * @param lmt
	 * @return
	 */
	public List<CoProblem> query(String lmt) {
		List<CoProblem> ans = new ArrayList<CoProblem>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoProblem";
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
				CoProblem problem = new CoProblem();
				Date createTime=df.parse(rst.getString("createTime"));
				problem.setId(rst.getInt("id"));
				problem.setConfigFile(rst.getString("configFile"));
				problem.setStatus(rst.getInt("status"));
				problem.setKeyword(rst.getString("keyword"));
				problem.setAverageScore(rst.getDouble("averageScore"));
				problem.setAverageUseTime(rst.getDouble("averageUseTime"));
				problem.setProviderName(rst.getString("providerName"));
				problem.setCreateTime(createTime);
				ans.add(problem);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoProblemDao", exp.getMessage());
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
	
	public boolean update(CoProblem cp){
		boolean ans=true;
		if(cp==null )
			return false;		
		String sql="";
		Connection conn=App.getSqlConnection();	
		if(conn==null)return false;
		sql="update CoProblem set ";
		//App.outPutErrorLog("reg user", sql);
		sql+=String.format("configFile='%s',status='%d',keyword='%s',submitCount='%d',averageScore='%f',averageUseTime='%f',providerName='%s' WHERE id=%d;",
				StringEscapeUtils.escapeSql(cp.getConfigFile()),
				cp.getStatus(),
				StringEscapeUtils.escapeSql(cp.getKeyword()),
				cp.getSubmitCount(),
				cp.getAverageScore(),
				cp.getAverageUseTime(),
				StringEscapeUtils.escapeSql(cp.getProviderName()),
				cp.getId()
				);
		//App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try{
			stmt=conn.createStatement();
			int count=stmt.executeUpdate(sql);
			ans= count==1;
		}
		catch(Exception exp){
			App.outPutErrorLog("CoProblemDAO", exp.getMessage());
			return false;
		}
		finally{
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
