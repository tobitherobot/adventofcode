package adventofcode.util;

public class Point3D 
{
	public int x;
	public int y;
	public int z;
	
	public Point3D(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void add(Point3D p)
	{
		x += p.x;
		y += p.y;
		z += p.z;
	}
	
	public void addX(int n)
	{
		x += n;
	}
	
	public void addY(int n)
	{
		y += n;
	}
	
	public void addZ(int n)
	{
		z += n;
	}
	
	public boolean equals(Point3D p)
	{
		return x==p.x && y==p.y && z==p.z;
	}
	
	public String toString()
	{
		return "<x="+x+", y="+y+", z="+z+">";
	}
}
