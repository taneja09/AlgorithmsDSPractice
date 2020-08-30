package LC_Medium;

import Helpers.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class M0094_BinaryTreeInOrder {

    public List<Integer> inorderTraversal(TreeNode root) {

        ArrayList<Integer> al = new ArrayList();
        return getTraversal(root, al);

    }

    public List<Integer>  getTraversal(TreeNode root, ArrayList<Integer> al ){
        if(root == null)
            return al;

        getTraversal(root.left, al);
        al.add(root.val);
        getTraversal(root.right, al);

        return al;
    }

    public List<Integer> inorderTraversalIterative(TreeNode root) {
        ArrayList<Integer> al = new ArrayList();
        Stack<TreeNode> st = new Stack();
        TreeNode curr = root;

        // if current node is null and stack is also empty, we're done
        while (!st.empty() || curr != null) {

            if (curr != null) {
                st.push(curr);
                curr = curr.left;
            } else {
                curr = st.pop();
                al.add(curr.val);
                curr = curr.right;
            }
        }

        return al;
    }


        public static void main(String[] args) {

            TreeNode root = TreeNode.createRandomBST(3);
            M0094_BinaryTreeInOrder cl = new M0094_BinaryTreeInOrder();
            //List<Integer> result = cl.inorderTraversal(root);
            List<Integer> result = cl.inorderTraversalIterative(root);
            for( int i : result)
                System.out.print(i);

        }

    }
