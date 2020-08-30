package LC_Medium;

import Helpers.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * there are only two scenarios at the end: root is robbed or is not.
 * If it is, due to the constraint that "we cannot rob any two directly-linked houses",
 * the next level of subtrees that are available would be the four "grandchild-subtrees"
 * (root.left.left, root.left.right, root.right.left, root.right.right).
 * However if root is not robbed, the next level of available subtrees would just be the two "child-subtrees" (root.left, root.right).
 */

public class M0337_HouseRobberIII {
	
	/******************************** Most Efficient Approach TC = 2^n SC = O(2^n)************************************************/
	
	//res[0] where root is excluded & res[1] where root is included = > this is done for all the nodes
	private int robDPSolution(TreeNode root){
		int[] res = robSub(root);
		return Math.max(res[0], res[1]);
	
	}
	
	private int[] robSub(TreeNode root) {
		if (root == null) return new int[2];  // new array with 0 values in all indexes
		
		int[] left = robSub(root.left);
		int[] right = robSub(root.right);
		
		int[] res = new int[2];
		
		//res[0] where root is excluded and we have choice of including or excluding the child
		// that's why [0] and [1] has been considered for both right and left child
		res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		
		//res[1] where root is included and then we need to exclude left an right child thats why [0] which denotes exclusion
		res[1] = root.val + left[0] + right[0];
		return res;
	}
	
	/***************************TLE  - Time Limit Exceeds *****************************************************/
	
	//TLE - tiime limit exceeds
	public int rob(TreeNode root) {
		if (root == null) return 0;
	    
	    int val = 0;
	    
	    if (root.left != null) {
	        val += rob(root.left.left) + rob(root.left.right);  //left grandchild calculations
	    }
	    
	    if (root.right != null) {
	        val += rob(root.right.left) + rob(root.right.right);  //right grandchild calculations
	    }
	    
	    //Take the max between (root + grandchild => val + root.val) or from direct children(rob(root.left) + rob(root.right))
		//because we can't rob from consecutive 2 layers of tree
	    return Math.max(val + root.val, rob(root.left) + rob(root.right));
	
	}
	
	/*************************** HashMap Design TC = O(n) SC = O(n) nodes on map*****************************************************/
	
	private int robHashing(TreeNode root) {
		return robHashing(root, new HashMap<>()	);
	}
	
	private int robHashing(TreeNode root, Map<TreeNode, Integer> map) {
		if(root == null) return 0;
		if(map.containsKey(root)) return map.get(root);
		
		int val = 0;
		
		if(root.left!= null){
			val+= robHashing(root.left.left, map) + robHashing(root.left.right, map);
		}
		if(root.right!= null){
				val+= robHashing(root.right.left, map) + robHashing(root.right.right, map);
		}
		
		val = Math.max(val+root.val ,robHashing(root.left,map) + robHashing(root.right,map));
		map.put(root,val);
		
		return val;
	}
	/***************************** Easy Solution but not optimized ***************************************************/
	private int robEasySolution(TreeNode root){
		if (root == null) return 0;
		return Math.max(robInclude(root), robExclude(root));
	}
	
	private int robInclude(TreeNode root){
		if(root == null) return 0;
		return robExclude(root.left) + robExclude(root.right) + root.val;
	}
	
	private int robExclude(TreeNode root){
		if(root == null) return 0;
		return rob(root.left) + rob(root.right);
	}
	
	//********************************************************************************//
	

	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(100);
		root.left = new TreeNode(3);
		root.left.left = new TreeNode(5);
		root.left.left.left = new TreeNode(90);
		M0337_HouseRobberIII cl = new M0337_HouseRobberIII();
		
		//int result = cl.rob(root);
		//System.out.print(result);
		
		//int resultHash = cl.robHashing(root);
		//System.out.println(resultHash);
		
		//int resultEasy = cl.robEasySolution(root);
		//System.out.println(resultEasy);
		
		int resultDP = cl.robDPSolution(root);
		System.out.println(resultDP);
	}
}
