package adventofcode.y19.obj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Intcode 
{
	private List<Integer> code;
	private List<Integer> tmp;
	
	private int ptr;
	
	public Intcode(List<Integer> c)
	{
		code = c;
		ptr = 0;
		
		tmp = new ArrayList<>(code);
	}
	
	public void step()
	{
		try
		{
			switch (tmp.get(ptr))
			{
				case 1:
					tmp.set(tmp.get(ptr+3), valueAt(ptr+1)+valueAt(ptr+2));
					ptr += 4;
					break;
				case 2:
					tmp.set(tmp.get(ptr+3), valueAt(ptr+1)*valueAt(ptr+2));
					ptr += 4;
					break;
				case 99:
					break;
				default:
					throw new NullPointerException("Opcode "+tmp.get(ptr)+" not found!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void run() 
	{
		while (!hasFinished()) {
			step();
		}
	}
	
	public void debug()
	{		
		while (!hasFinished()) 
		{
			System.out.println(tmp);
			step();
			
			new Scanner(System.in).nextLine();
		}
		System.out.println(tmp);
	}
	
	public void reset()
	{
		tmp = new ArrayList<>(code);
		ptr = 0;
	}
	
	public void set(int index, int element)
	{
		tmp.set(index, element);
	}
	
	public int get(int index)
	{
		return tmp.get(index);
	}
	
	private int valueAt(int pos)
	{
		return tmp.get(tmp.get(pos));
	}
	
	public int getOutput()
	{
		return tmp.get(0);
	}
	
	public boolean hasFinished()
	{
		return code.get(ptr)==99;
	}
}