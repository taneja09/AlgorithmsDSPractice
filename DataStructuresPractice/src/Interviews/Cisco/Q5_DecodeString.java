package Interviews.Cisco;

import java.util.*;

public class Q5_DecodeString {
	
	public String expandString(String s){
		if(s== null || s.length()==0) return "";
		Stack<Integer> count = new Stack<>();
		Stack<StringBuilder> result = new Stack<>();
		result.push(new StringBuilder());
		int multiplier = 0;
		
		for(int i = 0; i< s.length(); i++){
			char val = s.charAt(i);
			
			if(val == '{'){
				continue;
			}else if(val == '('){
				result.push(new StringBuilder().append(val));
			}else if(val == ')'){
				result.push(new StringBuilder().append(val));
			} else if(Character.isDigit(val)){
				multiplier = multiplier*10 + Character.getNumericValue(val);
			}else if( val == '}'){
				count.push(multiplier);
				multiplier = 0;
				calculateMultiples(result,count);
			} else{
				if(!result.peek().toString().equals("("))
					result.push(result.pop().append(val));
				
				else result.push(new StringBuilder().append(val));
			}
		}
		return result.pop().toString();
	}
	
	
	private void calculateMultiples(Stack<StringBuilder> result ,Stack<Integer> count){
		if(result.peek().toString().equals(")")) result.pop();
		int num = (count.isEmpty() || count.peek() == 0) ? 1 : count.pop();
		StringBuilder subString = result.pop();
		StringBuilder newString = new StringBuilder();
		for(int j  = 0; j< num; j++)
			newString.append(subString);
		
		if(result.peek().toString().equals("(")) result.pop();
		result.push(result.pop().append(newString));
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		Q5_DecodeString cl = new Q5_DecodeString();
		System.out.println(cl.expandString(str));
	}
}
