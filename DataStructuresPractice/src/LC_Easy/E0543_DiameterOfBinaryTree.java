package LC_Easy;

import Helpers.TreeNode;

/**
 * although the longest path doesn't have to go through the root node, it has to pass the root node of some subtree of the tree
 * (because it has to be from one leaf node to another leaf node, otherwise we can extend it for free).
 * The longest path that passes a given node as the ROOT node is T = left_height+right_height.
 * So you just calculate T for all nodes and output the max T.
 */
public class E0543_DiameterOfBinaryTree {
	private int diam = 0;
	public int diameterOfBinaryTree(TreeNode root) {
		maxDepth(root);
		 return diam;
	}
	
	private int maxDepth(TreeNode root){
		if (root == null) return 0;
		
		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		diam = Math.max(right + left, diam); //if new diam is greater than previous then update it
		return Math.max(left, right) + 1;  // keeps track of current node

	}
	
	public static void main(String[] args) {
		
		TreeNode root = TreeNode.createRandomBST(3);
		E0543_DiameterOfBinaryTree cl = new E0543_DiameterOfBinaryTree();
		int result = cl.diameterOfBinaryTree(root);
		System.out.println(result);
		
	}
}
