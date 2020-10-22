package adventofcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	
	public List<Integer> getIntList()
	{
		List<Integer> ints = new ArrayList<>();
		while (sc.hasNextInt()) ints.add(sc.nextInt());
		
		return ints;
	}
}