package LC_Easy;
/**
 * So the idea is similar as Two sum, using HashMap to store ( key : the prefix sum, value : how many ways get to this prefix sum) ,
 * and whenever reach a node, we check if prefix sum - target exists in hashmap or not, if it does, we added up the ways of prefix sum - target into res.
 *
 * For instance : in one path we have 1,2,-1,-1,2, then the prefix sum will be: 1, 3, 2, 1, 3,
 * let's say we want to find target sum is 2, then we will have{2}, {1,2,-1}, {2,-1,-1,2} and {2}ways.
 */

/**
 
 Time Complexity - O(n)
 Reason - linear scan
 
 Space Complexity - O(1)
 Reason -
*/

import Helpers.TreeNode;
import java.util.*;

public class E0437_PathSumIII {
	
	public int pathSum(TreeNode root, int sum) {
		TreeNode node = root;
		int count = 0;
		HashMap<Integer, Integer> preSum = new HashMap();
		preSum.put(0,1);  // 0 can be achieved using 1 way
		return helper(root, 0, sum, preSum);
	}
	private int helper(TreeNode node , int currSum, int target, HashMap<Integer, Integer> preSum){
		if(node == null) return 0;
		
		// update the prefix sum by adding the current val
		currSum += node.val;
		
		//if remaining result exit in map get and add 1 to it
		int res = preSum.getOrDefault(currSum - target, 0);
		preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
		
		
		res += helper(node.left, currSum, target, preSum) + helper(node.right, currSum, target, preSum);
		preSum.put(currSum, preSum.get(currSum) - 1);
		
		return res;
	}
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(5);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(6);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(0);
		root.right.right = new TreeNode(8);
		root.left.right.left = new TreeNode(7);
		root.left.right.right = new TreeNode(4);
		int sum = 4;
		E0437_PathSumIII cl = new E0437_PathSumIII();
		TreeNode.printNode(root);
		int result = cl.pathSum(root, sum);
		System.out.println(result);
	}
}
