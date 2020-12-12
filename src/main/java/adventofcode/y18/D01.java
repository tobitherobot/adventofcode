package adventofcode.y18;

import java.util.ArrayList;
import java.util.List;

import adventofcode.Day;
import adventofcode.InputReader;

public class D01 implements Day
{
	public Object star1() 
	{
		return new InputReader(1,18)
				.readIntList()
				.stream()
				.mapToInt(Integer::intValue)
				.sum();
	}

	public Object star2() 
	{
		List<Integer> input = new InputReader(1,18).readIntList();
		List<Integer> freqs = new ArrayList<>();
		int freq = 0;
		int idx = 0;
		
		do
		{
			if (idx<input.size()) freqs.add(freq);
			freq += input.get(idx%input.size());
			idx++;;
			
		}
		while (!freqs.contains(freq));
		
		return freq;
	}
}
