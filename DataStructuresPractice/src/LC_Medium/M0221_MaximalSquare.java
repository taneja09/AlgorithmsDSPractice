package LC_Medium;
/*
https://www.youtube.com/watch?v=NYeVhmWsWec
fill left and top edges with exactly same as given matrix
try to fill at the bottom end 1 and count that as square

 */
public class M0221_MaximalSquare {
	public int maximalSquare(char[][] matrix) {
		if(matrix.length == 0 || matrix[0].length == 0) return 0;
		int[][] sizes = new int[matrix.length][matrix[0].length];
		int max = 0;
		for(int i = 0; i< matrix.length; i++){
			for(int j = 0; j< matrix[0].length; j++){
				if( i== 0 || j == 0) sizes[i][j] = Character.getNumericValue(matrix[i][j]);
				else if(matrix[i][j] == '1'){
					sizes[i][j] = Math.min(Math.min(sizes[i-1][j],  //up element
													sizes[i][j-1]),   //left element
																	sizes[i-1][j-1]) + 1; // diagonal element
				}
				if(sizes[i][j] > max) max = sizes[i][j];
			}
		}
		
		return max * max;
	}
	public static void main(String[] args) {
		char[][] arr = {{'1','1','1','1','1'},{'1','1','1','1','1'},{'1','1','1','1','1'},{'1','1','1','1','1'}};
		M0221_MaximalSquare cl = new M0221_MaximalSquare();
		System.out.println(cl.maximalSquare(arr));
	}
}
