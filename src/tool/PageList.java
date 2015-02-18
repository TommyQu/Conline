package tool;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tool.DbConnection;

public class PageList {

	private Integer currentPage = 0;

	private Integer rowsPerPage = 0;

	private Integer currentCount = 0;

	private Integer maxPage = 0;

	private Integer maxRowCount = 0;

	private Connection c = null;
	
	public PageList(String tableName, Integer currentPage, Integer rowsPerPage) throws Exception {
		DbConnection dc=new DbConnection();
		c=dc.getConnection();
		this.currentPage = currentPage;
		this.rowsPerPage = rowsPerPage;
		setCurrentCount();
		setMaxRowCount(tableName);
		setMaxPage();
	}

	public Integer getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount() {
		this.currentCount = (currentPage - 1) * rowsPerPage;
	}

	public Integer getMaxPage() {
		return maxPage;
	}

	public void setMaxPage() {
		if (maxRowCount % rowsPerPage == 0&&maxRowCount!=0) {
			maxPage = maxRowCount / rowsPerPage;
		} else {
			maxPage = maxRowCount / rowsPerPage + 1;
		}
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(Integer rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public Integer getMaxRowCount() {
		return maxRowCount;
	}

	public void setMaxRowCount(String tableName) 
	{
		String findAllSql = "select count(*) from " + tableName;
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(findAllSql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				maxRowCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
