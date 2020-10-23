package adventofcode;

public class InputPrinter 
{
	/**
	 * convert a Boolean matrix to string
	 * @param matrix matrix
	 * @return string representation of matrix
	 */
	public static String print(Boolean[][] matrix)
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				
				if (matrix[i][j]==null) sb.append(' ');
				else if (matrix[i][j]) sb.append('#');
				else sb.append('.');
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}