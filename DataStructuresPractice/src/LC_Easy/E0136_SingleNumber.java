package LC_Easy;
/**
 Time Complexity -  O(N)
 Reason - Linear scan
 
 Space Complexity - O(1)
 Reason -  No extra space usage
 */
public class E0136_SingleNumber {
	public int singleNumber(int[] nums) {
		int ans = nums[0];
        for(int i = 1; i< nums.length; i++){
        	ans = ans^nums[i];
		}
        
        return ans;
    }
	public static void main(String[] args) {
		int[] arr = {4,1,2,1,2};
		E0136_SingleNumber cl = new E0136_SingleNumber();
		System.out.println(cl.singleNumber(arr));
	}
}
