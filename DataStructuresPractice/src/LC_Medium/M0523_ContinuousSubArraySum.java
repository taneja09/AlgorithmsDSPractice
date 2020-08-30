package LC_Medium;

import java.util.*;
/*
 Corner cases
 [0], 0 -> false;  you must have more than 2 value sum
 [5, 2, 4], 5 -> false; because we can return single value multiple read why <0,-1> below
 [0, 0], 100 -> true; because sum =0 is always multiple of anything
 [1,5], -6 -> true; because sum = 6 and sign doesnt matter in multiple



 */

public class M0523_ContinuousSubArraySum {
	/******************** Hash map   O(n^2) ********************/
	public boolean checkSubarraySum(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};
		//why <0,-1> can allow it to return true when the runningSum%k=0
		
		//why <0,-1> if first element is divisible by k , index -1 will help us not to return true
		// because if at 0 we have 6 which will give runningsum = 0  and from map map.get(0) = -1
		// i-prev > 1 => 0(-(-1) = 1 > 1 false and it will not return true
		int runningSum = 0;
		
		for (int i=0;i<nums.length;i++) {
			runningSum += nums[i];
			if( k!= 0) runningSum %= k;
			Integer prev = map.get(runningSum);
			if(prev != null) {
				if (i - prev > 1) return true;
			}
			else map.put(runningSum, i);
		}
		
		return false;
	}
	
	/*
	
	[23, 2, 4, 6, 7] & k = 6
	
	i = 0 => runningSum = 23  ,  23%6 = 5 , 5 doesnt exist in map , put in map <5, 0>
	i = 1 => runningSum = 5+2 7, 7%6 = 1  , 1 doesnt exist in map , put in map <1, 1>
	i = 2 => runningSum = 1+4 5, 5%6 = 5 , 5 exist in map  map.get(5) = 0 and i-0 > 1 return true;
	
	That means, in between these two indexes we must have added a number which is multiple of the k.
	
	 */
	
	
	
	/******************** Brute  Force  O(n^2) ********************/
	
	public boolean checkSubarraySumBF(int[] nums, int k) {
		for(int i = 0; i < nums.length-1; i++) {
			int sum = nums[i];
			for(int j = i+1; j< nums.length; j++){
				sum += nums[j];
				if((k == 0 && sum == 0) || sum%k == 0) return true;
			}
		}
		
		return false;
	}
	public static void main(String[] args) {
		int [] arr = {23,2,4,6,7};
		M0523_ContinuousSubArraySum cl = new M0523_ContinuousSubArraySum();
		System.out.println(cl.checkSubarraySumBF(arr,6));
	}
}
