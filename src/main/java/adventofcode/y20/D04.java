package adventofcode.y20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventofcode.Day;
import adventofcode.InputReader;

public class D04 implements Day
{
	public Object star1() 
	{
		List<String[]> input = new InputReader(4,20).readLinesSplit(" ");
		List<Map<String, String>> passes = getPasses(input);
		int count = 0;
		
		for (Map<String, String> pass : passes)
		{
			if (pass.containsKey("byr") && pass.containsKey("iyr") && pass.containsKey("eyr") && pass.containsKey("hgt") && 
					pass.containsKey("hcl") && pass.containsKey("ecl") && pass.containsKey("pid")) count++;
		}	
		return count;
	}

	public Object star2() 
	{
		List<String[]> input = new InputReader(4,20).readLinesSplit(" ");
		List<Map<String, String>> passes = getPasses(input);
		List<String> ecls = Arrays.asList("amb","blu","brn","gry","grn","hzl","oth");
		int count = 0;
		
		for (Map<String, String> pass : passes)
		{
			try
			{
				// bit messy
				int byr = Integer.parseInt(pass.get("byr"));
				int iyr = Integer.parseInt(pass.get("iyr"));
				int eyr = Integer.parseInt(pass.get("eyr"));
				int hgt = Integer.parseInt(pass.get("hgt").substring(0, pass.get("hgt").length()-2));
				String mtr = pass.get("hgt").substring(pass.get("hgt").length()-2);
				String hcl = pass.get("hcl");
				Integer.parseInt(pass.get("pid"));
			
				boolean f1 = 1920<=byr && byr<=2002;
				boolean f2 = 2010<=iyr && iyr<=2020;
				boolean f3 = 2020<=eyr && eyr<=2030;
				boolean f4 = (mtr.equals("cm") && 150<=hgt && hgt<=193) || (mtr.equals("in") && 59<=hgt && hgt<=76);
				boolean f5 = hcl.length()==7 && hcl.charAt(0)=='#' && hcl.substring(1).replaceAll("[0-9a-f]", "").length()==0;
				boolean f6 = ecls.contains(pass.get("ecl"));
				boolean f7 = pass.get("pid").length()==9;
				
				if (f1 && f2 && f3 && f4 && f5 && f6 && f7) count++;
			}
			catch (Exception e) {}
		}
		return count;
	}
	
	/**
	 * read all passports
	 * @param input 
	 * @return passports as map
	 */
	private List<Map<String, String>> getPasses(List<String[]> input)
	{
		List<Map<String, String>> passes = new ArrayList<>();
		Map<String, String> pass = new HashMap<>();
		
		for (String[] line : input)
		{
			for (String block : line)
			{				
				if (block.isEmpty())
				{
					passes.add(pass);
					pass = new HashMap<>();
				}
				else pass.put(block.split(":")[0], block.split(":")[1]);
			}
		}
		if (!pass.isEmpty()) passes.add(pass);
		
		return passes;
	}
}
