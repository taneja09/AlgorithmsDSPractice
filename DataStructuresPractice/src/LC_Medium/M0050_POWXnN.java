package LC_Medium;
/**
 *  Time Complexity - O(logn)
 *  Reason - Binary multiplication
 *
 *  Space Complexity - O(1)
 *  Reason - internal stack space only
 */
public class M0050_POWXnN {
	
	public double myPow(double x, int n) {
		if(n == 0) return 1;
		if (n == 1) return x;
		if(n < 0) {
			n = -n;
			if (n > 0)     //when n > 0 , it proves that no overflow , so x = 1/x ,otherwise , x should no be changed
			    x = 1 / x;
		}
		double result = myPow(x, n / 2);
		return n % 2 == 0 ? result * result : x * result * result;
   	}
   	
	public static void main(String[] args) {
		double x = 0.00001;
		int n = 2147483647;
		M0050_POWXnN cl = new M0050_POWXnN();
		System.out.println(cl.myPow(x,n));
	}
}
