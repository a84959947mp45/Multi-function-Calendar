
import java.util.*;
import java.lang.*;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;
public class DateX {
		    private String month;
		    private int day;
		    private int year; //a four digit number.

		    public DateX(int monthInt, int day, int year) {
			AnsiConsole.systemInstall();
		    	setDate(monthInt, day, year);
		    }
		    public DateX(String monthString, int day, int year) {
			AnsiConsole.systemInstall();
		    	setDate(monthString, day, year);
		    }


		    public void setDate(int monthInt, int day, int year)
		    {
		        if (dateOK(monthInt, day, year))
		        {
		            this.month = monthString(monthInt);
		            this.day = day;
		            this.year = year;
		        }
		        else
		        {
				System.out.println(ansi().fg(RED).a("錯誤的時間，請確認再輸入").reset());
		            return;
		        }
		    }

		    public void setDate(String monthString, int day, int year)
		    {
		        if (dateOK(monthString, day, year))
		        {
		            this.month = monthString;
		            this.day = day;
		            this.year = year;
		        }
		        else
		        {
				System.out.println(ansi().fg(RED).a("錯誤的時間，請確認再輸入").reset());
		            return;
		        }
		    }

		    public void setDate(int year)
		    {
		        setDate(1, 1, year);
		    }

		    public boolean dateOK(int monthInt, int dayInt, int yearInt)
		    {
		    	if ((monthInt == 2) && ((yearInt%400 == 0) || ((yearInt%4 == 0) && (yearInt%100 != 0))))
		    	{
		    		return ( (dayInt >=1) && (dayInt <= 29) &&
		    				(yearInt >= 1000) && (yearInt <= 9999)
		    				);
		    	}
		    	else if (monthInt == 1 || monthInt == 3 || monthInt == 5 || monthInt == 7 || monthInt == 8 ||
		    			 monthInt == 10 || monthInt == 12)
		    	{
			        return ( (monthInt >= 1) && (monthInt <= 12) &&
			                 (dayInt >= 1) && (dayInt <= 31) &&
			                 (yearInt >= 1000) && (yearInt <= 9999) );
			    }
		    	else if (monthInt == 4 || monthInt == 6 || monthInt == 9 || monthInt == 11)
		    	{
			        return ( (monthInt >= 1) && (monthInt <= 12) &&
			                 (dayInt >= 1) && (dayInt <= 30) &&
			                 (yearInt >= 1000) && (yearInt <= 9999) );
			    }

		    	else
		    	{
		            return ( (dayInt >=1) && (dayInt <= 28) &&
		    				(yearInt >= 1000) && (yearInt <= 9999)
		    				);
		    	}
		    }

		    public boolean dateOK(String monthString, int dayInt, int yearInt)
		    {
		    	if ((monthString.equals("February")) && ((yearInt%400 == 0) || ((yearInt%4 == 0) && (yearInt%100 != 0))))
		    	{
		    		return ( (dayInt >=1) && (dayInt <= 29) &&
		    				(yearInt >= 1000) && (yearInt <= 9999)
		    				);
		    	}
		    	else if (monthString.equals("January") || monthString.equals("March") || monthString.equals("May") ||
		    			monthString.equals("July") || monthString.equals("August") || monthString.equals("October") ||
		    			monthString.equals("December"))
		    	{
		            return ( monthOK(monthString) &&
		                 (dayInt >= 1) && (dayInt <= 31) &&
		                 (yearInt >= 1000) && (yearInt <= 9999) );
		    	}
		    	else if (monthString.equals("April") || monthString.equals("June") || monthString.equals("September") ||
		    			monthString.equals("November"))
		    	{
		            return ( monthOK(monthString) &&
		                 (dayInt >= 1) && (dayInt <= 30) &&
		                 (yearInt >= 1000) && (yearInt <= 9999) );
		    	}
		    	else
		    	{
		    		return ( (dayInt >=1) && (dayInt <= 28) &&
		    				(yearInt >= 1000) && (yearInt <= 9999)
		    				);
		    	}
		    }

		    public boolean monthOK(String month)
		    {
		        return (month.equals("January") || month.equals("February") ||
		                month.equals("March") || month.equals("April") ||
		                month.equals("May") || month.equals("June") ||
		                month.equals("July") || month.equals("August") ||
		                month.equals("September") || month.equals("October") ||
		                month.equals("November") || month.equals("December") );
		    }


		     public void readInput( )
		     {
		         boolean tryAgain = true;
		         Scanner keyboard = new Scanner(System.in);
		         while (tryAgain)
		         {
				System.out.println(ansi().fg(YELLOW).a("Enter month, day, and year.").reset());
				System.out.println(ansi().fg(RED).a("Do not use a comma.").reset());
		             String monthInput = keyboard.next( );
		             int dayInput = keyboard.nextInt( );
		             int yearInput = keyboard.nextInt( );
		             if (dateOK(monthInput, dayInput, yearInput) )
		             {
		                 setDate(monthInput, dayInput, yearInput);
		                 tryAgain = false;
		             }
		             else
				System.out.println(ansi().fg(RED).a("Illegal date. Reenter input.").reset());
		          }
		         keyboard.close();
		     }


