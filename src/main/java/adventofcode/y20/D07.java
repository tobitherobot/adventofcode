package adventofcode.y20;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import adventofcode.Day;
import adventofcode.InputReader;

public class D07 implements Day
{
	public Object star1() 
	{
		List<String> input = new InputReader(7,20).readLines();
		Map<String, Map<String, Integer>> rules = getRules(input);
		Set<String> luggage = new HashSet<>();
		Queue<String> check = new LinkedList<>();
		
		check.add("shinygold");
		
		while (!check.isEmpty())
		{	
			String chk = check.poll();
			
			for (String key : rules.keySet()) {
				
				if (rules.get(key).containsKey(chk) && !luggage.contains(key)) 
				{
					luggage.add(key);
					check.add(key);
				}
			}
		}
		return luggage.size();
	}

	public Object star2() 
	{
		List<String> input = new InputReader(7,20).readLines();
		Map<String, Map<String, Integer>> rules = getRules(input);
		
		return countLuggage("shinygold", rules);
	}
	
	/**
	 * count all contained luggage
	 * @param key initial luggage color
	 * @param rules luggage rules
	 * @return total sum of contained luggage
	 */
	private int countLuggage(String key, Map<String, Map<String, Integer>> rules)
	{
		Map<String, Integer> luggage = rules.get(key);
		int sum = 0;
		
		for (String k : luggage.keySet())
		{
			sum += luggage.get(k);
			sum += luggage.get(k) * countLuggage(k, rules);
		}
		return sum;
	}
	
	/**
	 * get all luggage rules
	 * @param lines input
	 * @return map of luggage color to map of contained luggage color to amount
	 */
	private Map<String, Map<String, Integer>> getRules(List<String> lines)
	{
		Map<String, Map<String, Integer>> rules = new HashMap<>();
		
		for (String line : lines)
		{
			Map<String, Integer> luggage = new HashMap<>();
			String[] split = line.split(" ");
			
			if (7<split.length) 
			{
				for (int i = 4; i<split.length; i+=4) {
					luggage.put(split[i+1]+split[i+2], Integer.parseInt(split[i]));
				}
			}
			
			rules.put(split[0]+split[1], luggage);
			luggage = new HashMap<>();
		}
		return rules;
	}
}
