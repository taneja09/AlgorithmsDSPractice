package LC_Medium;
/**
 
 Time Complexity - O(m * n)
 Reason - Accessing all elements
 
 Space Complexity - O(1)
 Reason - No extra space
*/

import java.util.Arrays;

public class M0073_SetMatrixZeros {
	
	public void setZeroes(int[][] matrix) {
		Boolean isCol = false;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) isCol = true;
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) { //set first row and column index as a flag
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
		}
		
		for (int l = 1; l < matrix.length; l++) {
			for(int m = 1; m< matrix[0].length; m++) {
				if (matrix[l][0] == 0 || matrix[0][m] == 0) matrix[l][m] = 0;
			}
		}
		
		// See if the first row needs to be set to zero as well
		if (matrix[0][0] == 0) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[0][j] = 0;
			}
		}
		
		// See if the first column needs to be set to zero as well
		if (isCol) {
			for (int i = 0; i < matrix.length; i++) {
				matrix[i][0] = 0;
			}
		}
	}
	
	/*********** Brute Force *********** Can't use this solution because array can contain Integer.Max_Value or MinValue chosen ******/
	
	public void setZeroesBrute(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					int x = 0;
					int y = 0;
					while (x < matrix[0].length) {
						matrix[i][x] = matrix[i][x] == 0 ? 0 : Integer.MAX_VALUE;
						x++;
					}
					while (y < matrix.length) {
						matrix[y][j] = matrix[y][j] == 0 ? 0 : Integer.MAX_VALUE;
						y++;
					}
				}
			}
		}
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == Integer.MAX_VALUE) {
					matrix[i][j] = 0;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] array = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
		M0073_SetMatrixZeros cl = new M0073_SetMatrixZeros();
		cl.setZeroes(array);
		for(int[] x : array)
			System.out.println(Arrays.toString(x));
	}
}
