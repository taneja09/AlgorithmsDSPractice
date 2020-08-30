package LC_Medium;
/*
Carifying output order:
output is sorted by X;
all elements with the same X are grouped together;
grouped together elements are sorted by Y (closest to root first);
If elements have the same Y - they are sorted by value (accending).
 */
import Helpers.TreeNode;
import java.util.*;

public class M0987_VerticalOrderTraversalBT {
	public List<List<Integer>> verticalTraversal(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
		int x = 0;
		int y = 0;
		dfsTree(map,x,y,root);
		for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
			List<Integer> list = new ArrayList<>();
			for (PriorityQueue<Integer> pq : ys.values()) {
				while (!pq.isEmpty()) {
					list.add(pq.poll());
				}
			}
			result.add(list);
		}
		return result;
	}
	
	private void dfsTree(TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>  map, int x, int y, TreeNode root){
		if(root == null) return;
		if (!map.containsKey(x)) {
			map.put(x, new TreeMap<>());
		}
		if (!map.get(x).containsKey(y)) {
			map.get(x).put(y, new PriorityQueue<>());
		}
		map.get(x).get(y).offer(root.val);
		dfsTree(map,x-1,y+1,root.left);
		dfsTree(map,x+1,y+1,root.right);
	}
	public static void main(String[] args) {
		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(5);
		root.left.left = new TreeNode(9);
		root.right = new TreeNode(1);
		root.right.left = new TreeNode(2);
		root.right.left.right = new TreeNode(3);
		root.right.left.right.right = new TreeNode(8);
		root.right.left.right.left = new TreeNode(4);
		root.right.left.right.left.left = new TreeNode(6);
		root.right.left.right.left.left.left = new TreeNode(7);
		M0987_VerticalOrderTraversalBT cl = new M0987_VerticalOrderTraversalBT();
		List<List<Integer>> result = cl.verticalTraversal(root);
		for(List<Integer> x : result) System.out.println(x);
	}
}
