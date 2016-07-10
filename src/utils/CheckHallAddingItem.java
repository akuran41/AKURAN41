package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db_process.ConnectDatabase;
import db_process.ReadDatabase;

public class CheckHallAddingItem
{
	private ArrayList<Object>	firstHall	= new ArrayList<Object>();
	private String				plantName;

	private boolean				isPlantable	= true;
	private int					bitkiID;

	public CheckHallAddingItem(ArrayList<Object> firstHall, String plantName)
	{
		this.firstHall = firstHall;
		this.plantName = plantName;

		ErrorLog errorLog = new ErrorLog();
		
		try
		{
			getData();
		}
		catch (SQLException e)
		{
			errorLog.generateLog(e);
		}
	}

	private void getData() throws SQLException
	{
		ConnectDatabase connection = null;
		connection = new ConnectDatabase(true);

		ReadDatabase getPlants = new ReadDatabase(connection.getMysqlConnection());
		// Get Set values from DB
		ResultSet rs = getPlants
				.getData("SELECT bitki_isik_siddet, bitki_gunduz_isik_sure, bitki_gunduz_ortam_isi, bitki_gunduz_nem, bitki_gunduz_o2, bitki_gunduz_c2o, "
						+ "bitki_gunduz_cansuyu_isi, bitki_cansuyu_ph, bitki_gunduz_gida_a, bitki_gunduz_gida_b, bitki_gunduz_gida_c, _id " + "FROM bitki "
						+ "WHERE bitki_adi = '" + plantName + "'");
		while (rs.next())
		{
			if (!firstHall.get(0).equals(rs.getString(3)))
				isPlantable = false;
			if (!firstHall.get(1).equals(rs.getString(4)))
				isPlantable = false;
			if (!firstHall.get(2).equals(rs.getString(7)))
				isPlantable = false;
			if (!firstHall.get(3).equals(rs.getString(5)))
				isPlantable = false;
			if (!firstHall.get(4).equals(rs.getString(6)))
				isPlantable = false;
			if (!firstHall.get(5).equals(rs.getString(1)))
				isPlantable = false;
			if (!firstHall.get(6).equals(rs.getString(8)))
				isPlantable = false;
			if (!firstHall.get(7).equals(rs.getString(9)))
				isPlantable = false;
			if (!firstHall.get(8).equals(rs.getString(10)))
				isPlantable = false;
			if (!firstHall.get(9).equals(rs.getString(11)))
				isPlantable = false;
			if (!firstHall.get(10).equals(rs.getString(2)))
				isPlantable = false;
			
			bitkiID = rs.getInt(12);
		}
	}

	public boolean isPlantableHall()
	{
		return isPlantable;
	}
	
	public int getBitkiID()
	{
		return bitkiID;
	}
}