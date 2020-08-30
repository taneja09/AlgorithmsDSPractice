package LC_Easy;

import Helpers.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class E0226_InvertBinaryTree {
	
	/************* Recursive Approach TC = O(n^2) SC = O(1) ************/
	public TreeNode invertTree(TreeNode root) {
		TreeNode node = root;
        if(node == null) return null;
		invertTree(node.left);
		invertTree(node.right);
		
		TreeNode temp = node.left;
		node.left = node.right;
		node.right = temp;
		
		return node;
    }
    
	/************* Iterative Approach TC = O(n^2) SC = O(1) ************/
	public TreeNode invertTreeIterative(TreeNode root) {
		if(root == null) return null;
		Queue<TreeNode> que = new LinkedList<>();
		TreeNode node = root;
		que.offer(node);
		
		while(!que.isEmpty()){
			TreeNode curr = que.poll();
			TreeNode temp = curr.left;
			curr.left = curr.right;
			curr.right = temp;
			if(curr.left != null)que.add(curr.left);
			if(curr.right!= null) que.add(curr.right);
		}
		
		return node;
	}
	
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		
		E0226_InvertBinaryTree cl = new E0226_InvertBinaryTree();
		TreeNode.printNode(root);
		//TreeNode node = cl.invertTree(root);
		TreeNode node1 = cl.invertTreeIterative(root);
		//TreeNode.printNode(node);
		TreeNode.printNode(node1);
	}
}
