package LC_Medium;

/**
 
 Time Complexity - O(n^2)
 Reason - 2 for loops
 
 Space Complexity - O(n)
 Reason - dp array
*/


//https://www.youtube.com/watch?v=GgP75HAvrlY&t=714s

/**
 * Given a sequence 1…n, to construct a Binary Search Tree (BST) out of the sequence
 * enumerate i as root to create BST
 * 1…(i-1) on its left side  && (i+1) to N on the right side
 * G(n): the number of unique BST for a sequence of length n.
 * F(i, n), 1 <= i <= n: the number of unique BST for root = i AND n as total number of nodes
 * G(n) = F(1, n) + F(2, n) + ... + F(n, n) // edge cases G(0)=1, G(1)=1  bcz with 0,1 only 1 tree can be formed
 *
 * G(3) => F(1, n) + F(2,n) + F(3,n) where n = 3
 * G(3)=> G(1-1) * G(n-1) + G(2-1) * G(n-2) + G(3-1) * G(3-n)
 * G(3)=> G(0) * G(2) + G(1) * G(1) + G(2) * G(0)
 * Great solution. I guess it is clearer to say F(i, N) = G(i-1) * G(N-i)
 */

public class M0096_UniqueBinarySearchTree {
//	public int numTrees(int n) {
//		int [] G = new int[n+1];
//		G[0] = G[1] = 1;
//
//		for(int i = 2; i<= n ; ++i){
//			for(int j = 1; j<= i; ++j ){
//				G[i] += G[j-1] * G[i-j];
//			}
//		}
//
//		return G[n];
// }
	
	//Just treat each number as root, and then left part * right part is the answer.
	
	public int numTrees(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 1; //Empty tree
		for (int i = 1; i <= n; i++) { //number amount: i
			for (int j = 1; j <= i; j++) { //Possible amount with j as the root
				dp[i] += dp[j - 1]* dp[i - j]; //Divide i numbers into 2 parts with j: 1) 0..j 2) j..i-1. Count[i] is count[left part] * count[right part]
			}
		}
		
		return dp[n];
	}
	
	public static void main(String[] args) {
		M0096_UniqueBinarySearchTree cl = new M0096_UniqueBinarySearchTree();
		int res = cl.numTrees(3);
		System.out.println(res);
	}
}
