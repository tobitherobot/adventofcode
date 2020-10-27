package adventofcode.y19;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import adventofcode.Day;
import adventofcode.FileReader;
import adventofcode.util.MathUtil;

public class D10 implements Day
{
	public Object star1() 
	{
		char[][] matrix = new FileReader(10,19).readCharMatrix();
		Map<String, Integer> counts = new TreeMap<>();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				for (int x = i; x < matrix.length; x++) {
					for (int y = (i==x ? j+1 : 0); y < matrix[x].length; y++) {
						
						if (matrix[i][j]=='#' && matrix[x][y]=='#')
						{
							int h = x-i;
							int w = y-j;
							
							int gcd = MathUtil.gcd(Math.abs(h), Math.abs(w));
							boolean inSight = true;
							System.out.println(h + " " + w + ": " + gcd);
							if (1<gcd)
							{								
								h /= gcd;
								w /= gcd;
								
								for (int n = 1; n < gcd; n++) 
								{
									if (matrix[i+(h*n)][j+(w*n)]=='#') {
										inSight = false;
										break;
									}
								}
							}
							
							if (inSight)
							{
								System.out.println("i c");
								if (!counts.containsKey(i+"/"+j)) {
									counts.put(i+"/"+j, 0);
								}
								counts.put(i+"/"+j, counts.get(i+"/"+j)+1);
								
								if (!counts.containsKey(x+"/"+y)) {
									counts.put(x+"/"+y, 0);
								}
								counts.put(x+"/"+y, counts.get(x+"/"+y)+1);
							}
						}
					}
				}
			}
		}
		System.out.println(counts);
		
		return counts.entrySet()
				.stream()
				.max(Map.Entry.comparingByValue())
				.get()
				.getValue();
	}

	public Object star2() 
	{

		return null;
	}
	
	private List<Asteroid> getField(char[][] matrix)
	{
		List<Asteroid> field = new ArrayList<>();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				
				if (matrix[i][j]=='#') {
					field.add(new Asteroid(new Point(i, j), field));
				}
			}
		}
		return field;
	}
}

class Asteroid
{
	private Point point;
	
	private Asteroid laser;
	
	private int pitch;
	private int dir;
	private int index;
	
	public Asteroid(Point p, List<Asteroid> f)
	{
		point = p;

		laser = null;
		
		pitch =-1;
		dir = -1;
		index = -1;
	}
	
	public void setLaser(Asteroid a)
	{
		laser = a;
	}
	
	private float getPitch(Asteroid a)
	{
		/*
		 *      -2/1
		 * -1/0 -1/1 -1/2
		 *  0/0  
		 */
		
		if (getDirection(a)==0) {
			return (float) Math.abs(getY()-a.getY()) / Math.abs(getX()-a.getX());
		}
		return -1;
	}
	
	/**
	 * returns the direction of itself seen from a
	 * 
	 * @param a asteroid to compare to (0/0)
	 * @return direction on the matrix
	 * 
	 * 0 = x<0/0<=y
	 * 1 = 0<=x/0<y
	 * 2 = 0<x/y<=0
	 * 3 = x<=0/y<0
	 * 
	 */
	private int getDirection(Asteroid a)
	{
		if (point.x<a.getX() && a.getY()<=point.y) {
			return 0;
		}
		else if (a.getX()<=point.x && a.getY()<point.y) {
			return 1;
		}
		else if (a.getX()<point.x && point.y<=a.getY()) {
			return 2;
		}
		else return 3;
	}
	
	private int getIndex(Asteroid a) 
	{
		return index;
	}
	
	public Point getPoint()
	{
		return point;
	}
	
	private int getX()
	{
		return point.x;
	}
	
	private int getY()
	{
		return point.y;
	}
}