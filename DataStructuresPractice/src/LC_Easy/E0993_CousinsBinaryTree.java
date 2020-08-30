package LC_Easy;

import Helpers.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class E0993_CousinsBinaryTree {

    public boolean isCousins(TreeNode root, int x, int y) {
        return findCousinsRecursive(root, x, null, root, y, null);
        }

     public boolean findCousinsRecursive(TreeNode rootX, int x, TreeNode rootXParent, TreeNode rootY, int y, TreeNode rootYParent){
         if (rootX == null || rootY == null) return false;
         if (rootX.val == x && rootY.val == y && rootXParent != rootYParent) return true;

         return findCousinsRecursive(rootX.left, x, rootX, rootY.left, y, rootY) ||
                 findCousinsRecursive(rootX.left, x, rootX, rootY.right, y, rootY) ||
                 findCousinsRecursive(rootX.right, x, rootX, rootY.left, y, rootY) ||
                 findCousinsRecursive(rootX.right, x, rootX, rootY.right, y, rootY);
     }

    public boolean isCousinsRecursive(TreeNode root, int x, int y) {
        if(root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            boolean isXexist = false;
            boolean isYexist = false;

            for(int i = 0; i< size; i++){
                TreeNode curr = queue.poll();
                if(curr.val == x) isXexist = true;
                if(curr.val == y) isYexist = true;
                if(curr.left != null && curr.right != null) {
                    if ((curr.left.val == x && curr.right.val == y) || (curr.left.val == y && curr.right.val == x))
                        return false;
                }
                if(curr.left != null)queue.offer(curr.left);
                if(curr.right != null)queue.offer(curr.right);
            }
            if (isXexist && isYexist)  return true;

            }
        return false;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        E0993_CousinsBinaryTree cl = new E0993_CousinsBinaryTree();
        int x = 4, y = 5;
        //boolean result = cl.isCousins(root, x, y);
        boolean result = cl.isCousinsRecursive(root, x, y);
        System.out.println(result);

    }
}
