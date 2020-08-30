package LC_Medium;

import Helpers.TreeNode;

public class M1026_MaxDifferenceBetNodeAncestor {
	public int maxAncestorDiff(TreeNode root) {
		if(root == null) return 0;
		int left = maxAncestorDiff(root.left);
		int right = maxAncestorDiff(root.right);
		return Math.max(Math.abs(root.val -left), Math.abs(root.val-right));
	}
	public static void main(String[] args) {
		TreeNode root = new TreeNode(8);
		root.left = new TreeNode(3);
		root.right = new TreeNode(10);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(6);
		root.right.right = new TreeNode(14);
		root.right.right.left = new TreeNode(13);
		root.left.right.left = new TreeNode(4);
		root.left.right.right = new TreeNode(7);
		TreeNode.printNode(root);
		M1026_MaxDifferenceBetNodeAncestor cl = new M1026_MaxDifferenceBetNodeAncestor();
		System.out.println(cl.maxAncestorDiff(root));
		
	}
}
