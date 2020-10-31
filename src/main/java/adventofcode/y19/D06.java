package adventofcode.y19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import adventofcode.Day;
import adventofcode.InputReader;

public class D06 implements Day
{
	public Object star1() 
	{
		List<String[]> input = new InputReader(6,19).readLinesSplit("\\)");
		Node root = createRoot(input);
		
		return root.countOrbits(0);
	}

	public Object star2() 
	{
		List<String[]> input = new InputReader(6,19).readLinesSplit("\\)");
		Node root = createRoot(input);
		
		return root.getDistance("YOU", "SAN");
	}
	
	/**
	 * creates the root node
	 * @param input
	 * @return root node
	 */
	private Node createRoot(List<String[]> input) 
	{
		Map<String, Node> nodes = new HashMap<>();
		
		for (String[] obj : input)
		{
			if (!nodes.containsKey(obj[0])) {
				nodes.put(obj[0], new Node(obj[0]));
			}
			if (!nodes.containsKey(obj[1])) {
				nodes.put(obj[1], new Node(obj[1]));
			}
			nodes.get(obj[0]).add(nodes.get(obj[1]));
		}
		
		Set<String> key = nodes.keySet();
		nodes.keySet().removeAll(input.stream().map(i -> i[1]).collect(Collectors.toList()));
		
		return nodes.get(new ArrayList<>(key).get(0));
	}
}

class Node
{
	private List<Node> nodes;
	
	private String name;
	
	public Node(String n) 
	{
		nodes = new ArrayList<>();		
		name = n;
	}
	
	public void add(Node n) {
		nodes.add(n);
	}
	
	/**
	 * count all orbits
	 * @param index starting value
	 * @return sum of all orbits
	 */
	public int countOrbits(int index)
	{
		int x = index;
		
		for (Node n : nodes) {
			x += n.countOrbits(index+1);
		}
		return x;
	}
	
	/**
	 * get distance between two nodes by name
	 * @param a nameA
	 * @param b nameB
	 * @return distance between
	 */
	public int getDistance(String a, String b)
	{
		return nodes.stream().mapToInt(n -> 
		{
			if (!n.contains(a)&&!n.contains(b)) {
				return 0;
			}
			else if (n.contains(a)&&n.contains(b)) {
				return n.getDistance(a, b);
			}
			else return 1+n.getDistance(a, b);
		}).sum();
	}
	
	public boolean contains(String s) 
	{
		for (Node n : nodes)
		{
			if (n.getName().equals(s) || n.contains(s)) return true;
		}
		return false;
	}
	
	public String getName()
	{
		return name;
	}
}