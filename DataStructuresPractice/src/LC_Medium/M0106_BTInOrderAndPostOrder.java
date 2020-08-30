package LC_Medium;

import Helpers.TreeNode;

public class M0106_BTInOrderAndPostOrder {
	public TreeNode buildTree(int[] inorder, int[] postorder) {
       return getBinaryTree(postorder.length-1, 0, inorder.length-1,postorder,inorder);
    }
    
    private TreeNode  getBinaryTree(int postIndex, int inStart, int inEnd, int[] postorder, int[] inorder ){
		if(postIndex <0 || inStart > inEnd) return null;
		
		TreeNode root = new TreeNode(postorder[postIndex]);
		int inRootFound = 0;
		for(int i = inStart ; i<= inEnd; i++){
			if(root.val == inorder[i])
				inRootFound = i;
		}
		
		
		root.right = getBinaryTree(postIndex-1,inRootFound+1, inEnd,postorder,inorder);
		root.left = getBinaryTree(postIndex + inRootFound-inEnd-1,inStart,inRootFound-1,postorder,inorder);
		
		return root;
	}
	public static void main(String[] args) {
		int[] inorder = {9,3,15,20,7};
		int[] postorder = {9,15,7,20,3};
		
		M0106_BTInOrderAndPostOrder cl = new M0106_BTInOrderAndPostOrder();
		TreeNode root = cl.buildTree(inorder,postorder);
		TreeNode.printNode(root);
	}
}
