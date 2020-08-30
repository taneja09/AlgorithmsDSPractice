package LC_Medium;

import Helpers.TreeNode;

import java.util.Stack;

/**
 Recursive Solution
 Time Complexity - O(n)
 Reason - 2 for loops
 
 Space Complexity - O(1)
 Reason - dp array
*/

public class M0098_ValidateBinarySearchTree {

    /********** Iterative Solution - TC = O(n)  and SC = O(n) ****************/
   	public boolean isValidBST(TreeNode root) {
     		Stack<TreeNode> st = new Stack();
   		TreeNode curr = root;
   		TreeNode pre = null;
   		
   		while(!st.isEmpty() || curr != null){
   			if(curr!=null){
   				st.push(curr);
   				curr = curr.left;
   			}else{
   				curr = st.pop();
   				if(pre != null && curr.val < pre.val) return false;
   				else {
   					pre = curr;
   					curr = curr.right;
   				}
   			}
   		}
   		
   		return true;
   	}

   	
    /********** Recursive Solution - TC = O(n)  and SC = O(1) ****************/
    
    public boolean isValidBSTRecursive(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal){
        if (root == null) return true;
        if(root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }

    public static void main(String[] args) {

        TreeNode root = TreeNode.createRandomBST(3);
        M0098_ValidateBinarySearchTree cl = new M0098_ValidateBinarySearchTree();
        boolean result = cl.isValidBST(root);
        //boolean result = cl.isValidBSTRecursive(root);
        System.out.println(result);

    }
}
