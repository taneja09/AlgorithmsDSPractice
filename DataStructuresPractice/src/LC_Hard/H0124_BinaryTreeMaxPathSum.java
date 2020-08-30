package LC_Hard;

import Helpers.TreeNode;
/*
maxValue = Math.max(maxValue, left + right + node.val);
The second maxValue contains the bigger between the left sub-tree and right sub-tree.
if (left + right + node.val < maxValue ) then the result will not include the parent node which means the maximum path is in the left branch or right branch.
 */
public class H0124_BinaryTreeMaxPathSum {
	int maxValue;
	public int maxPathSum(TreeNode root) {
		maxValue = Integer.MIN_VALUE;
	        maxPathDown(root);
	        return maxValue;
	    }
	    
	    private int maxPathDown(TreeNode node) {
	        if (node == null) return 0;
	        int left = Math.max(0, maxPathDown(node.left));
	        int right = Math.max(0, maxPathDown(node.right));
	        maxValue = Math.max(maxValue, left + right + node.val);
	        return Math.max(left, right) + node.val;
	    }
	public static void main(String[] args) {
		TreeNode root = new TreeNode(-3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);

		H0124_BinaryTreeMaxPathSum cl = new H0124_BinaryTreeMaxPathSum();
		System.out.println(cl.maxPathSum(root));
	}
}
