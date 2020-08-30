package LC_Medium;

import Helpers.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 
 Time Complexity - O(m+n)
 Reason - One pass
 
 Space Complexity - O(n)
 Reason - Saving in HashMap for inorder tree
*/

/**https://www.youtube.com/watch?v=PoBGyrIWisE
 *
 * Our aim is to find out the index of right child for current node in the preorder array
 * We know the index of current node in the preorder array - preStart (or whatever your call it), it's the root of a subtree
 * Remember pre order traversal always visit all the node on left branch before going to the right ( root -> left -> ... -> right),
 * therefore, we can get the immediate right child index by skipping all the node on the left branches/subtrees of current node
 *
 * The inorder array has this information exactly. Remember when we found the root in "inorder" array.
 * we immediately know how many nodes are on the left subtree and how many are on the right subtree
 * Therefore the immediate right child index is preStart + numsOnLeft + 1
 * (remember in preorder traversal array root is always ahead of children nodes but you don't know which one is the left child which one is the right, and this is why we need inorder array)
 * numsOnLeft = root - inStart.
 **/

/*
Run
 */

public class M0105_MakeTreePreAndInorder {
    /****************** With HashMap TC = O(n) & SC = O(n) ********************************/
    public TreeNode buildTreeWithMap(int[] preorder, int[] inorder) {
   		if(preorder == null || inorder == null || (preorder.length != inorder.length )) return null;
   		Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
   		for(int i = 0; i < inorder.length; i++) {
   	        inMap.put(inorder[i], i);
   	    }
   		return getResult (preorder,inorder,0,inorder.length-1, 0, inMap);
   		
   	}
   	
   	private TreeNode getResult(int[] preorder, int[] inorder, int inOrderStart, int inOrderEnd, int preStart, Map<Integer, Integer> inMap ){
   		if(preStart > preorder.length - 1 || inOrderStart> inOrderEnd) return null;
   		TreeNode node = new TreeNode(preorder[preStart]);
   		int inIndex = inMap.get(node.val);
   		node.left = getResult(preorder,inorder,inOrderStart, inIndex-1,preStart+1,inMap);
   		node.right = getResult(preorder,inorder,inIndex+1,inOrderEnd,preStart+inIndex-inOrderStart+1,inMap);
   		
   		return node;
   	}
    
    
    /****************** Without HashMap TC = O(n) & SC = O(1) ********************************/
    public TreeNode buildTree(int[] preorder, int[] inorder) {
		if(preorder == null || inorder == null || (preorder.length != inorder.length )) return null;
		return getResult (preorder,inorder,0,inorder.length-1, 0);
		
	}
	
	private TreeNode getResult(int[] preorder, int[] inorder, int inOrderStart, int inOrderEnd, int preStart){
		if(preStart > preorder.length - 1 || inOrderStart> inOrderEnd) return null;
		TreeNode node = new TreeNode(preorder[preStart]);
		int inIndex = 0; // Index of current root in inorder
		for(int i = inOrderStart ; i<= inOrderEnd; i++) {
			if(node.val == inorder[i]) inIndex = i;
		}
		
		node.left = getResult(preorder,inorder,inOrderStart, inIndex-1,preStart+1);
		node.right = getResult(preorder,inorder,inIndex+1,inOrderEnd,preStart+inIndex-inOrderStart+1);
		
		return node;
	}
    public static void main(String[] args) {

        M0105_MakeTreePreAndInorder cl = new M0105_MakeTreePreAndInorder();
        int[] preOrder = {3,9,20,15,7};
        int[] inOrder = {9,3,15,20,7};
       // TreeNode root = cl.buildTree(preOrder,inOrder);
        TreeNode root = cl.buildTreeWithMap(preOrder,inOrder);
        TreeNode.print(root);
    }
}
