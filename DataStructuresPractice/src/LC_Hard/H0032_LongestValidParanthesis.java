package LC_Hard;

import java.util.Stack;

public class H0032_LongestValidParanthesis {
	/*************** 1 Stack Solution TC = O(n) SC = O(n) **********************/
	public int longestValidParenthesesStack(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		int max_len=0;
		stack.push(-1); //for first case where we need 2 but 1-0 won't give me 2 hence 1- (-1) will be used
		for(int i = 0; i < s.length(); i++){
			char ch = s.charAt(i);
			if(ch == '(') stack.push(i);
			else{
				stack.pop();
				if(stack.isEmpty())
					stack.push(i);
				else
					max_len = Math.max(max_len, i- stack.peek());
			}
		}
		
		return max_len;
	}
	
	/*************** DP TC = O(n) SC = O(n) **********************/
	public int longestValidParenthesesDP(String s) {
		int maxans = 0;
		int dp[] = new int[s.length()];
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				if (s.charAt(i - 1) == '(') {
					dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
				} else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
					dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
				}
				maxans = Math.max(maxans, dp[i]);
			}
		}
		return maxans;
	}
	
	/*************** Brute Force TC = O(n^3) SC = O(n) TLE **********************/
	public int longestValidParenthesesBrute(String s) {
		int maxlen = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 2; j <= s.length(); j+=2) {
				if (isValid(s.substring(i, j))) {
					maxlen = Math.max(maxlen, j - i);
				}
			}
		}
		return maxlen;
	}
	
	private boolean isValid(String s){
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push('(');
			} else if (!stack.empty() && stack.peek() == '(') {
				stack.pop();
			} else {
				return false;
			}
		}
		return stack.empty();
	}
	public static void main(String[] args) {
		String paran = "())";
		H0032_LongestValidParanthesis cl = new H0032_LongestValidParanthesis();
		System.out.println(cl.longestValidParenthesesStack(paran));
	}
}
