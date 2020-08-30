package LC_Easy;

import Helpers.TreeNode;

import java.util.*;
/**
 *  Time Complexity - O(n)
 *  Reason - traversal all nodes
 *
 *  Space Complexity - O(log n)
 *  Reason - Queue size = number of nodes O(n)
 */

public class E0101_SymmetricTree {
	/*************   Iterative Approach ********************/
	public boolean isSymmetricIterative(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(root);
		while(!q.isEmpty()){
			TreeNode t1 = q.poll();
	        TreeNode t2 = q.poll();
	        
	        if(t1 == null && t2 == null) return true;
	        if(t1 == null || t2 == null) return false;
	        if(t1.val != t2.val) return false;
	        q.add(t1.left);
	        q.add(t2.right);
			q.add(t1.right);
			q.add(t2.left);
		}
		return true;
	}
	
	
	
	/*************   Recursive Solution 0ms - TC - O(n) SC - O(n) recurive stack calls ********************/
	public boolean isSymmetric(TreeNode root) {
		if(root == null) return true;
		return isSymmetric(root.left,root.right);
    }
    private boolean isSymmetric(TreeNode left, TreeNode right){
		if(left == null && right == null) return true;
		if(left == null || right == null) return false;
		if(left.val != right.val) return false;
		
		return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
	}
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right =  new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(4);
		
		E0101_SymmetricTree cl = new E0101_SymmetricTree();
		System.out.println(cl.isSymmetric(root));
		System.out.println(cl.isSymmetricIterative(root));
	}
}
