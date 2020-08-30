package LC_Medium;

public class M0494_TargetSum {
	
	/******************************  Recursion solution TC = 0(2^n) SC = O(2^n) depth of recursion ************************/
	int count = 0;
	public int findTargetSumWaysRecursion(int[] nums, int S) {
		return calculate(nums, 0, 0, S);
	}
	
	private int calculate (int[] nums, int i, int currSum,  int target){
		if (i == nums.length) {
			return currSum == target ? 1: 0;
		}else {
			return calculate(nums, i + 1, currSum + nums[i], target) +
				calculate(nums, i + 1, currSum - nums[i], target);
		}
	}
	/******************************  2D DP Array Solution -  ************************/
	public int findTargetSumWays2DArray(int[] nums, int S) {
		int maxSum = 0;
		for(int num : nums){
			maxSum += num;
		}
		
		// if all elements are negative their sum will surpass -S similarly positive elements will surpass S
		//and hence there can't be any solution which will result in target S
		if(maxSum < -S || maxSum < S) return 0;
		
		//since it's possible for our sum (in our recursive call)
		//to be from -maxSum to maxSum. We will initialize an array with size of 2 times maxSum + 1. Since maximum value is
		//maxSum * 2 (to account for index out of bounds)
		Integer [][] dp = new Integer[nums.length][maxSum*2 +1];
		return helper2DP(nums, 0, S, 0, maxSum ,dp);
	}
	
	private int helper2DP(int[] nums, int pos, int S, int sum, int maxSum, Integer[][] dp){
		if(pos == nums.length){
			return sum == S ? 1: 0;
		}
		
		if(dp[pos][sum + maxSum] == null){  //shifting to positive side -> for sum -4, index = 1(-4+5) & for sum 4, index = 9 (4+5)  because we can't go to negative index
			int positive = helper2DP(nums,pos+1,S,sum+nums[pos], maxSum, dp);
			int negative = helper2DP(nums,pos+1,S,sum-nums[pos], maxSum, dp);
			dp[pos][sum + maxSum] = positive + negative; //let's cache our result
		}
		
		return dp[pos][sum + maxSum];//return cached result
	}
	/******************************  Most Efficient 1D Array  ************************/
	
	public int findTargetSumWays(int[] nums, int S) {
		int sum = 0;
		for (int num : nums) {  //calculate the totalSum keeping all the elements in the array positive
			sum += num;
		}
		if (sum < S || -sum > S) { //If the target sum S is not reachable by the range
			return 0;
		}
		
		/**
		 * if we calculate total sum of all candidate numbers, then the range of possible calculation result will be in
		 * the range [-sum, sum]. So we can define an dp array with size sum * 2 + 1 to calculate number of possible ways
		 * to reach every target value between -sum to sum, and save results to dp array. dp[sum + S] will be out final
		 * result. (because dp[sum] or less represents number of possible ways to reach a number in range [-sum, 0])
		 *
		 * sub-problem: dp[i] represents number of possible ways to reach target i
		 * base case: dp[sum] = 1  //if we add all numbers
		 *
		 * recurrence relation: when doing inner loop iterations, we should create another temp dp array to store temp
		 * target array. Because if we use dp array to store temp results directly, we may have array boundary exception
		 * eg: for input [1,1,1,1,1], we will never reach dp[6] or d[-6]. However, if we use dp[j + nums[i]] to store
		 * temp results, we may proceed dp[5 + 1] += dp[5], which is considered incorrect case
		 * */
		
		
		int[] dp = new int[sum * 2 + 1];
		dp[sum] = 1;  //one way to get 5 which is adding all numbers
		for (int i = 0; i < nums.length; i++) {
			int[] tempTarget = new int[sum * 2 + 1];
			for (int j = 0; j < sum * 2 + 1; j++) {
				if (dp[j] != 0) {    //dp[j] is previous sum we can add or subtract new incoming element to it which is defined is j+nums[i] or j-nums[i]
					//and then we assigne the value to these 2 new sums by current val + old val
					tempTarget[j + nums[i]] += dp[j];
					tempTarget[j - nums[i]] += dp[j];
				}
			}
			dp = tempTarget;
		}
		return dp[sum + S];  //because dp's range starts from -sum --> 0 --> +sum so we need to add sum first, then the total starts from 0, then we add S
	}
	public static void main(String[] args) {
		int[] nums = {1, 1, 1, 1, 1};
		int target = 3;
		M0494_TargetSum cl = new M0494_TargetSum();
		int res = cl.findTargetSumWays(nums,target);
		System.out.println(res);
		
	}
}
