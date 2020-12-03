package adventofcode.y20;

import java.util.List;

import adventofcode.Day;
import adventofcode.InputReader;

public class D03 implements Day
{

	public Object star1() 
	{
		List<String> slope = new InputReader(3, 20).readLines();

		return countTrees(3, 1, slope);
	}

	public Object star2() 
	{
		List<String> slope = new InputReader(3, 20).readLines();

		return countTrees(1, 1, slope)*countTrees(3, 1, slope)*countTrees(5, 1, slope)*countTrees(7, 1, slope)*countTrees(1, 2, slope);
	}
	
	/**
	 * counts trees on slope
	 * @param right right offset
	 * @param down down offset
	 * @param slope all slopes
	 * @return tree count
	 */
	private long countTrees(int right, int down, List<String> slope)
	{
		long count = 0;
		int x = 0;
		
		while (x*down<slope.size())
		{
			if (isTree(x*right, x*down, slope))
			{
				count++;
			}
			x++;
		}
		return count;
	}
	
	/**
	 * @param right right offset
	 * @param down down offset
	 * @param slope all slopes
	 * @return is tree at pos?
	 */
	private boolean isTree(int right, int down, List<String> slope)
	{
		return slope.get(down).charAt(right % slope.get(0).length())=='#';
	}
}
