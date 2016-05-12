package utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Generator
{
	public String getMd5Password(String str)
	{
		return getMd5Hash(getMd5Hash(str));
	}

	private String getMd5Hash(String str)
	{
		/*
		 * StringBuffer buffer = new StringBuffer(); try { MessageDigest
		 * messageDigest = MessageDigest.getInstance("MD5"); byte[] hash =
		 * messageDigest.digest(); for (int i = 0; i < hash.length; i++) if
		 * ((0xff & hash[i]) < 0x10) buffer.append("0" +
		 * Integer.toHexString((0xFF & hash[i]))); else
		 * buffer.append(Integer.toHexString(0xFF & hash[i])); } catch
		 * (NoSuchAlgorithmException e) { e.printStackTrace(); } return
		 * buffer.toString();
		 */
		
		MessageDigest m = null;
		BigInteger i = null;
		
		try
		{
			m = MessageDigest.getInstance("MD5");
			byte[] data = str.getBytes("UTF-8");
			m.update(data, 0, data.length);
			i = new BigInteger(1, m.digest());
		}
		catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		return String.format("%1$032X", i);
	}
}