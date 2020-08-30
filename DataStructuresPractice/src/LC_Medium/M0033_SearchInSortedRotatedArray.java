package LC_Medium;

/**
 * Find the index of the smallest element.
 * Figure out in right or left side of the smallest index the target lies.
 * Run normal binary search.
 */

public class M0033_SearchInSortedRotatedArray {
	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) return -1;
		
		int left = 0, right = nums.length-1, start = 0;
		
		//1. find index of the smallest element
		while(left < right) {
			int mid = left + (right-left)/2;
			if (nums[mid] > nums[right]) {
				left = mid +1;
			} else right = mid;
		}
		
		//2. figure out in which side our target lies
		start = left;
		left = 0;
		right = nums.length-1;
		if (target >= nums[start] && target <= nums[right])
			left = start;
		else right = start;
		
		
		//3. Run normal binary search in sorted half.
		while(left <= right) {
			int mid = left + (right - left)/2;
			if (nums[mid] == target) return mid;
			
			if (nums[mid] > target) right = mid-1;
			else left = mid + 1;
		}
		return -1;
	}
	
	
	public static void main(String[] args) {
		M0033_SearchInSortedRotatedArray cl = new M0033_SearchInSortedRotatedArray();
		int[] arr = {4,5,6,7,0,1,2};
		int target = 4;
		System.out.println(cl.search(arr,target));
	}
}
