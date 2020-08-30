package LC_Medium;

import java.util.*;
/*
MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(15, 25); // returns false
MyCalendar.book(20, 30); // returns true
 */
public class M0729_MyCalendarI {
	TreeMap<Integer, Integer> calendar;
	public M0729_MyCalendarI(){
		calendar =  new TreeMap();
	}
	public boolean book(int start, int end) {
		Integer low = calendar.lowerKey(end);  //will return max key just lower than end => 10 will be returned
		if(low == null || calendar.get(low) <= start){  //calendar.get(10) = 20 <= 15 false hence second record cant be added but 3rd can bcz 20 <= 20
			calendar.put(start,end);
			return true;
		}
		return false;
	}






//	List<int[]> calendar;
//	public M0729_MyCalendarI(){
//		calendar = new ArrayList();
//	}
//
//	/** TC = O(n^2) SC = O(n) where n is number of events ***********/
//	public boolean book(int start, int end) {
//		for (int[] iv: calendar) {
//			if (iv[0] < end && start < iv[1]) return false;
//		}
//		calendar.add(new int[]{start, end});
//		return true;
//	}
//	public static void main(String[] args) {
//
//	}
}

