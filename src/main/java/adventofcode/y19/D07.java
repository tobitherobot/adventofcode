package adventofcode.y19;

import java.util.List;

import adventofcode.Day;
import adventofcode.InputReader;
import adventofcode.util.MathUtil;
import adventofcode.y19.obj.Intcode;

public class D07 implements Day
{
	public Object star1()
	{
		List<Long> code = new InputReader(7,19).readLongLineSplit(",");
		Intcode root = getMachines(code);
		long maxSignal = -1;

		StringBuilder phase = new StringBuilder("01234");
		
		do
		{
			root.addInputs(phase.toString());
			root.addInput(0);
			
			root.run();
			long signal = root.getNext().getNext().getNext().getNext().getLastOutput();
			maxSignal = Math.max(maxSignal, signal);
			root.reset();
		}
		while (MathUtil.nextPermutation(phase));
		
		return maxSignal;
	}

	public Object star2()
	{
		List<Long> code = new InputReader(7,19).readLongLineSplit(",");
		Intcode root = getMachines(code);
		long maxSignal = -1;
		
		StringBuilder phase = new StringBuilder("56789");
		root.getNext().getNext().getNext().getNext().addNext(root);
		
		do
		{
			root.addInputs(phase.toString());
			root.addInput(0);
			
			root.run();
			long signal = root.getNext().getNext().getNext().getNext().getLastOutput();
			maxSignal = Math.max(maxSignal, signal);
			root.reset();
		}
		while (MathUtil.nextPermutation(phase));
		
		return maxSignal;
	}
	
	/**
	 * get connected intcode machines
	 * @param code intcode
	 * @return root intcode machine
	 */
	private Intcode getMachines(List<Long> code)
	{
		Intcode root = new Intcode(code);
		
		root.addNext(new Intcode(code));
		root.getNext().addNext(new Intcode(code));
		root.getNext().getNext().addNext(new Intcode(code));
		root.getNext().getNext().getNext().addNext(new Intcode(code));
		
		return root;
	}
}