package LC_Easy;

import java.util.Arrays;
import java.util.HashSet;

public class E0217_ContainsDuplicate {
	/********** HashTable  TC = O(nlogn) SC = O(n)**********************/
	public boolean containsDuplicateHash(int[] nums) {
		if(nums.length == 1) return false;
		HashSet<Integer> hs = new HashSet<>();
		
		for(int num : nums){
			if(hs.contains(num)) return true;
			hs.add(num);
		}
		
		return false;
	}
	
	
	/********** Sorting TC = O(nlogn) SC = O(1)**********************/
	public boolean containsDuplicate(int[] nums) {
       if(nums.length == 1) return false;
       Arrays.sort(nums);
       for(int i = 1; i<nums.length; i++){
           if(nums[i] == nums[i-1]) return true;
       }
       
       return false;
   }
	public static void main(String[] args) {
		int[] nums = {1,1,1,3,3,4,3,2,4,2};
		E0217_ContainsDuplicate cl = new E0217_ContainsDuplicate();
		System.out.println(cl.containsDuplicate(nums));
		System.out.println(cl.containsDuplicateHash(nums));
	}
}
