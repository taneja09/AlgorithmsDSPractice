package LC_Medium;


import java.util.Arrays;
import java.util.PriorityQueue;

public class M0253_MeetingRoomsII {
	public int minMeetingRooms(int[][] intervals) {
		if(intervals == null || intervals.length ==0 ) return 0;
		Arrays.sort(intervals,(a, b)->a[0]-b[0]);
		
		// Use a min heap to track the minimum end time of merged intervals
		PriorityQueue<Integer> ends = new PriorityQueue<Integer>();
		ends.offer(intervals[0][1]);
		
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] >= ends.peek()) { // no overlap, then should update smallest end to bigger one
				ends.poll();
			}
			ends.offer(intervals[i][1]);
		}
		return ends.size();
	}
	public static void main(String[] args) {
		int[][] meeting = {{10,30},{5,10},{15,20}};
		M0253_MeetingRoomsII cl = new M0253_MeetingRoomsII();
		System.out.println(cl.minMeetingRooms(meeting));
	}
}
