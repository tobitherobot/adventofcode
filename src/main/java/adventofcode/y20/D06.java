package adventofcode.y20;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import adventofcode.Day;
import adventofcode.InputReader;

public class D06 implements Day
{
	public Object star1() 
	{
		List<String> input = new InputReader(6,20).readLines();
		Set<Character> answers = new HashSet<>();
		int sum = 0;
		
		for (String line : input)
		{
			if (line.isEmpty()) {
				sum += answers.size();
				answers.clear();
			}
			else for (char c : line.toCharArray()) {
				answers.add(c);
			}
		}
		sum += answers.size();
		
		return sum;
	}

	public Object star2() 
	{
		List<String> input = new InputReader(6,20).readLines();
		int[] answers = new int[26];
		int count = 0;
		int sum = 0;
		
		for (String line : input)
		{
			if (line.isEmpty()) 
			{
				for (int answer : answers) {
					if (count==answer) sum++;
				}
				answers = new int[26];
				count = 0;
			}
			else 
			{
				for (char c : line.toCharArray()) {
					answers[(int) (c-'a')]++;
				}
				count++;
			}
		}
		for (int answer : answers) {
			if (count==answer) sum++;
		}
		
		return sum;
	}
}
