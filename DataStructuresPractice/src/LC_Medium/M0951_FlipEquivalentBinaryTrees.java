package LC_Medium;

import Helpers.TreeNode;

import java.util.*;

public class M0951_FlipEquivalentBinaryTrees {
	/* we can just compare all the values are present in both trees that would be our solutions because we can flip them */
	public boolean flipEquivIterative(TreeNode root1, TreeNode root2) {
		List<Integer> vals1 = new ArrayList();
		List<Integer> vals2 = new ArrayList();
		dfs(root1, vals1);
		dfs(root2, vals2);
		return vals1.equals(vals2);
		
	}
	
	private void dfs(TreeNode node, List<Integer> vals){
		if (node != null) {
			vals.add(node.val);
			int L = node.left != null ? node.left.val : -1;
			int R = node.right != null ? node.right.val : -1;
			
			if (L < R) {
				dfs(node.left, vals);
				dfs(node.right, vals);
			}
			else {
				dfs(node.right, vals);
				dfs(node.left, vals);
			}
			
			vals.add(null);
		}
	}
	
	/************** TC = O(min(N1,n2)) *******************/
	public boolean flipEquiv(TreeNode root1, TreeNode root2) {
		if(root1 == null && root2 == null) return true;
		if(root1 == null || root2 == null) return false;
		if(root1.val != root2.val) return false;
		return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right,root2.right)) ||
			(flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
	}
	
	
	public static void main(String[] args) {
		TreeNode rootA = new TreeNode(1);
		rootA.left = new TreeNode(2);
		rootA.right = new TreeNode(3);
		rootA.left.left = new TreeNode(4);
		rootA.left.right = new TreeNode(5);
		rootA.right.left = new TreeNode(6);
		rootA.left.right.left = new TreeNode(7);
		rootA.left.right.right = new TreeNode(8);
		
		TreeNode rootB = new TreeNode(1);
		rootB.left = new TreeNode(3);
		rootB.right = new TreeNode(2);
		rootB.left.right = new TreeNode(6);
		rootB.right.left = new TreeNode(4);
		rootB.right.right = new TreeNode(5);
		rootB.right.right.left = new TreeNode(8);
		rootB.right.right.right = new TreeNode(7);
		
		M0951_FlipEquivalentBinaryTrees cl = new M0951_FlipEquivalentBinaryTrees();
		System.out.println(cl.flipEquiv(rootA,rootB));
		System.out.println(cl.flipEquivIterative(rootA,rootB));
	}
}
