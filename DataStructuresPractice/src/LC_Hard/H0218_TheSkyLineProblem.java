package LC_Hard;

import java.util.*;
/*
https://www.youtube.com/watch?v=GSBLe8cKu0s

  1. visit all start points and all end points in order;
  2. when visiting a point, we need to know whether it is a start point or a
      end point, based on which we can add a height or delete a height from
      our data structure;
      
  1. use a int[][] to collect all [start point, - height] and [end point, height]
      for every building;
  2. sort it, firstly based on the first value, then use the second to break
      ties;

Then

  1. we can visit all points in order;
  2. when points have the same value, higher height will shadow the lower one;
  3. we know whether current point is a start point or a end point based on the
      sign of its height;

 */
public class H0218_TheSkyLineProblem {
	public List<List<Integer>> getSkyline(int[][] buildings) {
		List<List<Integer>> result = new ArrayList<>();
		List<int[]> height = new ArrayList<>();
		
		for(int[] b:buildings) {
			// start point has negative height value
			height.add(new int[]{b[0], -b[2]});
			// end point has normal height value
			height.add(new int[]{b[1], b[2]});
		}
		
		// sort $height, based on the first value x, if necessary, use the second to
		// break ties
		Collections.sort(height, (a, b) -> {
			if(a[0] != b[0])
				return a[0] - b[0];
			return a[1] - b[1];
		});
		
		// Use a maxHeap to store possible heights
		Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
		
		// Provide a initial value to make it more consistent
		pq.offer(0);
		
		// Before starting, the previous max height is 0;
		int prev = 0;
		
		// visit all points in order
		for(int[] h:height) {
			if(h[1] < 0) pq.offer(-h[1]);   // a start point, add height
			else pq.remove(h[1]);  //end point remove height
			int cur = pq.peek(); // current max height;
			// compare current max height with previous max height, update result and
			// previous max height if necessary
			if(prev != cur) {
				List<Integer> res = new ArrayList<>();
				res.add(h[0]);
				res.add(cur);
				result.add(res);
				prev = cur;
			}
		}
		
		return result;
	}
	public static void main(String[] args) {
		int[][] points =  {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
		H0218_TheSkyLineProblem cl = new H0218_TheSkyLineProblem();
		List<List<Integer>> result = cl.getSkyline(points);
		for(List<Integer> x : result) System.out.println(x);
	}
	
}
