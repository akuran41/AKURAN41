package files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadMyIniFile
{
	private String		data		= null;
	private String		user		= null;
	private String		pass		= "";

	public ReadMyIniFile() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(FileUtility.getPath() + FileUtility.getFileName()));

		String line;
		while ((line = br.readLine()) != null)
		{
			if (line.contains("["))
				continue;

			if (line.contains("veritabani"))
			{
				String[] str = line.split("=");
				data = str[1];
			}

			if (line.contains("kullanici"))
			{
				String[] str = line.split("=");
				user = str[1];
			}

			if (line.contains("sifre"))
			{
				String[] str = line.split("=");
				pass = str[1];
			}
		}

		br.close();
	}
	
	public String getMyDatabaseName()
	{
		return data;
	}
	
	public String getMyUsername()
	{
		return user;
	}
	
	public String getMyPassword()
	{
		return pass;
	}
}