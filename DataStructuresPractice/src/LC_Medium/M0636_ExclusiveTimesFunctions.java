package LC_Medium;

import java.util.*;
/*
https://leetcode.com/problems/exclusive-time-of-functions/discuss/153497/Java-solution-using-stack-wrapper-class-and-calculation-when-pop-element-from-the-stack.
 */
public class M0636_ExclusiveTimesFunctions {
	/******************* Method 2 TC = O(n) **************************/
	public int[] exclusiveTime1(int n, List<String> logs) {
		Deque<Log1> stack = new ArrayDeque<>();
		int[] result = new int[n];
		for( String s : logs){
			Log1 log = new Log1(s);
			if(log.isStart)
				stack.push(log);
			else{
				Log1 top = stack.pop();
				result[top.id] += (log.time - top.time + 1 - top.subtime);
				/*
				we need to subtract the sub task if main task is in stack
				"0:start:0","1:start:2","1:end:5","0:end:6"
				we push start0 and start 1 once end of 1 comes calculate 5-2+1 = 4 in result
				when encountered end of task 0 => result = 6-0+1 = 7 which is not true because we had tasl 1 in between and
				when we calculate 4 for task 1, we need to subtract same amount for previous task for correct result
				 */
				if (!stack.isEmpty()) { //because
					stack.peek().subtime += (log.time - top.time + 1);
				}
			}
		}
		return result;
	}
	
	public static class Log1 {
		public int id;
		public boolean isStart;
		public int time;
		public int subtime;
		
		public Log1(String content) {
			String[] strs = content.split(":");
			id = Integer.valueOf(strs[0]);
			isStart = strs[1].equals("start");
			time = Integer.valueOf(strs[2]);
			subtime = 0;
		}
	}
	
	
	/******************* Method 1 TC = O(n) **************************/
	public int[] exclusiveTime(int n, List<String> logs) {
		Deque<Log> stack = new ArrayDeque<>();
		int[] result = new int[n];
		for( String s : logs){
			Log log = new Log(s);
			if(log.isStart)
				stack.push(log);
			else{
				Log top = stack.pop();
				result[top.id] += (log.time - top.time + 1);
				/*
				we need to subtract the sub task if main task is in stack
				"0:start:0","1:start:2","1:end:5","0:end:6"
				we push start0 and start 1 once end of 1 comes calculate 5-2+1 = 4 in result
				when encountered end of task 0 => result = 6-0+1 = 7 which is not true because we had tasl 1 in between and
				when we calculate 4 for task 1, we need to subtract same amount for previous task for correct result
				 */
				if (!stack.isEmpty()) { //because
					result[stack.peek().id] -= (log.time - top.time + 1);
				}
			}
		}
		return result;
	}
	public static class Log {
		public int id;
		public boolean isStart;
		public int time;
		
		public Log(String content) {
			String[] strs = content.split(":");
			id = Integer.valueOf(strs[0]);
			isStart = strs[1].equals("start");
			time = Integer.valueOf(strs[2]);
		}
	}
	
	public static void main(String[] args) {
		List<String> logs = new	ArrayList();
		logs.add("0:start:0");
		logs.add("1:start:2");
		logs.add("1:end:5");
		logs.add("0:end:6");
		M0636_ExclusiveTimesFunctions cl = new M0636_ExclusiveTimesFunctions();
		System.out.println(Arrays.toString(cl.exclusiveTime1(2,logs)));
		
	}
}
