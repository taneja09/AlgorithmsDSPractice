package LC_Easy;
/**
 Time Complexity -  O(1)
 Reason - gpop, top and getmin
 
 Space Complexity - O(n)
 Reason -  saving nodes
 */

public class E0155_MinStack {
	private Node head;
	public void push(int x) {
		if(head == null) head = new Node(x,x);  //starting is null so make new head
		else {
			head = new Node(x,Math.min(x,head.min),head);  // there is a node already, create new and take minimum of existing and new node
		}
	}
	
	public void pop() {
		head = head.next;  //pop the head
	}
	
	public int top() {
		return head.val;
	}
	
	public int getMin() {
		return head.min;
	}
	
	public static void main(String[] args) {
		E0155_MinStack cl = new E0155_MinStack();
		cl.push(-2);
		cl.push(0);
		cl.push(-1);
		System.out.println(cl.getMin());
		System.out.println(cl.top());
		cl.pop();
		System.out.println(cl.getMin());
	}
}

class Node {
	int val;
	int min;
	Node next;
	
	public Node(int val, int min) {
		this(val, min, null);
	}
	
	public Node(int val, int min, Node next) {
		this.val = val;
		this.min = min;
		this.next = next;
	}
}