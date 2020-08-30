package LC_Easy;

/**
 
 Time Complexity - O(Log(x)) where x number of digits and base 10 for log
 Reason - We need to consider each digit but we are also dividing it by 10
 
 Space Complexity - O(1)
 Reason - No extra space
 
*/
/**=====================================================================================*/

/**
 * If curr result  = result * 10 + remainder causes overflow then , current result >= Integer.Max/10  check this to avoid overflow
 * Two OR Clauses are there
 * If result > Integer.Max/10 then return 0 as its definitely overflow same for Min Case if result < Min_Val its overflow
 * If result = Integer.Max val || Integer.MinVal then, whatever we are trying to add at the end , we need to check
 * for Max last digit is 7 and for min last digit is 8 so compared and return 0
 */
public class E0007_ReverseInteger {
	public int reverse(int x) {
      	int result = 0;
      	
      	while(x != 0){
      	int remainder = x%10;
      	x = x/10;
      	
      	if(result > Integer.MAX_VALUE/10 || result == Integer.MAX_VALUE/10 && x > 7) return 0;
      	if(result < Integer.MIN_VALUE/10 || result == Integer.MIN_VALUE/10 && x < -8) return 0;
      	
      	result = result * 10 + remainder;
      	
	  }
      	
      	return result;
  }
	public static void main(String[] args) {
		int x = -123;
		E0007_ReverseInteger cl = new E0007_ReverseInteger();
		System.out.println(cl.reverse(x));
	}
}
