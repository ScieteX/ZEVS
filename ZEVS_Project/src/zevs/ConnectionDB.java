package zevs;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ConnectionDB extends CheckData
{
	protected final String driver = "com.mysql.jdbc.Driver";
	public final String login = "ZevsUser";
	public final String pass = "1357905ZEVSAC121";
	protected final String URL = "jdbc:mysql://localhost:3306/zevsdb"; 
	
	public void UpdateDataUser(Connection connection,String idUser, String Login, String Password, String Name, String Surname, String Patronymic, String Type, String varID)
	{
		String query = null;
		try {
	query = "UPDATE `zevsdb`.`users` SET `idUser`='"+idUser+"', `Login`='"+Login+"', `Password`='"+Password+"', `Name`='"+Name+"', `Surname`='"+Surname+"', `Patronymic`='"+Patronymic+"', `Type`='"+Type+"' WHERE `idUser`='"+varID+"'";
	PreparedStatement Update = connection.prepareStatement(query);
	Update.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Update Completed.");
		}
	}
	public void InsertDataUser(Connection connection,String idUser, String Login, String Password, String Name, String Surname, String Patronymic, String Type, int var)
	{
		String query = null;
		try {
			switch(var)
			{
			case 0:
				query = "INSERT INTO `zevsdb`.`users` (`Login`, `Password`, `Name`, `Surname`, `Patronymic`, `Type`) VALUES ('"+Login+"', '"+Password+"', '"+Name+"', '"+Surname+"', '"+Patronymic+"', '"+Type+"')";
			break;
			case 1: query = "INSERT INTO `zevsdb`.`users` (`idUser`,`Login`, `Password`, `Name`, `Surname`, `Patronymic`, `Type`) VALUES ('"+idUser+"','"+Login+"', '"+Password+"', '"+Name+"', '"+Surname+"', '"+Patronymic+"', '"+Type+"')"; break;
			}
			
			PreparedStatement insert = connection.prepareStatement(query);
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
	protected TableModel getUserData(Connection connection) throws SQLException
	{ DefaultTableModel defaultTableModel = new DefaultTableModel(new String[] {"idUser","Login","Password","Name","Surname","Patronymic","Type"},0){
		private static final long serialVersionUID = 1L;
		 public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		
	};
	String query = "SELECT * FROM zevsdb.users";
	PreparedStatement preparedStatement = connection.prepareStatement(query);
	ResultSet result = preparedStatement.executeQuery();
	while(result.next())
	{
		defaultTableModel.addRow(new Object[]{result.getString("idUser"),result.getString("Login"),result.getString("Password"),
				                              result.getString("Name"),result.getString("Surname"),result.getString("Patronymic"),
				                              result.getString("Type")});
	}
	return  defaultTableModel;
	}
	
	protected boolean deleteUser(Connection connection, String idUser)
	{ 
		boolean answer = false;
		try {
			PreparedStatement insert = connection.prepareStatement("DELETE FROM `zevsdb`.`users` WHERE `idUser`='"+idUser+"'");
			insert.executeUpdate();
			answer = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Delete Completed.");
		}
				
		return answer;
		
		
	}
	protected boolean chekUserID(Connection connection, String idUser) throws SQLException
	{
		boolean result = false;
		String query = "SELECT `idUser` FROM zevsdb.users WHERE `idUser` = '"+idUser+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet Rset = preparedStatement.executeQuery();
		while(Rset.next())
		{
			if(Rset.getString("idUser") != null)
			{
				result = true;
			}
			else
				result = false;
		}
			
		return result;
	}
	protected TableModel getInformationTextData(Connection connection) throws SQLException
	{ DefaultTableModel defaultTableModel = new DefaultTableModel(new String[] {"idTextdata","Name","Text"},0){
		private static final long serialVersionUID = 1L;
		 public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		
	};
	String query = "SELECT * FROM zevsdb.textdata";
	PreparedStatement preparedStatement = connection.prepareStatement(query);
	ResultSet result = preparedStatement.executeQuery();
	while(result.next())
	{
		defaultTableModel.addRow(new Object[]{result.getString("idTextdata"),result.getString("Name"),result.getString("Text")});
	}
	return  defaultTableModel;
	}
	
	protected void InsertTextData(Connection connection, String idTextdata, String Name, String Text, int var)
	{
		String query = null;
		try {
			switch(var)
			{
			case 0:
				query = "INSERT INTO `zevsdb`.`textdata` (`Name`, `Text`) VALUES ('"+Name+"', '"+Text+"')";
			break;
			case 1: query = "INSERT INTO `zevsdb`.`textdata` (`idTextdata`,`Name`, `Text`) VALUES ('"+idTextdata+"','"+Name+"', '"+Text+"')"; break;
			}
			
			PreparedStatement insert = connection.prepareStatement(query);
			insert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Insert Completed.");
		}
		
	}
	protected boolean chekInformationDataID(Connection connection, String idTextdata) throws SQLException
	{
		boolean result = false;
		String query = "SELECT `idTextdata` FROM zevsdb.textdata WHERE `idTextdata` = '"+idTextdata+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet Rset = preparedStatement.executeQuery();
		while(Rset.next())
		{
			if(Rset.getString("idTextdata") != null)
			{
				result = true;
			}
			else
				result = false;
		}
			
		return result;
	}
	
    protected boolean deleteTextData(Connection connection, String idTextdata)
    {
    	boolean answer = false;
		try {
			PreparedStatement insert = connection.prepareStatement("DELETE FROM `zevsdb`.`textdata` WHERE `idTextdata`='"+idTextdata+"'");
			insert.executeUpdate();
			answer = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Delete Completed.");
		}
				
		return answer;
    }
}
