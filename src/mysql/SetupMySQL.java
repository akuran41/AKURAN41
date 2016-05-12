package mysql;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SetupMySQL
{
	public SetupMySQL() throws IOException
	{
		// gets program.exe from inside the JAR file as an input stream
		InputStream is = getClass().getResource("mysql.msi").openStream();
		// sets the output stream to a system folder
		OutputStream os = new FileOutputStream("c:\\mysql.msi");

		// 2048 here is just my preference
		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1)
		{
			os.write(b, 0, length);
		}

		is.close();
		os.close();

		Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL c:\\mysql.msi");
	}
}