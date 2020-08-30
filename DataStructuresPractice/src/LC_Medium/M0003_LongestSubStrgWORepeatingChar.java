package LC_Medium;

/**
 *  Time Complexity - O(n)
 *  Reason - traversal of whole string
 *
 *  Space Complexity - O(n)
 *  Reason - storage in HashMap
 */

import java.util.HashMap;

public class M0003_LongestSubStrgWORepeatingChar {
	public int lengthOfLongestSubstring(String s) {
		if (s.length()==0) return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int max=0;
		for (int i=0, j=0; i<s.length(); i++){
			
			if (map.containsKey(s.charAt(i))){
				/* Why use Math.max here
				Consider the input: "tmsmfdut"
				for the case when i = s.length()-1.
				Here, j= 2.
				if you just use , map.get(s.charAt(i))+1, then j will be set to 1 and it will give wrong answer.*/
				j = Math.max(j,map.get(s.charAt(i))+1);
			}
			map.put(s.charAt(i),i);
			max = Math.max(max,i-j+1);
		}
		
		return max;
   }
	public static void main(String[] args) {
		String s = "adbccba";
		M0003_LongestSubStrgWORepeatingChar cl = new M0003_LongestSubStrgWORepeatingChar();
		System.out.println(cl.lengthOfLongestSubstring(s));
	}
}


