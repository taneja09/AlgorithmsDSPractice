package Algorithms.DynamicProgramming;

/*	i downwards		j ==>
	[0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1]
	[0, 0, 1, 2, 2, 3, 3, 3, 3, 3, 3]
	[0, 0, 1, 5, 5, 6, 7, 7, 8, 8, 8]
	[0, 0, 1, 5, 9, 9, 10, 14, 14, 15, 16]
	
 */

public class KnapSackOand1 {
	//******************** 2D Matrix Idea ***********************//
	public int knapsackSolve(int[] w, int[] v, int W) {
		int[][] store = new int[w.length][W + 1];
		store[0][0] = 0;   //populate first index as 0
		
		for (int j = 1; j < store[0].length; j++) {  //fill first row to initialize
			if (w[0] <= j)
				store[0][j] = v[0];  // if given weight W[0] can be accommodated by columns then fill  columns with value of that weight
			else store[0][j] = 0;
		}
		// w[0] = 2 and weight j (0,1,2,3, ..... W)  will vary from 0 to 10
		//				  j[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
		// with w[0] as 2 [0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1] here 0 and 1 index can't accommodate item of Weight 2 which is w[0] and hence value is 0
		
		for (int i = 1; i < store.length; i++) {
			for (int j = 1; j < store[0].length; j++) {
				// if a weight is more than the allowed weight j, that weight cannot be picked.
				if (w[i] > j) store[i][j] = store[i - 1][j];
				else
					store[i][j] = Math.max(store[i - 1][j], v[i] + store[i - 1][j - w[i]]);  // either exclude or include the current weight and get the max value
			}
		}
		return store[store.length-1][W];
	}
	
	//******************** ID  Idea ***********************//
	
	public int IDknapsackSolve(int[] w, int[] v, int W) {
		int[] dp = new int[W+1];
		for(int i = 0; i< w.length ; i++){
			int[] newdp = new int[W+1];
			for(int j = 0; j<= W ; j++ ){
				if(w[i] > j) newdp[j] = dp[j];
				else newdp[j] = Math.max(dp[j], dp[j-w[i]] + v[i]);
			}
			dp = newdp;
		}
		return dp[W];
	}
	
	
	
	public static void main(String[] args) {
		int[] w = {2 ,3, 3,4, 6};
		int[] v = {1,2,5,9,4};
		int W = 10;
		
		KnapSackOand1 cl = new KnapSackOand1();
		System.out.println(cl.knapsackSolve(w,v,W));
		System.out.println(cl.IDknapsackSolve(w,v,W));
	}
}
