package adventofcode;

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
	
	public FileReader(int d, int y)
	{
		load(d, y);
	}
	
	public void load(int d, int y)
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
	 * reads seperated string as split line of ints
	 * @param s regex
	 * @return list of ints
	 * 
	 * <pre>
	 * List<0,1,2,3,4>
	 * </pre>
	 */
	public List<Integer> readIntListSplit(String s)
	{
		return Arrays.stream(sc.next().split(s))
				.map(Integer::parseInt)
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
	 * 
	 * @return
	 */
	public Scanner getSacanner()
	{
		return sc;
	}
}