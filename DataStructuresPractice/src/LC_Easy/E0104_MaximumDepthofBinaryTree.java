package LC_Easy;

import Helpers.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *  Time Complexity - O(n)
 *  Reason - traversal all nodes
 *
 *  Space Complexity - O(log n)
 *  Reason - internal stack
 */
public class E0104_MaximumDepthofBinaryTree {
	
	/****************** Recursive Solutions ****************************/
	public int maxDepth(TreeNode root) {
	    if(root == null) return 0;
	    return  1+ Math.max(maxDepth(root.left),maxDepth(root.right));
	}
	
	/****************** Iterative Solutions ****************************/
	
	public int maxDepthBFS(TreeNode root) {
     Queue<TreeNode> que = new LinkedList();
     int depth = 0;
     if(root == null) return depth;
     
     que.offer(root);
     
     while(!que.isEmpty()){
         int level = que.size();  //current level size to pop them out in one arrayList
         List<Integer> subList = new LinkedList<Integer>();  // List for each level
         for(int i  = 0; i< level; i++){
             if(que.peek().left != null) que.offer(que.peek().left);  //added left node to que if not null
             if(que.peek().right != null) que.offer(que.peek().right);  //added right node to que if not null
             subList.add(que.poll().val);
         }
         
         depth++;
     }
     
     return depth;
 }
	
	
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right =  new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		
		E0104_MaximumDepthofBinaryTree cl = new E0104_MaximumDepthofBinaryTree();
		System.out.println(cl.maxDepth(root));
		System.out.println(cl.maxDepthBFS(root));
	}
}
