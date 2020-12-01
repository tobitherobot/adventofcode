package adventofcode.y20;

import java.util.List;

import adventofcode.Day;
import adventofcode.InputReader;

public class D01 implements Day
{
	public Object star1() 
	{
		List<Integer> ints = new InputReader(1, 20).readIntList();
		
		for (int i = 0; i < ints.size()-1; i++) {
			for (int j = i+1; j < ints.size(); j++) {
				
				if (ints.get(i)+ints.get(j)==2020) {
					return ints.get(i)*ints.get(j);
				}
			}
		}
		
		return null;
	}

	public Object star2() 
	{
		List<Integer> ints = new InputReader(1, 20).readIntList();
		
		for (int i = 0; i < ints.size()-2; i++) {
			for (int j = i+1; j < ints.size()-1; j++) {
				for (int k = j+1; k < ints.size(); k++) {
				
					if (ints.get(i)+ints.get(j)+ints.get(k)==2020) {
						return ints.get(i)*ints.get(j)*ints.get(k);
					}
				}
			}
		}
		
		return null;
	}
}
