package db_process;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utils.ErrorLog;
import files.ReadMyIniFile;

public class ConnectDatabase
{
	private ErrorLog	errorLog	= null;
	private Connection	connection	= null;
	private boolean		isConnected	= false;

	private String		data		= null;
	private String		user		= null;
	private String		pass		= "";

	public ConnectDatabase() throws SQLException
	{
		errorLog = new ErrorLog();
		
		String driverName = "org.gjt.mm.mysql.Driver";
		try
		{
			Class.forName(driverName);
		}
		catch (ClassNotFoundException e)
		{
			errorLog.generateLog(e);
		}

		// Get database params
		readIniFile();

		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", user, pass);

		if (connection != null)
			isConnected = true;
	}

	public ConnectDatabase(boolean createForConnection) throws SQLException
	{
		if(errorLog == null)
			errorLog = new ErrorLog();
		
		String driverName = "org.gjt.mm.mysql.Driver";
		try
		{
			Class.forName(driverName);
		}
		catch (ClassNotFoundException e)
		{
			errorLog.generateLog(e);
		}

		// Get database params
		readIniFile();

		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + data + "?useUnicode=true&characterEncoding=UTF-8", user, pass);

		if (connection != null)
			isConnected = true;
	}

	public Connection getMysqlConnection()
	{
		return connection;
	}

	/*
	 * public void closeConnection() { try { connection.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } }
	 */

	public boolean isConnected()
	{
		return isConnected;
	}

	private void readIniFile()
	{
		ReadMyIniFile readMyIniFile = null;
		
		try
		{
			readMyIniFile = new ReadMyIniFile();
		}
		catch (IOException e)
		{
			errorLog.generateLog(e);
		}
		
		data = readMyIniFile.getMyDatabaseName();
		user = readMyIniFile.getMyUsername();
		pass = readMyIniFile.getMyPassword();
	}
}