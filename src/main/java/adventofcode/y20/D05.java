package adventofcode.y20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adventofcode.Day;
import adventofcode.InputReader;

public class D05 implements Day
{
	public Object star1() 
	{
		List<String> input = new InputReader(5,20).readStringList();
		int maxId = -1;
		
		for (String in : input)
		{
			int row = Integer.parseInt(in.substring(0,7).replace('F', '0').replace('B', '1'), 2);
			int col = Integer.parseInt(in.substring(7).replace('R', '1').replace('L', '0'), 2);
			
			maxId = Math.max(maxId, row*8+col);
		}
		return maxId;
	}

	public Object star2()
	{
		List<String> input = new InputReader(5,20).readStringList();
		List<Integer> ids = new ArrayList<>();
		int miss = -1;
		
		for (String in : input)
		{
			int row = Integer.parseInt(in.substring(0,7).replace('F', '0').replace('B', '1'), 2);
			int col = Integer.parseInt(in.substring(7).replace('R', '1').replace('L', '0'), 2);
			
			ids.add(row*8+col);
		}
		Collections.sort(ids);
		
		for (int i = ids.get(0); i <= ids.get(ids.size()-1); i++) 
		{
			if (!ids.contains(Integer.valueOf(i))) {
				miss = i;
				break;
			}
		}
		return miss;
	}
}
