package Interviews.RobinHood;
import java.util.Arrays;

public class Q5_ReplaceMatrix4By4 {
	public void setMatrix(String[][] matrix) {
		int m = matrix.length;
		int n = 0;
		if (m > 0) n = matrix[0].length;
		int matCount = n / 4;
		int[] missing = new int[matCount];
		int[] missingSort = missing.clone();
		Arrays.sort(missingSort);
		
		fillMissingNumbers(matrix, m, n, missing);
		adjustSubMatrix(matrix, m, n, missing);
	}
	
	private void adjustSubMatrix(String[][] matrix, int m, int n, int[] missing){
		String[][] temp = new String[m][n];
		
		for(int i = 0 ; i< m; i++){
			for(int j = 0 ; j< n; j++){
			
			}
		}
		
	}
	
	private void fillMissingNumbers(String[][] matrix, int m, int n, int[] missing){
		int maxsum = 16 * (16+1)/2;
		int missIdx = 0;
		int total = 0;
		int row = 0;
		int col = 0;
		
		for(int j = 0; j<n ; j++){  //col
			for(int i = 0; i<4; i++){  //row
				if(matrix[i][j].equals("?")){
					row = i; col = j;
				}
				else
					total += Integer.parseInt(matrix[i][j]);
			}
			if (j % 4 == 3 ) {
				int miss = maxsum - total;
				matrix[row][col] = Integer.toString(miss);
				missing[missIdx++] = miss;
				total = 0;
				row = 0;
				col = 0;
			}
		}
	}
	public static void main(String[] args) {
		String[][] mat = {{"14","3","10","4","16","10","?","2","?","9","15","11"},
			{"16","7","8","2","1","4","8","3","3","16","7","13"},
			{"?","9","6","5","14","12","7","6","2","10","4","14"},
			{"15","1","13","12","9","15","5","13","1","8","12","6"}};
		
		Q5_ReplaceMatrix4By4 obj = new Q5_ReplaceMatrix4By4();
		obj.setMatrix(mat);
		for(String[] arr : mat)
			System.out.println(Arrays.toString(arr));
	}
}
