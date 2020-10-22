package adventofcode;

public interface Day 
{	
	public Object star1();
	public Object star2();
	
	default void solve()
	{
		System.out.println("Star #1: " + String.valueOf(star1()));
		System.out.println("Star #2: " + String.valueOf(star2()));
	}
}
