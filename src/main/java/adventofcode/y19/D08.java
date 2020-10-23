package adventofcode.y19;

import adventofcode.Day;
import adventofcode.FileReader;
import adventofcode.InputPrinter;

public class D08 implements Day
{
	public Object star1() 
	{
		String s = new FileReader(8,19).readLine();
		long min = Long.MAX_VALUE;
		long sum = -1;
		
		for (int i = 0; i < s.length()-149; i += 150)
		{
			String sub = s.substring(i, i+150);
			long count = sub.chars().filter(c -> c=='0').count();
			
			if (count < min)
			{
				min = count;
				sum = sub.chars().filter(c -> c=='1').count()*sub.chars().filter(c -> c=='2').count();
			}
		}		
		return sum;
	}

	public Object star2() 
	{
		String s = new FileReader(8,19).readLine();
		Boolean[][] img = new Boolean[6][25];
		
		for (int j = 0; j < 6; j++) {
			for (int k = 0; k < 25; k++) {
				
				int idx = j*25+k;
				while (idx<s.length() && s.charAt(idx)=='2') idx += 150;
				
				if (s.charAt(idx)=='0') img[j][k] = false;
				else if (s.charAt(idx)=='1') img[j][k] = true;
				else img[j][k] = null;
			}
		}
		return "\n"+InputPrinter.print(img);
	}
}