package zevs;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionDB 
{
	protected final String driver = "com.mysql.jdbc.Driver";
	public final String login = "ZevsUser";
	public final String pass = "1357905ZEVSAC121";
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
		PreparedStatement statement = connection.prepareStatement("SELECT Login, Password, Type FROM zevsdb.users WHERE Login = '"+Login+"'");
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
	protected String getTextData(Connection connection, String Name) throws Exception
	{
	String resultString = "";
	String query = null;
	if(Name.equals("Âñå"))
	{
		query = "SELECT Text FROM zevsdb.textdata";
	}
	else
		query = "SELECT Name, Text FROM zevsdb.textdata WHERE Name = '"+Name+"'";
	PreparedStatement preparedStatement = connection.prepareStatement(query);
	ResultSet result = preparedStatement.executeQuery();
	while(result.next())
	{
		resultString += result.getString("Text");
		resultString += "\n-----------------------ÊÎÍÅÖ------------------------------\n";
	}
	return resultString;
	}
	
	protected ArrayList getAllTextName(Connection connection, int typeCategory) throws Exception
	{
		String query = "SELECT Name FROM zevsdb.textdata"; 
		ArrayList list = new ArrayList();
		switch(typeCategory)
		{
		case 1: query = "SELECT Name FROM zevsdb.category";
		break;
		case 2: query = "SELECT Name FROM zevsdb.jessdata";
		break;
		default: list.add("Âñå");
		}
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		while(result.next())
		{
			list.add(result.getString("Name"));
		}
		return list;
	}
	protected String getJessCode(Connection connection, String Name) throws Exception
	{
		String resultString = null;
		String query = "SELECT Name, JessCode FROM zevsdb.jessdata WHERE Name = '"+Name+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		while(result.next())
		{
			resultString = result.getString("JessCode");
		}
		return resultString;
	}
	protected ArrayList getUserColumnName(Connection connection) throws SQLException
	{ ArrayList Names = new ArrayList();
	String query = "SELECT * FROM zevsdb.users";
	PreparedStatement preparedStatement = connection.prepareStatement(query);
	ResultSet result = preparedStatement.executeQuery();
	for(int i = 1; i <= result.getMetaData().getColumnCount(); i++)
	{
		Names.add(result.getMetaData().getColumnName(i));
	}
	return  Names;
	}
	
	protected Object[] [] getUserData(Connection connection) throws SQLException
	{
		String query = "SELECT * FROM zevsdb.users";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		Object[] [] dataObjects = new Object [50] [50];
		while(result.next())
		{
			
		}
		
		return dataObjects;
	}

}
