package LC_Hard;

import java.util.HashMap;
import java.util.Map;

public class H0076_MinimumWindowSubstring {
	public String minWindow(String s, String t) {
        if(t.length() > s.length()) return "";
        Map<Character,Integer> map = new HashMap<>();
        for(char c : t.toCharArray()) map.put(c,map.getOrDefault(c,0)+1);
        
        int begin = 0, end = 0, count = map.size();
		int len = Integer.MAX_VALUE,head = 0;
        
        while(end < s.length()){
        	char c = s.charAt(end);
        	if(map.containsKey(c)){
        		map.put(c,map.get(c)-1);
				if(map.get(c) == 0) count--;
			}
        	
        	end++;
        	
        	while(count == 0){
        		char temp = s.charAt(begin);
        		if(map.containsKey(temp)){
        			map.put(temp,map.get(temp)+1);
        			if(map.get(temp)>0) count++;
				}
        		if(end-begin < len){
        			len = end-begin;
        			head = begin;
				}
        		begin++;
			}
		}
        
        return len == Integer.MAX_VALUE ? "" : s.substring(head,head+len);
    }
	public static void main(String[] args) {
		String s = "ADOBECODEBANC";
		String t = "ABC";
		H0076_MinimumWindowSubstring cl = new H0076_MinimumWindowSubstring();
		System.out.println(cl.minWindow(s,t));
	}
}
