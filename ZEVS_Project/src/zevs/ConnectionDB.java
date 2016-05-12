package zevs;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDB 
{
	protected final String driver = "com.mysql.jdbc.Driver";
	protected final String login = "ZevsUser";
	protected final String pass = "1357905ZEVSAC121";
	protected final String URL = "jdbc:mysql://localhost:3306/zevsdb"; 
	
	public void InsertData(Connection connection, String Login, String Password, String Name, String Surname, String Patronymic, String Type)
	{
		try {
			PreparedStatement insert = connection.prepareStatement("INSERT INTO `zevsdb`.`users` (`Login`, `Password`, `Name`, `Surname`, `Patronymic`, `Type`) VALUES ('"+Login+"', '"+Password+"', '"+Name+"', '"+Surname+"', '"+Patronymic+"', '"+Type+"')");
			insert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Insert Completed.");
		}
	}
	
	public String checkLoginPass(Connection connection, String Login, boolean pass, boolean type) throws Exception
	{
		String ResultReturn = null;
		Connection con = connection;
		PreparedStatement statement = con.prepareStatement("SELECT Login, Password, Type FROM zevsdb.users WHERE Login = '"+Login+"'");
		ResultSet result = statement.executeQuery();
		while(result.next())
		{
			if(pass == true && type == true)
			ResultReturn = result.getString("Login") + result.getString("Password") + result.getString("Type");
			else
			if(pass == true && type == false)
			ResultReturn = result.getString("Login") + result.getString("Password");
			else
			if(pass == false && type == true)
			ResultReturn = result.getString("Login") + result.getString("Type");
			
			if(pass == false && type == false)
			ResultReturn = result.getString("Login");
		}
		return ResultReturn;
	}
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
