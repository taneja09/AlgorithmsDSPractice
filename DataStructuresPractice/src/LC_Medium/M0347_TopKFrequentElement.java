package LC_Medium;
/**
 
 Time Complexity - O(n)
 Reason - traversal required for complete array
 
 Space Complexity - O(n)
 Reason - map space
*/
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class M0347_TopKFrequentElement {
    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> hm = new HashMap();

        for(int i : nums){
            hm.put(i,hm.getOrDefault(i,1)+1);
        }

        // init heap 'the less frequent element first'
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> hm.get(n1) - hm.get(n2));  //top element is less frequent  top 3 then 2 then 1

        for( int key : hm.keySet()){
            heap.add(key);
            if(heap.size() > k)
                heap.poll();   //3 will pe popped because size of heap is 3 and now only most frequent element 1 and 2 are remaining in heap
        }

        int[] res = new int[k];
        int i = 0;
        while(!heap.isEmpty() && i < k){
            res[i] = heap.poll();
            i++;
        }

        return res;
    }

    /**
     *
     Idea is simple. Build a array of list to be buckets with length 1 to sort.
     */

//    public int[] topKFrequentBucketSort(int[] nums, int k) {
//
//
//    }
    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,3};
        int k = 2;
        M0347_TopKFrequentElement cl = new M0347_TopKFrequentElement();
        int[] res = cl.topKFrequent(arr,k);
        //int[] res = cl.topKFrequentBucketSort(arr,k);
        System.out.println(Arrays.toString(res));


    }
}
