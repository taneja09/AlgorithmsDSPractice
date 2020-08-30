package LC_Medium;

import Helpers.TreeNode;

import java.util.ArrayList;
import java.util.Stack;
/**
 
 Time Complexity - O(n)
 Reason - n is number of tree nodes
 
 Space Complexity - O(n)
 Reason - for stack usage of n nodes in DFS
*/
public class M0230_KthSmallestBST {

    public int kthSmallest(TreeNode root, int k) {
        // Simple inorder traversal for BST will return elements in order from smallest to largest
        Stack<TreeNode> st = new Stack();
        while((root != null || !st.isEmpty())){
            while(root != null){
                st.push(root);
                root = root.left ;
            }
            root = st.pop(); k--;
            if(k == 0) break;  // traversed k elements break and come out of loop , current root point to kth smallest
            else root = root.right;
        }
        return root.val;
    }

    public int kthSmallestRecursive(TreeNode root, int k) {
        ArrayList<Integer> nums = inOrderRecursive(root, new ArrayList<Integer>());
        return nums.get(k - 1);
    }

    public ArrayList<Integer>inOrderRecursive(TreeNode root, ArrayList<Integer> al){

        if(root == null) return al;
        inOrderRecursive(root.left,al);
        al.add(root.val);
        inOrderRecursive(root.right,al);
        return al;

    }



    public static void main(String[] args) {
        TreeNode root = TreeNode.createRandomBST(8);
        M0230_KthSmallestBST cl = new M0230_KthSmallestBST();
        int k = 3;
        //int result = cl.kthSmallest(root,k);
        int result = cl.kthSmallestRecursive(root,k);
        System.out.println(result);

    }
}
