package LC_Medium;

import java.util.ArrayList;
import java.util.List;
/*
Find the range between lower and first element in the array.
Find ranges between adjacent elements in the array.
Find the range between upper and last element in the array.
 */

public class M0163_MissingRanges {
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		List<String> result = new ArrayList<String>();
		if (nums == null || nums.length == 0){
			result.add(createRange(lower,upper));
			return result;
		}
		if(lower < nums[0]){
			result.add(createRange(lower,nums[0]-1));
			
		}
		
		for(int i = 0; i<nums.length-1; i++){
			if(nums[i+1] != nums[i] && nums[i+1] >  nums[i] + 1)
				result.add(createRange(nums[i]+1,nums[i+1]-1));
		}
		
		if(upper > nums[nums.length-1])
			result.add(createRange(nums[nums.length-1]+1,upper));
		
		return result;
	}
	
	private String createRange(int first, int second){
		if(first == second) return ""+first;
		String res = first + "->" + second;
		return res;
	}
	public static void main(String[] args) {
		int[] arr = {-2147483648,2147483647};
		int lower = -2147483648;
		int upper = 2147483647;
		M0163_MissingRanges cl = new M0163_MissingRanges();
		List<String>  res = cl.findMissingRanges(arr,lower,upper);
		System.out.println(res);
	}
}
