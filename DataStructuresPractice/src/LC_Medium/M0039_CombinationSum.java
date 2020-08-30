package LC_Medium;


/**
 
 Time Complexity - O(N^M)
 Reason - Number of elements in array - N and Target = M ..
 suppose if elements are [1,1,1] & target = 4 then worst case will be depth of recursion = 4(M)
 
 Space Complexity - O(N^M)
 Reason -  sublist combinations
*/


import java.util.ArrayList;
import java.util.List;

public class M0039_CombinationSum {
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<Integer> sublist = new ArrayList<>();
		List<List<Integer>> result = new ArrayList<>();
		getResultValues(candidates,result,sublist,target,0);
		return result;
	}
	
	private void getResultValues(int[]candidates,List<List<Integer>> result, List<Integer> sublist,int remain, int start){
			if(remain < 0) return;
			else if(remain == 0) result.add(new ArrayList<>(sublist));
			else {
				for (int i = start; i < candidates.length; i++) {
					sublist.add(candidates[i]);
					getResultValues(candidates, result, sublist, remain - candidates[i], i); // not i + 1 because we can reuse same elements
					sublist.remove(sublist.size() - 1);
				}
			}
	}
	
	public static void main(String[] args) {
		int[] candidates = {2,3,6,7};
		int target = 7;
		M0039_CombinationSum obj = new M0039_CombinationSum();
		List<List<Integer>> result = obj.combinationSum(candidates,target);
		for(List<Integer> res : result){
			for(int i : res);
			System.out.print(res);
		}
		
	}
	
}
