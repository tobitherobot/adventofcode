package adventofcode.y19;

import java.util.ArrayList;
import java.util.List;

import adventofcode.Day;
import adventofcode.InputReader;
import adventofcode.util.Point3D;

public class D12 implements Day
{
	public Object star1() 
	{
		List<String> input = new InputReader(12,19).readLines();
		List<Point3D[]> moons = getMoons(input);
		
		for (int i = 0; i < 1000; i++)
		{
			// apply all gravities
			for (int j = 0; j < moons.size(); j++)
			{
				for (int k = j+1; k < moons.size(); k++)
				{
					applyGravity(moons.get(j), moons.get(k));
				}
			}
			
			// apply all velocities
			for (Point3D[] moon : moons)
			{
				moon[0].x += moon[1].x;
				moon[0].y += moon[1].y;
				moon[0].z += moon[1].z;
			}
		}
		return getEnergy(moons);
	}

	// not working :(
	public Object star2() 
	{
		List<String> input = new InputReader(12,19).readLines();
		List<Point3D[]> moons = getMoons(input);
		boolean loop = true;
		long steps = 0;
		
//		do
//		{
//			// apply all gravities
//			for (int j = 0; j < moons.size(); j++)
//			{
//				for (int k = j+1; k < moons.size(); k++)
//				{
//					applyGravity(moons.get(j), moons.get(k));
//				}
//			}
//			
//			// apply all velocities
//			for (Point3D[] moon : moons)
//			{
//				moon[0].x += moon[1].x;
//				moon[0].y += moon[1].y;
//				moon[0].z += moon[1].z;
//			}
//			
//			steps++;
//		}
//		while (loop);
		
		return steps;
	}
	
	/**
	 * apply gravities for pair of moons
	 * @param moonA moon A
	 * @param moonB moon B
	 */
	private void applyGravity(Point3D[] moonA, Point3D[] moonB)
	{
		if (moonA[0].x < moonB[0].x) {
			moonA[1].x++;
			moonB[1].x--;
		}
		else if (moonA[0].x > moonB[0].x) {
			moonA[1].x--;
			moonB[1].x++;
		}
		
		if (moonA[0].y < moonB[0].y) {
			moonA[1].y++;
			moonB[1].y--;
		}
		else if (moonA[0].y > moonB[0].y) {
			moonA[1].y--;
			moonB[1].y++;
		}
		
		if (moonA[0].z < moonB[0].z) {
			moonA[1].z++;
			moonB[1].z--;
		}
		else if (moonA[0].z > moonB[0].z) {
			moonA[1].z--;
			moonB[1].z++;
		}
	}
	
	/**
	 * calculate energy of all moons
	 * @param moons all moons
	 * @return total energy
	 */
	private long getEnergy(List<Point3D[]> moons)
	{
		long sum = 0;
		
		for (Point3D[] moon : moons)
		{
			sum += (Math.abs(moon[0].x) + 
					Math.abs(moon[0].y) +
					Math.abs(moon[0].z)) * (
					Math.abs(moon[1].x) +
					Math.abs(moon[1].y) + 
					Math.abs(moon[1].z));
		}
		return sum;
	}
	
	/**
	 * convert to moons
	 * @param input
	 * @return Point3D array with position and velocity
	 */
	private List<Point3D[]> getMoons(List<String> input)
	{
		List<Point3D[]> moons = new ArrayList<>();
		
		for (String in : input) 
		{
			String[] pos = in.substring(3, in.length()-1).replaceAll("[yz=,]", "").replaceAll(" +", " ").split(" ");
			moons.add(new Point3D[] {new Point3D(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), Integer.parseInt(pos[2])), new Point3D(0, 0, 0)});
		}
		return moons;
	}
}