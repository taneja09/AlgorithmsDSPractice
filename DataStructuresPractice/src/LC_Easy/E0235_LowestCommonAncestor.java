package LC_Easy;

import Helpers.TreeNode;

public class E0235_LowestCommonAncestor {
	
	/************* Iterative approach TC = O(n) n = nodes SC = O(1)********************************/
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode node = root;
		
		while(node != null){
			if(p.val > node.val && q.val > node.val) node = node.right;
			else if(p.val < node.val && q.val < node.val) node = node.left;
			else return node;
		}
		return null;
	}
	
	/************* Recursive approach TC = O(n) n = nodes SC = O(1)********************************/
	
	
	public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
		
		if(p.val < root.val && q.val < root.val ) return lowestCommonAncestor(root.left,p,q);
		else if(p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right,p,q);
		else return root;//// We have found the split point, i.e. the LCA node.
	}
	
	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(6);
		root.left = new TreeNode(2);
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(7);
		root.right.right = new TreeNode(9);
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(4);
		root.left.right.left = new TreeNode(3);
		root.left.right.right = new TreeNode(5);
		E0235_LowestCommonAncestor cl = new E0235_LowestCommonAncestor();
		TreeNode result = cl.lowestCommonAncestor(root, root.left, root.left.right);
		System.out.println(result.val);
		//TreeNode resultRec = cl.lowestCommonAncestorRecursive(root, root.left, root.left.left);
		//System.out.println(resultRec.val);
	}
}
