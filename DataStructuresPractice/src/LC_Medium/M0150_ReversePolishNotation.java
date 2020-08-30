package LC_Medium;

import java.util.Stack;

public class M0150_ReversePolishNotation {
	public int evalRPN(String[] tokens) {
		if(tokens.length == 0|| tokens == null) return 0;
		Stack<Integer> st = new Stack();
		for(String s: tokens){
			if(s.equals("*") || s.equals("/") || s.equals("+") || s.equals("-")){
					st.push(calculateValue(st.pop(),st.pop(),s));
			}else{
				st.push(Integer.parseInt(s));
			}
		}
		return st.pop();
	}
	
	private int calculateValue(int f, int s,String operator){
		switch(operator){
			case "+":
				return f + s;
			case "*":
				return f * s;
			case "/":
				return s/f;
			case "-":
				return s-f;
		}
		
		return 0;
	}
	public static void main(String[] args) {
		String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
		M0150_ReversePolishNotation cl = new M0150_ReversePolishNotation();
		System.out.println(cl.evalRPN(tokens));
	}
}
