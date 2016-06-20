package utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5Generator
{
	private ErrorLog	errorLog	= null;

	public String getMd5Password(String str)
	{
		errorLog = new ErrorLog();
		
		return getMd5Hash(getMd5Hash(str));
	}

	private String getMd5Hash(String str)
	{
		MessageDigest m = null;
		BigInteger i = null;
		
		try
		{
			m = MessageDigest.getInstance("MD5");
			byte[] data = str.getBytes("UTF-8");
			m.update(data, 0, data.length);
			i = new BigInteger(1, m.digest());
		}
		catch (Exception e)
		{
			errorLog.generateLog(e);
		}
		
		return String.format("%1$032X", i);
	}
}