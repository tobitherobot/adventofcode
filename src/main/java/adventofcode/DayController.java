package adventofcode;

public class DayController 
{
	public void run(int d, int y)
	{
		Day day = getDay(d, y);
		
		if (day!=null) day.print();
	}
	
	public void runYear(int y)
	{
		System.out.println("Year " + y + ":");
		
		for (int i = 1; i <= 25; i++)
		{
			Day day = getDay(i, y);
			
			if (day!=null) day.time();
		}
	}
	
	private Day getDay(int d, int y)
	{
		Day day = null;
		
		try
		{
			Class<?> cl = Class.forName("adventofcode.y"+y+".D"+String.format("%02d", d));
			day = (Day) cl.getConstructor().newInstance();
		} 
		catch (Exception e) {}
		
		return day;
	}
}
