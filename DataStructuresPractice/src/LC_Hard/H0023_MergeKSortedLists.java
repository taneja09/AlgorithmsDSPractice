package LC_Hard;

import Helpers.ListNode;

import java.util.PriorityQueue;

public class H0023_MergeKSortedLists {
	
	/*************** Merge Sort  TC = O(n*k logk) where n is total number of nodes n klog for merge sort*****************/
	public ListNode mergeKListsMergeSort(ListNode[] lists) {
		if (lists == null || lists.length == 0) return null;
		return sort(lists,0,lists.length-1);
	}
	
	private ListNode sort(ListNode[] lists, int start, int end){
		if(start>= end) return lists[start];
		int mid  = start + (end-start)/2;
		ListNode l1 = sort(lists, start,mid);
		ListNode l2 = sort(lists, mid+1,end);
		return merge(l1,l2);
		
	}
	
	private ListNode merge(ListNode l1, ListNode l2){
		if(l2 == null) return l1;
		if(l1 == null) return l2;
		if(l1.val < l2.val) {
			l1.next = merge(l1.next,l2);
			return l1;
		}
		l2.next = merge (l1,l2.next);
		return l2;
	}
	/*************** Priority Queue TC = O(n*logk)
	 * where n is total number of nodes in k lists..
	 * logk for node insertion  **********************/
	public ListNode mergeKLists(ListNode[] lists) {
		if(lists == null || lists.length == 0) return null;
		PriorityQueue<ListNode> pq = new PriorityQueue<>((l1,l2)->l1.val - l2.val);
		
		// add first node of each list to the minHeap
		for(ListNode l : lists){
			if(l != null){
				  pq.add(l);
			}
		}
		
		
		ListNode res = new ListNode(-1);
		ListNode node = res;
		while(!pq.isEmpty()){
			res.next = pq.poll();
			res = res.next;
			// add next node to minHeap
			if(res.next != null) pq.add(res.next);
		}
		
		return node.next;
	}
	public static void main(String[] args) {
		ListNode l1 = new ListNode(-2);
		l1.next = new ListNode(-1);
		l1.next.next = new ListNode(-1);
		l1.next.next.next = new ListNode(-1);
		l1.next.next.next.next = null;

//		ListNode l2 = new ListNode(1);
//		l2.next = new ListNode(3);
//		l2.next.next = new ListNode(4);
//		l2.next.next.next = null;
//
//		ListNode l3 = new ListNode(2);
//		l3.next = new ListNode(6);
//		l3.next.next = null;
		
		ListNode l4 = null;
		
		ListNode[] list = new ListNode[]{l1,l4};
		
		H0023_MergeKSortedLists cl = new H0023_MergeKSortedLists();
		ListNode result =  cl.mergeKLists(list);
		ListNode.printLinkList(result);
	}
}
