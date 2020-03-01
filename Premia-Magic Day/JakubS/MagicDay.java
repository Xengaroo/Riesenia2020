import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class MagicDay {
	
	private static boolean isPalindrom(String s)
	{
		for (int i=0, n=s.length() ; i < n ; i++)
		{
			if (s.charAt(i) != s.charAt(n - i - 1))
			{
				return false;
			}
		}
		return true;
	}
	
	public static int numberOfDays(int century)
	{				
		int number = 0;
		
		//https://stackoverflow.com/questions/8166390/java-generate-all-dates-between-x-and-y		
		//https://stackoverflow.com/questions/5220061/format-date-from-14-aug-to-yyyymmdd
		//https://www.geeksforgeeks.org/localdate-format-method-in-java/		

		int startYear = (century - 1) * 100 + 1; //1, 101, 201, ...		
		int endYear = (century * 100);			 //100, 200, 300,...
		
		LocalDate start = LocalDate.of(startYear, 1, 1);
		LocalDate end = LocalDate.of(endYear, 12, 31);						
		
		DateTimeFormatter americanFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		DateTimeFormatter britFormat = DateTimeFormatter.ofPattern("ddMMyyyy");

		while ( start.isBefore(end) || start.isEqual(end) )
		{
			String britString = britFormat.format(start);		
			String americanString = americanFormat.format(start);			

				if (isPalindrom(americanString) && isPalindrom(britString))
				{
					number++;
				}
				
				start = start.plusDays(1);
		}		
		return number;
	}
}
