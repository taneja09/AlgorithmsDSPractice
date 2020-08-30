package LC_Medium;
import java.util.Arrays;
/*
Notes: start from the right and check if that is increasing series from right to left
If yes , we already have the biggest number and we can just reverse the whole array and return
Example [9,4,3,2,1] => 94321 already biggest number just reverse and output

If No, [9,3,5,4,1] then find where increasing seq break from right to left , index (1) => at 3
now again start from right and find next val > 3 which is 4
swap 3 and 4 => 9,4,5,3,1
and then reverse the remaining string because its increasing streak from right to left 5,3,1 after reversing 1,3,5 which is smaller

at the end we will have [9,4,1,3,5] which is next greater value
Why reverse = > if we dont reverse and just swap we will have [9,4,5,3,1] > [9,4,1,3,5] and we want next greater so we need to reverse
 */

/**
 
 Time Complexity - O(n)
 Reason - single loop
 
 Space Complexity - O(1)
 Reason -
 */
public class M0031_NextPermutation {
	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length == 0) return;
		int start = nums.length - 2;
		
		while (start >= 0 && nums[start] >= nums[start + 1]) start--;
		if (start >= 0) {
			int end = nums.length - 1;
			while (end >= 0 && (nums[end] <= nums[start])) end--;
			swap(nums, start, end);
		}
		
		reverse(nums, start + 1, nums.length - 1);
	}
	
	private void swap(int[] nums, int start, int end) {
		int temp = nums[start];
		nums[start] = nums[end];
		nums[end] = temp;
		
	}
	
	private void reverse(int[] nums, int start, int end) {
		while (start < end) {
			swap(nums, start, end);
			start++;
			end--;
		}
	}
	
	public static void main(String[] args) {
		int[] nums = {1,2,3};
		M0031_NextPermutation cl = new M0031_NextPermutation();
		cl.nextPermutation(nums);
		System.out.println(Arrays.toString(nums));
	}
}
