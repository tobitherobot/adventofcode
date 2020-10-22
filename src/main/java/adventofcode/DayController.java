package adventofcode;

public class DayController 
{
	public void run(int d, int y)
	{
		try
		{
			Class<?> cl = Class.forName("adventofcode.y"+y+".D"+String.format("%02d", d));
			
			Day day = (Day) cl.getConstructor().newInstance();
			day.solve();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void runAll()
	{
		
	}
}
