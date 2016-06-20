package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ErrorLog
{
	private static String	errLogPath	= "C:\\sera\\errLog";

	private FileWriter		fw;
	private BufferedWriter	bw;
	private PrintWriter		out;

	private String			year;
	private String			month;
	private String			day;
	private String			hour;

	public ErrorLog()
	{
		// Setup for date format
		SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy", Locale.getDefault());
		SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MM", Locale.getDefault());
		SimpleDateFormat dateFormatDay = new SimpleDateFormat("dd", Locale.getDefault());
		SimpleDateFormat dateFormatHour = new SimpleDateFormat("H:m:s", Locale.getDefault());

		Date date = new Date();

		year = dateFormatYear.format(date);
		month = dateFormatMonth.format(date);
		day = dateFormatDay.format(date);
		hour = dateFormatHour.format(date);

		// Create wrapper Folder
		createFolder();
		// First create YEAR Folder
		createYearFolder();
		// Second create MONTH Folder
		createMonthFolder();
	}

	private void createFolder()
	{
		File file = new File(errLogPath);

		if (!file.exists())
			file.mkdirs();
	}

	private void createYearFolder()
	{
		File file = new File(errLogPath + "\\" + year);

		if (!file.exists())
			file.mkdirs();
	}

	private void createMonthFolder()
	{
		File file = new File(errLogPath + "\\" + year + "\\" + month);

		if (!file.exists())
			file.mkdirs();
	}

	private void createDayFile() throws IOException
	{
		File file = new File(errLogPath + "\\" + year + "\\" + month + "\\" + day + ".txt");

		if (!file.exists())
			file.createNewFile();
	}

	public void generateLog(Exception e)
	{
		StackTraceElement[] stack = e.getStackTrace();

		String log = "";

		for (StackTraceElement s : stack)
		{
			log += year + "/" + month + "/" + day + " " + hour + " --> " + s.toString() + "\n\t\t";
		}

		try
		{
			createDayFile();
			
			fw = new FileWriter(errLogPath + "\\" + year + "\\" + month + "\\" + day + ".txt", true);
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			out.println(log);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			try
			{
				if (out != null)
				{
					out.close();
				}
				else if (bw != null)
				{
					bw.close();
				}
				else if (fw != null)
				{
					fw.close();
				}
			}
			catch (IOException err)
			{
			}
		}
	}
}