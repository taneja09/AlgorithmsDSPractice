package LC_Hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class H0239_SlidingWindowMaximum {
	public int[] maxSlidingWindowSlidingTemplate(int[] nums, int k) {
		if (nums == null || k <= 0) {
			return new int[0];
		}
		
		int n = nums.length;
		// store results
		int[] res = new int[n - k + 1];
		int resIdx = 0;
		
		// store index in the queue
		Deque<Integer> q = new ArrayDeque<>();
		
		for (int i = 0; i < n; i++) {
			
			// need to remove unnecessary elements, which are now not part of sliding
			// window.
			while (!q.isEmpty() && q.peek() <= i + k) { //index is greater than window then remove unnecessary elements
				q.poll();
			}
			
			// Remove all elements smaller than the currently
			// being added element (remove useless elements)
			while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
				q.pollLast();
			}
			
			// Add current element at last of queue.
			q.offer(i);
			
			// The element at the front of the queue is the largest element of
			// previous window, so save in results.
			if (i >= k - 1) {      //current index is considering the min window so that value can be assigned.
				// in case of k = 3, i = 0 & 1 indexes never assign result value, when i = 2 that means we have 0,1,2 3 indexes and value can be assigned now with max
				res[resIdx++] = nums[q.peek()];
			}
		}
		return res;
		
	}
	/********** TLE *************************************/
	public int[] maxSlidingWindow(int[] nums, int k) {
		int[] result = new int[nums.length-k + 1];
		if(k>nums.length) return result;
		
		int begin = 0, end = 0;
		int max = Integer.MIN_VALUE;
		
		while(begin < result.length){
			for(int i = begin; i<begin+k && begin+k<=nums.length; i++){
				max = Math.max(max,nums[i]);
			}
			result[begin] = max;
			max = Integer.MIN_VALUE;
			begin++;
		}
		
		return result;
	}
	public static void main(String[] args) {
		int nums[] = {1,3,-1,-3,5,3,6,7};
		int k = 3;
		H0239_SlidingWindowMaximum cl = new H0239_SlidingWindowMaximum();
		System.out.println(Arrays.toString(cl.maxSlidingWindowSlidingTemplate(nums,k)));
	}
}

/*
Monotonic queue
push: push an element into the queue; O (1) (amortized)
pop: pop an element out of the queue; O(1) (pop = remove, it can't report this element)
max: report the max element in queue;O(1)

It takes only O(n) time to process a N-size sliding window minimum/maximum problem.
Note: different from a priority queue (which takes O(nlogk) to solve this problem),
it doesn't pop the max element: It pops the first element (in original order) in queue.


 */