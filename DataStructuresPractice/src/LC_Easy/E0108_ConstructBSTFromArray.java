package LC_Easy;

/**
 
 Time Complexity - O(n)
 Reason - One pass
 
 Space Complexity - O(1)
 Reason - internal stack
*/

import Helpers.TreeNode;

public class E0108_ConstructBSTFromArray {
	public TreeNode sortedArrayToBST(int[] nums) {
       if(nums.length == 0) return null;
       if(nums.length == 1) return new TreeNode(nums[0]);
       
      return helper(nums,0,nums.length-1);
    }
    
    private TreeNode helper(int[] nums, int start, int end){
		if(start > end) return null;
		
		int mid = (start + end) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = helper(nums,start,mid-1);
		root.right = helper(nums,mid+1,end);
		
		return root;
	}
	public static void main(String[] args) {
		int[] array = {-10,-3,0,5,9};
		E0108_ConstructBSTFromArray cl = new E0108_ConstructBSTFromArray();
		TreeNode res = cl.sortedArrayToBST(array);
		TreeNode.printNode(res);
	}
}
