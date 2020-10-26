package adventofcode.y19;

import java.util.List;

import adventofcode.Day;
import adventofcode.FileReader;
import adventofcode.y19.obj.Intcode;

public class D09 implements Day
{
	public Object star1() 
	{
		List<Long> code = new FileReader(9,19).readLongListSplit(",");
		Intcode intcode = new Intcode(code);
		
		intcode.addInput(1);
		intcode.run();
		
		return intcode.getLastOutput();
	}

	public Object star2() 
	{
		List<Long> code = new FileReader(9,19).readLongListSplit(",");
		Intcode intcode = new Intcode(code);
		
		intcode.addInput(2);
		intcode.run();
		
		return intcode.getLastOutput();
	}
}