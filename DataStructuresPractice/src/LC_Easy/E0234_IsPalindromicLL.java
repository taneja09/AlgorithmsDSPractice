package LC_Easy;

import Helpers.ListNode;

import java.util.ArrayList;

public class E0234_IsPalindromicLL {
	ListNode ref;
	/******************* Most Efficient Approach TC = O(n) SC = O(n) Arraylist space ***************/
	public boolean isPalindromeRecursive(ListNode head) {
		if(head == null || head.next == null) return true;
		ref = head;
		return helper(head);
	}
	
	private boolean helper(ListNode node){
		if(node == null) return true;
		boolean pal = helper(node.next);
		boolean isEqual = node.val == ref.val ? true : false;
		ref = ref.next;
		return pal && isEqual;
	}
	
	/******************* Iterative Approach TC = O(n) SC = O(n) Arraylist space ***************/
	public boolean isPalindromeIterative(ListNode head) {
		if(head == null || head.next == null) return true;
		ArrayList<ListNode> al = new ArrayList<>();
		
		while(head != null){
			al.add(head);
			head = head.next;
		}
		
		int start = 0;
		int end = al.size()-1;
		
		while(start < end){
			if(al.get(start).val != al.get(end).val) return false;
			start++;
			end--;
		}
		
		return true;
	}
	
	
	/**************** LL Reversal Process ***********************************/
	public boolean isPalindrome(ListNode head) {
		if(head == null || head.next == null) return true;
		ListNode first = head , second = head.next;
		while(first != null && second != null && second.next != null){
			first = first.next;
			second = second.next.next;
		}
		
		second = first.next;
		first.next = null;
		first = head;
		
		//reverse second list
		second = reversList(second);
		
		//compare both linkedLists
		while(first != null && second != null){
			if(first.val != second.val) return false;
			first = first.next;
			second = second.next;
		}
		
		if( (first == null || first.next == null) && second == null) return true;
		else return false;
	}
	
	
	private ListNode reversList(ListNode node){
		if(node == null || node.next == null) return node;
		ListNode res = reversList(node.next);
		node.next.next = node;
		node.next = null;
		return res;
	}
	public static void main(String[] args) {
		ListNode head = new ListNode(-129);
		head.next = new ListNode(-129);
	//	head.next.next = new LinkListNode(2);
		//head.next.next.next = new LinkListNode(1);
		//head.next.next.next.next = null;
		head.next.next = null;
		E0234_IsPalindromicLL cl = new E0234_IsPalindromicLL();
		System.out.println(cl.isPalindromeRecursive(head));
		
	}
}
