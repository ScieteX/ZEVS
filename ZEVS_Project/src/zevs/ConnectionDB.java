package zevs;

import java.sql.DriverManager;

import java.sql.Connection;

public class ConnectionDB 
{
	protected final String driver = "com.mysql.jdbc.Driver";
	protected final String login = "ZevsUser";
	protected final String pass = "1357905ZEVSAC121";
	protected final String URL = "jdbc:mysql://localhost:3306/zevsdb"; 
	
	public Connection getConnection(String login, String pass) throws Exception
	{
		try
		{
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(URL, login, pass);
		System.out.println("Connected");
		return connection;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}

}
