package LC_Hard;

import java.util.*;

public class H0295_MedianFromDataStream {
	List<Double> list;
	PriorityQueue<Integer> min = new PriorityQueue<>();
	PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
	public H0295_MedianFromDataStream() {
		list = new ArrayList();
	}
	
	/****************** Sorting TC = O(nlogn)*********************/
	public void addNumSort(int num) {
		list.add((double)num);
	}
	
	public double findMedianSort() {
		Collections.sort(list);
		int size = list.size();
		return size%2 != 0 ? list.get(size/2) : (list.get(size/2) + list.get((size/2)-1))/2;
	}
	
	/****************** 2 Queues TC = O(logn)*********************/
	/*
	 		2 queues keeps track of smaller and bigger half of the stream.
	 		if there are more elements in max that means we have total odd numbers of elements - return max.peek
	 		if both queues have same number of elements , peek from both and return their average
	 		
	 		min queue - hold the larger element of stream and when peek, provides least larger element of the stream
	 		max queue - hold the smaller elements of stream and when peek, provides biggest element of the stream
	 		
	 		priority queue takes care of sorting:
	 		
	 		min queue - elements are sorted in ascending order
	 		max queue- elements are sorted in descending order. any bigger element added to max comes at top
	 					if max queue has lesser size than min , then put element back to max queue
	 		
	 		min queue - sorted in ascending order ,if added to min queue which holds larger elements
	 			 		 and sorted in ascending order that means min queue will give least larger number
	 		
	 		hence you will always has largest number ( of left sorted half) in max peek
	 		and smallest number of (of right sorted half ) in min -- think about median of two sorted arrays
	 		
	 		i.e max queue = 1,2,3 (top)  min queue = 9,7,4(top)   and hence 3 + 4 / 2 median
	 		i.e max queue = 1,2(top) and min queue = 3(top) , there are total odd elements and hence return max.peek as median
	 		
	 */
	public void addNumPQ(int num) {
		max.offer(num);
		min.offer(max.poll());
		
		if(max.size() < min.size())
			max.offer(min.poll());
	}
	
	
	public double findMedianPQ() {
		if(max.size() == min.size()) return (min.peek() + max.peek())/2.0;
		else return max.peek();
	}
	
	public static void main(String[] args) {
		H0295_MedianFromDataStream obj = new H0295_MedianFromDataStream();
		obj.addNumSort(1);
		obj.addNumSort(2);
		System.out.println(obj.findMedianSort());
		obj.addNumSort(3);
		System.out.println(obj.findMedianSort());
		
		/**************   2 QUEUEs   ******************/
		
		obj.addNumPQ(1);
		obj.addNumPQ(2);
		System.out.println(obj.findMedianPQ());
		obj.addNumPQ(3);
		System.out.println(obj.findMedianPQ());
		
	}
	
}
