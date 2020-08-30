package LC_Medium;

import Helpers.TreeNode;
import apple.laf.JRSUIUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class M0449_SerializeAndDeserializeBST {
	// Encodes a tree to a single string.
	private static final String spliter = ",";
	private static final String NN = "X";
	
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		buildString(root, sb);
		return sb.toString();
		
	}
	
	private void buildString(TreeNode node, StringBuilder sb){
		
		if (node == null) {
			sb.append("null" + spliter);
		} else{
			sb.append(node.val + spliter);
			buildString(node.left, sb);
			buildString(node.right, sb);
		}
		
		return;
	}
	
	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		Queue<String> que = new LinkedList<>(Arrays.asList(data.split(spliter)));
		return buildTree(que);
		
	}
	
	private TreeNode buildTree(Queue<String> que){
		String cur = que.poll();
		if (cur.equals("null")) {
			return null;
		}else {
			TreeNode root = new TreeNode(Integer.valueOf(cur));
			root.left = buildTree(que);
			root.right = buildTree(que);
			return root;
		}
	}
	
	public static void main(String[] args) {
		
		M0449_SerializeAndDeserializeBST cl = new M0449_SerializeAndDeserializeBST();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(6);
		root.right = new TreeNode(14);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(7);
		root.right.left = new TreeNode(19);
		root.right.right = new TreeNode(16);
		TreeNode.printNode(root);
		String res = cl.serialize(root);
		System.out.println("serialize" + res);
		TreeNode deserialize = cl.deserialize(res);
		TreeNode.printNode(deserialize);
		
	}
}
