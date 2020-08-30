package LC_Medium;
/**
 
 Time Complexity - O(n*n!)
 Reason - n! to compute permutation and n for copying solution to new sublist and saving in result
 
 Space Complexity - O(n*n!)
 Reason - saving temporary combinations + final sublist combination
*/

import java.util.ArrayList;
import java.util.List;

public class M0046_Permutation {
	
	public List<List<Integer>> permute(int[] nums) {
	
		List<Integer> sublist = new ArrayList<>();
		List<List<Integer>> result = new ArrayList<>();
		permuteValues(nums,result,sublist,0);
		return result;
	
	}
	
	private void permuteValues(int[] nums,List<List<Integer>> result,List<Integer> sublist, int index ) {
		if (sublist.size() == nums.length) {
			result.add(new ArrayList(sublist));
			return;
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (!sublist.contains(nums[i])) {
					if (sublist.add(nums[i])) {
						permuteValues(nums, result, sublist, i);
						sublist.remove(sublist.size() - 1);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] nums = {1,2,3};
		M0046_Permutation cl = new M0046_Permutation();
		List<List<Integer>> result = cl.permute(nums);
		for(List s : result){
			for(int k : (ArrayList<Integer>)s)
				System.out.print(k);
			System.out.println();
		}
	}
}

/*

i = 0 , combo = [1]
backtrack function => go to for loop
i = 0 continue because combo has 1 already it will change to i = 1 combo = [1,2]
i = 0 continue, i = 1 continue , finally combo = [1,2,3]
==============
add to result , back to combo remove line combo = [1,2] here recall i was = 2 when 3 was added to combo and loop completed for i = 2

now it goes back to stack and for i = 1 only back track was completed , last line of for loop was not done
so it will remove 2 from combo ...combo = [1]
 */