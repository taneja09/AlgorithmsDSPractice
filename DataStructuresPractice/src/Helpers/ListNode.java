package Helpers;

import java.util.Scanner;
//createLinkList
//createCLL
//createFullCLL
//addNodeAtBegining
//addNodeAtEnd
//addNodeAtNthPosition
//deleteNodeAtNthPosition
//deleteNodeFromEnd
//deleteNodeFromBeginning
//mergeLL
//printLinkList
//printCLL
//printLinkListUsingRecursion
//reverseLinkList
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode prev;
    public ListNode down;
    public ListNode random;
    public ListNode(int val){
        this.val = val;
        this.next = null;
        this.prev = null;
        this.down = null;
        this.random = null;
    }

    public static void main(String[] args){
        ListNode head = null;
        printLinkList(head);
        head = addNodeAtBegining(head,4);
        printLinkList(head);
        head = addNodeAtBegining(head,3);
        printLinkList(head);
        head = addNodeAtBegining(head,2);
        printLinkList(head);
        head = addNodeAtBegining(head,1);
        printLinkList(head);
        head = addNodeAtEnd(head,5);
        printLinkList(head);
        head = addNodeAtEnd(head,6);
        printLinkList(head);
        head = addNodeAtNthPosition(head,7,7);
        printLinkList(head);
        head = deleteNodeAtNthPosition(head,7);
        printLinkListUsingRecursion(head);
    }

    public static ListNode createLinkList(){
        ListNode head = null;
        System.out.println("Enter number of elements u wanna have in your linklist.");
        Scanner in =new Scanner(System.in);
        int n = in.nextInt();
        int input;
        System.out.println("Enter "+n+" elements.");
        for(int i = 0;i < n;i++){
            in = new Scanner(System.in);
            input = in.nextInt();
            head = addNodeAtEnd(head,input);
        }
        return head;
    }

    public static ListNode createCLL(){
        ListNode head = null;
        System.out.println("Enter number of elements u wanna have in your linklist.");
        Scanner in =new Scanner(System.in);
        int n = in.nextInt();
        int input;
        System.out.println("Enter "+n+" elements.");
        for(int i = 0;i < n;i++){
            in = new Scanner(System.in);
            input = in.nextInt();
            head = addNodeAtEnd(head,input);
        }
        ListNode ptr = head;
        while(ptr.next != null){
            ptr = ptr.next;
        }
        ptr.next = head.next.next;
        return head;
    }

    public static ListNode createFullCLL(){
        ListNode head = null;
        System.out.println("Enter number of elements u wanna have in your linklist.");
        Scanner in =new Scanner(System.in);
        int n = in.nextInt();
        int input;
        System.out.println("Enter "+n+" elements.");
        for(int i = 0;i < n;i++){
            in = new Scanner(System.in);
            input = in.nextInt();
            head = addNodeAtEnd(head,input);
        }
        ListNode ptr = head;
        while(ptr.next != null){
            ptr = ptr.next;
        }
        ptr.next = head;
        return head;
    }

    public static ListNode addNodeAtBegining(ListNode head, int val){
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        head = newNode;
        return head;
    }

    public static ListNode addNodeAtEnd(ListNode head, int val){
        ListNode newNode = new ListNode(val);
        ListNode ptr = head;
        if(head == null){
            return newNode;
        }
        else{
            while(ptr.next != null) ptr = ptr.next;
            ptr.next = newNode;
            return head;
        }
    }

    //inserting nodes on a one based index LL
    public static ListNode addNodeAtNthPosition(ListNode head, int val, int pos) {
        ListNode newNode = new ListNode(val);
        ListNode ptr = head;
        int n = 1;
        while(ptr != null){
            n++;
            ptr = ptr.next;
        }
        if(pos > (n+2)){
            System.out.println("Enter a valid index");
            return head;
        }

        if(head == null){
            head = newNode;
            return head;
        }
        ptr = head;
        for(int i = 1;i <= pos-2;i++) ptr = ptr.next;
        newNode.next = ptr.next;
        ptr.next = newNode;
        return head;
    }

    public static ListNode deleteNodeAtNthPosition(ListNode head, int pos){
        int n = 0;
        ListNode ptr = head;
        if(head == null){
            System.out.println("No element to delete");
            return head;
        }
        while(ptr.next != null){
            n++;
            ptr=ptr.next;
        }
        if(pos < n){
            System.out.println("Enter a valid index");
            return head;
        }
        if(pos == 1){
            head = ptr.next;
            return head;
        }
        ptr = head;
        for(int i = 1;i <= pos-2;i++) ptr = ptr.next;
        ptr.next = (ptr.next).next;
        return head;
    }

    public static ListNode deleteNodeFromEnd(ListNode head){
        int n = 0;
        ListNode ptr = head;
        if(head == null){
            System.out.println("No element to delete");
            return head;
        }
        while(ptr.next != null){
            n++;
            ptr=ptr.next;
        }
        ptr = head;
        for(int i = 1;i <= n-2;i++) ptr = ptr.next;
        ptr.next = (ptr.next).next;
        return head;
    }

    public static ListNode deleteNodeFromBeginning(ListNode head){
        ListNode ptr = head;
        if(head == null){
            System.out.println("No element to delete");
            return head;
        }
        head = ptr.next;
        return head;
    }

    public static ListNode mergeLL(ListNode a, ListNode b){
        ListNode ptr = a;
        if(a == null && b == null) return null;
        else if(a == null) return b;
        else if(b == null) return a;
        else{
            while(ptr.next != null){
                ptr = ptr.next;
            }
            ptr.next = b;
        }
        return a;
    }

    public static void printLinkList(ListNode head){
        ListNode ptr = head;
        if(head == null) System.out.println("List is empty");
        while(ptr != null){
            System.out.print(ptr.val+"->");
            ptr = ptr.next;
        }
        System.out.println();
    }

    public static void printCLL(ListNode head){
        ListNode ptr = head;
    }
    public static void printLinkListUsingRecursion(ListNode ptr){
        if(ptr == null){
            System.out.println();
            return;
        }
        System.out.print(ptr.val+"->");
        printLinkListUsingRecursion(ptr.next);
    }

    public static ListNode reverseLinkList(ListNode currentPtr){
        if(currentPtr.next == null){
            return currentPtr;
        }
        ListNode ptr = reverseLinkList(currentPtr.next);
        ListNode nextPtr = currentPtr.next;
        nextPtr.next = currentPtr;
        currentPtr.next = null;
        return ptr;
    }

    public static ListNode reverseLinkListIterative(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode ptr = cur.next;
            cur.next = prev;
            prev = cur;
            cur = ptr;
        }
        return prev;
    }
}
