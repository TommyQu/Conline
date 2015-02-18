package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import tool.DbConnection;
public class App {
	static Connection getSqlConnection(){
		try
		{
		  DbConnection db=new DbConnection();
		  return db.getConnection();
		}
		catch(Exception e)
		{
			return null;
		}
		
	}
	static void outPutErrorLog(String str1,String str2){
		System.out.println(str1+str2);
	}
	
	
	
}
