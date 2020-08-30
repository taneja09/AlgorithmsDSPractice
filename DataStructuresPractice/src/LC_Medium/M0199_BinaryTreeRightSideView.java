package LC_Medium;

import Helpers.TreeNode;

import java.util.*;

public class M0199_BinaryTreeRightSideView {
	/********** Iterative Solution TC = O(n) SC  = O(n) *****************/
	public List<Integer> rightSideViewRecursive(TreeNode root) {
		List<Integer> al = new ArrayList<>();
		if(root == null) return al;
		helperMethod( root, al,0);
		return al;
	}
	
	private void helperMethod(TreeNode root, List<Integer> al, int depth){
		if (root == null) return;
		if (depth == al.size()) al.add(root.val);
		helperMethod(root.right,al,depth+1);
		helperMethod(root.left,al,depth+1);
	}
	
	/********** Iterative Solution TC = O(n) SC  = O(n) *****************/
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> al = new ArrayList<>();
		if(root == null) return al;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()){
			int size = q.size();
			int i =0;
			TreeNode curr = null;
			while(i<size){
				curr = q.poll();
				if(curr.left != null) q.add(curr.left);
				if(curr.right != null) q.add(curr.right);
				i++;
			}
			al.add(curr.val);
		}
		return al;
	}
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(5);
		root.right.right = new TreeNode(4);
		
		M0199_BinaryTreeRightSideView cl = new M0199_BinaryTreeRightSideView();
		List<Integer> res = cl.rightSideViewRecursive(root);
		for(int k : res) System.out.print(k);
	}
}
