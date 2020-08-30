package LC_Easy;
/**
 *  Time Complexity - 0(n+m) where n length of array
 *  Reason - accessing all elements of both linkedlist
 *
 *  Space Complexity - 0(1)
 *  Reason - Constant space
 */
import Helpers.ListNode;

public class E0160_IntersectionOfTwoLinkedList {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		ListNode s1 = headA, s2 = headB; int c1=0, c2=0 ,diff= 0;
		
		while (s1 != null) {
			c1++;
			s1 = s1.next;
		}
		while (s2 != null) {
			c2++;
			s2 = s2.next;
		}
		s1 = headA; s2 = headB;
		
		if(c1 > c2){
			diff = c1-c2;
			while(diff > 0 && s1 != null){
				s1 = s1.next;
				diff--;
			}
		}else{
			diff = c2-c1;
			while(diff > 0 && s2 != null){
				s2 = s2.next;
				diff--;
			}
		}
		
		while(s1 != s2){
			s1 = s1.next;
			s2 = s2.next;
		}
		
		return s1;
	}
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(8);
		l1.next.next.next = new ListNode(9);
		l1.next.next.next.next = new ListNode(5);
		
		
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(0);
		l2.next.next = new ListNode(1);
		l2.next.next.next = l1.next.next;
		l2.next.next.next.next = l1.next.next.next;
		l2.next.next.next.next.next = l1.next.next.next.next;
		
		E0160_IntersectionOfTwoLinkedList cl = new E0160_IntersectionOfTwoLinkedList();
		ListNode.printLinkList(cl.getIntersectionNode(l1,l2));
	}
}
