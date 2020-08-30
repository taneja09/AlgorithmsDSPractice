package LC_Medium;
/**
 
 Time Complexity - O(n)
 Reason - linear scan
 
 Space Complexity - O(1)
 Reason -
*/
import java.util.*;

public class M0438_FindAnagaramInString {
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> res = new ArrayList<>();
		if (s == null || s.length() == 0 || p == null || p.length() == 0) return res;
		int[] hash = new int[26]; //character hash
		
		for (char c : p.toCharArray())
			hash[c -'a']++;
		
		// two points, initialize count to 0;
		// both left and right will move in same direction  ---> resizing the window size from left and right pointer
		int left = 0, right = 0, count = 0;
		
		while (right < s.length()) {
			
			if(hash[s.charAt(right) -'a'] > 0){   //if hash contains the value then increment count & move right
				hash[s.charAt(right) -'a']--;
				count++;
				right++;
			}else{                                // if character doesnt exist in hash, decrement count & move left ++ .
				hash[s.charAt(left)-'a']++;
				count--;
				left++;
				
			}
			if(count == p.length()) {
				res.add(left);
			}
		}
		return res;
	}
	
	
	
	private boolean checkIfAnagram(String sb, String p){
		int[] count = new int[26];
		for(int i = 0; i<sb.length(); i++){
			count[sb.charAt(i) -'a']++;
			count[p.charAt(i) -'a']--;
		}
		
		for(int j = 0; j< count.length; j++)
			if(count[j] !=0) return false;
		
		return true;
	}
	
	public static void main(String[] args) {
		String s= "cbaebabacd", p= "abc";
		M0438_FindAnagaramInString cl = new M0438_FindAnagaramInString();
		List<Integer> res = cl.findAnagrams(s,p);
		for(int i : res) System.out.println(i);
		
	}
}



