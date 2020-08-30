package LC_Easy;

/**
 
 Time Complexity - O(n) where n is length of String
 Reason - One pass
 
 Space Complexity - O(n)
 Reason - worst case when all brackedts are left side ... else n/2 if all of them are in pair
*/

import java.util.Stack;

public class E0020_ValidParantheses {
	public boolean isValid(String s) {
		Stack<Character> st = new Stack<>();
		for(char c : s.toCharArray()){
			if(c == '(' || c == '{' || c == '[')
				st.push(c);
			else if(!matched(st,c))
				return false;
			
		}
		return st.isEmpty();
	}
	
	private boolean matched(Stack<Character> st, Character c){
		if(!st.isEmpty()) {
			if (c == ')' && st.peek() == '(') {
				st.pop();
				return true;
			} else if (c == '}' && st.peek() == '{') {
				st.pop();
				return true;
			} else if (c == ']' && st.peek() == '[') {
				st.pop();
				return true;
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		String str = "(";
		E0020_ValidParantheses cl = new E0020_ValidParantheses();
		System.out.println(cl.isValid(str));
	}
}
