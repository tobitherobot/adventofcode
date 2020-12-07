package adventofcode.y19;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventofcode.Day;
import adventofcode.InputReader;
import adventofcode.y19.obj.Intcode;

public class D13 implements Day
{	
	private long score = 0;
	
	public Object star1() 
	{
		List<Long> code = new InputReader(13,19).readLongLineSplit(",");
		Intcode intcode = new Intcode(code);
		
		intcode.run();
		Map<Point, Character> tiles = getTiles(intcode, new HashMap<>());
				
		return tiles.entrySet().stream().filter(p -> p.getValue() == 'X').count();
	}

	public Object star2() 
	{
		List<Long> code = new InputReader(13,19).readLongLineSplit(",");
		Intcode intcode = new Intcode(code);
		Map<Point, Character> tiles;
		
		intcode.set(0, 2);
		intcode.run();
		tiles = getTiles(intcode, new HashMap<>());
		
		while (!hasFinished(tiles))
		{
			intcode.addInput(getJoystick(tiles));
			intcode.run();
			
			tiles = getTiles(intcode, tiles);
			//System.out.println(InputPrinter.print(tiles));
		}
		return score;
	}
	
	/**
	 * get tiles
	 * @param intcode intcode machine
	 * @param tiles previous tile map
	 * @return updated tile map
	 */
	private Map<Point, Character> getTiles(Intcode intcode, Map<Point, Character> tiles)
	{		
		while (intcode.hasOutput())
		{
			int posX = (int) intcode.getOutput();
			int posY = (int) intcode.getOutput();
			long out = intcode.getOutput();
			char c;
			
			switch ((int) out)
			{
				case 0:
					c = ' ';
					break;
				case 1:
					c = '#';
					break;
				case 2:
					c = 'X';
					break;
				case 3:
					c = '=';
					break;
				case 4:
					c = 'O';
					break;
				default:
					c = 'ö';
					score = out;
			}
			if (0<=posX) tiles.put(new Point(posX, posY), c);
		}
		return tiles;
	}
	
	/**
	 * get the next joystick position
	 * @param tiles tile map
	 * @return next direction of joystick
	 */
	private int getJoystick(Map<Point, Character> tiles)
	{
		int posPaddle = tiles.entrySet().stream().filter(e -> e.getValue()=='=').findFirst().get().getKey().x;
		int posBall = tiles.entrySet().stream().filter(e -> e.getValue()=='O').findFirst().get().getKey().x;
		
		if (posPaddle < posBall) return 1;
		else if (posPaddle > posBall) return -1;
		else return 0;
	}
	
	/**
	 * check if all blocks are destroyed
	 * @param tiles tile map
	 * @return has game finished?
	 */
	private boolean hasFinished(Map<Point, Character> tiles)
	{
		return tiles.entrySet().stream().filter(e -> e.getValue()=='X').count() == 0;
	}
}
