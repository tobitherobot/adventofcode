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
	 * returns list of ints
	 * @return list of ints
	 */
	public List<Integer> getIntList()
	{
		List<Integer> ints = new ArrayList<>();
		while (sc.hasNextInt()) ints.add(sc.nextInt());
		
		return ints;
	}
	
	/**
	 * returns split string as list of ints
	 * @param s regex
	 * @return list of ints
	 */
	public List<Integer> getIntListSplit(String s)
	{
		return Arrays.stream(sc.next().split(s))
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}
	
	/**
	 * returns split arrays of strings as list
	 * @param s regex
	 * @return list of string arrays
	 */
	public List<String[]> getStringListSplitArray(String s)
	{
		List<String[]> strings = new ArrayList<>();
		while (sc.hasNext()) strings.add(sc.next().split(s));
		
		return strings;
	}
	
	public Scanner getScanner()
	{
		return sc;
	}
}