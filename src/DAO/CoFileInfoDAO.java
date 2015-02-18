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

import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;

import domain.CoFileInfo;


public class CoFileInfoDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 删除文件信息
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
		sql = "delete from CoFileInfo  WHERE " + lmt;
		boolean ans = true;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoFileInfo", exp.getMessage());
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
	 * 插入文件 信息
	 * 
	 * @param fileInfo
	 * @return
	 */
	public boolean insert(CoFileInfo fileInfo) {
		boolean ans = true;
		if (fileInfo == null)
			return false;
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return false;

		sql = "insert into CoFileInfo (userId,authority,targetPage,fileName,fileSize,filePath,downCount,createTime) VALUES";

		sql += String
				.format("('%d','%d','%s','%s','%d','%s','%d','%s');", 
						fileInfo.getUserId(),
						fileInfo.getAuthority(),
						StringEscapeUtils.escapeSql(fileInfo.getTargetPage()),
						StringEscapeUtils.escapeSql(fileInfo.getFileName()),
						fileInfo.getFileSize(),
						StringEscapeUtils.escapeSql(fileInfo.getFilePath()),
						fileInfo.getDownCount(),
						StringEscapeUtils.escapeSql(df.format(fileInfo.getCreateTime())));
		// App.outPutErrorLog("reg user", sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			ans = count == 1;
		} catch (Exception exp) {
			App.outPutErrorLog("CoFileInfoDao", exp.getMessage());
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
	public List<CoFileInfo> query(String lmt) {
		List<CoFileInfo> ans = new ArrayList<CoFileInfo>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from CoFileInfo";
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
				CoFileInfo fileInfo = new CoFileInfo();
				//String createTime=df.format(rst.getDate("createTime"));
				Date createTime=df.parse(rst.getString("createTime"));
				fileInfo.setId(rst.getInt("id"));
				fileInfo.setUserId(rst.getInt("userId"));
				fileInfo.setAuthority(rst.getInt("authority"));
				fileInfo.setFileName(rst.getString("fileName"));
				fileInfo.setFileSize(rst.getInt("fileSize"));
				fileInfo.setFilePath(rst.getString("filePath"));
				fileInfo.setDownCount(rst.getInt("downCount"));
				fileInfo.setCreateTime(createTime);
				ans.add(fileInfo);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("CoFileInfoDAO", exp.getMessage());
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
