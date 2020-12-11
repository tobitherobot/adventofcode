package adventofcode.y20;

import adventofcode.Day;
import adventofcode.InputReader;

public class D11 implements Day
{
	public Object star1() 
	{
		Boolean[][] matrix = new InputReader(11,20).readBooleanMatrix('#', 'L', '.');
		Boolean[][] applied = applyRulesOld(matrix);
		int count = 0;
		
		while (!compare(matrix, applied))
		{
			matrix = applied.clone();
			applied = applyRulesOld(matrix);
		}
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				
				if (matrix[i][j]!=null && matrix[i][j]) count++;
			}
		}
		return count;
	}

	public Object star2() 
	{
		Boolean[][] matrix = new InputReader(11,20).readBooleanMatrix('#', 'L', '.');
		Boolean[][] applied = applyRulesNew(matrix);
		int count = 0;
		
		
		while (!compare(matrix, applied))
		{
			matrix = applied.clone();
			applied = applyRulesNew(matrix);
		}
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				
				if (matrix[i][j]!=null && matrix[i][j]) count++;
			}
		}
		return count;
	}
	
	/**
	 * apply seating rules from star 1
	 * @param matrix initial matrix
	 * @return new matrix with applied rules
	 */
	private Boolean[][] applyRulesOld(Boolean[][] matrix)
	{
		Boolean[][] applied = new Boolean[matrix.length][matrix[0].length];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				
				if (matrix[i][j]==null) continue;
				else if (!matrix[i][j] && countAdjacents(matrix, i, j)==0) {
					applied[i][j] = true;
				}
				else if (matrix[i][j] && 4<=countAdjacents(matrix, i, j)) {
					applied[i][j] = false;
				}
				else applied[i][j] = matrix[i][j];
			}
		}
		return applied;
	}
	
	/**
	 * apply seating rules from star 2
	 * @param matrix initial seats
	 * @return new matrix with applied rules
	 */
	private Boolean[][] applyRulesNew(Boolean[][] matrix)
	{
		Boolean[][] applied = new Boolean[matrix.length][matrix[0].length];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				
				if (matrix[i][j]==null) continue;
				else if (!matrix[i][j] && countDiagonals(matrix, i, j)==0) {
					applied[i][j] = true;
				}
				else if (matrix[i][j] && 5<=countDiagonals(matrix, i, j)) {
					applied[i][j] = false;
				}
				else applied[i][j] = matrix[i][j];
			}
		}
		return applied;
	}
	
	/**
	 * count all adjacent occupied seats
	 * @param matrix Boolean seat matrix
	 * @param y y-pos
	 * @param x x-pos
	 * @return adjacent occupied seats
	 */
	private int countAdjacents(Boolean[][] matrix, int y, int x)
	{
		int count = 0;
		int[] off = new int[] {-1, 0, 1};
		
		for (int offX : off) {
			for (int offY : off) {
				try {
					if (offX==0 && offY==0) continue;
					else if (matrix[y+offY][x+offX]) count++;
				}
				catch (Exception e) {}
			}
		}
		return count;
	}
	
	/**
	 * count all visible occupied seats
	 * @param matrix Boolean seat matrix
	 * @param y y-pos
	 * @param x x-pos
	 * @return visible occupied seats
	 */
	private int countDiagonals(Boolean[][] matrix, int y, int x)
	{
		int count = 0;
		int[] off = new int[] {-1, 0, 1};
		
		for (int offX : off) {
			for (int offY : off) {
				
				try 
				{
					if (offX==0 && offY==0) continue;
					int py = y;
					int px = x;
					
					while (true) 
					{
						py += offY;
						px += offX;
						if (matrix[py][px]!=null) {
							if (matrix[py][px]) count++;
							break;
						}
					}
				}
				catch (Exception e) {}
			}
		}
		return count;
	}
	
	/**
	 * compare two matrices if they're identical
	 * @param mtx1
	 * @param mtx2
	 * @return are identical?
	 */
	private boolean compare(Boolean[][] mtx1, Boolean[][] mtx2)
	{
		for (int i = 0; i < mtx1.length; i++) {
			for (int j = 0; j < mtx1[i].length; j++) {
				
				if (mtx1[i][j]!=mtx2[i][j]) return false;
			}
		}
		return true;
	}
}
