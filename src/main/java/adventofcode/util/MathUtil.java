package adventofcode.util;

public class MathUtil 
{
	/**
	 * generate the next permutation of a string
	 * @param sb stringbuilder
	 * @return was successful
	 */
	public static boolean nextPermutation(StringBuilder sb) 
	{
		int l = sb.length();
		int i = l - 1;

		while (i > 0) 
		{
			if (sb.charAt(i-1) < sb.charAt(i)) break;
	    	i--;
	  	}
	  	if (i <= 0) return false;

	  	int j = l-1;
	  	
	  	while (j >= i) 
	  	{
	    	if (sb.charAt(i-1) < sb.charAt(j)) break;
	    	j--;
	  	}
	  	swap(i-1, j, sb);

	  	l--;
	  	while (i < l) 
	  	{
	    	swap(i, l, sb);
	    	l--;
	    	i++;
	  	}
	  	return true;
	}

	private static void swap(int x, int y, StringBuilder sb) 
	{
	  	char tmp = sb.charAt(x);
	  	sb.setCharAt(x, sb.charAt(y));
	  	sb.setCharAt(y, tmp);
	}
}
