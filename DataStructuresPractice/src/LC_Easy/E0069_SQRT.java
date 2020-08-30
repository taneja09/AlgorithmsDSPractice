package LC_Easy;
/**
 
 Time Complexity - O(log n)
 Reason - Binary Search
 
 Space Complexity - O(1)
 Reason - No extra space
*/

/**
 * I think this problem's key point is that we want to find the largest number,
 * which num*num <= x, therefore we should use the binary search to find the upper bound within the range(1,x).
 */
public class E0069_SQRT {
	public int mySqrt(int x) {
	    if(x <= 1) return 1;
		int i = 1;
		int j = x;
		
		while(i<= j){
			int mid = i + (j-i)/2;
			if (mid <= x/mid){
				i = mid+1;
			}else
				j = mid-1;
		}
		
		return i-1;
	}
	
	/**============Brute Force: Time complexity = O(sqrt(x)) ======================*/
	
//	public int mySqrt(int x) {
//		if (x == 0) return 0;
//		for (int i = 1; i <= x / i; i++)
//			if (i <= x / i && (i + 1) > x / (i + 1))// Look for the critical point: i*i <= x && (i+1)(i+1) > x
//				return i;
//		return -1;
//	}
	
	public static void main(String[] args) {
		int x = 10000;
		E0069_SQRT cl = new E0069_SQRT();
		System.out.println(cl.mySqrt(x));
	}
}
