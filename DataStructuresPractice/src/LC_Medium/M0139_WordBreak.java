package LC_Medium;

import java.util.*;

public class M0139_WordBreak {
	/************ Recursive Solution TC = O(2^n) sc = O(2^n) depth of recursion - TLE *********/
	public boolean wordBreak(String s, List<String> wordDict) {
		Set<String> set = new HashSet<>(wordDict);  //put all element in hashset for better search
		return wb(s, set);
	}
	
	private boolean wb(String s, Set<String> set){
		if( s.length() == 0) return true;
		for(int i = 0; i<= s.length(); i++){
			if(set.contains(s.substring(0,i))  &&  wb(s.substring(i),set))
				return true;
		}
		
		return false;
	}
	
	/************ DP Top Down Solution TC = O(n^2) sc = O(n)*********/
	public boolean wordBreakDP(String s, List<String> wordDict) {
		boolean[] f = new boolean[s.length() + 1];  //f(i) represents that whether subarray(0, i) can be segmented into words from the dictionary
		f[0] = true; //So f[0] means whether subarray(0, 0) that "" can be segmented YES
		Set<String> set = new HashSet<>(wordDict); //create set for faster check
		
		for (int i = 1; i <= s.length(); i++) {  //upper bound
			for (int j = i-1; j >= 0; j--) {  //lower bound
				f[i] = set.contains(s.substring(j, i)) && f[j];
				if(f[i]) break;
			}
		}
		return f[s.length()];
	}
	public static void main(String[] args) {
		String word = "leetcode";
		ArrayList<String> sl = new ArrayList();
		sl.add("leet"); sl.add("code");
		M0139_WordBreak cl = new M0139_WordBreak();
		System.out.println(cl.wordBreakDP(word,sl));
	}
}
