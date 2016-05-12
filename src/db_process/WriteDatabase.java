package db_process;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class WriteDatabase
{
	private Connection	connection	= null;
	
	public WriteDatabase(Connection connection)
	{
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
			e.printStackTrace();
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
					e.printStackTrace();
				}
		}
	}
}