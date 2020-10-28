package adventofcode.y19.obj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class Intcode 
{
	private List<Long> original;
	private List<Long> code;
	
	private int ptr;
	private int relBase;
	
	private Intcode next;
	private Queue<Long> input;
	private Queue<Long> output;
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
		output = new LinkedList<>();
		lastOutput = -1;
		
		memory = new HashMap<>();
		
		isPaused = false;
	}
	
	/**
	 * do a single instruction
	 */
	public void step()
	{
		int opcode = Math.toIntExact(get(ptr));
		int[] modes = new int[] {opcode%100, opcode/100%10, opcode/1000%10, opcode/10000};

		try
		{
			switch (modes[0])
			{
				case 1: // add: param1+param2 at param3
					set(ptr+3, modes[3], get(ptr+1, modes[1]) + get(ptr+2, modes[2]));
					ptr += 4;
					break;
				case 2: // mul: param1*param2 at param3
					set(ptr+3, modes[3], get(ptr+1, modes[1]) * get(ptr+2, modes[2]));
					ptr += 4;
					break;
				case 3: // in: save input in param1
					if (!input.isEmpty()) {
						set(ptr+1, modes[1], input.poll());
						isPaused = false;
						ptr += 2;
					}
					else isPaused = true;
					break;
				case 4: // out: output param1
					lastOutput = get(ptr+1, modes[1]);
					if (next!=null) {
						next.addInput(Math.toIntExact(lastOutput));
					}
					output.add(lastOutput);
					ptr += 2;
					break;
				case 5: // jit: if param1 is not 0, set ptr to param2
					if (get(ptr+1, modes[1])!=0) {
						ptr = Math.toIntExact(get(ptr+2, modes[2]));
					}
					else ptr += 3;
					break;
				case 6: // jif: if param1 is 0, set ptr to param2
					if (get(ptr+1, modes[1])==0) {
						ptr = Math.toIntExact(get(ptr+2, modes[2]));
					}
					else ptr += 3;
					break;
				case 7: // lt: if param1 less than param2, it stores 1 in param3 (otherwise 0)
					if (get(ptr+1, modes[1]) < get(ptr+2, modes[2])) {
						set(ptr+3, modes[3], 1);
					}
					else set(ptr+3, modes[3], 0);
					ptr += 4;
					break;
				case 8: // eq: if param1 equals param2, it stores 1 in param3 (otherwise 0)
					if (get(ptr+1, modes[1]) == get(ptr+2, modes[2])) {
						set(ptr+3, modes[3], 1);
					}
					else set(ptr+3, modes[3], 0);
					ptr += 4;
					break;
				case 9: // adj: adjust relative base by param1
					relBase += get(ptr+1, modes[1]);
					ptr += 2;
					break;
				case 99: // end: end program
					break;
				default:
					throw new NullPointerException("Opcode "+modes[0]+" not found!");
			}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * convert the get-parameter with its mode to its value
	 * @param value initial value
	 * @param mode parameter mode
	 * @return converted value
	 */
	private long get(long value, int mode)
	{
		switch (mode) 
		{
			case 0: // position mode
				return get(get(value));
			case 1: // immediate mode
				return get(value);
			case 2: // relative mode
				return get(get(value)+relBase);
			default:
				return Long.MIN_VALUE;
		}
	}
	
	/**
	 * convert the set-parameter with its mode to its value
	 * @param idx initial index
	 * @param mode parameter mode
	 * @param value value to store
	 * @return converted value
	 */
	private void set(long idx, int mode, long value)
	{
		switch (mode) 
		{
			case 0: // position mode
				set(get(idx), value);
				break;
			case 1: // immediate mode
				set(idx, value);
				break;
			case 2: // relative mode
				set(get(idx)+relBase, value);
				break;
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
	 * manually change a value in the code
	 * @param idx index
	 * @param v new value
	 */
	public void set(long idx, long v)
	{
		if (idx<code.size()) code.set(Math.toIntExact(idx), v);
		else memory.put(idx, v);
	}
	
	/**
	 * manually get a value in the code
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
	 * add an input
	 * @param n value
	 */
	public void addInput(long n)
	{
		input.add(n);
	}
	
	/**
	 * add inputs for multiple connected intcode machines
	 * @param p string of single digit inputs, one digit per machine
	 */
	public void addInputs(String p)
	{
		if (!p.isEmpty())
		{
			addInput(Character.getNumericValue(p.charAt(0)));
			
			if (next!=null) {
				next.addInputs(p.substring(1));
			}
		}
	}
	
	/**
	 * queue of previous outputs
	 * @return next output
	 */
	public long getOutput()
	{
		return output.poll();
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
		
		input.clear();
		output.clear();
		lastOutput = -1;
		
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
	
	/**
	 * get the intcode machine's extra memory
	 * @return extra memory hashmap
	 */
	public Map<Long,Long> getMemory()
	{
		return memory;
	}
	
	/**
	 * manually set the code
	 * @param c code
	 */
	public void setCode(List<Long> c)
	{
		original = c;
		reset();
	}
	
	/**
	 * nicely returns the relative base, the current code and pointer position and the extra memory
	 */
	public String toString()
	{		
		StringBuilder sb = new StringBuilder(String.join(" ", code.stream()
				.map(String::valueOf)
				.collect(Collectors.toList())) + " ");
		int idx = -1;
		
		for (int i = 0; i < ptr; i++) {
			idx = sb.indexOf(" ", idx+1);
		}
		sb.insert(idx+1, ">");
		sb.insert(sb.indexOf(" ", idx+1), "<");
		
		return "("+relBase+")\t" + sb.toString() + memory;
	}
}