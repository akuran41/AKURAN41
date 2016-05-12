/*
 * JAVADOC
 * Bu sinif Sistem uzerinde MySQL kontrolu yapiyor.
 * 
 * Returns:
 * Eger MySQL var ve calisiyorsa TRUE,
 * yoksa veya calismiyorsa FALSE 
 * donderir. 
 */

package net_process;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class PingToMySQL
{
	public static boolean checkMySql()
	{
		SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 3306);
		Socket socket = new Socket();
		
		try
		{
			socket.connect(socketAddress, 5000);
			
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				socket.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return false;
	}
}