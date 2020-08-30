package LC_Medium;

import java.util.LinkedList;
import java.util.Queue;

public class M0116_NextRightPointer {
    /*============================ Recursive Solution===================================/
     /**
      *
      * TC - O(n)
      * SC - O(n)
      */
    
    public Node connect(Node root) {
      Queue<Node> que = new LinkedList();
      if(root == null) return null;
      que.offer(root);
      
      while(!que.isEmpty()){
          int level = que.size();  //current level size
		  Node prev = null; // for sake of connections
		  
          for(int i  = 0; i< level; i++){
          	  Node x = que.poll();
          	  if(prev != null) prev.next = x;  //poll current node and check if there is prev node at that level & connect
          	  prev = x;
              if(x.left != null) que.offer(x.left);  //added left node to que
              if(x.right != null) que.offer(x.right);  //added right node to que
          }
      }
      
      return root;
    }
    
    /*============================ Recursive Solution===================================/
    /**
     * Recursive Solution - classy
     * TC - O(n)
     * SC - O(1)
     */
    
    public Node connectRecursive(Node root) {
           connectLinks(root);
           return root;
       }
       
       public void connectLinks(Node root) {
           if (root == null || root.left == null) return;
           root.left.next = root.right;  //Join Children of current root
           
           if (root.next != null)  // join cousins by checking if root has a sibling  i.e.  root = 2
               root.right.next = root.next.left;
           
   		//recur for both left and right children of root
           connectLinks(root.left);
           connectLinks(root.right);
       }
    
    public static void main(String[] args) {
        Node m = new Node(1);
        m.left = new Node(2);
        m.right = new Node(3);
        m.left.left =  new Node(4);
        m.left.right =  new Node(5);
        m.right.left = new Node(6);
        m.right.right = new Node(7);
        
        M0116_NextRightPointer cl = new M0116_NextRightPointer();
        //Node x = cl.connect(m);
        Node y = cl.connectRecursive(m);
    }
    
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
    
    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }
    
    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
