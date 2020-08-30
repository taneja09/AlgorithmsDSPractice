package LC_Medium;
/**
 Time Complexity - O(n)
 Reason - One pass
 
 Space Complexity - O(1)
 Reason - constant space
 */
/**
 * split the problem is 1. not rob the 1st house; 2. rob 1st house, because 1 and 2 won't have any intersection
 */
public class M0213_HouseRobberII {
	public int rob(int[] nums) {
		if(nums==null||nums.length==0) return 0;
		int exclude_1st = rob_helper(nums, 1, nums.length-1);  //if you exclude 1st , you can certainly loop till last element
		int include_1st = nums[0] + rob_helper(nums, 2, nums.length-2);  //length length-2 because if you include 1st you can't include last due to circle
		return Math.max(include_1st, exclude_1st);
	}
	
	public int rob_helper(int[] nums, int start, int end) {
		int pre_i_2 = 0;
		int pre_i_1 = 0;
		
		for(int i=start; i<=end; i++)
		    {
		        int cur = Math.max(nums[i] + pre_i_2, pre_i_1); //alternate selection  it is similar to house robber I question
		        int temp = pre_i_1;
		        pre_i_1 = cur;
		        pre_i_2 = temp;
		    }
		return pre_i_1;
	}
	
	public static void main(String[] args) {
		int[] houses = {1,2,3,1};
		M0213_HouseRobberII cl = new M0213_HouseRobberII();
		int res = cl.rob(houses);
		System.out.println(res);
	}
	
	
}
