package db_process;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadDatabase
{
	private Connection	connection;

	public ReadDatabase(Connection connection)
	{
		this.connection = connection;
	}
	
	public ResultSet getData(String query) throws SQLException
	{
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		return rs;
	}
}