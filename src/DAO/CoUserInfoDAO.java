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

import domain.CoUserInfo;

public class CoUserInfoDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 删除用户信息
	 * 
	 * @param lmt
	 * @return
	 */
	public boolean delete(String lmt) {
		if (lmt == null || lmt.isEmpty())
			return false;
		String sql = " ";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;
		sql = "delete from CoUserInfo  WHERE " + lmt;
		//System.out.println(sql);
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoUserInfo", exp.getMessage());
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
	 * 插入用户信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public boolean insert(CoUserInfo userInfo) {
		boolean ans = true;
		if (userInfo == null)
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;

		sql = "insert into CoUserInfo (name,pwd,sex,status,authority,activeCode,email,qq,phone,address,zipCode,school,birthday,major,grade,createTime) VALUES";

		sql += String
				.format("('%s','%s','%s','%d','%d','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');", 
						StringEscapeUtils.escapeSql(userInfo.getName()),
						StringEscapeUtils.escapeSql(userInfo.getPwd()),
						StringEscapeUtils.escapeSql(userInfo.getSex()),
						userInfo.getStatus(),
						userInfo.getAuthority(),
						StringEscapeUtils.escapeSql(userInfo.getActiveCode()),
						StringEscapeUtils.escapeSql(userInfo.getEmail()),
						StringEscapeUtils.escapeSql(userInfo.getQq()),
						StringEscapeUtils.escapeSql(userInfo.getPhone()),
						StringEscapeUtils.escapeSql(userInfo.getAddress()),
						StringEscapeUtils.escapeSql(userInfo.getZipCode()),
						StringEscapeUtils.escapeSql(userInfo.getSchool()),
						StringEscapeUtils.escapeSql(df.format(userInfo.getBirthday())),
						StringEscapeUtils.escapeSql(userInfo.getMajor()),
						StringEscapeUtils.escapeSql(userInfo.getGrade()),
						StringEscapeUtils.escapeSql(df.format(userInfo.getCreatTime()))
								
								);
		// App.outPutErrorLog("reg user", sql);
		System.out.println(sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoUserInfoDao", exp.getMessage());
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
	 * 查询用户
	 * 
	 * @param lmt
	 * @return
	 */
	public List<CoUserInfo> query(String lmt) {
		List<CoUserInfo> ans = new ArrayList<CoUserInfo>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoUserInfo";
		if (lmt != null && lmt.isEmpty() == false)
			sql += " WHERE " + lmt;
		if (this.queryStartPos >= 0 && this.queryStartCount >= 0) {
			sql += String.format(" LIMIT %d,%d ", this.queryStartPos,
					this.queryStartCount);
		} else if (this.queryStartCount >= 0) {
			sql += String.format(" LIMIT %d ", this.queryStartCount);
		}
		sql += ";";
		//System.out.println(sql);
		PreparedStatement pStmt;
		try {
			pStmt = conn.prepareStatement(sql);
			ResultSet rst = pStmt.executeQuery();
			if (rst == null)
				return ans;
			while (rst.next()) {
				CoUserInfo userInfo = new CoUserInfo();
				Date createTime=df.parse(rst.getString("createTime"));
				userInfo.setId(rst.getInt("id"));
				userInfo.setName(rst.getString("name"));
				userInfo.setPwd(rst.getString("pwd"));
				userInfo.setSex(rst.getString("sex"));
				userInfo.setStatus(rst.getInt("status"));
				userInfo.setAuthority(rst.getInt("authority"));
				userInfo.setActiveCode(rst.getString("activeCode"));
				userInfo.setEmail(rst.getString("email"));
				userInfo.setQq(rst.getString("qq"));
				userInfo.setPhone(rst.getString("phone"));
				userInfo.setAddress(rst.getString("address"));
				userInfo.setZipCode(rst.getString("zipCode"));
				userInfo.setSchool(rst.getString("school"));
				userInfo.setBirthday(rst.getDate("birthday"));
				userInfo.setMajor(rst.getString("major"));
				userInfo.setGrade(rst.getString("grade"));
				userInfo.setCreatTime(createTime);
				//System.out.println(rst.getDate("createTime"));
				ans.add(userInfo);
				//System.out.println(userInfo.getName());
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoUserInfoDao", exp.getMessage());
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
	 * 更新用户信息 
	 * @param user
	 * @return
	 */
	public boolean updateUser(CoUserInfo user){
		boolean ans=true;
		if(user==null )
			return false;		
		String sql="";
		Connection conn=App.getSqlConnection();	
		if(conn==null)return false;
		sql="update CoUserInfo set ";
		//App.outPutErrorLog("reg user", sql);
		sql+=String.format("pwd='%s',email='%s',qq='%s',phone='%s',address='%s',zipCode='%s' WHERE id=%d;",
				StringEscapeUtils.escapeSql(user.getPwd()),
				StringEscapeUtils.escapeSql(user.getEmail()),
				StringEscapeUtils.escapeSql(user.getQq()),
				StringEscapeUtils.escapeSql(user.getPhone()),
				StringEscapeUtils.escapeSql(user.getAddress()),
				StringEscapeUtils.escapeSql(user.getZipCode()),
				user.getId()
				);
		//App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try{
			stmt=conn.createStatement();
			int count=stmt.executeUpdate(sql);
			ans= count==1;
		}
		catch(Exception exp){
			App.outPutErrorLog("CoUserInfoDAO", exp.getMessage());
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
