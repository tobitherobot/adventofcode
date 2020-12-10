package adventofcode.y20;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventofcode.Day;
import adventofcode.InputReader;

public class D10 implements Day
{
	public Object star1() 
	{
		List<Integer> input = new InputReader(10,20).readIntList();
		int diff3 = 0;
		int diff1 = 0;
		
		Collections.sort(input);
		input.add(0, 0);
		input.add(input.get(input.size()-1)+3);
		
		for (int i = 0; i < input.size()-1; i++)
		{
			if (input.get(i+1)-input.get(i)==3) diff3++;
			else if (input.get(i+1)-input.get(i)==1) diff1++;
		}
		
		return diff3*diff1;
	}

	public Object star2() 
	{
		List<Integer> input = new InputReader(10,20).readIntList();
		Map<Integer, Long> counts = new HashMap<>();
		
		Collections.sort(input, Collections.reverseOrder());
		input.add(0);
		
		counts.put(input.get(0), 1L);
		
		for (int in : input)
		{
			if (!counts.containsKey(in)) counts.put(in, 0L);
			
			for (int j = 1; j <= 3; j++)
			{
				if (counts.containsKey(in+j)) {
					counts.put(in, counts.get(in)+counts.get(in+j));
				}
			}
		}
		return counts.get(0);
	}
}
