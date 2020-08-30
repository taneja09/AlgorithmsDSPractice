package Interviews.RobinHood;

import java.util.*;

public class Q9_DuplicatesInSubArray {
	private int duplicateOnSegment(int[] arr){
		int result = 0;
		
		for(int i = 0; i< arr.length; i++) {
			int counter = 0;
			Map<Integer, Integer> map = new HashMap<>();
			for (int j = i; j < arr.length; j++) {
				map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);
				if (map.get(arr[j]) >= 2) counter++;
				if (counter != 0 && counter >= map.size()) result += 1;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] arr = {0,0,0,0};
		Q9_DuplicatesInSubArray cl = new Q9_DuplicatesInSubArray();
		System.out.println(cl.duplicateOnSegment(arr));
	}
}
