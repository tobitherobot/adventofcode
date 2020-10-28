package adventofcode;

import java.awt.Point;
import java.util.List;

public class InputPrinter 
{
	/**
	 * convert a boolean matrix to a string
	 * @param matrix matrix
	 * @param t char for true
	 * @param f char for false
	 * @return string representation of matrix
	 */
	public static String print(boolean[][] matrix, char t, char f)
	{
		StringBuilder sb = new StringBuilder("\n");
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				
				if (matrix[i][j]) sb.append(t);
				else sb.append(f);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static String print(boolean[][] matrix)
	{
		return print(matrix, '#', '.');
	}
	
	/**
	 * convert a Boolean matrix to string
	 * @param matrix matrix
	 * @param t char for true
	 * @param f char for false
	 * @param n char for null
	 * @return string representation of matrix
	 */
	public static String print(Boolean[][] matrix, char t, char f, char n)
	{
		StringBuilder sb = new StringBuilder("\n");
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				
				if (matrix[i][j]==null) sb.append(n);
				else if (matrix[i][j]) sb.append(t);
				else sb.append(f);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static String print(Boolean[][] matrix)
	{
		return print(matrix, '#', '.', ' ');
	}
	
	/**
	 * convert a list of points to string
	 * @param points list of points
	 * @param t char for if point exists
	 * @param f char for if no point exists
	 * @return string representation of points
	 */
	public static String print(List<Point> points, char t, char f)
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
		return print(matrix, t, f);
	}
	
	public static String print(List<Point> points)
	{
		return print(points, '#', '.');
	}
}