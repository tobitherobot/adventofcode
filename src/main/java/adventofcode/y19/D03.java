package adventofcode.y19;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import adventofcode.Day;
import adventofcode.InputReader;

public class D03 implements Day
{
	public Object star1() 
	{
		List<String[]> input = new InputReader(3, 19).readLinesSplit(",");
		List<Point> wireA = getWire(input.get(0));
		List<Point> wireB = getWire(input.get(1));
		long res = Integer.MAX_VALUE;
			
		for (int i = 0; i < wireA.size()-1; i++) {
			for (int j = 0; j < wireB.size()-1; j++) {
				
				Point n = getIntersection(wireA.get(i), wireA.get(i+1), wireB.get(j), wireB.get(j+1));
				
				if (n!=null)
				{
					res = Math.min(res, getDistance(n, new Point(0, 0)));
				}
			}
		}		
		return res;
	}

	public Object star2() 
	{
		List<String[]> input = new InputReader(3, 19).readLinesSplit(",");
		List<Point> wireA = getWire(input.get(0));
		List<Point> wireB = getWire(input.get(1));
		Set<Point> cross = new HashSet<>();
			
		for (int i = 0; i < wireA.size()-1; i++) {
			for (int j = 0; j < wireB.size()-1; j++) {
				
				Point n = getIntersection(wireA.get(i), wireA.get(i+1), wireB.get(j), wireB.get(j+1));
				
				if (n!=null) cross.add(n);
			}
		}
		
		Map<Point, Integer> stepsA = countSteps(wireA, cross);
		Map<Point, Integer> stepsB = countSteps(wireB, cross);
		long res = Long.MAX_VALUE;
		
		for (Point cr : cross) {
			res = Math.min(res, stepsA.get(cr)+stepsB.get(cr));
		}
		return res;
	}
	
	/**
	 * returns if p is on the line ab
	 * @param a line start point
	 * @param b line end point
	 * @param p single point
	 * @return is point p on line ab
	 */
	private boolean isOnLine(Point a, Point b, Point p)
	{
		if (a.x==b.x) return p.x==a.x && (a.y<=p.y && p.y<=b.y || b.y<=p.y && p.y<=a.y);
		else return p.y==a.y && (a.x<=p.x && p.x<=b.x || b.x<=p.x && p.x<=a.x);
	}
	
	/**
	 * returns the manhattan distance between points a and b
	 * @param a first point
	 * @param b second point
	 * @return manhattan distance
	 */
	public int getDistance(Point a, Point b)
	{
		return Math.abs(a.x-b.x) + Math.abs(a.y-b.y);
	}
	
	/**
	 * returns the intersection of the lines ab and cd
	 * @param A first line start point
	 * @param B first line end point
	 * @param C second line start point
	 * @param D second line end point
	 * @return point of intersection, null if they don't cross
	 */
	private Point getIntersection(Point A, Point B, Point C, Point D)
	{
        int a1 = B.y - A.y; 
        int b1 = A.x - B.x; 
        int c1 = a1*(A.x) + b1*(A.y); 
       
        int a2 = D.y - C.y; 
        int b2 = C.x - D.x; 
        int c2 = a2*(C.x)+ b2*(C.y); 
       
        int det = a1*b2 - a2*b1; 
       
        if (det!=0)
        {        	
        	int x = (b2*c1 - b1*c2)/det; 
        	int y = (a1*c2 - a2*c1)/det; 
            Point n = new Point(x, y);
            
            if (isOnLine(A, B, n)&&isOnLine(C, D, n)) return n;
        } 
        return null;
	}
	
	/**
	 * returns a map of points with each count of steps for that wire
	 * @param wire path that will be checked
	 * @param cross list of points, that lie on the wire 
	 * @return map of points with steps taken
	 */
	public Map<Point, Integer> countSteps(List<Point> wire, Set<Point> cross)
	{
		Map<Point, Integer> steps = new HashMap<>();
		int count = 0;
		
		for (int i = 0; i < wire.size()-1; i++) {
			for (Point cr : cross) {
				
				if (isOnLine(wire.get(i), wire.get(i+1), cr)) 
				{
					steps.put(cr, count+getDistance(wire.get(i), cr));
				}
			}
			count += getDistance(wire.get(i), wire.get(i+1));
		}
		return steps;
	}
	
	/**
	 * returns a list of all points on path
	 * @param input
	 * @return list of points
	 */
	private List<Point> getWire(String[] input)
	{
		List<Point> wire = new ArrayList<>();
		Point p = new Point(0, 0);
		wire.add(new Point(p));
		
		for (String s : input) 
		{
			int value = Integer.parseInt(s.substring(1));

			switch (s.charAt(0))
			{
				case 'U':
					p.y += value;
					break;
				case 'R':
					p.x += value;
					break;
				case 'D':
					p.y -= value;
					break;
				case 'L':
					p.x -= value;
					break;
			}
			wire.add(new Point(p));
		}
		return wire;
	}
}