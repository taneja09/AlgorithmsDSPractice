package LC_Easy;

/**
 *  Time Complexity - O(n)
 *  Reason - traversal all nodes
 *
 *  Space Complexity - O(log n) for best case where tree is balanced tree n O(n) for worst case
 *  where tree might be aligining at one side
 */

import Helpers.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class E0100_SameTree {
	public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
		Queue<TreeNode> queue = new LinkedList<>();
		if (p == null && q == null) return true;
		else if (p == null || q == null) return false;
		if (p != null && q != null) {
			queue.offer(p);
			queue.offer(q);
		}
		
		while(!queue.isEmpty()){
			TreeNode first = queue.poll();
			TreeNode second = queue.poll();
			if (first == null && second == null) continue;     //dont return true here because may be both left are null but their right doesnt match   [12,null,-60]   &  [12,null,72]
			if (first == null || second == null) return false;
			if(first.val != second.val) return false;
			queue.offer(first.left);
			queue.offer(second.left);
			queue.offer(first.right);
			queue.offer(second.right);
		}
		
		return true;
	}
	
	
	private boolean check(TreeNode p, TreeNode q) {
		// p and q are null
		if (p == null && q == null) return true;
		// one of p and q is null
		if (q == null || p == null) return false;
		if (p.val != q.val) return false;
		return true;
	}
	
	/*************** Recursive Solution ******************************/
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if(p == null && q ==null) return true;
		if(p == null || q == null) return false;
		if(p.val != q.val) return false;
		return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
	}
	public static void main(String[] args) {
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
		root1.right =  new TreeNode(1);
		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(1);
		root2.right =  new TreeNode(2);
		
		E0100_SameTree cl = new E0100_SameTree();
		System.out.println(cl.isSameTree(root1,root2));
		System.out.println(cl.isSameTreeIterative(root1,root2));
	}
}
