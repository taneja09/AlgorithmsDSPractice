package LC_Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class M0398_RandomPickIndex{
	int[] nums;
	Random rand = new Random();
	HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
	
	/************* Random Pick --TC = 0(N) SC = O(n) *****************/
	public M0398_RandomPickIndex(int[] nums) {
		this.nums = nums;
	}
	
	public int pick(int target) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (target == nums[i]) count++;
		}
		int pickIndex = rand.nextInt(count);
		for(int i = 0; i < nums.length; i++) {
			if(target == nums[i]){
				if(pickIndex-- == 0) return i;  //because the element which have 1 entry
				// their count will be 1 and count-- will prove that 0th element that means 1st element itself is getting picked
			}
			
		}
		
		return -1;
	}
	
	/************* Random Pick --TC = 0(N) SC = O(n) *****************/
	public void M0398_RandomPickIndex1(int[] nums) {
		for(int i = 0; i< nums.length; i++){
			if(hm.containsKey(nums[i])){
				ArrayList s = hm.get(nums[i]);
				s.add(i);
			}else{
				ArrayList s = new ArrayList();
				s.add(i);
				hm.put(nums[i],s);
			}
		}
	}
	
	public int pick1(int target) {
		if(hm.containsKey(target)){
			ArrayList al = hm.get(target);
			int index = rand.nextInt(al.size());
			return (int)al.get(index);
		}
		
		return -1;
	}
	public static void main(String[] args) {
		int[] arr = {1,2,3,3,3};
		M0398_RandomPickIndex cl  = new M0398_RandomPickIndex(arr);
		System.out.println(cl.pick(3));
	}
}
