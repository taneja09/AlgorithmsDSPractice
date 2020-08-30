package LC_Medium;
/**
 Recursive Solution
 Time Complexity - O(n)
 Reason - All tree nodes are accessed iteratively
 
 Space Complexity - O(n)
 Reason -  queue is used for storing the nodes
 */
import Helpers.TreeNode;

import java.util.*;

public class M0103_BinaryTreeZigZagTraversal {
    
    /*************** Recursive Solution **********************************/
    public List<List<Integer>> zigzagLevelOrderRecursive(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelHelper(res, root, 0);
        
        for(int i = 0; i<res.size(); i++){
            if(i % 2 != 0)  Collections.reverse(res.get(i));
        }
        return res;
    }
    
    private void levelHelper(List<List<Integer>> res, TreeNode root, int height){
        if(root == null) return;
        if(height == res.size())
            res.add(new ArrayList<>());
        
        res.get(height).add(root.val);
        levelHelper(res, root.left, height +1);
        levelHelper(res, root.right, height +1);
    }
    
    
    /*************** Iterative Solution **********************************/
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
    
    public static void main(String[] args) {
        
        TreeNode root = TreeNode.createRandomBST(7);
        M0103_BinaryTreeZigZagTraversal cl = new M0103_BinaryTreeZigZagTraversal();
        //List<List<Integer>> result = cl.zigzagLevelOrder(root);
        List<List<Integer>> result = cl.zigzagLevelOrderRecursive(root);
        for(List<Integer> k : result){
            System.out.println(k.toString());
        }
        
    }
}
