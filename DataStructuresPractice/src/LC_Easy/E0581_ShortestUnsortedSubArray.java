package LC_Easy;

import java.util.Arrays;

public class E0581_ShortestUnsortedSubArray {
	/************* normal brute force - TC = O(n^2)***********************/
	public int findUnsortedSubarray(int[] nums) {
		int l = nums.length, r = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] < nums[i]) {
					r = Math.max(r,j);
					l = Math.min(l,i);
				}
			}
			
		}
		return r-l < 0 ? 0 : r-l + 1;
	}
	/************* Sorting - Find the unmatch element between sorted and non-sorted ***********************/
	public int findUnsortedSubarraySorting(int[] nums) {
		int[] snums = nums.clone();
		Arrays.sort(snums);
		int start = snums.length, end = 0;
		for (int i = 0; i < snums.length; i++) {
			if (snums[i] != nums[i]) {
				start = Math.min(start, i);
				end = Math.max(end, i);
			}
		}
		return (end - start >= 0 ? end - start + 1 : 0);
	}
	
	/************* Most Efficient Solution TC - O(n) SC = O(1) ***********************/
	/*
	The idea of the  solution is to first find an index from left that the corresponding array value breaks the ascending order,
	another index from right that breaks the descending order.
	Then in the subarray between these two indices, find the min and max values. Back to the array,
	find the position from left that large than the min value, and the position from right that smaller than the max value,
	the distance of these two positions will be the answer.
	Here is my implementation, though messy, just for reference.
	
	[2, 6, 4, 8, 10, 9, 15]
	max = Math.max(max, nums[i]);  will stop where nums[i] = 6 because next is 4
	min = Math.min(min, A[n-1-i]); will stop where nums[n-1-i) = 9 because next is 10
	 */
	public int findUnsortedSubarrayEfficient(int[] nums) {
		int n = nums.length, beg = -1, end = -2, min = nums[n-1], max = nums[0];
		for (int i=1;i<n;i++) {
			max = Math.max(max, nums[i]);
			min = Math.min(min, nums[n-1-i]);
			if (nums[i] < max) end = i;  //this will point to end index = 4
			if (nums[n-1-i] > min) beg = n-1-i; //this will point to index = 1
		}
		
		return end - beg + 1;
	}
	
	
	public static void main(String[] args) {
		int[] arr = {2, 6, 4, 8, 10, 9, 15};
		E0581_ShortestUnsortedSubArray cl = new E0581_ShortestUnsortedSubArray();
		System.out.println(cl.findUnsortedSubarrayEfficient(arr));
	}
}
