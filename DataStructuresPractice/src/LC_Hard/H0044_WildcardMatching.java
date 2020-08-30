package LC_Hard;
/*
https://leetcode.com/problems/wildcard-matching/discuss/17810/Linear-runtime-and-constant-space-solution
/*
Time Complexity tc = o(M*N)
space complexity SC = O(1)
 */

import java.util.Arrays;

public class H0044_WildcardMatching {
	
	/********************** Stupid Implementation ***************************/
	public boolean isMatch(String s, String p) {
		int i = 0;
		int j = 0;
		int starIndex = -1;
		int iIndex = -1;
		
		while (i < s.length()) {
			//Increment both pointers
			if (j < p.length() && (p.charAt(j) == '?' || p.charAt(
				j) == s.charAt(i))) {
				++i;
				++j;
			}else if (j < p.length() && p.charAt(j) == '*') { // * found , increment pattern pointer j and remember this * position
				starIndex = j;  //2
				iIndex = i;   //2
				j++;   //j = 3
			}else if (starIndex != -1) {  //last pattern was * hence we can increase string s pointer i and set j back to * + 1
				j = starIndex + 1;
				iIndex++;
				i = iIndex;
				
			}else {
				return false;
			}
		}
		
		while (j < p.length() && p.charAt(j) == '*') {
			++j;
		}
		return j == p.length();
	}
	
	public boolean isMatchDP(String s, String p) {
		if (s == null || p == null) return false;
		int sLen = s.length();
		int pLen = p.length();
		boolean[][] dp = new boolean[sLen + 1][pLen + 1];
		
		// Base cases:
		dp[0][0] = true;
		for (int i = 1; i <= sLen; i++) {
			dp[i][0] = false;
		}
		
		for (int j = 1; j <= pLen; j++) {
			if (p.charAt(j - 1) == '*') {
				dp[0][j] = dp[0][j - 1];
			}
		}
		
		for (int i = 1; i <= sLen; i++) {
			for (int j = 1; j <= pLen; j++) {
				if ((s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') && dp[i - 1][j - 1])
					dp[i][j] = true;
				else if (p.charAt(j - 1) == '*' && (dp[i - 1][j] || dp[i][j - 1]))
					dp[i][j] = true;
			}
		}
		
		for(boolean[] x: dp){
			System.out.println(Arrays.toString(x));
		}
		
		return dp[sLen][pLen];
	}
	public static void main(String[] args) {
		String s = "bbaa";
		String p = "*a*a";
		H0044_WildcardMatching cl = new H0044_WildcardMatching();
		System.out.println(cl.isMatchDP(s,p));
	}
}
