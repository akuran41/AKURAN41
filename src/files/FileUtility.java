/*
 * @Author (
 * 				IBRAHIM YENER
 * 				2016/06/01
 * )
 *	@Javadoc : General file utility class
 *
 *  Params : Static class for file activities such as copy, delete, path.
 * */

package files;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtility
{
	public static final String getPath()
	{
		return "C:\\sera\\ini";
	}
	
	public static final String getFileName()
	{
		return "\\mysera.ini";
	}
	
	public static final String getImageFolder()
	{
		return "C:\\sera\\resimler";
	}
	
	public static void copyMyFile(File filename) throws IOException
	{
		File destLocation = new File(FileUtility.getImageFolder() + "\\" + filename.getName());
		FileUtils.copyFile(filename, destLocation);
	}
	
	public static void deleteMyFile(String originalFileName)
	{
		new File(FileUtility.getImageFolder() + "\\" + originalFileName).delete();
	}
}