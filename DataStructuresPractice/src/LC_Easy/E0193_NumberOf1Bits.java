package LC_Easy;
/**
 *  Time Complexity - O(1)
 *  Reason - traversal of 32 bits
 *
 *  Space Complexity - O(1)
 *  Reason - No extra space required
 */
public class E0193_NumberOf1Bits<nn> {
	public int hammingWeight(int n) {
		int count = 0;
		for(int i =0; i< 32; i++){
			int result =  n&1;
			if(result>0) count++;
			n = n>>1;
		}
		
		return count;
	}
	
	/********* Not checking all 32 bits ********************/
	/*the least significant 1-bit in n  is equals to  0th bit in n - 1
	n & (n-1) = always flips the least significant 11-bit in nn to 0 and keeps all other bits the same.
	
	*/

	public int hammingWeightApproach2(int n) {
		int count =0;
		while(n != 0)
		{
			n = n&(n-1);
			count++;
		}
		return count;
	}
	
	public static void main(String[] args) {
		E0193_NumberOf1Bits cl = new E0193_NumberOf1Bits();
		System.out.println(cl.hammingWeight(11));
		System.out.println(cl.hammingWeightApproach2(11));
	}
}
