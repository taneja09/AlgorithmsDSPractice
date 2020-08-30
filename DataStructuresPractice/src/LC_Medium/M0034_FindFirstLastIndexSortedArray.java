package LC_Medium;

import java.util.Arrays;

public class M0034_FindFirstLastIndexSortedArray {
	
	/**
	 
	 Time Complexity - O(log n)
	 Reason - Binary Search
	 
	 Space Complexity - O(1)
	 Reason - Constant space
	 */
	
	/****************************   Easy **************************************/
	
	public int[] searchRange1(int[] nums, int target) {
		if(nums == null || nums.length == 0){return new int[]{-1,-1};}
		int leftIndex = binarySearch(nums, 0, nums.length-1, -1, true, target);
		int rightIndex = binarySearch(nums, 0, nums.length-1, -1, false, target);
		return new int[]{leftIndex, rightIndex};
	}
	/** We will execute this method for both leftMost and rightMost Indexes**/
	private int binarySearch(int nums[], int start, int end, int lastSeen, boolean left, int target){
		//lastseen will keep track of current leftmost (if boolean left == true) or rightmost value found till now
		if(start > end) return lastSeen;
		int mid = start + (end -start ) / 2;
		if(nums[mid] == target)lastSeen = mid;
		
		if(target <= nums[mid] && left) return binarySearch(nums, start, mid-1, lastSeen, left, target); // move towards left for boolean left == true bcz we need leftmost index for target
		else if (target >= nums[mid]) return binarySearch(nums, mid+1, end, lastSeen, left, target);  // move towards right for right most target value
		else return binarySearch(nums, start,mid-1 , lastSeen, left, target);
		
	}
	
	/************** Not so good method *******************/
	public int[] searchRange(int[] nums, int target) {
		int[] result = new int[2];
		result[0] = findFirst(nums, target); // start index
		result[1] = findLast(nums, target);  //end index
		return result;
	}
	
	private int findFirst(int[] nums, int target){
		int idx = -1;
		int start = 0;
		int end = nums.length - 1;
		while(start <= end){
			int mid = (start + end) / 2;
			if(nums[mid] >= target){
				end = mid - 1;  //pushing to left side firt
			}else{
				start = mid + 1;
			}
			if(nums[mid] == target) idx = mid;
		}
		return idx;
	}
	
	private int findLast(int[] nums, int target){
		int idx = -1;
		int start = 0;
		int end = nums.length - 1;
		while(start <= end){
			int mid = (start + end) / 2;
			if(nums[mid] <= target){
				start = mid + 1;
			}else{
				end = mid - 1;
			}
			if(nums[mid] == target) idx = mid;
		}
		return idx;
	}
	
	/************** Binary search Template ***********************/
	
	public int[] searchRangeBS(int[] nums, int target) {
		double left = target - 0.5, right = target + 0.5;
		int l = bs(nums, left);
		int r = bs(nums, right);
		if(l == r) return new int[]{-1, -1};
		return new int[]{l, r-1};
	}
	
	public int bs(int[] nums, double target) {
		int l = 0, h = nums.length-1;
		while(l <= h){
			int m = l + (h - l)/2;
			if(target > nums[m]) l = m+1;
			else h = m-1;
		}
		return l;
	}
	
	public static void main(String[] args) {
		M0034_FindFirstLastIndexSortedArray cl = new M0034_FindFirstLastIndexSortedArray();
		int[] array = {1,1,2,3}; int target = 1;
		System.out.println(Arrays.toString(cl.searchRange(array,target)));
		System.out.println(Arrays.toString(cl.searchRangeBS(array,target)));
	}
}
