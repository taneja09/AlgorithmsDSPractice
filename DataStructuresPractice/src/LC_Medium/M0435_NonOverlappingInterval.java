package LC_Medium;
/**
 
 Time Complexity - O(nlogn)
 Reason - sort + searching linearly
 
 Space Complexity - O(1)
 Reason -
*/
import java.util.Arrays;
// https://leetcode.com/problems/non-overlapping-intervals/discuss/727549/Java-or-1-ms-or-Easy-with-Explanation-or-Beginner-Friendly
public class M0435_NonOverlappingInterval {
	public int eraseOverlapIntervals(int[][] intervals) {
		if (intervals.length <= 1) return 0;
		Arrays.sort(intervals,(a,b)->a[0] -b[0]);  //sort based on interval start
		int[] interval = intervals[0]; //reference interval to compare with other intervals
		int count = 0;
		
		for(int i = 1; i<intervals.length; i++){
			int[] arr = intervals[i];
			//if there is overlapping scenario take the minimum end of intervals i.e [[1,2], [1,3], [2,3]]
			//interval = [1,2] and it overlaps with [1,3] => we can drop [1,3] so that [1,2] and [2,3] can be separate interval
			//this is because we want to drop minimum number of interval,
			// if it was maximum it will become same as merging intervals
			//and we'll eat up 2 intervals and final output would have been [1,3]
			if(arr[0] < interval[1]){
				interval[1] = Math.min(arr[1],interval[1]);
				count++;
			}else{
				interval = arr;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
		M0435_NonOverlappingInterval cl = new M0435_NonOverlappingInterval();
		System.out.println(cl.eraseOverlapIntervals(intervals));
	}
}
