package LC_Medium;
/**
 
 Time Complexity - O(m)
 Reason - traversal required for whole array
 
 Space Complexity - O(1)
 Reason - Constant space
*/

/** Keep track of the smallest index i that can "jump" to the last index n-1
 * Check whether the current index can jump to this smallest index
 * */
public class M0055_JumpGames {
	public boolean canJump(int[] nums) {
		int n = nums.length ;
		if(n == 1) return true;
		int last=n-1,i,j;
		for(i=n-2;i>=0;i--){
		        if(i+nums[i]>=last)last=i;
		    }
       return last<=0;
   }
	public static void main(String[] args) {
			int[] array = {2,3,1,1,4};
		M0055_JumpGames cl = new M0055_JumpGames();
		System.out.println(cl.canJump(array));
	}
	
}
