package adventofcode.y19;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import adventofcode.Day;
import adventofcode.FileReader;
import adventofcode.util.MathUtil;

public class D10 implements Day
{
	private Point laser = null;
	
	public Object star1() 
	{
		List<Point> asteroids = new FileReader(10,19).readPoints('#');
		Map<Point, Integer> counts = new HashMap<>();
		
		for (Point asteroid : asteroids) {
			counts.put(asteroid, 0);
		}
		
		for (int i = 0; i < asteroids.size(); i++) {
			for (int j = i+1; j < asteroids.size(); j++) {

				if (getIndex(asteroids.get(i), asteroids.get(j), asteroids)==0)
				{
					counts.put(asteroids.get(i), counts.get(asteroids.get(i))+1);
					counts.put(asteroids.get(j), counts.get(asteroids.get(j))+1);
				}
			}
		}
		
		laser = counts.entrySet()
				.stream()
				.max(Map.Entry.comparingByValue())
				.get()
				.getKey();
		
		return counts.get(laser);
	}

	public Object star2() 
	{
		List<Point> asteroids = new FileReader(10,19).readPoints('#');
		Map<Float, Point> values = new TreeMap<>();
		asteroids.remove(laser);
		
		for (Point p : asteroids)
		{
			float value = getIndex(laser, p, asteroids)*360 + getAngle(laser, p);
			values.put(value, p);
		}
		List<Point> points = new ArrayList<>(values.values());
		Point p = points.get(199);
		
		return p.y*100+p.x;
	}
	
	/**
	 * returns the index of point a relative to point b
	 * index = how many points are on a line between a and b
	 * 
	 * @param a point a
	 * @param b point b
	 * @param field field of all available points
	 * @return index
	 */
	private int getIndex(Point a, Point b, List<Point> field)
	{
		int index = 0;
		int h = (int) (a.getX()-b.getX());
		int w = (int) (a.getY()-b.getY());
		int gcd = MathUtil.gcd(Math.abs(h), Math.abs(w));
		
		if (1<gcd)
		{								
			h /= gcd;
			w /= gcd;
			
			for (int n = 1; n < gcd; n++) 
			{
				if (field.contains(new Point((int)b.getX()+(h*n),(int)b.getY()+(w*n)))) {
					index++;
				}
			}
		}
		return index;
	}
	
	/**
	 * return the angle between of point b relative to point a
	 * @param a anchor point (laser)
	 * @param b relative point
	 * @return angle of b to a
	 */
	private float getAngle(Point a, Point b)
	{
		return ((float) Math.toDegrees(Math.atan2(a.x - b.x, a.y - b.y))+270f)%360;
	}
}