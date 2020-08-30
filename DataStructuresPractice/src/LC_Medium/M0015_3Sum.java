package LC_Medium;
/**
	 *  Time Complexity - O(N^2)
	 *  Reason -
	 *
	 *  Space Complexity - 0(S)  where S  is number of combinations
	 *  Reason -
 */
import java.util.*;
public class M0015_3Sum {
	
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new LinkedList<>();
		for (int i = 0; i < nums.length-2; i++) {
			if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
				if(nums[i]>0) break;  //nums[i]<=nums[j] <= nums[k] if nums[i] >0 that means ans will always be +ve and sum will never be 0
				int lo = i + 1, hi = nums.length - 1, newsum = 0 - nums[i];
				while (lo < hi) {
					if (nums[lo] + nums[hi] == newsum) {
						res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
						while (lo < hi && nums[lo] == nums[lo + 1]) lo++; //skip the same number from start
						while (lo < hi && nums[hi] == nums[hi - 1]) hi--; //skip the same number from end
						lo++;
						hi--;
					} else if (nums[lo] + nums[hi] < newsum) {
						while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
						lo++;
					} else {
						while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
						hi--;
					}
				}
			}
		}
		return res;
   }
	public static void main(String[] args) {
		int[] array = {-1,0,1,2,-1,-4};
		M0015_3Sum cl = new M0015_3Sum();
		List<List<Integer>> res = cl.threeSum(array);
		for(List<Integer> x : res){
			for(int i : x)
				System.out.print(i);
			System.out.println("");
		}
	}
}
