//By 陈帆

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import domain.VCoUserLog;

public class VCoUserLogDAO {
	public int queryStartPos = -1;
	public int queryStartCount = -1;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 查询论坛主题
	 * @param lmt
	 * @return
	 */
	public List<VCoUserLog> query(String lmt) {
		List<VCoUserLog> ans = new ArrayList<VCoUserLog>();
		String sql = "";
		Connection conn = App.getSqlConnection();
		if (conn == null)
			return null;
		sql = "select * from VCoUserLog";
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
				VCoUserLog vcul=new VCoUserLog();
				vcul.setId(rst.getInt("id"));
				vcul.setUserId(rst.getInt("userId"));
				vcul.setAuthority(rst.getInt("userAuthority"));
				Date createTime=df.parse(rst.getString("createTime"));
				vcul.setCreateTime(createTime);
				vcul.setLogContent(rst.getString("logContent"));
				vcul.setLogType(rst.getString("logType"));
				vcul.setName(rst.getString("userName"));
				ans.add(vcul);
			}
			rst.close();
			pStmt.close();
		} catch (Exception exp) {
			App.outPutErrorLog("VCoUserLogDAO", exp.getMessage());
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
