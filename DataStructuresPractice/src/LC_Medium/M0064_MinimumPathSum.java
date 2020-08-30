package LC_Medium;
/**
 
 Time Complexity - O(m*n)
 Reason - traversal of all elements
 
 Space Complexity - O(m*n)
 Reason - dp array
*/

/**
 * https://leetcode.com/problems/minimum-path-sum/discuss/344980/Java.-Details-from-Recursion-to-DP.
 * grid[0][0] as the basic case. This is when we jump out of recursion
 * grid[row - 1][col - 1] would be the starting point
 */
public class M0064_MinimumPathSum {
	
	/****** TLE Recursion Solution  2^(m+n)**********************/
	public int minPathSumRecursion(int[][] grid) {
		int len = grid.length;
		int wid = grid[0].length;
		return getHelperMethod(grid,len-1,wid-1);
	}

	private int getHelperMethod(int[][] grid, int row, int col){
		if(row == 0 && col == 0) return grid[row][col]; // this is the exit of the recursion
		if(row == 0) return grid[row][col] + getHelperMethod(grid, row, col - 1); /** when we reach the first row, we could only move horizontally.*/
		if(col == 0) return grid[row][col] + getHelperMethod(grid, row-1, col ); /** when we reach the first row, we could only move horizontally.*/
		return grid[row][col] + Math.min(getHelperMethod(grid, row - 1, col),getHelperMethod(grid, row, col-1)); /** we want the min sum path so we pick the cell with the less value */
 }

	
	
	/****** Top Down  TC = O(m*n) SC = O(m*n) **********************/
	public int minPathSumTD(int[][] grid) {
		int[][] dp = new int[grid.length][grid[0].length];
		return minPathSumTD(grid.length-1,grid[0].length-1, grid, dp);
	}
	
	private int minPathSumTD(int m, int n, int[][] grid, int[][] dp){
		if(m == 0 && n ==0) return grid[m][n];
		if(dp[m][n] > 0) return dp[m][n];
		if(m == 0 ) return grid[m][n] + minPathSumTD(m,n-1,grid,dp);
		if(n == 0 ) return grid[m][n] + minPathSumTD(m-1,n,grid,dp);
		else dp[m][n] = grid[m][n] +  Math.min(minPathSumTD(m,n-1,grid,dp), minPathSumTD(m-1,n,grid,dp));
		
		return dp[m][n];
	}
	
	/****** Bottom Up DP  TC = O(m*n) SC = O(m*n)  **********************/
	public static int minPathSumBU(int[][] grid) {

		int height = grid.length;
		int width = grid[0].length;
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if(row == 0 && col == 0) grid[row][col] = grid[row][col];
				else if(row == 0 && col != 0) grid[row][col] = grid[row][col] + grid[row][col - 1];
				else if(col == 0 && row != 0) grid[row][col] = grid[row][col] + grid[row - 1][col];
				else grid[row][col] = grid[row][col] + Math.min(grid[row - 1][col], grid[row][col - 1]);
			}
		}
		return grid[height - 1][width - 1];
	}
	
	/****** Bottom Up DP  without DP array   TC = O(m*n) SC = O(1)  **********************/
	public int minPathSum(int[][] grid) {
		int rows = grid.length, cols = grid[0].length;
	
		// Initialize first col with curr value + prev cell val
		for(int row = 1; row < rows; row++) {
			grid[row][0] += grid[row-1][0];
		}
		// Initialize first row with curr value + prev cell val
		for(int col = 1; col < cols; col++) {
			grid[0][col] += grid[0][col-1];
		}
	
		for(int i = 1; i < rows; i++) {
			for(int j = 1; j < cols; j++) {
				grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
			}
		}
	
		return grid[rows-1][cols-1];
	}
	
	public static void main(String[] args) {
		M0064_MinimumPathSum cl = new M0064_MinimumPathSum();
		int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
		int result = cl.minPathSum(grid);
		System.out.println(result);
	}
}
