package adventofcode;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import adventofcode.y19.obj.Intcode;

public class IntcodeTest 
{
	private Intcode intcode = new Intcode(new ArrayList<>());
	
	private FileReader fr = new FileReader();
	
	@Test
	public void testDay02()
	{
		fr.setInput("1,9,10,3,2,3,11,0,99,30,40,50");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.run();
		Assertions.assertEquals(3500, intcode.get(0));
		Assertions.assertEquals(70, intcode.get(3));
		
		fr.setInput("1,0,0,0,99");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.run();
		Assertions.assertEquals(2, intcode.get(0));
		
		fr.setInput("2,3,0,3,99");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.run();
		Assertions.assertEquals(6, intcode.get(3));
		
		fr.setInput("2,4,4,5,99,0");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.run();
		Assertions.assertEquals(9801, intcode.get(5));
		
		fr.setInput("1,1,1,4,99,5,6,0,99");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.run();
		Assertions.assertEquals(30, intcode.get(0));
	}
	
	@Test
	public void testDay05()
	{
		fr.setInput("1002,4,3,4,33");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.run();
		Assertions.assertEquals(99, intcode.get(4));
	}
	
	@Test
	public void testDay09()
	{
		fr.setInput("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.run();
		Assertions.assertEquals(99, intcode.getLastOutput());
		
		fr.setInput("1102,34915192,34915192,7,4,7,99,0");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.run();
		Assertions.assertEquals(1219070632396864L, intcode.getLastOutput());
		
		fr.setInput("104,1125899906842624,99");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.run();
		Assertions.assertEquals(1125899906842624L, intcode.getLastOutput());
		
		// thx to u/Kache for these test cases
		fr.setInput("109,-1,4,1,99");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.run();
		Assertions.assertEquals(-1, intcode.getLastOutput());
		
		fr.setInput("109,-1,104,1,99");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.run();
		Assertions.assertEquals(1, intcode.getLastOutput());
		
		fr.setInput("109,-1,204,1,99");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.run();
		Assertions.assertEquals(109, intcode.getLastOutput());
		
		fr.setInput("109,1,9,2,204,-6,99");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.run();
		Assertions.assertEquals(204, intcode.getLastOutput());
		
		fr.setInput("109,1,109,9,204,-6,99");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.run();
		Assertions.assertEquals(204, intcode.getLastOutput());
		
		fr.setInput("109,1,209,-1,204,-106,99");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.run();
		Assertions.assertEquals(204, intcode.getLastOutput());
		
		fr.setInput("109,1,3,3,204,2,99");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.addInput(123456789L);
		intcode.run();
		Assertions.assertEquals(123456789L, intcode.getLastOutput());
		
		fr.setInput("109,1,203,2,204,2,99");
		intcode.setCode(fr.readLongListSplit(","));
		intcode.addInput(123456789L);
		intcode.debug();
		Assertions.assertEquals(123456789L, intcode.getLastOutput());
	}
}
