package adventofcode;

public interface Day 
{	
	public String star1();
	public String star2();
	
	default void solve()
	{
		System.out.println("Star #1: " + star1());
		System.out.println("Star #2: " + star2());
	}
}
