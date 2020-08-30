package LC_Easy;

/**
 
 Time Complexity - 0(n)
 Reason - accessing all elements of array
 
 Space Complexity - 0(n)
 Reason - Creating a HashMap of all elements
 
 */

import java.util.Arrays;
import java.util.HashMap;

public class E0001_TwoSum {
	public int[] twoSum(int[] nums, int target) {
		int[] res = new int[2];
		HashMap<Integer,Integer> hm = new HashMap<>();
		for(int i = 0; i<nums.length; i++){
			if(hm.containsKey(target - nums[i])){
				res[0] = i;
				res[1] = hm.get(target - nums[i]);
			}else{
				hm.put(nums[i],i);
			}
		}
		return res;
	}
	public static void main(String[] args) {
		int array[] = {2,7,11,15};
		int sum = 9;
		E0001_TwoSum cl = new E0001_TwoSum();
		int[] res = cl.twoSum(array,sum);
		System.out.println(Arrays.toString(res));
	}
}
