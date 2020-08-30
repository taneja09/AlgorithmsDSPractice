package LC_Easy;

import java.util.Arrays;

public class E0252_MeetingRooms {
	public boolean canAttendMeetings(int[][] intervals) {
		if(intervals == null || intervals.length ==0 ) return true;
		Arrays.sort(intervals,(a, b)->a[0]-b[0]);
		int[] x = intervals[0];
		
		for(int i = 1; i<intervals.length ; i++){
			if(intervals[i][0] > x[1]) return false;
			else x[1] = Math.max(intervals[i][1],x[1]);
		}
		
		return true;
	}
	public static void main(String[] args) {
		int[][] meeting = {{6,15},{13,20},{16,17}};
		E0252_MeetingRooms cl = new E0252_MeetingRooms();
		System.out.println(cl.canAttendMeetings(meeting));
	}
}
