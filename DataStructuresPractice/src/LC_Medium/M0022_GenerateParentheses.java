package LC_Medium;

import java.util.ArrayList;
import java.util.List;

public class M0022_GenerateParentheses {
	/*************************  Recursion with SubString- 0ms    *********************************/
	public List<String> generateParenthesisEasy(int n) {
		List<String> res = new ArrayList<>();
		helper(res, new StringBuilder(), 0, 0, n);
		return res;
	}
	
	private void helper(List<String> res, StringBuilder sb, int open, int close, int n) {
		if(open == n && close == n) {
			res.add(sb.toString());
			return;
		}
		
		if(open < n) {
			sb.append("(");
			helper(res, sb, open+1, close, n);
			sb.setLength(sb.length()-1);
		}
		if(close < open) {
			sb.append(")");
			helper(res, sb, open, close+1, n);
			sb.setLength(sb.length()-1);
		}
	}
	
	/*************************  Recursion with String - 1ms  *********************************/
	
	public List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList();
		getCombination(ans, "", 0, 0, n);
		return ans;
	}
	
	private void getCombination(List<String> result,String cur, int open, int close, int n){
		if (cur.length() == n * 2) {
			result.add(cur);
			return;
		}
		if (open < n)
			getCombination(result, cur+"(", open+1, close, n);
		if (close < open)
			getCombination(result, cur+")", open, close+1, n);
	}
	
	public static void main(String[] args) {
		M0022_GenerateParentheses cl = new M0022_GenerateParentheses();
		//List<String>  result = cl.generateParenthesis(2);
		List<String>  result = cl.generateParenthesisEasy(2);
		for(String s: result)
			System.out.println(s);
	}
}

/*
While we are using String, and add "(" or ")" for recursion , a new string is created which is sent to the recursion process.
When string comes back its the old string which can be used for next addition of parantheses.

But However, when we use StringBuilder and append , the same stringBuilder is sent for recursion and same stringBuilder is return
and hence to make to its old state we need to remove the last character from it explicitly. It is similar to unchoosing from the backtrack

 */