package adventofcode.y19;

import java.util.List;

import adventofcode.Day;
import adventofcode.FileReader;

public class D01 implements Day
{
	public Object star1() 
	{
		List<Integer> modules = new FileReader(1,19).getIntList();
		long sum = 0L;
		
		for (int module : modules) {
			sum += Math.max(0, module/3-2);
		}
		return sum;
	}

	public Object star2() 
	{
		List<Integer> modules = new FileReader(1,19).getIntList();
		long sum = 0L;
		
		for (int module : modules) {
			sum += getFuel(module);
		}
		return sum;
	}
	
	private long getFuel(long m)
	{
		long fuel = Math.max(0, m/3-2);
		
		if (0<fuel) fuel += getFuel(fuel);
		
		return fuel;
	}
}