package LC_Medium;
/*
Due to negative number and property of multiply, we need max and min ends at i-1 in case negative number at i causes them swap.
Therefore, we maintain two local optimal variables, update them in each iteration and the global maximum as well.
 */

/**
 Time Complexity -  O(n)
 Reason - Linear scan
 
 Space Complexity - O(1)
 Reason -  No extra space usage
 */
public class M0152_MaximumProductSubarray {
	public int maxProduct(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		int min = 1;
		int max = 1;
		int maxGlobal = Integer.MIN_VALUE;
		
		for(int i = 0; i<nums.length; i++){
			int minLocal = nums[i] * min;
			int maxLocal = nums[i] * max;
			min = Math.min(nums[i], Math.min(minLocal, maxLocal));
			max = Math.max(nums[i], Math.max(minLocal, maxLocal));
			maxGlobal = Math.max(max,maxGlobal);
		}
		return maxGlobal;
	}
	public static void main(String[] args) {
		int[] array = {2,3,-2,4};
		M0152_MaximumProductSubarray cl = new M0152_MaximumProductSubarray();
		System.out.println(cl.maxProduct(array));
	}
}
