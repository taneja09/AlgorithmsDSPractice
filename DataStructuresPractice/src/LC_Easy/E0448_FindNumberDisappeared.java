package LC_Easy;

import java.util.ArrayList;
import java.util.List;

public class E0448_FindNumberDisappeared {
	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer>  al = new ArrayList<>();
        for(int i = 0; i< nums.length; i++){
        	int val = Math.abs(nums[i])-1;
        	if(nums[val]>0)
        		nums[val] = -nums[val];
		}
  
		for(int i = 0; i< nums.length; i++){
			if(nums[i] > 0) al.add(i+1);
		}
		
		return al;
    }
	public static void main(String[] args) {
		int[] array = {4,3,2,7,8,2,3,1};
		E0448_FindNumberDisappeared cl = new E0448_FindNumberDisappeared();
		List<Integer> al = cl.findDisappearedNumbers(array);
		for(int a: al)
		System.out.println(a);
	}
}
