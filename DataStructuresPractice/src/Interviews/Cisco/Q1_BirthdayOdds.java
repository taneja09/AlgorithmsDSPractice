package Interviews.Cisco;

import java.util.HashMap;

public class Q1_BirthdayOdds {
	public int getOdddayBirthday(String days, int num){
		int count = 0;
		HashMap<Integer,Integer> map = new HashMap<>();
		String[] arr = days.split(" ");
		if(num == 0 || num != arr.length ) return count;
		for(String x : arr) {
			int y = Integer.valueOf(x);
			map.put(y,map.getOrDefault(y,0)+1);
		}
		for(int key : map.keySet()){
			if(key> 0 && map.get(key)%2 != 0) count++;
		}
		return count;
	}
	public static void main(String[] args) {
		int k = 5;
		String nums = "1 5 7 5 5 0";
		Q1_BirthdayOdds cl = new Q1_BirthdayOdds();
		System.out.println(cl.getOdddayBirthday(nums,k));
	}
}
