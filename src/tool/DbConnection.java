package tool;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbConnection {
	private Context ctx;
	private DataSource ds;
	private Connection conn;
	public DbConnection() throws NamingException, SQLException
	{
		ctx=new InitialContext();
		ds=(DataSource)ctx.lookup("java:comp/env/jdbc/conline");
		conn=ds.getConnection();
	}
	public Connection getConnection()
	{
		return this.conn;
	}
}
