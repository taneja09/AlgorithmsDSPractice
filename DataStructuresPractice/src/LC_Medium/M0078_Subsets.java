package LC_Medium;
/**
 
 Time Complexity - O(n*2^n)
 Reason - 2^n for backtracking and n for copying the sublist to result
 
 Space Complexity - O(2^n)
 Reason -  all combinations of subsets
*/
import java.util.ArrayList;
import java.util.List;

public class M0078_Subsets {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList();
		List<Integer> sublist = new ArrayList();
		getSubsets(nums,result,sublist,0);
		return result;
	}
	
	private void getSubsets(int[] nums, List<List<Integer>> result, List<Integer> sublist,int index){
			result.add(new ArrayList(sublist));
			
			for(int i = index; i< nums.length; i++) {
				sublist.add(nums[i]);
				getSubsets(nums, result, sublist, i+1);
				sublist.remove(sublist.size() - 1);
			}
	}
	public static void main(String[] args) {
		int[] set = {1,2,3};
		M0078_Subsets cl = new M0078_Subsets();
		List<List<Integer>> result = cl.subsets(set);
		for(List<Integer> x : result)
				System.out.println(x);
	}
}
