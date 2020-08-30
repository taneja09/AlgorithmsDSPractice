package LC_Easy;

import java.util.HashSet;
/**
 *  Time Complexity - O(log n) base 10
 *  Reason - everytime division by 10
 *
 *  Space Complexity - O(n)
 *  Reason - adding sum to hash table again n again
 */
public class E0202_IsHappyNumber {
	public boolean isHappy(int n) {
       int[] array = {0,1,4,9,16,25,36,49,64,81,100};
       HashSet<Integer> hs = new HashSet();
       return result(n,hs, array);
   }
	public boolean result(int n, HashSet<Integer> hs,int[] array) {
		if(n==1) return true;
		if(hs.contains(n)) return false;
		hs.add(n);
		int sum = 0;
		while (n > 0) {
			int x = n%10;
			sum+= array[x];
			n = n/10;
		}
		return result(sum,hs,array);
	}
 
	public static void main(String[] args) {
		E0202_IsHappyNumber cl = new E0202_IsHappyNumber();
		System.out.println(cl.isHappy(68));
	}
}
