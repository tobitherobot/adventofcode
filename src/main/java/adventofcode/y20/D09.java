package adventofcode.y20;

import java.util.Collections;
import java.util.List;

import adventofcode.Day;
import adventofcode.InputReader;

public class D09 implements Day
{
	public Object star1() 
	{
		List<Integer> input = new InputReader(9,20).readIntList();
		int length = 25;
		long value = -1;
		
		for (int i = length; i < input.size(); i++)
		{
			List<Integer> sub = input.subList(i-length,i);
			boolean valid = false;
			value = input.get(i);
			
			for (int n : sub)
			{
				if (sub.contains(input.get(i)-n) && input.get(i)-n!=n)
				{
					valid = true;
					break;
				}
			}
			if (!valid)	break;
		}
		return value;
	}

	public Object star2() 
	{
		List<Integer> input = new InputReader(9,20).readIntList();
		long invalid = (long) star1();
		long value = -1;
		
		for (int i = 0; i < input.size()-1; i++)
		{
			int sum = input.get(i);
			int off = 0;
			
			while (i+off<input.size() && sum < invalid)
			{
				off++;
				sum += input.get(i+off);
			}
			
			if (sum==invalid) 
			{
				List<Integer> sub = input.subList(i, i+off+1);
				Collections.sort(sub);
				
				value = sub.get(0) + sub.get(sub.size()-1);
				break;
			}
		}
		return value;
	}
}
