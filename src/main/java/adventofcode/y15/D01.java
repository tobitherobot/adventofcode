package adventofcode.y15;

import adventofcode.Day;
import adventofcode.InputReader;

public class D01 implements Day
{
	public Object star1() 
	{
		String input = new InputReader(1,15).readLine();
		int floor = 0;
		
		for (int i = 0; i < input.length(); i++) 
		{
			if (input.charAt(i)=='(') floor++;
			else floor--;
		}
		return floor;
	}

	public Object star2() 
	{
		String input = new InputReader(1,15).readLine();
		int floor = 0;
		int idx = 0;
		
		while (0<=floor)
		{
			if (input.charAt(idx)=='(') floor++;
			else floor--;
			idx++;
		}
		return idx;
	}
}