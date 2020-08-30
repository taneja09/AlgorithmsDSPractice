package LC_Easy;

/**
 *  Time Complexity - O(n)
 *  Reason - only one time traversal
 *
 *  Space Complexity - O(1)
 *  Reason - No extra space required
 */

public class E0053_MaximumSubArray {
	public int maxSubArray(int[] nums) {
		int maxtillNow = nums[0];
		int maxValue = nums[0];
		
		for(int i = 1; i< nums.length; i++){
			maxtillNow = Math.max(nums[i],maxtillNow+nums[i]);
			maxValue = Math.max(maxValue,maxtillNow);
		}
		
		return maxValue;
	}
	public static void main(String[] args) {
		int[] array = {-2,1,-3,4,-1,-1,1,-5,4};
		E0053_MaximumSubArray cl = new E0053_MaximumSubArray();
		System.out.println(cl.maxSubArray(array));
	}
}
