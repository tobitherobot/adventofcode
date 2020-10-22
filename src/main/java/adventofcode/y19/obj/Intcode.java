package adventofcode.y19.obj;

import java.util.ArrayList;
import java.util.List;

public class Intcode 
{
	private List<Integer> original;
	private List<Integer> code;
	
	private List<Integer> input;
	private List<Integer> output;
	
	private int ptr;
	
	private boolean isFinished;
	private boolean isBroken;
	
	public Intcode(List<Integer> o)
	{
		original = o;
		code = new ArrayList<>(original);
		
		input = new ArrayList<>();
		output = new ArrayList<>();
		
		ptr = 0;
	}
	
	public void step()
	{
		int opcode = code.get(ptr);
		int[] modes = new int[] {opcode%100, opcode/100%10, opcode/1000%10, opcode/10000};

		try
		{
			switch (opcode%100)
			{
				case 1: // add: param1+param2 at param3
					code.set(code.get(ptr+3), getValue(ptr+1, modes[1]) + getValue(ptr+2, modes[2]));
					ptr += 4;
					break;
				case 2: // mul: param1*param2 at param3
					code.set(code.get(ptr+3), getValue(ptr+1, modes[1]) * getValue(ptr+2, modes[2]));
					ptr += 4;
					break;
				case 3: // in: save input in param1
					code.set(code.get(ptr+1), input.get(0));
					ptr += 2;
					break;
				case 4: // out: output param1
					output.add(getValue(ptr+1, modes[1]));
					ptr += 2;
					break;
				case 5: // jit: if param1 is not 0, set ptr to param2
					if (getValue(ptr+1, modes[1])!=0) {
						ptr = getValue(ptr+2, modes[2]);
					}
					else ptr += 3;
					break;
				case 6: // jif: if param1 is 0, set ptr to param2
					if (getValue(ptr+1, modes[1])==0) {
						ptr = getValue(ptr+2, modes[2]);
					}
					else ptr += 3;
					break;
				case 7: // lt: if param1 less than param2, it stores 1 in param3 (otherwise 0)
					if (getValue(ptr+1, modes[1]) < getValue(ptr+2, modes[2])) {
						code.set(getValue(ptr+3, modes[3]), 1);
					}
					else code.set(getValue(ptr+3, modes[3]), 0);
					ptr += 3;
					break;
				case 8: // eq: if param1 equals param2, it stores 1 in param3 (otherwise 0)
					if (getValue(ptr+1, modes[1]) == getValue(ptr+2, modes[2])) {
						code.set(getValue(ptr+3, modes[3]), 1);
					}
					else code.set(getValue(ptr+3, modes[3]), 0);
					ptr += 3;
					break;
				case 99: // end: end program
					isFinished = true;
					break;
				default:
					throw new NullPointerException("Opcode "+modes[0]+" not found!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			isBroken = true;
		}
	}
	
	public void run() 
	{
		while (!isFinished() && !isBroken) {
			step();
		}
	}
	
	public void debug()
	{		
		while (!isFinished() && !isBroken) 
		{
			System.out.println(code);
			step();
		}
		System.out.println("Finished!");
	}
	
	public void reset()
	{
		code = new ArrayList<>(original);
		ptr = 0;
	}
	
	public void set(int index, int element)
	{
		code.set(index, element);
	}
	
	public int get(int index)
	{
		return code.get(index);
	}
	
	private int getValue(int value, int mode)
	{
		if (mode==0) return code.get(code.get(value));
		else return code.get(value);
	}
	
	public void addInput(int n)
	{
		input.add(n);
	}
	
	public List<Integer> getOutput()
	{
		return output;
	}
	
	public int getLastOutput()
	{
		return output.get(output.size()-1);
	}
	
	public int getFirst()
	{
		return code.get(0);
	}
	
	public boolean isFinished()
	{
		return isFinished;
	}
	
	public boolean isBroken()
	{
		return isBroken;
	}
}