package LC_Medium;

import java.util.Arrays;
import java.util.PriorityQueue;

public class M0215_KthLargestElement {

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length -k;

        return nums[len];
    }

    public int findKthLargestPriorityQueue(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int val : nums) {
            queue.offer(val);

            if (queue.size() > k)  //remove all elements after kth element
                queue.poll();

        }

        return queue.peek(); // this will kth largest element

    }


    public static void main(String[] args) {

        M0215_KthLargestElement cl = new M0215_KthLargestElement();
        int[] array = {3,2,3,1,2,4,5,5,6};
        //int res = cl.findKthLargest(array,2);
        int res = cl.findKthLargestPriorityQueue(array,4);
        System.out.print(res);
    }
}
