package adventofcode;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import adventofcode.y19.D02;
import adventofcode.y19.D05;
import adventofcode.y19.D07;
import adventofcode.y19.D09;
import adventofcode.y19.D11;
import adventofcode.y19.obj.Intcode;

public class IntcodeTest 
{	
	@Test
	public void testDays()
	{
		Day day = null;
		
		day = new D02();
		Assertions.assertEquals(3101878, (long) day.star1());
		Assertions.assertEquals(8444, (int) day.star2());
		
		day = new D05();
		Assertions.assertEquals(5182797, (long) day.star1());
		Assertions.assertEquals(12077198, (long) day.star2());
		
		day = new D07();
		Assertions.assertEquals(46248, (long) day.star1());
		Assertions.assertEquals(54163586, (long) day.star2());
		
		day = new D09();
		Assertions.assertEquals(2752191671L, (long) day.star1());
		Assertions.assertEquals(87571, (long) day.star2());
		
		day = new D11();
		Assertions.assertEquals(2054, (int) day.star1());
		// too lazy for star2
	}
	
	@Test
	public void testExamples()
	{
		Intcode intcode = new Intcode(new ArrayList<>());
		InputReader ir = null;
		
		ir = new InputReader("1,9,10,3,2,3,11,0,99,30,40,50");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.run();
		Assertions.assertEquals(3500, intcode.get(0));
		Assertions.assertEquals(70, intcode.get(3));
		
		ir = new InputReader("1,0,0,0,99");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.run();
		Assertions.assertEquals(2, intcode.get(0));
		
		ir = new InputReader("2,3,0,3,99");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.run();
		Assertions.assertEquals(6, intcode.get(3));
		
		ir = new InputReader("2,4,4,5,99,0");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.run();
		Assertions.assertEquals(9801, intcode.get(5));
		
		ir = new InputReader("1,1,1,4,99,5,6,0,99");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.run();
		Assertions.assertEquals(30, intcode.get(0));
		
		ir = new InputReader("1002,4,3,4,33");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.run();
		Assertions.assertEquals(99, intcode.get(4));
		
		ir = new InputReader("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.run();
		Assertions.assertEquals(99, intcode.getLastOutput());
		
		ir = new InputReader("1102,34915192,34915192,7,4,7,99,0");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.run();
		Assertions.assertEquals(1219070632396864L, intcode.getLastOutput());
		
		ir = new InputReader("104,1125899906842624,99");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.run();
		Assertions.assertEquals(1125899906842624L, intcode.getLastOutput());
	}
	
	@Test
	public void testCustom()
	{
		Intcode intcode = new Intcode(new ArrayList<>());
		InputReader ir = null;
		
		// thx to u/Kache for these test cases
		ir = new InputReader("109,-1,4,1,99");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.run();
		Assertions.assertEquals(-1, intcode.getLastOutput());
		
		ir = new InputReader("109,-1,104,1,99");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.run();
		Assertions.assertEquals(1, intcode.getLastOutput());
		
		ir = new InputReader("109,-1,204,1,99");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.run();
		Assertions.assertEquals(109, intcode.getLastOutput());
		
		ir = new InputReader("109,1,9,2,204,-6,99");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.run();
		Assertions.assertEquals(204, intcode.getLastOutput());
		
		ir = new InputReader("109,1,109,9,204,-6,99");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.run();
		Assertions.assertEquals(204, intcode.getLastOutput());
		
		ir = new InputReader("109,1,209,-1,204,-106,99");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.run();
		Assertions.assertEquals(204, intcode.getLastOutput());
		
		ir = new InputReader("109,1,3,3,204,2,99");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.addInput(123456789L);
		intcode.run();
		Assertions.assertEquals(123456789L, intcode.getLastOutput());
		
		ir = new InputReader("109,1,203,2,204,2,99");
		intcode.setCode(ir.readLongLineSplit(","));
		intcode.addInput(123456789L);
		intcode.run();
		Assertions.assertEquals(123456789L, intcode.getLastOutput());
	}
}