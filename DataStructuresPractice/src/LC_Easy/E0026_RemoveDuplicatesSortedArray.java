package LC_Easy;

/**
 
 Time Complexity - O(n)
 Reason - Array traversal 1 time
 
 Space Complexity - O(1)
 Reason - No extra space
*/

public class E0026_RemoveDuplicatesSortedArray {
	public int removeDuplicates(int[] nums) {
		if(nums.length <= 1) return nums.length;
		int i  = 0;
		
		for(int j = 1; j < nums.length; j++ ){
			if (nums[j] != nums[i]) {
				i++;
				nums[i] = nums[j];
			}
		}
		
		return  i + 1;
	}
	
	public static void main(String[] args) {
		int[] array = {0,1,1,1,3,3,4};
		E0026_RemoveDuplicatesSortedArray cl = new E0026_RemoveDuplicatesSortedArray();
		System.out.println(cl.removeDuplicates(array));
	}
}