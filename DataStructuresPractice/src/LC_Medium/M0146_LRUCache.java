package LC_Medium;
/**
 Time Complexity -  O(1) for GET and Put
 Reason -
 
 Space Complexity - O(n) where n is cpapacity of the hash
 Reason -  No extra space usage
 */

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

import java.util.Hashtable;

/**
 The problem can be solved with a hashtable that keeps track of the keys and its values in the double linked list.
 Lookup is O(1) in hashtable so get() becomes very fast.
 One interesting property about double linked list is that the node can remove itself without other reference. and Hence rearranging becomes easy
 In addition, it takes constant time to add and remove nodes from the head or tail and runtime becomes 0(1) for both operations.
 */
public class M0146_LRUCache {
    private Hashtable<Integer, DLinkedNode> cache = new Hashtable<>();  //Hash backed up by Doubly Linked List
    private int count;
    private int capacity;  //capacity of LRU Cache
    private DLinkedNode head, tail;  //initialize 2 nodes head and tail

    //DoublyLinkNode object has a key, value , previous node pre and next node post
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode post;
    }

    /*
     * Always add the new node right after head;
     */

    public M0146_LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;  //Cache is initialized with a capacity

        head = new DLinkedNode();    // head node is assigned with new object with previous node as null
        head.pre = null;

        tail = new DLinkedNode();  // tail node is assigned with new object with next node as null
        tail.post = null;

        head.post = tail;  //[head] <--> [tail]  in doublyLinkedList, head and tail pointing to each other via pre and post node
        tail.pre = head;
    }

    private void addNode(DLinkedNode node) {
        //[head] <--> [New Node = node] <--> node] <--> [tail]
        node.pre = head;
        node.post = head.post;

        head.post.pre = node;
        head.post = node;
    }

    public int get(int key) {
        //[head] <--> [node1] <--> [node2] <--> [tail]
        //get node2
        DLinkedNode node = cache.get(key);
        if(node == null){
            return -1; // should raise exception here.
        }

        // move the accessed node to the head;
        this.moveToHead(node);
        return node.value;
    }

    /**
     * Move certain node in between to the head.
     */
    private void moveToHead(DLinkedNode node){
        this.removeNode(node);
        this.addNode(node);
    }

    /**
     * Remove an existing node from the linked list.
     */
    private void removeNode(DLinkedNode node){
        DLinkedNode pre = node.pre;
        DLinkedNode post = node.post;

        pre.post = post;
        post.pre = pre;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);

        //if node doesn't exist
        if(node == null){
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;
            this.cache.put(key, newNode);
            this.addNode(newNode);

            ++count;
            if(count > capacity){
                // pop the tail
                DLinkedNode tail = this.popTail();
                this.cache.remove(tail.key);
                --count;
            }
        }else{
            // update the value.
            node.value = value;
            this.moveToHead(node);
        }
    }

    // pop the current tail.
    private DLinkedNode popTail(){
        DLinkedNode res = tail.pre;
        this.removeNode(res);
        return res;
    }



}
