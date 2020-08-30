package LC_Easy;
/**
 Time Complexity -  O(n)
 Reason - single pass
 
 Space Complexity - O(1)
 Reason -
 */
import java.util.Arrays;

public class E0268_MissingNumber {
	/******** XOR Formula O(n) *******************/
	public int missingNumberXOR(int[] nums) {
      int missing = nums.length;
      for (int i = 0; i < nums.length; i++) {
          missing ^= i ^ nums[i];  //XOR index with number whatever is missing will remain in last
      }
      return missing;
  }
	/******** Gauss Formula O(n) *******************/
	public int missingNumber(int[] nums) {
		if(nums == null | nums.length == 0) return 0;
		int psum = 0;
		int sum = nums.length * (nums.length+1)/2;
		for(int i : nums) psum+= i;
		return sum-psum;
	}
	
	/******** Sorting Formula O(nlogn) *******************/
	public int missingNumberSort(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		Arrays.sort(nums);
		// Ensure that n is at the last index
		if (nums[nums.length-1] != nums.length) {
			return nums.length;
		}
		// Ensure that 0 is at the first index
		else if (nums[0] != 0) {
			return 0;
		}
		
		for(int i = 1; i<nums.length; i++){
			if(nums[i] - nums[i-1] > 1) return nums[i]-1;
		}
		
		return -1;
	}
	public static void main(String[] args) {
		E0268_MissingNumber cl = new E0268_MissingNumber();
		int[] arr = {9,6,4,2,3,5,7,0,1};
		System.out.println(cl.missingNumberXOR(arr));
	}
}
