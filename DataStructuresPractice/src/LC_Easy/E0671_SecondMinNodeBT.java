package LC_Easy;

import Helpers.TreeNode;
/*
just imagine we are trying to find second minimum value in both left and right nodes,
if the node value is the same as root value then considering we already have the minimum value (root),
continue to find second minimum value in the subtree. Hope it helps!
 */
public class E0671_SecondMinNodeBT {
	public int findSecondMinimumValue(TreeNode root) {
		if (root == null) return -1;
		int l = root.left.val == root.val ? findSecondMinimumValue(root.left) : root.left.val;
		int r = root.right.val == root.val ? findSecondMinimumValue(root.right) : root.right.val;
		
		return l == -1 || r == -1 ? Math.max(l, r) : Math.min(l, r);
	}
	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(4);
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(8);
		root.right.right = new TreeNode(7);
		
		E0671_SecondMinNodeBT cl = new E0671_SecondMinNodeBT();
		System.out.println(cl.findSecondMinimumValue(root));
		
	}
}
