package LC_Easy;

public class E0190_ReverseBits {
	
	/***************** TC =  O(1) loop works for 32 times ****************/
	public int reverseBits(int n) {
		int result = 0;
    	for(int i = 0 ; i< 32; i++){
			result <<= 1;   //make space for 2 digits to accommodate the incoming digit
			result = result | (n&1);  //  And given number last digit with 1 to get actual digit  1&1 =1 , 1&0 = 0 use the result and append it with |
			n = n>>1; // discard the processed bit
		}
    	return result;
    }
	public static void main(String[] args) {
		E0190_ReverseBits cl = new E0190_ReverseBits();
		System.out.println(cl.reverseBits(3));
	}
}
