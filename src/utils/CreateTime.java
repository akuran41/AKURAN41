package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateTime
{
	public static String getCurrentTime()
	{
		// Tarih ve Zaman olustur
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());
		return simpleDateFormat.format(date);
	}
}