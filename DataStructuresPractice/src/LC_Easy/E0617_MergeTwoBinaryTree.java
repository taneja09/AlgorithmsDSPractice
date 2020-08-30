package LC_Easy;

import Helpers.TreeNode;

import java.util.Stack;

public class E0617_MergeTwoBinaryTree {
	/**************** Create a new Tree = TC = O(n) and SC = O(n) depth of recursion + new tree ***************/
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if(t1 == null && t2 == null) return null;
		if(t1 == null || t2 == null) return t1 == null ? t2 : t1;
		
		int val1 = t1 != null ? t1.val : 0;
		int val2 = t2 != null ? t2.val : 0;
		TreeNode res = new TreeNode(val1+val2);
		res.left = mergeTrees(t1 == null ? null : t1.left,t2== null ? null : t2.left);
		res.right = mergeTrees(t1 == null ? null : t1.right,t2== null ? null : t2.right);
		
		return res;
	}
	
	/**************** Merge in Same tree = TC = O(n) and SC = O(n) depth of recursion***************/
	public TreeNode mergeTreesEasy(TreeNode t1, TreeNode t2) {
		if(t1 == null && t2 == null) return null;
		if(t1 == null || t2 == null) return t1 == null ? t2 : t1;
		
		t1.val += t2.val;
		t1.left = mergeTreesEasy(t1.left,t2.left);
		t1.right = mergeTrees(t1.right, t2.right);
		
		return t1;
	}
	
	/**************** Merge in Same tree = TC = O(n) and SC = O(n) depth of recursion***************/
	public TreeNode mergeTreesIterative(TreeNode t1, TreeNode t2) {
		if (t1 == null) return t2;
		Stack< TreeNode[] > stack = new Stack();
		stack.push(new TreeNode[] {t1, t2});
		
		while (!stack.isEmpty()) {
			TreeNode[] t = stack.pop();
			if (t[0] == null || t[1] == null) continue; // if both nodes are null continue;
			t[0].val += t[1].val;  // if not null first tree val get added with second tree
			
			if (t[0].left == null) t[0].left = t[1].left;  //merge left nodes
			else stack.push(new TreeNode[] {t[0].left, t[1].left});  // if both nodes are valid push them to stack for processing
			
			if (t[0].right == null) t[0].right = t[1].right;  //merge left nodes
			else stack.push(new TreeNode[] {t[0].right, t[1].right});
		}
		
		return t1;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(3);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(5);
		
		
		TreeNode root1 = new TreeNode(2);
		root1.left = new TreeNode(1);
		root1.right = new TreeNode(3);
		root1.left.right = new TreeNode(4);
		root1.right.right = new TreeNode(7);
		
		E0617_MergeTwoBinaryTree cl = new E0617_MergeTwoBinaryTree();
		TreeNode res = cl.mergeTreesIterative(root,root1);
		TreeNode.printNode(root);
		TreeNode.printNode(root1);
		TreeNode.printNode(res);
	}
}
