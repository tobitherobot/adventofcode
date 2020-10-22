package adventofcode.y19;

import adventofcode.Day;

public class D04 implements Day
{
	public Object star1() 
	{
		int lower = 138307;
		int upper = 654504;
		
		int n = lower;
		int count = 0;
		
		while (n<=upper)
		{
			StringBuilder sb = new StringBuilder(String.valueOf(n+1));
			boolean flag = false;
			
			for (int i = 0; i < sb.length()-1; i++)
			{
				if (sb.charAt(i) > sb.charAt(i+1))
				{
					sb.setCharAt(i+1, sb.charAt(i));
					flag = true;
				}
				else if (sb.charAt(i) == sb.charAt(i+1)) flag = true;
			}
			n = Integer.parseInt(sb.toString());
			
			if (flag && n<=upper) count++;
		}
		return count;
	}

	public Object star2() 
	{
		int lower = 138307;
		int upper = 654504;
		
		int n = lower;
		int count = 0;
		
		while (n<=upper)
		{
			StringBuilder sb = new StringBuilder(String.valueOf(n+1));
			boolean flag = false;
			int x = 0;
			
			for (int i = 0; i < sb.length()-1; i++)
			{				
				if (sb.charAt(i) > sb.charAt(i+1))
				{
					sb.setCharAt(i+1, sb.charAt(i));
					x++;
				}
				else if (sb.charAt(i) == sb.charAt(i+1)) x++;
				
				if (sb.charAt(i) != sb.charAt(i+1)) 
				{
					if (x==1) flag = true;
					x = 0;
				}
			}
			flag = flag || x==1;
			
			n = Integer.parseInt(sb.toString());
			
			if (flag && n<=upper) count++;
		}
		return count;
	}
}