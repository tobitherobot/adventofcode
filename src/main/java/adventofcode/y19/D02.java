package adventofcode.y19;

import java.util.List;

import adventofcode.Day;
import adventofcode.FileReader;
import adventofcode.y19.obj.Intcode;

public class D02 implements Day
{
	public Object star1() 
	{
		List<Long> code = new FileReader(2, 19).readLongListSplit(",");
		Intcode intcode = new Intcode(code);
		
		intcode.set(1, 12);
		intcode.set(2, 2);
		intcode.debug();
		
		return intcode.get(0);
	}

	public Object star2() 
	{
		List<Long> code = new FileReader(2, 19).readLongListSplit(",");
		Intcode intcode = new Intcode(code);
		int res = -1;
		
		for (int n = 0; n<100; n++) {
			for (int v = 0; v<100; v++) {
				
				intcode.set(1, n);
				intcode.set(2, v);
				intcode.run();
				
				if (intcode.get(0)==19690720) 
				{
					res = 100*n+v;
					break;
				}
				else intcode.reset();
			}
			if (0<=res) break;
		}
		return res;
	}
}