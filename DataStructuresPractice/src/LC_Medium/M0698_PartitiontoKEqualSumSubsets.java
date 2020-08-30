package LC_Medium;
/*
https://www.youtube.com/watch?v=qpgqhp_9d1s
*/
public class M0698_PartitiontoKEqualSumSubsets {
	
	/* ======================== Backtrack Solution TC =  O(k * 2^n) ================================= */
	public boolean canPartitionKSubsetsBackTrack(int[] nums, int k) {
		int sum = 0, maxNum = 0;
		for (int num : nums) {
			sum += num;
			maxNum = Math.max(maxNum, num);
		}
		if (sum % k != 0 || maxNum > sum / k) {
			return false;
		}
		return canPartitionKSubsetsFrom(nums, k, new boolean[nums.length], sum / k, 0, 0);
	}
	
	private boolean canPartitionKSubsetsFrom(int[] nums,int k,boolean[] visited, int targetSubsetSum, int curSubsetSum,  int nextIndexToCheck){
		if (k == 0) return true;
		if (curSubsetSum == targetSubsetSum){
			return canPartitionKSubsetsFrom(nums, k - 1, visited, targetSubsetSum, 0, 0);
		}
		for (int i = nextIndexToCheck; i < nums.length; i++) {
			if (!visited[i] && curSubsetSum + nums[i] <= targetSubsetSum) {
				visited[i] = true;  //SELECT
				if (canPartitionKSubsetsFrom(nums, k, visited, targetSubsetSum, curSubsetSum + nums[i], i + 1)) {
					return true;  //EXPLORE
				}
				visited[i] = false; //UNSELECT
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[] arr = {2,2,2,2,3,4,5};
		M0698_PartitiontoKEqualSumSubsets cl = new M0698_PartitiontoKEqualSumSubsets();
		System.out.println(cl.canPartitionKSubsetsBackTrack(arr,4));
	}
}
