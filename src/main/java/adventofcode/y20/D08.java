package adventofcode.y20;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import adventofcode.Day;
import adventofcode.InputReader;

public class D08 implements Day
{
	public Object star1() 
	{
		List<String> input = new InputReader(8,20).readLines();
		Set<Integer> exec = new HashSet<>();
		int ptr = 0;
		int acc = 0;
		
		while (!exec.contains(ptr))
		{
			String[] split = input.get(ptr).split(" ");
			exec.add(ptr);
			
			switch (split[0])
			{
				case "acc":
					acc += Integer.parseInt(split[1]);
					ptr++;
					break;
				case "jmp":
					ptr += Integer.parseInt(split[1]);
					break;
				default:
					ptr++;
			}
		}
		return acc;
	}

	public Object star2() 
	{
		List<String> input = new InputReader(8,20).readLines();
		int idx = 0;
		int ptr = 0;
		int acc = 0;
		
		do
		{
			List<String> cmds = new ArrayList<>(input);
			Set<Integer> exec = new HashSet<>();
			ptr = 0;
			acc = 0;
			
			// replace every jmp/nop command with its counterpart 
			while (cmds.get(idx).charAt(2) != 'p') idx++;
			if (cmds.get(idx).charAt(0)=='j') cmds.set(idx, "nop " + cmds.get(idx).substring(4));
			else cmds.set(idx,  "jmp " + cmds.get(idx).substring(4));
				
			// let program run over new code and check if it reaches the end
			while (!exec.contains(ptr) && 0<=ptr && ptr<cmds.size())
			{
				String[] split = cmds.get(ptr).split(" ");
				exec.add(ptr);
				
				switch (split[0])
				{
					case "acc":
						acc += Integer.parseInt(split[1]);
						ptr++;
						break;
					case "jmp":
						ptr += Integer.parseInt(split[1]);
						break;
					default:
						ptr++;
				}
			}
			idx++;
		}
		while (ptr<input.size());

		return acc;
	}
}
