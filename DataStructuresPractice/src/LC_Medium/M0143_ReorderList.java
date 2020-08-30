package LC_Medium;

import Helpers.ListNode;
/**
 Time Complexity -  O(N)
 Reason - Linear scan
 
 Space Complexity - O(1)
 Reason -  No extra space usage
 */
public class M0143_ReorderList {
	
	public void reorderList(ListNode head) {
		if(head == null) return;
		ListNode first = head, second = head.next; // Pointers for traversal
		
		// Cut the list into two halves
		while(first != null && first.next != null && second != null && second.next != null){
			first =first.next;
			second = second.next.next;
		}
		
		second = first.next; //second half of list
		first.next = null;
		first = head; //first half of list
		
		//Reverse the Linked List
		second = ReverseList(second);
		
		
		
		//Merge both Lists
		ListNode temp1, temp2;
		while(first != null  && second != null  ){
			temp1 = first.next;
			temp2 = second.next;
			first.next = second;
			second.next = temp1;
			first = temp1;
			second = temp2;
		}
	}
	
	//LinkList Reverse
	//https://www.youtube.com/watch?v=O0By4Zq0OFc
	private ListNode ReverseList (ListNode node){
		if (node == null || node.next == null)
			return node;
		ListNode rest = ReverseList(node.next);
		node.next.next = node;
		node.next = null;
		return rest;
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
//		head.next.next.next.next.next = new LinkListNode(6);
//		head.next.next.next.next.next.next = new LinkListNode(7);
//		head.next.next.next.next.next.next.next = new LinkListNode(8);
//		head.next.next.next.next.next.next.next.next = null;
		head.next.next.next.next.next = null;
		
		M0143_ReorderList cl = new M0143_ReorderList();
		cl.reorderList(head);
		ListNode.printLinkList(head);
	}
}
