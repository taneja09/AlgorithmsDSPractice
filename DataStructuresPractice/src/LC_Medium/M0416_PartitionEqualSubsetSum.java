package LC_Medium;
// https://www.youtube.com/watch?v=UmMh7xp07kY
/*
Sum has to be even for an array to be partitioned into equal parts.
This can be written as dp[i][j] = (dp[i-1][j] || dp[i-1][j-nums[i-1]]);
the current index i,j is true if one of the conditions match

If we exclude the current number from the set, which is we just use the value from the cell above which is dp[i-1][j]
because j represents sum and if from prev row, we got the sum
that means even if we exclude current number . we will still have sum which we need. OR

If we include the current value in the previous calculated set which is dp[i-1][j-nums[i-1]].
Current value will be nums[i-1] because "i" starts from 1 but the array is a 0-based index.

 */
public class M0416_PartitionEqualSubsetSum {
	public boolean canPartition(int[] nums) {
		int sum = 0;
		int count = 0;
		
		//Chcek sum should be even , get new target = sum/2 and initialize boolean dp array
		for(int num : nums) sum += num;
		if(sum%2 != 0) return false;
		int target = sum/2;
		boolean[][] dp = new boolean[nums.length+1][target + 1];
		
		//fill in values
		dp[0][0] = true;  //because 0 can be created with 0
		for (int i = 1; i <= nums.length; i++) dp[i][0] = true;  //fill first column as True because 0 can be created by not taking any value;
		for (int i = 1; i <= target; i++) dp[0][i] = false;  //because 0 can't form all other sums
		
		for(int i = 1; i < dp.length; i++) {
			for(int j = 1; j < dp[0].length; j++) {
				dp[i][j] = dp[i-1][j];
				if (j >= nums[i-1]) {
					/*
					* Exclude the number. In this case, we will see if we can get ‘s’ from the subset excluding this number: dp[i-1][s]
					* Include the number if its value is not more than ‘s’. In this case, while we include the number at i
					we will see if we can find a subset to get the remaining sum: dp[i-1][s-num[i]]  where remaining sum is S-nums[i]
					* because if the remaining sum also return T , we can mark it true.
					*/
					dp[i][j] = (dp[i][j] || dp[i-1][j-nums[i-1]]);
				}
			}
		}
		return dp[nums.length-1][target];
		
	}
	public static void main(String[] args) {
		M0416_PartitionEqualSubsetSum cl = new M0416_PartitionEqualSubsetSum();
		int[] arr = {1,2,5};
		System.out.println(cl.canPartition(arr));
	}
}
