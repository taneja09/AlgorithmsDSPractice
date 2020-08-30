package LC_Easy;

import Helpers.ListNode;
/**
 *  Time Complexity - 0(n+m) where n length of array
 *  Reason - accessing all elements of array with all characters of strings
 *
 *  Space Complexity - 0(1)
 *  Reason - Constant space
 */
public class E0021_MergeTwoSortedLists {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1 == null && l2 == null) return null;
		if(l1 == null || l2 == null) return l1!= null ? l1 : l2;
		ListNode head = new ListNode(-1) ;
		ListNode result = head;
		while( l1 != null && l2 != null ){
			if(l1.val < l2.val){
				head.next =  new ListNode(l1.val);
				head = head.next;
				l1 = l1.next;
			}else{
				head.next =  new ListNode(l2.val);
				head = head.next;
				l2 = l2.next;
			}
		}
		
		if(l1 != null) head.next = l1;
		if(l2 != null) head.next = l2;
		
		return result.next;
		
    }
    
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
  		l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
		l1.next.next.next = null;
		
		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);
		l2.next.next.next = null;
		
		E0021_MergeTwoSortedLists cl = new E0021_MergeTwoSortedLists();
		ListNode result =  cl.mergeTwoLists(l1,l2);
		ListNode.printLinkList(result);
		
	}
}
