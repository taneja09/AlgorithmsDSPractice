package LC_Medium;
/**
 
 Time Complexity - O(m*n)
 Reason - traversal of all elements
 
 Space Complexity - O(m*n)
 Reason - dp array
*/


public class M0062_UniquePaths {
	/*=============== Combinatorial Solution with Mathematics =================*/
	public int uniquePaths(int m, int n) {
		if(m == 1 || n == 1)
			return 1;
		m--;n--;
		if(m < n) {              // Swap, so that m is the bigger number because C(m+n,m) = C(m+n,n) due to C(n,k) = (n)!/(n-k)!n!
			m = m + n;n = m - n;m = m - n;
		}
		long res = 1;
		int j = 1;
		for(int i = m+1; i <= m+n; i++, j++){       // Instead of taking factorial, keep on multiply & divide
			res *= i;
			res /= j;
		}
		return (int)res;
	}
	
	/*=============== Recursive Solution TC = 2^m+n =================*/
	
	//Selecting each cell is another way of doing it i.e. its one of the combination of reaching end of the matrix
	public int uniquePathsRecursive(int m, int n) {
		return uniquePathsRecursion(m-1,n-1);
	}
	
	private int uniquePathsRecursion(int m, int n) {
		if(m<0 || n<0) return 0;
		if( m == 0 || n == 0) return 1;
		else return uniquePathsRecursion(m-1, n) + uniquePathsRecursion(m,n-1);
	}
	
	
	/*=============== Memoization - Top to Bottom === TC: O(m*n), SC: O(m*n) =================*/
	public int uniquePathsTopDown(int m, int n) {
		int[][] dp = new int[m][n];
		return uniquePathsTopToDown(m-1,n-1, dp);
	}
	private int uniquePathsTopToDown(int m, int n, int[][] dp){
		if(m<0 || n<0) return 0;
		else if(dp[m][n] > 0) return dp[m][n];
		else if(m == 0 || n == 0) return 1;
		else dp[m][n] = uniquePathsTopToDown(m-1,n,dp) + uniquePathsTopToDown(m,n-1,dp);
		
		return dp[m][n];
	}
	
	/*=============== Tabulation - Bottom Up Approach  TC: O(m*n), SC: O(m*n)  =================*/
	/*
	core logic is that, except the first row and first column (for which the number of paths is 1),
	the number of paths to get to any other block in grid is the sum of paths from its adjacent columns (one up and one left)
	For example - when n = 4 and m = 6, table will look like this:
	1	1	1	1	1	1
    1	2	3	4	5	6
    1	3	6	10	15	21
    1	4	10	20	35	56
	*/
	public int uniquePathsBottomUp(int m, int n) {
		int[][] dp = new int[m][n];
		for(int i = 0; i<m; i++){
			for(int j =0; j<n;j++){
				if(i==0 || j== 0) dp[i][j] =1;
				else dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		}
		return dp[m-1][n-1];
	}
	
	/*=============== Main Method =================*/
	
	public static void main(String[] args) {
		int m = 3;
		int n = 2;
		M0062_UniquePaths cl = new M0062_UniquePaths();
		System.out.println(cl.uniquePaths(m,n));
		System.out.println(cl.uniquePathsRecursive(m,n));
		System.out.println(cl.uniquePathsTopDown(m,n));
		System.out.println(cl.uniquePathsBottomUp(m,n));
	}
}

/**                       //"Combinatorial Solution "//
 * For mxn grid, robot has to move exactly m-1 steps down and n-1 steps right and these can be done in any order
 * given in question, 3x7 matrix, robot needs to take 2+6 = 8 steps with 2 down and 6 right in any order.
 *
 * Denote down as 'D' and right as 'R', following is one of the path :-
 * D R R R D R R R  , If we can find all permutation of this , then that will be the total number of paths
 *
 * Total permutations = (m+n)! / (m! * n!)
 *
 * https://betterexplained.com/articles/easy-permutations-and-combinations/
 */
 
