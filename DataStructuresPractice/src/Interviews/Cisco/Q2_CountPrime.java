package Interviews.Cisco;

/**
 *  Time Complexity - O(n(log(logn)))
 *  Reason - See in Code
 *
 *  Space Complexity - O(n)
 *  Reason - adding sum to hash table again n again
 */
public class Q2_CountPrime {
	public int countPrimes(int n) {
		boolean[] isPrime = new boolean[n];
		int count =0;
		for(int i =2; i < n; i++){  //O(n)
			if(isPrime[i]) continue;
			count++;  // false means its prime
			for(int j = i; j< n; j=j+i){ //j = j+i == multiple of i   //O(log(logn))
				isPrime[j] = true;
			}
		}
		return count;
	}
	public static void main(String[] args) {
		Q2_CountPrime cl = new Q2_CountPrime();
		System.out.println(cl.countPrimes(10));
	}
}
