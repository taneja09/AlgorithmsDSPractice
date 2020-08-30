package LC_Hard;

import Helpers.TreeNode;
import apple.laf.JRSUIUtils;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class H0297_SerializeAndDeserializeBT {
	
	private static final String spliter = ",";
	private static final String NN = "X";
	
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		buildString(root, sb);
		return sb.toString();
	}
	
	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		Queue<String> que = new LinkedList<>();
		que.addAll(Arrays.asList(data.split(spliter)));
		return buildTree(que);
	}
	
	public TreeNode buildTree(Queue<String> que){
		String val = que.poll();
		if(val.equals(NN)) return null;
		TreeNode node = new TreeNode(Integer.valueOf(val));
		node.left = buildTree(que);
		node.right = buildTree(que);
		return node;
	}
	
	private void buildString(TreeNode node, StringBuilder sb) {
		if(node == null) sb.append(NN).append(spliter);
		else{
			sb.append(node.val).append(spliter);
			buildString(node.left,sb);
			buildString(node.right,sb);
		}
	}
	public static void main(String[] args) {
		H0297_SerializeAndDeserializeBT obj = new H0297_SerializeAndDeserializeBT();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(6);
		root.right = new TreeNode(14);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(7);
		root.right.left = new TreeNode(19);
		root.right.right = new TreeNode(16);
		System.out.println("Original tree: \n");
		TreeNode.printNode(root);
		String res = obj.serialize(root);
		System.out.println("serialize    -->  " + res + "\n");
		TreeNode deserialize = obj.deserialize(res);
		System.out.println("Deserialize tree:");
		TreeNode.printNode(deserialize);
	}
}
