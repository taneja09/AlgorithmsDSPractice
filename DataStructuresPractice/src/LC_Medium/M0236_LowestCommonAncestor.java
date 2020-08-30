package LC_Medium;
/**
 Time Complexity -  O(n)
 Reason - single pass
 
 Space Complexity - O(n) where n is number of nodes
 Reason -   Storing nodes in space of hashmap, hash & stack
 */

import Helpers.TreeNode;
import java.util.*;
public class M0236_LowestCommonAncestor {
	/********************** Recursive Solution TC = O(n) && SC = O(1)****************/
	//https://www.youtube.com/watch?v=uKhLoNaG9LI
	public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
		if( root == p || root == q || root == null) return root;
		TreeNode left = lowestCommonAncestorRecursive( root.left,  p,  q);
		TreeNode right = lowestCommonAncestorRecursive( root.right,  p,  q);
		if(left != null && right != null)   return root;
		return left != null ? left : right;
	}
	
	/********************** Iterative Solution TC = O(n) && SC = O(n)****************/
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		HashMap<TreeNode,TreeNode> hm = new HashMap();  //node and root combination will be there in map
		Stack<TreeNode> st = new Stack<>();
		HashSet<TreeNode> hs = new HashSet();
		
		st.push(root);
		hm.put(root,null);
		
		while( !hm.containsKey(p) || !hm.containsKey(q)){
			TreeNode rnode = st.pop();
			if(rnode.left!= null) {
				st.push(rnode.left);
				hm.put(rnode.left,rnode);
			}
			if(rnode.right!= null) {
				st.push(rnode.right);
				hm.put(rnode.right,rnode);
			}
		}
		
		TreeNode first = p;
		TreeNode second = q;
		
		while(first != null) {
			hs.add(first);
			first = hm.get(first);
		}
		
		while(!hs.contains(second)){
			second = hm.get(second);
		}
		
		return second;
		
	}
	

	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(4);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(6);
		root.left.right = new TreeNode(8);
		M0236_LowestCommonAncestor cl = new M0236_LowestCommonAncestor();
		TreeNode result = cl.lowestCommonAncestor(root, root.left, root.left.left);
		System.out.println(result.val);
		TreeNode resultRec = cl.lowestCommonAncestorRecursive(root, root.left, root.left.left);
		System.out.println(resultRec.val);
	}
}
