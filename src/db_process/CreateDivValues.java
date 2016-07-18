package db_process;

import java.sql.Connection;

public class CreateDivValues
{
	private WriteDatabase	write;

	private int				bolumID;

	public CreateDivValues(Connection connection, int bolumID)
	{
		write = new WriteDatabase(connection);

		this.bolumID = bolumID;

		// First We drop old values
		// DROP DIV values
		String dropCansuyu = "DELETE FROM bolum_cansuyu WHERE bolum_id = '" + bolumID + "'";
		String dropIsik = "DELETE FROM bolum_isik WHERE bolum_id = '" + bolumID + "'";
		String dropSu = "DELETE FROM bolum_su WHERE bolum_id = '" + bolumID + "'";
		String dropCO = "DELETE FROM bolum_co WHERE bolum_id = '" + bolumID + "'";
		String dropO = "DELETE FROM bolum_o WHERE bolum_id = '" + bolumID + "'";

		write.executeQuery(dropCansuyu);
		write.executeQuery(dropIsik);
		write.executeQuery(dropSu);
		write.executeQuery(dropCO);
		write.executeQuery(dropO);
	}

	public void createCansuyu(String[] val)
	{
		String query = "INSERT INTO bolum_cansuyu (bolum_id, basla_saat, bitis_saat, basla_dakika, bitis_dakika) VALUES ";
		query += "('" + bolumID + "', '" +val[0]+ "', '" +val[1]+ "', '" +val[2]+ "', '" +val[3]+ "'),";
		query += "('" + bolumID + "', '" +val[4]+ "', '" +val[5]+ "', '" +val[6]+ "', '" +val[7]+ "'),";
		query += "('" + bolumID + "', '" +val[8]+ "', '" +val[9]+ "', '" +val[10]+ "', '" +val[11]+ "'),";
		query += "('" + bolumID + "', '" +val[12]+ "', '" +val[13]+ "', '" +val[14]+ "', '" +val[15]+ "'),";
		query += "('" + bolumID + "', '" +val[16]+ "', '" +val[17]+ "', '" +val[18]+ "', '" +val[19]+ "');";
		write.executeQuery(query);
	}

	public void createIsik(String[] val)
	{
		String query = "INSERT INTO bolum_isik (bolum_id, basla_saat, bitis_saat, basla_dakika, bitis_dakika) VALUES ";
		query += "('" + bolumID + "', '" +val[0]+ "', '" +val[1]+ "', '" +val[2]+ "', '" +val[3]+ "'),";
		query += "('" + bolumID + "', '" +val[4]+ "', '" +val[5]+ "', '" +val[6]+ "', '" +val[7]+ "'),";
		query += "('" + bolumID + "', '" +val[8]+ "', '" +val[9]+ "', '" +val[10]+ "', '" +val[11]+ "'),";
		query += "('" + bolumID + "', '" +val[12]+ "', '" +val[13]+ "', '" +val[14]+ "', '" +val[15]+ "'),";
		query += "('" + bolumID + "', '" +val[16]+ "', '" +val[17]+ "', '" +val[18]+ "', '" +val[19]+ "');";
		write.executeQuery(query);
	}

	public void createSu(String[] val)
	{
		String query = "INSERT INTO bolum_su (bolum_id, basla_saat, bitis_saat, basla_dakika, bitis_dakika) VALUES ";
		query += "('" + bolumID + "', '" +val[0]+ "', '" +val[1]+ "', '" +val[2]+ "', '" +val[3]+ "'),";
		query += "('" + bolumID + "', '" +val[4]+ "', '" +val[5]+ "', '" +val[6]+ "', '" +val[7]+ "'),";
		query += "('" + bolumID + "', '" +val[8]+ "', '" +val[9]+ "', '" +val[10]+ "', '" +val[11]+ "'),";
		query += "('" + bolumID + "', '" +val[12]+ "', '" +val[13]+ "', '" +val[14]+ "', '" +val[15]+ "'),";
		query += "('" + bolumID + "', '" +val[16]+ "', '" +val[17]+ "', '" +val[18]+ "', '" +val[19]+ "');";
		write.executeQuery(query);
	}

	public void createCO(String[] val)
	{
		String query = "INSERT INTO bolum_co (bolum_id, basla_saat, bitis_saat, basla_dakika, bitis_dakika) VALUES ";
		query += "('" + bolumID + "', '" +val[0]+ "', '" +val[1]+ "', '" +val[2]+ "', '" +val[3]+ "'),";
		query += "('" + bolumID + "', '" +val[4]+ "', '" +val[5]+ "', '" +val[6]+ "', '" +val[7]+ "'),";
		query += "('" + bolumID + "', '" +val[8]+ "', '" +val[9]+ "', '" +val[10]+ "', '" +val[11]+ "'),";
		query += "('" + bolumID + "', '" +val[12]+ "', '" +val[13]+ "', '" +val[14]+ "', '" +val[15]+ "'),";
		query += "('" + bolumID + "', '" +val[16]+ "', '" +val[17]+ "', '" +val[18]+ "', '" +val[19]+ "');";
		write.executeQuery(query);
	}

	public void createO(String[] val)
	{
		String query = "INSERT INTO bolum_o (bolum_id, basla_saat, bitis_saat, basla_dakika, bitis_dakika) VALUES ";
		query += "('" + bolumID + "', '" +val[0]+ "', '" +val[1]+ "', '" +val[2]+ "', '" +val[3]+ "'),";
		query += "('" + bolumID + "', '" +val[4]+ "', '" +val[5]+ "', '" +val[6]+ "', '" +val[7]+ "'),";
		query += "('" + bolumID + "', '" +val[8]+ "', '" +val[9]+ "', '" +val[10]+ "', '" +val[11]+ "'),";
		query += "('" + bolumID + "', '" +val[12]+ "', '" +val[13]+ "', '" +val[14]+ "', '" +val[15]+ "'),";
		query += "('" + bolumID + "', '" +val[16]+ "', '" +val[17]+ "', '" +val[18]+ "', '" +val[19]+ "');";
		write.executeQuery(query);
	}
}