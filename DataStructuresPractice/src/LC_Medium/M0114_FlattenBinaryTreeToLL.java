package LC_Medium;
/**
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * -----------
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */

import Helpers.TreeNode;

import java.util.Stack;


public class M0114_FlattenBinaryTreeToLL {

	/********** Recursive Approach  TC = O(n) SC = O(1)*****************************/
	/* =====================  EXPLANATION  ========================================/
	
	we are actually performing a reverse -  postorder traversal {left right root} (post order) ==> {right left root}
	Check flattened tree is reverse of postorder {6->5->4->3->2->1}
	
	Post Order traversal =
	
	public void flatten(TreeNode root) {
	    if (root == null)
	        return;
	    flatten(root.right);
	    flatten(root.left);
	}
	
	PS : set each node's right pointer as the previous one
		 and make left as null;
	*/
	
	public void flattenRecursive(TreeNode root) {
		flatten(root, null);
	}
	
	private TreeNode flatten(TreeNode root, TreeNode prev){
		if(root == null) return prev;
		prev = flatten(root.right,prev);
		prev = flatten(root.left,prev);
		root.right = prev;
		root.left = null;
		prev = root;
		return prev ;
	}
	
	
	/********** Iterative Approach  TC = O(n) SC = O(n)*****************************
	 * Step 1. push root in stack
	 * Step 2. Pop each node from stack and make popped element as right of previous one
	 * Step 3. push right child and then left child of the current node
	 *
	 */
	public void flatten(TreeNode root) {
		if(root == null) return;
		Stack<TreeNode> s = new Stack<>();
		s.push(root);
		TreeNode prev = null, curr = null;
		
		while(!s.isEmpty()){
			curr = s.pop();
			if(prev != null) {prev.right = curr; prev.left=null;}
			if(curr.right != null) s.push(curr.right);
			if(curr.left != null) s.push(curr.left);
			prev = curr;
		}
		curr.right = null;
	}
	
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(6);
		M0114_FlattenBinaryTreeToLL cl = new M0114_FlattenBinaryTreeToLL();
		cl.flattenRecursive(root);
		//cl.flatten(root);
		TreeNode.printNode(root);
	}
}
