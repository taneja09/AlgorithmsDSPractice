package LC_Hard;

import java.util.*;

public class H0460_LFUCache {
	int capacity,size,minFreq;
	HashMap<Integer,Node> keyMap;
	HashMap<Integer,DLinkedList> freqMap;
	
	public H0460_LFUCache(int capacity){
		this.capacity = capacity;
		this.size = minFreq = 0;
		keyMap = new HashMap<>();
		freqMap = new HashMap<>();  //frequency  & DListNode
		freqMap.put(0,new DLinkedList(0));
	}
	
	/********* GET Operation **************/
	public int get(int key) {
		if(!keyMap.containsKey(key)) return -1;
		Node node = keyMap.get(key);
		return update(node);
	}
	
	private int update(Node node){
		int freq = node.freq;
		freqMap.get(freq).delete(node);
		node.freq ++;
		freqMap.computeIfAbsent(node.freq,k->new DLinkedList(node.freq)).add(node);
		
		while (freqMap.get(minFreq).isEmpty()) minFreq++;
		return node.value;
	}
	
	void delete(Node node){
		node.next.prev = node.prev;
		node.prev.next = node.next;
	}
	
	/************** PUT Operations ****************/
	public void put(int key, int value) {
		if(capacity==0) return;
		if (keyMap.containsKey(key)){
			Node node = keyMap.get(key);
			node.value = value;
			update(node);
			return ;
		}
		if(size>=capacity){
			Node old = freqMap.get(minFreq).pop();
			keyMap.remove(old.key);
			size --;
		}
		
		Node node = new Node(key,value);
		freqMap.get(0).add(node);
		keyMap.put(key,node);
		minFreq = 0;
		size ++;
	}
	/*******  Node Class ************/
	
	class Node{
		int key,value,freq;
		Node prev,next;
		
		Node(int key,int value){
			this.key = key;
			this.value = value;
			this.freq = 0;
		}
	}
	
	/*******  DLinkedList Class ************/
	class DLinkedList {
		int freq;
		Node head, tail;
		
		DLinkedList(int freq) {
			this.freq = freq;
			head = new Node(0, 0);
			tail = new Node(0, 0);
			head.next = tail;
			tail.prev = head;
		}
		
		//Add a Node just next to head
		void add(Node node){
			Node t = head.next;
			head.next = node;
			node.prev = head;
			node.next = t;
			t.prev = node;
		}
		
		//if the list is empty .. head.next = tail ..there will be no intermediate element b/w head and tail
		boolean isEmpty(){
			return head.next==tail;
		}
		
		// remove a nod from DLinkList
		void delete(Node node){
			node.next.prev = node.prev;
			node.prev.next = node.next;
		}
		
		//remove from the end of the list
		Node pop(){
			if (isEmpty()) return null;
			Node node = tail.prev;
			delete(node);
			return node;
		}
	}
	public static void main(String[] args) {
		H0460_LFUCache cl = new H0460_LFUCache(2);
		cl.put(1,1);
		cl.put(2,2);
		cl.get(1);
		cl.put(3,3);
		cl.get(2);
		cl.get(3);
		cl.put(4,4);
		cl.get(1);
		cl.get(3);
		cl.get(4);
	}
}
