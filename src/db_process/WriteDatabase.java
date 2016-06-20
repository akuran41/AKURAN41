package db_process;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ErrorLog;

public class WriteDatabase
{
	private ErrorLog	errorLog	= null;
	private Connection	connection	= null;
	
	public WriteDatabase(Connection connection)
	{
		errorLog = new ErrorLog();
		
		this.connection = connection;
	}
	
	public void executeQuery(String query)
	{
		Statement queryStatement = null;
		try
		{
			queryStatement = connection.createStatement();
			queryStatement.executeUpdate(query);
		}
		catch (SQLException e)
		{
			errorLog.generateLog(e);
		}
		finally
		{
			if(queryStatement != null)
				try
				{
					queryStatement.close();
				}
				catch (SQLException e)
				{
					errorLog.generateLog(e);
				}
		}
	}
}