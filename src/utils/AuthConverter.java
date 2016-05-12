package utils;

public class AuthConverter
{
	public static int convertReadableAuthToId(String str)
	{
		if (str == "")
			return 0;
		else if (str == "Sistem kaydedici")
			return 1;
		else if (str == "Administrator")
			return 2;
		else if (str == "Mali sorumlu")
			return 3;
		else if (str == "Teknik sorumlu")
			return 4;
		else if (str == "Yetiştirici")
			return 5;
		else if (str == "İzleyici")
			return 6;
		else
			return 7;
	}
	
	public static String convertIdToReadableAuth(int i)
	{
		switch(i)
		{
			case 1:
				return "Sistem kaydedici";
			case 2:
				return "Administrator";
			case 3:
				return "Mali sorumlu";
			case 4:
				return "Teknik sorumlu";
			case 5:
				return "Yetiştirici";
			default:
				return "İzleyici";
		}
	}
}