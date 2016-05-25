/*
 * Convert month to int variable
 * @PARAM : int month
 * @RETURN : int
 * @DEFAULT : 12
 */
package utils;

public class MonthConverter
{
	public static int convertMonth(int m)
	{
		switch (m)
		{
			case 0 :		//	In Java 0 means JANUARY
				return 1;
			case 1 :
				return 2;
			case 2 :
				return 3;
			case 3 :
				return 4;
			case 4 :
				return 5;
			case 5 :
				return 6;
			case 6 :
				return 7;
			case 7 :
				return 8;
			case 8 :
				return 9;
			case 9 :
				return 10;
			case 10 :
				return 11;
			default :
				return 12;
		}
	}
}