package LC_Medium;

import Helpers.TreeNode;
import java.util.*;

/**
 Recursive Solution
 Time Complexity - O(n)
 Reason - All tree nodes are accesed iteratively
 
 Space Complexity - O(n)
 Reason -  queue is used for storing the nodes
 */

/**
 * Compilation of multiple binary tree questions
 * https://leetcode.com/problems/binary-tree-level-order-traversal/discuss/114449/A-general-approach-to-level-order-traversal-questions-in-Java
 */
public class M0102_BinaryTreeLevelOrder {
    
    /************ DFS Solution *************************/
    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelHelper(res, root, 0);
        return res;
    }
    
    private void levelHelper(List<List<Integer>> res,TreeNode root, int level ){
        if (root == null) return;
        if (level == res.size()) {
            res.add(new LinkedList());
        }
        res.get(level).add(root.val); // will add the values for the referred level
        levelHelper(res, root.left, level+1);
        levelHelper(res, root.right, level+1);
    }
    
    /******** BFS Solution *************************/
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        Queue<TreeNode> que = new LinkedList();
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
    
    public static void main(String[] args) {
        
        TreeNode root = TreeNode.createRandomBST(5);
        M0102_BinaryTreeLevelOrder cl = new M0102_BinaryTreeLevelOrder();
        // List<List<Integer>> result = cl.levelOrder(root);
        List<List<Integer>> result = cl.levelOrderDFS(root);
        for(List<Integer> k : result){
            System.out.println(k.toString());
        }
        
    }
}
