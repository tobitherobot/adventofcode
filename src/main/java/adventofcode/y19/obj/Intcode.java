package adventofcode.y19.obj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Intcode 
{
	private List<Integer> original;
	private List<Integer> code;
	
	private int ptr;
	
	private Intcode next;
	private Queue<Integer> input;
	private int lastOutput;
	
	private boolean isPaused;
			
	public Intcode(List<Integer> c)
	{
		original = c;
		code = new ArrayList<>(original);
		
		ptr = 0;
		
		next = null;
		input = new LinkedList<>();
		lastOutput = -1;
		
		isPaused = false;
	}
	
	/**
	 * do a single instruction
	 */
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
					if (!input.isEmpty()) {
						code.set(code.get(ptr+1), input.poll());
						isPaused = false;
						ptr += 2;
					}
					else isPaused = true;
					break;
				case 4: // out: output param1
					int o = getValue(ptr+1, modes[1]);
					if (next!=null) {
						next.addInput(o);
					}
					lastOutput = o;
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
						code.set(code.get(ptr+3), 1);
					}
					else code.set(code.get(ptr+3), 0);
					ptr += 4;
					break;
				case 8: // eq: if param1 equals param2, it stores 1 in param3 (otherwise 0)
					if (getValue(ptr+1, modes[1]) == getValue(ptr+2, modes[2])) {
						code.set(code.get(ptr+3), 1);
					}
					else code.set(code.get(ptr+3), 0);
					ptr += 4;
					break;
				case 99: // end: end program
					break;
				default:
					throw new NullPointerException("Opcode "+modes[0]+" not found!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * run the entire intcode program
	 */
	public void run() 
	{	
		if (!isFinished())
		{
			try
			{
				do {
					step();
				}
				while (!isFinished() && !isPaused);
				
				if (next!=null && !next.isFinished()) next.run();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * run the entire intcode program with current code displayed
	 */
	public void debug()
	{		
		if (!isFinished())
		{
			try
			{
				System.out.println(code);
				
				do
				{
					step();
					System.out.println(code);
				}
				while (!isFinished() && !isPaused);
				
				if (next!=null && !next.isFinished()) next.debug();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * convert the parameter with its mode to its value
	 * @param value initial value
	 * @param mode parameter mode
	 * @return converted value
	 */
	private int getValue(int value, int mode)
	{
		if (mode==0) return code.get(code.get(value));
		else return code.get(value);
	}
	
	/**
	 * manually change an element in the code
	 * @param index index
	 * @param element value
	 */
	public void set(int index, int element)
	{
		code.set(index, element);
	}
	
	/**
	 * manually get an element in the code
	 * @param index index
	 * @return current value at index
	 */
	public int get(int index)
	{
		return code.get(index);
	}
	
	/**
	 * connect another intcode machine to output values to
	 * @param n intcode machine
	 */
	public void addNext(Intcode n)
	{
		next = n;
	}
	
	/**
	 * returns the connected intcode machine
	 * @return next intcode machine, null if none added
	 */
	public Intcode getNext()
	{
		return next;
	}
	
	/**
	 * add input for multiple connected intcode machines
	 * @param p string of single digit inputs, one digit per machine
	 */
	public void setPhase(String p)
	{
		if (!p.isEmpty())
		{
			addInput(Character.getNumericValue(p.charAt(0)));
			
			if (next!=null) {
				next.setPhase(p.substring(1));
			}
		}
	}
	
	/**
	 * add an input
	 * @param n value
	 */
	public void addInput(int n)
	{
		input.add(n);
	}
	
	/**
	 * get latest output of that intcode machine
	 * @return last output
	 */
	public int getLastOutput()
	{
		return lastOutput;
	}
	
	/**
	 * reset the machine and connected machines to initial state
	 */
	public void reset()
	{
		code = new ArrayList<>(original);
		input.clear();
		
		lastOutput = -1;
		ptr = 0;
		
		isPaused = false;
		
		if (next!=null && !next.isReset()) next.reset();
	}
	
	/**
	 * returns if the code pointer is on 99
	 * @return is finished
	 */
	public boolean isFinished()
	{
		return code.get(ptr)==99;
	}
	
	/**
	 * returns if the intcode machine is in its initial state
	 * @return is reset
	 */
	public boolean isReset()
	{
		return code.equals(original) && !isFinished() && ptr==0;
	}
}