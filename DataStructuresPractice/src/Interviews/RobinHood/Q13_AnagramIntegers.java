package Interviews.RobinHood;

import java.lang.reflect.Array;
import java.util.*;

public class Q13_AnagramIntegers {
	private int countAnagrams(int[] arr){
		List<String> wordlist = new ArrayList();
		Map<String, List<String>> map = new HashMap<>();
		
		for(int x : arr)
			wordlist.add(Integer.toString(x));
		
		for(String str : wordlist){
			char[] charArray = str.toCharArray();
			Arrays.sort(charArray);
			String key = new String(charArray);
			if(map.containsKey(key)){
				map.get(key).add(str);
			}else{
				List<String> list = new ArrayList<>();
				list.add(str);
				map.put(key,list);
			}
		}
		
		int count = 0;
		for(String key : map.keySet()) {
			if (map.get(key).size() >= 2){
				int n = map.get(key).size();
				count += (n * (n-1))/2;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		int[] arr = {25, 35, 872, 228, 53, 278, 872, 728};
		Q13_AnagramIntegers obj = new Q13_AnagramIntegers();
		System.out.println(obj.countAnagrams(arr));
	}
}
