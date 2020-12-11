package adventofcode;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputReader 
{
	private Scanner sc = null;
	
	/**
	 * set the input for a specific day
	 * @param d day
	 * @param y year
	 */
	public InputReader(int d, int y)
	{
		try
		{
			File file = new File("src/main/resources/y"+y+"/D"+String.format("%02d", d)+".txt");
			sc = new Scanner(new FileInputStream(file));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * set an input string
	 * @param s input string
	 */
	public InputReader(String s)
	{
		sc = new Scanner(s);
	}
	
	/**
	 * reads a single line as string
	 * @return string
	 */
	public String readLine()
	{
		return sc.nextLine();
	}
	
	/**
	 * read all lines as list of strings
	 * @return list of strings
	 */
	public List<String> readLines()
	{
		List<String> lines = new ArrayList<>();
		
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		return lines;
	}
	
	/**
	 * read a single line split as string array
	 * @param s regex
	 * @return string array
	 */
	public String[] readLineSplit(String s)
	{
		return sc.nextLine().split(s);
	}
	
	/**
	 * read all lines split as list of string arrays
	 * @param s regex
	 * @return list of string arrays
	 */
	public List<String[]> readLinesSplit(String s)
	{
		List<String[]> lines = new ArrayList<>();
		
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine().split(s));
		}
		return lines;
	}
	
	/**
	 * reads all words as list of strings
	 * @return list of strings
	 */
	public List<String> readStringList()
	{
		List<String> strings = new ArrayList<>();
		
		while (sc.hasNext()) {
			strings.add(sc.next());
		}		
		return strings;
	}
	
	/**
	 * reads all numbers as list of ints
	 * @return list of ints
	 */
	public List<Integer> readIntList()
	{
		List<Integer> ints = new ArrayList<>();
		
		while (sc.hasNextInt()) {
			ints.add(sc.nextInt());
		}		
		return ints;
	}
	
	/**
	 * read a single line split as list of longs
	 * @param s regex
	 * @return list of longs
	 */
	public List<Long> readLongLineSplit(String s)
	{	
		return Arrays.stream(readLineSplit(s))
				.map(Long::parseLong)
				.collect(Collectors.toList());
	}
		
	/**
	 * reads all lines as char matrix
	 * @return char matrix
	 */
	public char[][] readCharMatrix()
	{
		List<String> strings = new ArrayList<>();
		
		while (sc.hasNext()) {
			strings.add(sc.next());
		}
		char[][] m = new char[strings.size()][];
		
		for (int i = 0; i < m.length; i++) {
			m[i] = strings.get(i).toCharArray();
		}		
		return m;
	}
	
	/**
	 * reads a Boolean matrix
	 * @param t char for true
	 * @param f char for false
	 * @param n char for null
	 * @return Boolean matrix
	 */
	public Boolean[][] readBooleanMatrix(char t, char f, char n)
	{
		List<String> lines = readLines();
		Boolean[][] matrix = new Boolean[lines.size()][];
		
		for (int i = 0; i < lines.size(); i++)
		{
			Boolean[] line = new Boolean[lines.get(i).length()];
			
			for (int j = 0; j < lines.get(i).length(); j++)
			{
				if (lines.get(i).charAt(j)==t) line[j] = true;
				else if (lines.get(i).charAt(j)==f) line[j] = false;
				else line[j] = null;
			}
			matrix[i] = line;
		}
		return matrix;
	}
	
	/**
	 * read a char matrix as list of points
	 * @param ch character that represents a point
	 * @return list of points
	 */
	public List<Point> readPoints(char ch)
	{
		List<Point> points = new ArrayList<>();
		int row = 0;
		
		while (sc.hasNext()) 
		{
			String s = sc.next();

			for (int i = 0; i < s.length(); i++) 
			{
				if (s.charAt(i)==ch) {
					points.add(new Point(row, i));
				}
			}
			row++;
		}
		return points;
	}
	
	/**
	 * get scanner
	 * @return scanner
	 */
	public Scanner getScanner()
	{
		return sc;
	}
}