package LC_Easy;
/**
 
 Time Complexity - O(n)
 Reason -
 
 Space Complexity - O(1)
 Reason -
*/
import Helpers.TreeNode;

public class E0572_SubTreeOfAnotherTree {
	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (s == null) return false;
		if (isSame(s, t)) return true;
		return isSubtree(s.left, t) || isSubtree(s.right, t);
	}

	private boolean isSame(TreeNode s, TreeNode t) {
		if (s == null && t == null) return true;
		if (s == null || t == null) return false;
		if (s.val != t.val) return false;
		return isSame(s.left, t.left) && isSame(s.right,t.right);
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(5);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(6);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(0);
		root.right.right = new TreeNode(8);
		root.left.right.left = new TreeNode(7);
		root.left.right.right = new TreeNode(4);
		
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(0);
		root1.right = new TreeNode(8);
		
		TreeNode.printNode(root);
		TreeNode.printNode(root1);
		
		E0572_SubTreeOfAnotherTree cl = new E0572_SubTreeOfAnotherTree();
		System.out.println(cl.isSubtree(root,root1));
		
	}
}
