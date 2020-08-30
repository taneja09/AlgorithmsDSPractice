package LC_Medium;
/**
 
 Time Complexity - O(n)
 Reason - traversal required for complete array
 
 Space Complexity - O(1)
 Reason - Constant space
*/

import java.util.Arrays;

/**
 * This is the function we get, now we need find the other pattern for the function to get the general function. After we analyze the above function, we can get
 * dp[0] = 0;
 *
 * dp[1] = dp[1-1] + 1;
 *
 * dp[2] = dp[2-2] + 1;
 *
 * dp[3] = dp[3-2] +1;
 *
 * dp[4] = dp[4-4] + 1;
 *
 * dp[5] = dp[5-4] + 1;
 *
 * dp[6] = dp[6-4] + 1;
 *
 * dp[7] = dp[7-4] + 1;
 *
 * dp[8] = dp[8-8] + 1;
 * ..
 */

public class M0338_CountingBits {
	
	public int[] countBits(int num) {
   		int[] dp = new int[num+1];
		int i = 0; int b = 1; // 2 ^ 0
		
		while(b <= num){
			while(i < b && i+b <= num){
				dp[i+b] = dp[i] + 1;
				i++;
			}
			i=0;
			b = b << 1;
		}
		
		return dp;
   }
   
   //********************** Most Efficient approach ****************************//
	
	public int[] countBitsDP(int num) {
   		int[] result = new int[num+1];
		int offset = 1;
		
		for (int i = 1; i < num + 1; i++){
			System.out.println(i + " offset " + offset);
			if (i == offset*2){
				offset = offset * 2;  //setting the offset to the power of 2 ,
			}
			result[i] = result[i - offset] + 1;
		}
		return result;
   }
   
   
	public static void main(String[] args) {
		M0338_CountingBits cl = new M0338_CountingBits();
		//int[] result = cl.countBits(8);
		int[] result1 = cl.countBitsDP(10);
		System.out.println(Arrays.toString(result1));
	}
}
