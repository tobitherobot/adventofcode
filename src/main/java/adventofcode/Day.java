package adventofcode;

public interface Day 
{	
	public Object star1();
	public Object star2();
	
	default String solve()
	{
		return solveStar1() + "\n" + solveStar2();
	}
	
	default String solveStar1()
	{
		return "Star #1: " + String.valueOf(star1());
	}
	
	default String solveStar2()
	{
		return "Star #2: " + String.valueOf(star2());
	}
	
	default void print()
	{
		System.out.println(solveStar1());
		System.out.println(solveStar2());
	}
	
	default void time()
	{
		long tmp = System.nanoTime();
		solveStar1();
		double time1 = ((System.nanoTime()-tmp) / 1000) / 1000.0;
		
		tmp = System.nanoTime();
		solveStar2();
		double time2 = ((System.nanoTime()-tmp) / 1000) / 1000.0; 
				
		System.out.println(getClass().getSimpleName() + ": " + time1 + "ms\t" + time2 + "ms");
	}
}