		    public void writeOutput( )
		    {
			System.out.print(ansi().fg(YELLOW).a(""));
		        System.out.println(month + " " + day + " " + year);

			System.out.print(ansi().fg(YELLOW).a("").reset());
		    }

		    public void setMonth(int monthNumber)
		    {
		        if ((monthNumber <= 0) || (monthNumber > 12))
		        {

				System.out.println(ansi().fg(RED).a("Fatal Error").reset());
		            System.exit(0);
		        }
		        else
		            month = monthString(monthNumber);
		    }

		    public void setDay(int day)
		    {
		        if ((day <= 0) || (day > 31))
		        {
				System.out.println(ansi().fg(RED).a("Fatal Error").reset());
		            System.exit(0);
		        }
		        else
		            this.day = day;
		    }

		    public void setYear(int year)
		    {
		        if ( (year < 1000) || (year > 9999) )
		        {
				System.out.println(ansi().fg(RED).a("Fatal Error").reset());
		            System.exit(0);
		        }
		        else
		            this.year = year;
		    }

		    public boolean equals(DateX otherDate)
		    {
		        return ( (month.equalsIgnoreCase(otherDate.month))
		                  && (day == otherDate.day) && (year == otherDate.year) );
		    }
		    public int precedes(DateX otherDate)
		    {
		        if( (year < otherDate.year) ||
		           (year == otherDate.year && getMonth( ) < otherDate.getMonth( )) ||
		           (year == otherDate.year && month.equals(otherDate.month)
		                                         && day < otherDate.day) ) {
		        	 return -1;
		        }else {
		        	return 1;
		        }

		    }

		    public String toString( )
		    {
		        return (month + " " + day + " " + year);
		    }

		    public int getDay( )
		    {
		        return day;
		    }

		    public int getYear( )
		    {
		        return year;
		    }

		    public int getMonth( )
		    {
		        if (month.equalsIgnoreCase("January"))
		            return 1;
		        else if (month.equalsIgnoreCase("February"))
		            return 2;
		        else if (month.equalsIgnoreCase("March"))
		            return 3;
		        else if (month.equalsIgnoreCase("April"))
		            return 4;
		        else if (month.equalsIgnoreCase("May"))
		            return 5;
		        else if (month.equals("June"))
		            return 6;
		        else if (month.equalsIgnoreCase("July"))
		            return 7;
		        else if (month.equalsIgnoreCase("August"))
		            return 8;
		        else if (month.equalsIgnoreCase("September"))
		            return 9;
		        else if (month.equalsIgnoreCase("October"))
		            return 10;
		        else if (month.equalsIgnoreCase("November"))
		            return 11;
		        else if (month.equalsIgnoreCase("December"))
		            return 12;
		        else
		        {
				System.out.println(ansi().fg(RED).a("錯誤的月份，請確認後再輸入").reset());

		            return 0; //Needed to keep the compiler happy
		        }
		    }
		    
		    public static int getMonth(String m)
		    {
		        if (m.equalsIgnoreCase("January"))
		            return 1;
		        else if (m.equalsIgnoreCase("February"))
		            return 2;
		        else if (m.equalsIgnoreCase("March"))
		            return 3;
		        else if (m.equalsIgnoreCase("April"))
		            return 4;
		        else if (m.equalsIgnoreCase("May"))
		            return 5;
		        else if (m.equals("June"))
		            return 6;
		        else if (m.equalsIgnoreCase("July"))
		            return 7;
		        else if (m.equalsIgnoreCase("August"))
		            return 8;
		        else if (m.equalsIgnoreCase("September"))
		            return 9;
		        else if (m.equalsIgnoreCase("October"))
		            return 10;
		        else if (m.equalsIgnoreCase("November"))
		            return 11;
		        else if (m.equalsIgnoreCase("December"))
		            return 12;
		        else
		        {
		            return 0; //Needed to keep the compiler happy
		        }
		    }

		    private String monthString(int monthNumber)
		    {
		        switch (monthNumber)
		        {
		        case 1:
		            return "January";
		        case 2:
		            return "February";
		        case 3:
		            return "March";
		        case 4:
		            return "April";
		        case 5:
		            return "May";
		        case 6:
		            return "June";
		        case 7:
		            return "July";
		        case 8:
		            return "August";
		         case 9:
		            return "September";
		        case 10:
		            return "October";
		        case 11:
		            return "November";

		        case 12:
		            return "December";
		        default:
				System.out.println(ansi().fg(RED).a("錯誤的月份，請確認後再輸入").reset());
		            return "Error"; //to keep the compiler happy
		        }
		    }


	}
