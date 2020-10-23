package adventofcode.y19.obj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Intcode 
{
	private List<Long> original;
	private List<Long> code;
	
	private int ptr;
	private int relBase;
	
	private Intcode next;
	private Queue<Long> input;
	private long lastOutput;
	
	private Map<Long, Long> memory;
	
	private boolean isPaused;
			
	public Intcode(List<Long> c)
	{
		original = c;
		code = new ArrayList<>(original);
		
		ptr = 0;
		relBase = 0;
		
		next = null;
		input = new LinkedList<>();
		lastOutput = -1;
		
		memory = new HashMap<>();
		
		isPaused = false;
	}
	
	/**
	 * do a single instruction
	 */
	public void step()
	{
		long opcode = get(ptr);
		int[] modes = new int[] {
				Math.toIntExact(opcode%100), 
				Math.toIntExact(opcode/100%10),
				Math.toIntExact(opcode/1000%10),
				Math.toIntExact(opcode/10000)};

		try
		{
			switch (modes[0])
			{
				case 1: // add: param1+param2 at param3
					set(get(ptr+3), getValue(ptr+1, modes[1]) + getValue(ptr+2, modes[2]));
					ptr += 4;
					break;
				case 2: // mul: param1*param2 at param3
					set(get(ptr+3), getValue(ptr+1, modes[1]) * getValue(ptr+2, modes[2]));
					ptr += 4;
					break;
				case 3: // in: save input in param1
					if (!input.isEmpty()) {
						set(get(ptr+1), input.poll());
						isPaused = false;
						ptr += 2;
					}
					else isPaused = true;
					break;
				case 4: // out: output param1
					long o = getValue(ptr+1, modes[1]);
					if (next!=null) {
						next.addInput(o);
					}
					lastOutput = o;
					System.out.println(lastOutput);
					ptr += 2;
					break;
				case 5: // jit: if param1 is not 0, set ptr to param2
					if (getValue(ptr+1, modes[1])!=0) {
						ptr = Math.toIntExact(getValue(ptr+2, modes[2]));
					}
					else ptr += 3;
					break;
				case 6: // jif: if param1 is 0, set ptr to param2
					if (getValue(ptr+1, modes[1])==0) {
						ptr = Math.toIntExact(getValue(ptr+2, modes[2]));
					}
					else ptr += 3;
					break;
				case 7: // lt: if param1 less than param2, it stores 1 in param3 (otherwise 0)
					if (getValue(ptr+1, modes[1]) < getValue(ptr+2, modes[2])) {
						set(get(ptr+3), 1);
					}
					else set(get(ptr+3), 0);
					ptr += 4;
					break;
				case 8: // eq: if param1 equals param2, it stores 1 in param3 (otherwise 0)
					if (getValue(ptr+1, modes[1]) == getValue(ptr+2, modes[2])) {
						set(get(ptr+3), 1);
					}
					else set(get(ptr+3), 0);
					ptr += 4;
					break;
				case 9: // adj: adjust relative base by param1
					relBase += getValue(ptr+1, modes[1]);
					ptr += 2;
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
	 * convert the parameter with its mode to its value
	 * @param value initial value
	 * @param mode parameter mode
	 * @return converted value
	 */
	private long getValue(int value, int mode) // -1 2, rlBase: 1
	{
		switch (mode) 
		{
		case 0: // position mode
			return get(get(value));
		case 1: // immediate mode
			return get(value);
		case 2: // relative mode
			return get(relBase+get(value));
		default:
			return Long.MIN_VALUE;
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
				System.out.println(this);
				
				do
				{
					step();
					System.out.println(this);
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
	 * manually change an element in the code
	 * @param idx index
	 * @param elm value
	 */
	public void set(long idx, long elm)
	{
		if (idx<code.size()) code.set(Math.toIntExact(idx), elm);
		else memory.put(idx, elm);
	}
	
	/**
	 * manually get an element in the code
	 * @param idx index
	 * @return current value at index
	 */
	public long get(long idx)
	{
		if (idx<code.size()) {
			return code.get(Math.toIntExact(idx));
		}
		else if (!memory.containsKey(idx)) {
			memory.put(idx, 0L);
		}
		return memory.get(idx);
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
	public void addInput(long n)
	{
		input.add(n);
	}
	
	/**
	 * get latest output of that intcode machine
	 * @return last output
	 */
	public long getLastOutput()
	{
		return lastOutput;
	}
	
	/**
	 * reset the machine and connected machines to initial state
	 */
	public void reset()
	{
		code = new ArrayList<>(original);
		ptr = 0;
		relBase = 0;
		
		lastOutput = -1;
		input.clear();
		isPaused = false;
		
		memory = new HashMap<>();
		
		if (next!=null && !next.isReset()) next.reset();
	}
	
	/**
	 * returns if the code pointer is on 99
	 * @return is finished
	 */
	public boolean isFinished()
	{
		return get(ptr)==99;
	}
	
	/**
	 * returns if the intcode machine is in its initial state
	 * @return is reset
	 */
	public boolean isReset()
	{
		return code.equals(original) && !isFinished() && ptr==0;
	}
	
	public String toString()
	{
		return code + " " + memory;
	}
}