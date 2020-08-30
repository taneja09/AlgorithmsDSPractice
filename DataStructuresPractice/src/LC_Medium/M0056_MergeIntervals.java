package LC_Medium;

/**
 
 Time Complexity - O(nlogn)
 Reason - sort + searching linearly
 
 Space Complexity - O(n)
 Reason - to save the new arraylist of intervals
*/

import java.util.ArrayList;
import java.util.Arrays;

public class M0056_MergeIntervals {
	
	public int[][] merge(int[][] intervals) {
		if (intervals.length <= 1) return intervals;
		
		ArrayList<int[]> list = new ArrayList();
		Arrays.sort(intervals, (a1,a2) -> a1[0] - a2[0]);

		int[] newInterval = intervals[0];
		list.add(newInterval);
		
		for (int[] interval : intervals) {
			if (interval[0] <= newInterval[1]) // Overlapping intervals, move the end if needed
				newInterval[1] = Math.max(newInterval[1], interval[1]);
			else{
				newInterval = interval;
				list.add(newInterval);
			}
		}
		return list.toArray(new int[list.size()][]);
	}
	
	public static void main(String[] args) {
		int[][] intervals = {{1,4},{4,5}};
		M0056_MergeIntervals cl = new M0056_MergeIntervals();
		int[][] results = cl.merge(intervals);
		for(int[] x : results){
			System.out.println(Arrays.toString(x));
		}
	}
}
