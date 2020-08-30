package LC_Easy;

import Helpers.TreeNode;

import java.util.Stack;

public class E0938_RangeSumBST {
	public int rangeSumBSTEasy(TreeNode root, int L, int R) {
		if(root == null) return 0;
		if(root.val > R) return rangeSumBST(root.left, L, R);
		if(root.val < L) return rangeSumBST(root.right, L, R);
		return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
	}
	/*==================================================== */
	int sum = 0;
	public int rangeSumBST(TreeNode root, int L, int R) {
		dfs(root, L, R);
		return sum;
	}
	
	private void dfs(TreeNode root, int L, int R){
		if(root == null) return ;
		if(L <= root.val && R >= root.val){
			sum += root.val;
		}
		if (L < root.val)
			dfs(root.left, L, R);
		if (root.val < R)
			dfs(root.right, L, R);
	}
	
	/*==================================================== */
	
	public int rangeSumBSTIterative(TreeNode root, int L, int R) {
		int ans = 0;
		Stack<TreeNode> stack = new Stack();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			if (node != null) {
				if (L <= node.val && node.val <= R)
					ans += node.val;
				if (L < node.val)
					stack.push(node.left);
				if (node.val < R)
					stack.push(node.right);
			}
		}
		
		return ans;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.right = new TreeNode(15);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(7);
		root.right.right = new TreeNode(18);
		
		E0938_RangeSumBST cl = new E0938_RangeSumBST();
		System.out.println(cl.rangeSumBST(root, 7, 15));
	}
}
