package LC_Medium;

import java.util.*;

public class M0973_KClosestPointsToOrigin {
	public int[][] kClosest(int[][] points, int K) {
		int[][] Array = new int[K][];
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new customCompare());
		for(int[] point : points){
			pq.add(point);
			if(pq.size() > K) pq.poll();
		}
		
		int x = 0;
		while(!pq.isEmpty()){
			Array[x] = pq.remove();
			x++;
		}
		
		return Array;
	}
	
	/************** Quick Sort/ Quick Select ****************/
	
	public int[][] kClosestQuickSort(int[][] points, int K) {
		int len =  points.length, l = 0, r = len - 1;
		while (l <= r) {
			int mid = helper(points, l, r);
			if (mid == K) break;
			if (mid < K) l = mid + 1;
			else r = mid - 1;
		}
		
		return Arrays.copyOfRange(points,0,K);
	}
	
	private int helper(int[][] A, int l, int r) {
		int[] pivot = A[l];
		while (l < r) {
			while (l < r && compare(A[r], pivot) >= 0) r--;  //A[r] >> pivot
			A[l] = A[r]; // move pivot to right
			while (l < r && compare(A[l], pivot) <= 0) l++;
			A[r] = A[l];  // move pivot to left
		}
		
		A[l] = pivot;
		return l;
	}
	
	private int compare(int[] p1, int[] p2) {
		return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
	}
	public static void main(String[] args) {
		int[][] point= {{3,3},{5,-1},{-2,4}};
		M0973_KClosestPointsToOrigin cl = new M0973_KClosestPointsToOrigin();
		int[][] result = cl.kClosestQuickSort(point,2);
		for(int[] res : result) System.out.println(Arrays.toString(res));
	}
	
	
}

class customCompare implements Comparator<int[]> {
	@Override
	public int compare(int[] o1, int[] o2) {
		int sum1 = (o1[0] * o1[0]) + (o1[1] * o1[1]);
		int sum2 = (o2[0] * o2[0]) + (o2[1] * o2[1]);
		return sum2-sum1;
	}
}

