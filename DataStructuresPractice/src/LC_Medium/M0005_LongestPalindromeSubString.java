package LC_Medium;

/**
 
 Time Complexity - O(n^2)
 Reason - Since expanding a palindrome around its center could take O(n) for all elements its O(n) * O(n)
 
 Space Complexity - O(n1)
 Reason - Not using any additional space
*/

public class M0005_LongestPalindromeSubString {
	
	public String longestPalindrome(String s) {
		String max = "";
		for (int i = 0; i < s.length(); i++) {
			String s1 = extend(s, i, i); // assuming the string to be odd length
			String s2 = extend(s, i, i + 1); //assuming the string to be even length
			if (s1.length() > max.length()) max = s1;
			if (s2.length() > max.length()) max = s2;
		}
		return max;
	}
	/*
	for all indexes , we need to expand the palindromic string and check.
	Example :
	Send the pointers and expand it at both sides like i = 2, j =3 ( i goes left i-- && j goes right j++)
	check for i = 2 = j = 3
	ex[and i = 1 && j ==4
	expand i = 0 && j == 5
	
	till length is exhausted or no match is found and return the substring
	 
	 */
	private String extend(String s, int i, int j) {
		while(i>=0 && j<s.length()){
			if (s.charAt(i) != s.charAt(j)) break;
			else {
				i--;
				j++;
			}
		}
		
		return s.substring(i + 1, j);
	}
	
	/**
	 
	 Time Complexity - O(n^2)
	 Reason - Since expanding a palindrome around its center could take O(n) for all elements its O(n) * O(n)
	 
	 Space Complexity - O(n^2)
	 Reason - Extra space to store previous values
	*/
	
	/**
	 * ======================  DP Solution ===================== //
	 */
	public String DPlongestPalindrome(String s) {
		if (s.length() < 2) return s;
		int max=0, start =0, end=0;
		boolean dp[][] = new boolean[s.length()][s.length()];
		
		for (int i = 0; i < s.length(); i++) {
			dp[i][i] = true;
		}
		
		for (int i = s.length() - 1; i >= 0; i--) {
			for (int j = i + 1; j < s.length(); j++) {
				if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {  //j - i <=2 means length is less than 2 it will automatically be palindrome
					dp[i][j] = true;
					if (j - i + 1 > max) {    //new palindrome length > existing max value;
						max = j - i + 1;
						start = i;
						end = j;
					}
				}
			}
		}
		return s.substring(start,end+1);
	}
	
	public static void main(String[] args) {
		String s = "cbbd";
		M0005_LongestPalindromeSubString cl = new M0005_LongestPalindromeSubString();
		System.out.println(cl.longestPalindrome(s));
		System.out.println(cl.DPlongestPalindrome(s));
	}
}
