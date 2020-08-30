package LC_Medium;
/*
Inflection Point .

All the elements to the left of inflection point > first element of the array 3
All the elements to the right of inflection point < first element of the array 3

[3,4,5,1,2]

mid = 5
inflection point between 5 and 1 ... all element from 3-5 are > 3
all elements between 1-2 are < 3

 */

/**
 Time Complexity -  O(logn)
 Reason - Linear scan
 
 Space Complexity - O(1)
 Reason -  No extra space usage
 */
public class M0153_FindMinRotatedSortedArray {
	
	/*======================= Brute Force  TC = O(n) ============================*/
	public int findMin(int[] nums) {
		if(nums == null || nums.length ==0) return Integer.MIN_VALUE;
		int prev = nums[0];
		for(int i = 1;i < nums.length; i++){
			if(prev < nums[i]) prev = nums[i];
			else return nums[i];
		}
		
		return nums[0];
	}
	/*======================= Binary Search  TC = O(logn) ============================*/
	public int findMinBinary(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}
		
		int end = nums.length-1;
		int start = 0;
		if (nums[end] > nums[start]) {  //array is already sorted if last element is greater than first element
			return nums[0];
		}
		
		while(end>= start){
			int mid = start + (end - start) / 2;
			
			/* Stop conditions
			nums[mid] > nums[mid + 1] Hence, mid+1 is the smallest.
			nums[mid - 1] > nums[mid] Hence, mid is the smallest.
			*/
			
			if (nums[mid] > nums[mid + 1]) {
				return nums[mid + 1];
			}
			if (nums[mid - 1] > nums[mid]) {
				return nums[mid];
			}
			
			/*
			If mid element > first element of array this means that we need to look for the inflection point on the right of mid.
			If mid element < first element of array this that we need to look for the inflection point on the left of mid.
			*/
			if (nums[mid] > nums[0]) start = mid + 1;
			else end = mid - 1;
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		int[] array = {3, 4, 5, 1, 2};
		M0153_FindMinRotatedSortedArray cl = new M0153_FindMinRotatedSortedArray();
		System.out.println(cl.findMin(array));
		System.out.println(cl.findMinBinary(array));
	}
	
}