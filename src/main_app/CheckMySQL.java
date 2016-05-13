package main_app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;

import net_process.PingToMySQL;
import db_process.ConnectDatabase;
import files.FilePath;
import files.ReadMyIniFile;

public class CheckMySQL
{
	private ConnectDatabase	connectDatabase;
	private boolean			mySqlExists	= false;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args)
	{
		CheckMySQL checkMySql = new CheckMySQL();

		// Check ini file first
		checkMySql.checkIniFile();
		// Create image folder
		checkMySql.checkPictureFolder();
		// check connection
		checkMySql.sqlConnection();
	}

	public void sqlConnection()
	{
		// Ping to MySQL
		mySqlExists = PingToMySQL.checkMySql();

		if (mySqlExists)
		{
			try
			{
				// Veritabani baglantisi yap.
				connectDatabase = new ConnectDatabase();

				// INI dosyasindan veri tabani ismini al
				String myDatabaseName = new ReadMyIniFile().getMyDatabaseName();
				String databaseName = "";

				ResultSet resultSet = connectDatabase.getMysqlConnection().getMetaData().getCatalogs();

				// MySQL icindeki veritbani listesini al
				while (resultSet.next())
				{
					// Eger liste icerisinde SERA isminde veri tabani varsa
					// donguyu sonlandir
					if (resultSet.getString(1).equals(myDatabaseName))
					{
						databaseName = resultSet.getString(1);
						break;
					}
				}

				// ResultSet i kapat
				resultSet.close();

				if (databaseName.equals(myDatabaseName))
				{
					// Veri tabani bulundu ve baglanti yapildi
					KullaniciGiris kullaniciGiris = new KullaniciGiris();
					kullaniciGiris.setVisible(true);
				}
				else
				{
					// Baglanti yoksa uyari ekrani goster
					DatabaseErrorScreen databaseErrorScreen = new DatabaseErrorScreen();
					databaseErrorScreen.setVisible(true);
				}
			}
			catch (SQLException | IOException e)
			{
				// Baglanti yoksa uyari ekrani goster
				DatabaseErrorScreen databaseErrorScreen = new DatabaseErrorScreen();
				databaseErrorScreen.setVisible(true);
			}
		}
		else
		{
			// Kurulum ekranina git
			FailedToConnect failedToConnect = new FailedToConnect();
			failedToConnect.setVisible(true);
		}
	}

	private void checkIniFile()
	{
		File file = new File(FilePath.getPath());

		if (!Files.isDirectory(Paths.get(FilePath.getPath())))
		{
			file.mkdirs();

			File iniFile = new File(FilePath.getPath() + FilePath.getFileName());
			try
			{
				iniFile.createNewFile();

				OutputStream output = new FileOutputStream(FilePath.getPath() + FilePath.getFileName());
				Writer writer = new OutputStreamWriter(output);

				writer.write("[Database]\nveritabani=sera\n[User name]\nkullanici=null\n[Password]\nsifre=null");
				writer.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private void checkPictureFolder()
	{
		File file = new File(FilePath.getImageFolder());
		// Eger sistem uzerinde C:\sera\resimler klasoru yoksa yeni klasor
		// olustur.
		if (!file.exists())
			file.mkdirs();
	}
}