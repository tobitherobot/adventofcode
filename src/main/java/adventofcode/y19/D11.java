package adventofcode.y19;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import adventofcode.Day;
import adventofcode.InputReader;
import adventofcode.InputPrinter;
import adventofcode.y19.obj.Intcode;

public class D11 implements Day 
{
	public Object star1() 
	{
		List<Long> code = new InputReader(11,19).readLongLineSplit(",");
		Intcode intcode = new Intcode(code);
		
		Map<Point, Long> colors = paint(intcode, new HashMap<>());		
		return colors.size();
	}

	public Object star2() 
	{
		List<Long> code = new InputReader(11,19).readLongLineSplit(",");
		Intcode intcode = new Intcode(code);
		
		Map<Point, Long> colors = new HashMap<>();		
		colors.put(new Point(0,0), 1L);
		colors = paint(intcode, colors);
		
		List<Point> points = colors.entrySet().stream()
				.filter(e -> e.getValue().equals(1L))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
		
		return "\n" + new InputPrinter().parse(points).get();
	}
	
	/**
	 * let the program run and paint the tiles
	 * @param intcode intcode machine
	 * @param colors map of tiles
	 * @return painted map of tiles
	 */
	private Map<Point,Long> paint(Intcode intcode, Map<Point,Long> colors)
	{
		Point pos = new Point(0,0);
		int dir = 0; // 0=up, 1=right, 2=down, 3=left
				
		while (!intcode.isFinished())
		{
			if (!colors.containsKey(pos)) {
				colors.put(new Point(pos), 0L);
			}
			intcode.addInput(colors.get(pos));
			
			intcode.run();
			colors.put(pos, intcode.getOutput());

			if (intcode.getOutput()==0) {
				dir = (dir+3)%4;
			}
			else dir = (dir+1)%4;
			
			if (dir==0) pos.y--;
			else if (dir==1) pos.x++;
			else if (dir==2) pos.y++;
			else pos.x--;
		}
		return colors;
	}
}