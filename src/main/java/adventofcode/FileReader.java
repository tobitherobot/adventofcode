package adventofcode;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileReader 
{
	private InputStream is = null;
	private Scanner sc = null;
	
	/**
	 * set the input for specific day
	 * @param d day
	 * @param y year
	 */
	public FileReader(int d, int y)
	{
		try
		{
			File file = new File("src/main/resources/y"+y+"/D"+String.format("%02d", d)+".txt");
			is = new FileInputStream(file);
			sc = new Scanner(is);
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
	public FileReader(String s)
	{
		sc = new Scanner(s);
	}
	
	/**
	 * reads line as string
	 * @return string
	 * 
	 * <pre>
	 * "abcdef"
	 * </pre>
	 */
	public String readLine()
	{
		return sc.nextLine();
	}
	
	/**
	 * reads all lines for numbers
	 * @return list of ints
	 * 
	 * <pre> 
	 * List<0 1 
	 *      2 3
	 *      4>
	 * </pre>
	 */
	public List<Integer> readIntList()
	{
		List<Integer> ints = new ArrayList<>();
		while (sc.hasNextInt()) ints.add(sc.nextInt());
		
		return ints;
	}
	
	/**
	 * reads seperated string as split line of longs
	 * @param s regex
	 * @return list of longs
	 * 
	 * <pre>
	 * List<0L,1L,2L,3L,4L>
	 * </pre>
	 */
	public List<Long> readLongListSplit(String s)
	{	
		return Arrays.stream(sc.next().split(s))
				.map(Long::parseLong)
				.collect(Collectors.toList());
	}
	
	/**
	 * returns split arrays of strings as list
	 * @param s regex
	 * @return list of string arrays
	 * 
	 * <pre>
	 * List<String[]{0,1,2,3,4},
	 *      String[]{5,6,7,8,9}>
	 * </pre>
	 */
	public List<String[]> readStringListSplitArray(String s)
	{
		List<String[]> strings = new ArrayList<>();
		while (sc.hasNext()) strings.add(sc.next().split(s));
		
		return strings;
	}
	
	/**
	 * reads a character matrix
	 * @return matrix
	 * 
	 * <pre>
	 * [['.','#'],
	 *  ['#','.']]
	 * </pre>
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
	 * read a list of 2d points
	 * @param ch character that represents a point
	 * @return list of points
	 * 
	 * <pre>
	 * List<Point(0,1),
	 *  	Point(1,0)>
	 * </pre>
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