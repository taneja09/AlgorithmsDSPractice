package Helpers;

import java.util.*;

public class TreeTraversal {
    /**
     *Iterative solution without stack usage.
     * 1. push elements into stack one by one and recur till end of left child
     * 2. if curr == null, pop from stack and make it curr and print
     * 3. Add the right child of curr popped recursively left branch and again repeat
     */
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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        if(root == null) return result;

        que.offer(root);

        while(!que.isEmpty()){
            int level = que.size();  //current level size to pop them out in one arrayList
            List<Integer> subList = new LinkedList<Integer>();  // List for each level
            for(int i  = 0; i< level; i++){
                if(que.peek().left != null) que.offer(que.peek().left);  //added left node to que if not null
                if(que.peek().right != null) que.offer(que.peek().right);  //added right node to que if not null
                subList.add(que.poll().val);
            }

            result.add(subList);
        }

        return result;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        if(root == null) return result;

        que.offer(root);
        boolean direction = true;

        while(!que.isEmpty()){
            int level = que.size();
            List<Integer> subList = new LinkedList<Integer>();  // List for each level

            for(int i  = 0; i< level; i++){
                if (que.peek().left != null) que.offer(que.peek().left);
                if (que.peek().right != null) que.offer(que.peek().right);

                if(direction)
                    subList.add(que.poll().val);
                else
                    subList.add(0,que.poll().val);  // reversing the order while adding to list
            }

            direction = direction ? false : true;  // quite hacky -- checks the value of direction and reverse the value
            result.add(subList);
        }

        return result;
    }
}
