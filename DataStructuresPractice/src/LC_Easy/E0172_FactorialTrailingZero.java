package LC_Easy;

/**
 *  Time Complexity - O(log n)
 *  Reason - keep on dividing so logn base 2
 *
 *  Space Complexity - O(1)
 *  Reason - No extra space required
 */

public class E0172_FactorialTrailingZero {
	/************* O(2^n/2) *********************/
	public int trailingZeroes(int n) {
		return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
	}
	/************* O(logn) *******************/
	public int trailingZeroesType(int n) {
		int count = 0;
		while( n!= 0){
			n = n/5;
			count+= n;
		}
		
		return count;
	}
	public static void main(String[] args) {
		E0172_FactorialTrailingZero cl = new E0172_FactorialTrailingZero();
		System.out.println(cl.trailingZeroes(10));
		System.out.println(cl.trailingZeroesType(2));
	}
}
