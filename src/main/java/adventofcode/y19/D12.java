package adventofcode.y19;

import java.util.List;

import adventofcode.Day;
import adventofcode.InputReader;
import adventofcode.util.Point3D;

public class D12 implements Day
{
	public Object star1() 
	{
		List<Point3D> moons = new InputReader(12,19).read3DPoints();
		
		for (Point3D moon : moons) System.out.println(moon);
		return null;
	}

	public Object star2() 
	{
		return null;
	}
}