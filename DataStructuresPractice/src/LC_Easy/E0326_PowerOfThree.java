package LC_Easy;
/**
 Time Complexity -  O(n)
 Reason - single pass
 
 Space Complexity - O(1)
 Reason -
 */
public class E0326_PowerOfThree {
	public boolean isPowerOfThree(int n) {
		if(n<3) return false;
		while(n%3 == 0){
			n = n/3;
		}
		
		return n ==1;
	}
	/*1162261467 is max Integer which can be formed with 3 power and if dividing by n , remainder = 0 then its power of 3 */
	public boolean isPowerOfThreeMath(int n) {
		return n > 0 && 1162261467 % n == 0;
	}
	
	/*log formula
	* */
	public boolean isPowerOfThreeMathLog(int n) {
		return (Math.log10(n) / Math.log10(3)) % 1 == 0;
	}
	public static void main(String[] args) {
		E0326_PowerOfThree cl = new E0326_PowerOfThree();
		System.out.println(cl.isPowerOfThree(27));
	}
}
