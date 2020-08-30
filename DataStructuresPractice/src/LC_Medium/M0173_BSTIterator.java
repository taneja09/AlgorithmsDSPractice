package LC_Medium;
/*
Notes :

When analyzing amortized time complexities, I find it easiest to reason that each node gets pushed and popped exactly once in next() when iterating over all N nodes.
That comes out to 2N * O(1) over N calls to next(), making it O(1) on average, or O(1) amortized.
 */
import Helpers.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*******************************Iterative  TC = O(1)  because every node goes and comes out of stack in O(1) exactly once
 * & SC = O(n) n is number of nodes in Tree
 *
 * Push the left tree and then one by one after popping check if it has right and push it in stack
 *
 * *****************************************/
class M0173_BSTIteratorIterative {
	Stack<TreeNode> st = new Stack<>();
	public M0173_BSTIteratorIterative(TreeNode root) {
		helperInsertLefTree(root,st);
	}
	
	private void helperInsertLefTree(TreeNode root,Stack<TreeNode> st){
		while(root != null){
			st.add(root);
			root = root.left;
		}
	}

	/** @return the next smallest number */
	public int nextIteartive() {
		TreeNode x = st.pop();
		if(x.right != null)
		helperInsertLefTree(x.right,st);
		return x.val;
	}
	
	/** @return whether we have a next smallest number */
	public boolean hasNextIterative() {
		return !st.isEmpty();
	}
}


/*******************************Recursive TC = O(n) & SC = O(n) n is number of nodes in Tree *****************************************/
public class M0173_BSTIterator {
	Queue<TreeNode> q = new LinkedList<>();
	public M0173_BSTIterator(TreeNode root) {
		fillQueue(q,root);
	}
	
	private void fillQueue(Queue<TreeNode> q,TreeNode root){
		if(root == null) return;
		fillQueue(q,root.left);
		q.add(root);
		fillQueue(q,root.right);
	}
	/** @return the next smallest number */
	public int next() {
		return q.poll().val;
	}
	
	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !q.isEmpty();
	}
	/************************************************************************/
	
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(7);
		root.left = new TreeNode(3);
		root.right = new TreeNode(15);
		root.right.left = new TreeNode(9);
		root.right.right = new TreeNode(20);
		M0173_BSTIterator cl = new M0173_BSTIterator(root);
		System.out.println(cl.next());
		System.out.println(cl.next());
		System.out.println(cl.hasNext());
		System.out.println(cl.next());
		System.out.println(cl.hasNext());
		System.out.println(cl.next());
		System.out.println(cl.hasNext());
		System.out.println(cl.next());
		System.out.println(cl.hasNext());
	}
}
