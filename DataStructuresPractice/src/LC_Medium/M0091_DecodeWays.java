package LC_Medium;

/*
Recursion Technique = we have two choices - decode single char or decode this char and the next combined.
If we are able to decode, we move the index i accordingly and make recursive call on the subproblem (substring).
Valid Singles - 1-9
Valid doubles 10-26
nothing should start from 0 and hence 01 - 1 so we cant decode 01
 */

public class M0091_DecodeWays {
	
	/********8 Time limit Exceeds - **************************/
	public int numDecodingsRecursive(String s) {
		return s.isEmpty()? 0: numDecodingsRecursive(s,0);
	}
	
	private int numDecodingsRecursive(String s,int i){
		if(i >= s.length()) return 1;
		int count = 0;
		int val = Integer.parseInt(s.substring(i, i+1));
		if(1 <= val && val <= 9) count +=  numDecodingsRecursive(s, i+1);
		if(s.charAt(i) != '0'  && i+1 < s.length() && Integer.parseInt(s.substring(i, i+2)) <= 26) count += numDecodingsRecursive(s, i+2);
		return count;
	}
	
	/******** Top Down Memoization DP TC= O(n) SC = O(n+1) **************************/
	public int numDecodingsTD(String s) {
		int[] dp = new int[s.length()+1];
		return s.isEmpty()? 0: numDecodingsTD(s,0, dp);
	}
	
	private int numDecodingsTD(String s, int i, int[] dp ){
		if(i >= s.length()) return 1;
		if(dp[i] > 0 ) return dp[i];
		dp[i] = 0;
		int val = Integer.parseInt(s.substring(i, i+1));
		if(1 <= val && val <= 9) dp[i] +=  numDecodingsTD(s, i+1,dp);
		if(s.charAt(i) != '0'  && i+1 < s.length() && Integer.parseInt(s.substring(i, i+2)) <= 26) dp[i] += numDecodingsTD(s, i+2,dp);
		
		return dp[i];
	}
	
	/******** Bottom Up DP   TC= O(n) SC = O(n+1) **************************/
	//for given string "12" array is [1,1,2]  Then when i = 2, first is 2, second is 12, both can be decoded. So dp[2] = dp[1] + dp[0],
	public int numDecodings(String s) {
		int[] dp = new int[s.length()+1];
		dp[0] = 1;
		dp[1] = s.charAt(0) != '0' ? 1 : 0; //when there's one character, if it is not zero, it can only be 1 decode way. If it is 0, there will be no decode ways.
		for(int i = 2; i<= s.length(); i++){
			int first = Integer.valueOf(s.substring(i - 1, i));
         	int second = Integer.valueOf(s.substring(i - 2, i));
			if (first >= 1 && first <= 9) dp[i] += dp[i-1];     //these 2 lines just doing dp[2] = dp[2-1] + dp[2-2] or dp[1] + dp[0];
			if (second >= 10 && second <= 26) dp[i] += dp[i-2];
		}
		return dp[s.length()];
	}
	
	public static void main(String[] args) {
		String s = "226";
		M0091_DecodeWays  cl = new M0091_DecodeWays();
		System.out.println(cl.numDecodingsRecursive(s));
		System.out.println(cl.numDecodingsTD(s));
		System.out.println(cl.numDecodings(s));
	}
}
