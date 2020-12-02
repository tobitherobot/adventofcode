package adventofcode.y20;

import java.util.List;

import adventofcode.Day;
import adventofcode.InputReader;

public class D02 implements Day
{
	public Object star1() 
	{
		List<String> policies = new InputReader(2, 20).readLines();
		int count = 0;
		
		for (String policy : policies) {
			if (checkPolicyOld(policy)) count++;
		}
		return count;
	}

	public Object star2() 
	{
		List<String> policies = new InputReader(2, 20).readLines();
		int count = 0;
		
		for (String policy : policies) {
			if (checkPolicyNew(policy)) count++;
		}
		return count;
	}
	
	private boolean checkPolicyOld(String policy)
	{
		String[] split = policy.split(" ");
		String[] ints = split[0].split("-");
		
		int min = Integer.parseInt(ints[0]);
		int max = Integer.parseInt(ints[1]);
		char c = split[1].charAt(0);
		String pw = split[2];
		
		int count = (int) pw.chars().filter(ch -> ch==c).count();
		
		return min<=count && count<=max;
	}
	
	private boolean checkPolicyNew(String policy)
	{
		String[] split = policy.split(" ");
		String[] ints = split[0].split("-");
		
		int posA = Integer.parseInt(ints[0]);
		int posB = Integer.parseInt(ints[1]);
		char c = split[1].charAt(0);
		String pw = split[2];
				
		return pw.charAt(posA-1)==c ^ pw.charAt(posB-1)==c;
	}
}