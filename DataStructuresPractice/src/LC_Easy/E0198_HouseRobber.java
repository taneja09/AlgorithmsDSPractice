package LC_Easy;

/**
 Time Complexity - O(n)
 Reason - One pass
 
 Space Complexity - O(1)
 Reason - constant space
 */

/*
*https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.
*
* robbery of current house + loot from houses before the previous
loot from the previous house robbery and any loot captured before that
rob(i) = Math.max( rob(i - 2) + currentHouseValue, rob(i - 1) )
*/
public class E0198_HouseRobber {
	
	//***************** Recursive approach ***********************//
	
	/**
	 public int rob(int[] nums) {
	 return rob(nums, nums.length - 1);
	 }
	 
	 private int rob(int[] nums, int i) {
	 if (i < 0) {
	 return 0;
	 }
	 return Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
	 }
	 
	 
	 **/
	
	//***************** Recursive + memo (top-down)***********************//
	
/*
int[] memo;
	public int rob(int[] nums) {
	    memo = new int[nums.length + 1];
	    Arrays.fill(memo, -1);
	    return rob(nums, nums.length - 1);
	}
	
	private int rob(int[] nums, int i) {
	    if (i < 0) {
	        return 0;
	    }
	    if (memo[i] >= 0) {
	        return memo[i];
	    }
	    int result = Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
	    memo[i] = result;
	    return result;
	}
	*/
	
	//***************** Iterative - bottom-up approach***********************//
	
	/*
	public int rob(int[] nums) {
		if (nums.length == 0) return 0;
		int[] memo = new int[nums.length + 1];
		memo[0] = 0;
		memo[1] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int val = nums[i];
			memo[i+1] = Math.max(memo[i], memo[i-1] + val);
		}
		return memo[nums.length];
	}
	
	*/
	
	//***************** Iterative - 2 variables approach***********************//
				//	  i-2 , i-1 ,  i
	/* the order is: prev2, prev1, num  */
	public int rob(int[] nums) {
		if (nums.length == 0) return 0;
		int prev1 = 0;
		int prev2 = 0;
		
		for (int num : nums) {
			//System.out.println("incoming " + prev2 + " " + prev1 + " " + num);
			int tmp = prev1;
			prev1 = Math.max(prev2 + num, prev1);
			prev2 = tmp;
			//System.out.println("outgoing " +prev2 + " " + prev1 + " " + num);
		}
		
		return prev1;
	}
	
	
	public static void main(String[] args) {
		int[] houses = {1,2,3,1};
		E0198_HouseRobber cl = new E0198_HouseRobber();
		int res = cl.rob(houses);
		System.out.println(res);
	}
	
}
