package LC_Hard;

import java.util.HashMap;
import java.util.Map;

public class H0010_RegularExpressionMatch {
	private Map<String, Boolean> cache;
	/******************* DP Bottom Up Approach O(p.length() * s.length())  SC = O(Min(s,p)) depth of recursion **********/
	/*
	https://leetcode.com/problems/regular-expression-matching/discuss/191830/Java-DP-solution-beats-100-with-explanation
	 */
	public boolean isMatchBottomUp(String s, String p) {
		if (p == null || p.length() == 0) return (s == null || s.length() == 0);
		boolean dp[][] = new boolean[s.length()+1][p.length()+1];
		dp[0][0] = true; //because for dp[0-i] string dp[0-j] is valid i.e. empty pattern p="" only thing that is valid is an empty string s=""
		
		for (int j=2; j<=p.length(); j++) {
			dp[0][j] = p.charAt(j-1) == '*' && dp[0][j-2];
		}
		
		for (int j=1; j<=p.length(); j++) {
			for (int i=1; i<=s.length(); i++) {
				if (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.')
					dp[i][j] = dp[i-1][j-1];
				else if(p.charAt(j-1) == '*')
					dp[i][j] = dp[i][j-2] || ((s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') && dp[i-1][j]);
			}
		}
		
		return dp[s.length()][p.length()];
	}
	
	/******************* DP Top Down Approach TC = O (n^2)  SC = O(Min(s,p)) depth of recursion **********/
	public boolean isMatchTopDown(String s, String p) {
		Boolean [][] cache = new Boolean[s.length()+1][p.length()];
		return matchTopDown(s,p,cache,0,0);
	}
	
	private boolean matchTopDown(String s, String p,Boolean[][] cache, int i , int j){
		if(j == p.length()) return i == s.length();
		if(cache[i][j] != null) return cache[i][j];
		
		if(p.length() > j+1 && p.charAt(j+1) == '*') {
			if (matchTopDown(s, p, cache, i, j + 2)) return true;
			
			if (s.length() > i && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) {
				if (matchTopDown(s, p, cache, i + 1, j + 2)) return true;
				if (matchTopDown(s, p, cache, i + 1, j)) return true;
			}
		}else{
			if (s.length() > i && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'))
				if(matchTopDown(s,p,cache,i+1,j+1)) return true;
		}
		
		cache[i][j] = false;
		return false;
	}
	
	
	/******************* DP Top Down Approach TC = O (n^2) SC = O(Min(s,p)) depth of recursion **********/
	public boolean isMatchTopDownHash(String s, String p) {
		cache = new HashMap<>();
		return matchTopDownHash(s, p);
	}
	
	private boolean matchTopDownHash(String s, String p) {
		String key = s.length() + ":" + p.length();
		if (cache.containsKey(key)) {
			return cache.get(key);
		}
		
		boolean res = false;
		if (p.length() == 0) {
			res = s.length() == 0;
		} else if (p.length() > 1 && p.charAt(1) == '*') {
			if (s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
				res =  matchTopDownHash(s, p.substring(2)) || matchTopDownHash(s.substring(1), p);
			} else {
				res = matchTopDownHash(s, p.substring(2));
			}
		} else {
			if (s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
				res = matchTopDownHash(s.substring(1), p.substring(1));
			}
		}
		
		cache.put(key, res);
		System.out.println(cache);
		return res;
	}
	/******************* Recursive Approach TC = O(2^n) n = p+s SC = O(2^n) depth of recursion **********/
		/*
		https://leetcode.com/problems/regular-expression-matching/discuss/5802/Simple-java-recursive-solution-with-two-cases
		 */
	public boolean isMatchRecursive(String s, String p) {
		if(p.length() == 0) return s.length() ==0;
		if(p.length() > 1 && p.charAt(1) == '*') { // second char is '*'
			if (isMatchRecursive(s, p.substring(2))) {
				return true;
			}
			if(s.length() > 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))) {
				return isMatchRecursive(s.substring(1), p);
			}
			return false;
		}else{   // second char is not '*'
			if(s.length() > 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))) {
				return isMatchRecursive(s.substring(1), p.substring(1));
			}
			return false;
		}
	}
	public static void main(String[] args) {
		String s = "aab";
		String p = "c*a*b";
		H0010_RegularExpressionMatch cl = new H0010_RegularExpressionMatch();
		System.out.println(cl.isMatchBottomUp(s,p));
		
	}
}
