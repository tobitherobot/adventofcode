package adventofcode;

public interface Day 
{	
	public Object star1();
	public Object star2();
	
	default String solve()
	{
		return "Star #1: " + String.valueOf(star1()) + "\nStar #2: " + String.valueOf(star2());
	}
	
	default void print()
	{
		System.out.println("Star #1: " + String.valueOf(star1()));
		System.out.println("Star #2: " + String.valueOf(star2()));
	}
	
	default void time()
	{
		long time = System.nanoTime();
		solve();
		System.out.println(getClass().getSimpleName() + ": " + (System.nanoTime()-time)/1000000 + "ms");
	}
}