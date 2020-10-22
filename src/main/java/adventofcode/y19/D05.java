package adventofcode.y19;

import java.util.List;

import adventofcode.Day;
import adventofcode.FileReader;
import adventofcode.y19.obj.Intcode;

public class D05 implements Day
{
	public Object star1() 
	{
		List<Integer> code = new FileReader(5,19).getIntListSplit(",");
		Intcode intcode = new Intcode(code);
		
		intcode.addInput(1);
		intcode.run();

		return intcode.getLastOutput();
	}

	public Object star2() 
	{
		List<Integer> code = new FileReader(5,19).getIntListSplit(",");
		Intcode intcode = new Intcode(code);
		
		intcode.addInput(5);
		intcode.debug();
		
		return intcode.getLastOutput();
	}
}