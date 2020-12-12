package adventofcode.y20;

import java.util.List;

import adventofcode.Day;
import adventofcode.InputReader;

public class D12 implements Day
{
	public Object star1()
	{
		List<String> input = new InputReader(12,20).readLines();
		int facing = 1;
		int posX = 0;
		int posY = 0;
		
		for (String in : input)
		{
			int n = Integer.parseInt(in.substring(1));
			
			switch (in.charAt(0))
			{
			case 'N':
				posY += n;
				break;
			case 'S':
				posY -= n;
				break;
			case 'E':
				posX += n;
				break;
			case 'W':
				posX -= n;
				break;
			case 'L':
				facing -= n/90;
				while (facing<0) facing += 4;
				break;
			case 'R':
				facing += n/90;
				facing %= 4;
				break;
			case 'F':
				if (facing==0) posY += n;
				else if (facing==1) posX += n;
				else if (facing==2) posY -= n;
				else posX -= n;
				break;
			}
		}
		return Math.abs(posX)+Math.abs(posY);
	}

	public Object star2() 
	{
		List<String> input = new InputReader(12,20).readLines();
		int shipX = 0;
		int shipY = 0;
		int wpX = 10;
		int wpY = 1;
		
		for (String in : input)
		{
			int n = Integer.parseInt(in.substring(1));
			
			switch (in.charAt(0))
			{
			case 'N':
				wpY += n;
				break;
			case 'S':
				wpY -= n;
				break;
			case 'E':
				wpX += n;
				break;
			case 'W':
				wpX -= n;
				break;
			case 'L':
				for (int i = 0; i < n/90; i++) {
					int tmp = wpX;
					wpX = -wpY;
					wpY = tmp;
				}
				break;
			case 'R':
				for (int i = 0; i < n/90; i++) {
					int tmp = wpX;
					wpX = wpY;
					wpY = -tmp;
				}
				break;
			case 'F':
				for (int i = 0; i < n; i++) {
					shipX += wpX;
					shipY += wpY;
				}
				break;
			}
		}
		return Math.abs(shipX)+Math.abs(shipY);
	}
}
