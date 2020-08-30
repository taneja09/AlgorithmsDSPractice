package LC_Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class H0057_InsertIntervals {
	public int[][] insertConcise(int[][] intervals, int[] newInterval) {
		int i = 0;
		int start = newInterval[0];
		int end = newInterval[1];
		List<int[]> result = new ArrayList<>();
		while (i < intervals.length && intervals[i][1] < start) {
			result.add(intervals[i++]);
		}
		while (i < intervals.length && intervals[i][0] <= end) {
			start = Math.min(start, intervals[i][0]);
			end = Math.max(end, intervals[i][1]);
			i++;
		}
		result.add(new int[]{start, end});
		while (i < intervals.length) result.add(intervals[i]);
		
		int[][] resultArr = new int[result.size()][2];
		int idx =0;
		for(int[] inter : result) {
			resultArr[idx] = inter;
			idx++;
		}
		
		return resultArr;
		
	}
	public int[][] insert(int[][] intervals, int[] newInterval) {
		ArrayList<int[]> res = new ArrayList();
		boolean set = false;
		int i = 0;
		if(newInterval[1] < intervals[0][0]) {
			res.add(newInterval);
			for(int[] inter : intervals)
				res.add(inter);
			
		}
		else if(newInterval[0] > intervals[intervals.length-1][1]){
			for(int[] inter : intervals)
				res.add(inter);
			res.add(newInterval);
		}
		
		else {
			while (i < intervals.length) {
				if(set){
					res.add(intervals[i]);
					i++;
				}
				else if (i + 1 < intervals.length && newInterval[0] > intervals[i][1] && newInterval[1] < intervals[i + 1][0]) {
					res.add(intervals[i]);
					res.add(newInterval);
					set = true;
					i++;
				} else if (newInterval[0] <= intervals[i][1] ) {
					int[] merge = new int[]{Math.min(intervals[i][0], newInterval[0]), Math.max(intervals[i][1], newInterval[1])};
					while (i < intervals.length && intervals[i][0] <= merge[1]) {
						merge[1] = Math.max(merge[1], intervals[i][1]);
						i++;
					}
					newInterval = merge;
					res.add(merge);
					set = true;
				} else {
					res.add(intervals[i]);
					i++;
				}
			}
		}
		
		int[][] result = new int[res.size()][2];
		int idx =0;
		for(int[] inter : res) {
			result[idx] = inter;
			idx++;
		}
		
		return result;
	}
	public static void main(String[] args) {
		int[][] intervals = {{3,5},{12,15}};
		int[] merge = {6,6};
		H0057_InsertIntervals cl = new H0057_InsertIntervals();
		int[][]  res = cl.insert(intervals,merge);
		for(int[] x : res) System.out.print(Arrays.toString(x));
	}
}
