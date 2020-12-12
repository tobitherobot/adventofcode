package adventofcode;

import java.awt.Point;
import java.util.List;
import java.util.Map;

public class InputPrinter 
{
	private String s = null;
	
	/**
	 * print parsed string
	 */
	public void print()
	{
		System.out.println(s);
	}
	
	/**
	 * get parsed string
	 * @return s
	 */
	public String get()
	{
		return s;
	}
	
	/**
	 * parse a char matrix
	 * @param matrix char matrix
	 */
	public InputPrinter parse(char[][] matrix)
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < matrix.length; i++) {
			sb.append(new String(matrix[i]) + "\n");
		}
		
		s = sb.toString();
		return this;
	}
	
	/**
	 * parse a boolean matrix
	 * @param matrix matrix
	 * @param t char for true
	 * @param f char for false
	 */
	public InputPrinter parse(boolean[][] matrix, char t, char f)
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				
				if (matrix[i][j]) sb.append(t);
				else sb.append(f);
			}
			sb.append("\n");
		}
		
		s = sb.toString();
		return this;
	}
	
	public InputPrinter parse(boolean[][] matrix)
	{
		return parse(matrix, '#', '.');
	}
	
	/**
	 * parse a Boolean matrix
	 * @param matrix matrix
	 * @param t char for true
	 * @param f char for false
	 * @param n char for null
	 */
	public InputPrinter parse(Boolean[][] matrix, char t, char f, char n)
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				
				if (matrix[i][j]==null) sb.append(n);
				else if (matrix[i][j]) sb.append(t);
				else sb.append(f);
			}
			sb.append("\n");
		}
		
		s = sb.toString();
		return this;
	}
	
	public InputPrinter parse(Boolean[][] matrix)
	{
		return parse(matrix, '#', '.', ' ');
	}
	
	/**
	 *parse a list of points
	 * @param points list of points
	 * @param t char for if point exists
	 * @param f char for if no point exists
	 */
	public InputPrinter parse(List<Point> points, char t, char f)
	{
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		
		for (Point p : points) 
		{
			minX = Math.min(minX, p.x);
			minY = Math.min(minY, p.y);
			maxX = Math.max(maxX, p.x);
			maxY = Math.max(maxY, p.y);
		}
		
		boolean[][] matrix = new boolean[maxY-minY+1][maxX-minX+1];
		int offX = -minX;
		int offY = -minY;
		
		for (Point p : points) {
			matrix[p.y+offY][p.x+offX] = true;
		}
		return parse(matrix, t, f);
	}
	
	public InputPrinter parse(List<Point> points)
	{
		return parse(points, '#', '.');
	}
	
	/**
	 * parse a point-to-char map
	 * @param map tile map
	 */
	public InputPrinter print(Map<Point, Character> map)
	{
		int maxX = map.entrySet().stream().max((e1, e2) -> e1.getKey().x > e2.getKey().x ? 1 : -1).get().getKey().x;
		int maxY = map.entrySet().stream().max((e1, e2) -> e1.getKey().y > e2.getKey().y ? 1 : -1).get().getKey().y;
		char[][] canvas = new char[maxY+1][maxX+1];
		
		for (Point p : map.keySet())
		{
			canvas[p.y][p.x] = map.get(p);
		}
		
		return parse(canvas);
	}
}