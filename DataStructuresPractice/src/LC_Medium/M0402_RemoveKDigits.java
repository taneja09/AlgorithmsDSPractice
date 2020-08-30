package LC_Medium;

import java.util.*;

/**
 Observation 1: our highest prioirty is to move the number that is at index 0, with index 1 being 0
 For example, if we have 3004567 and we want to remove 1 digit, we defintely want to remove 3, so that we can
 get rid of the following 2 zeros, ending up with 4567, which will always give us the biggest decrease
 
 Observation 2: if there is no case of observation 1, then we want to remove the biggest number in the first ascenging sequence.
 For example, if we have 234543, we want to remove the 5 first, since after 5 the number starts going down.
 
 Observatino 3: if we have to remove more than 1 digit, every digit removal can use the same strategy, i.e we can use greedy algorithm here, aka not dp.
 
 */

public class M0402_RemoveKDigits {
	public String removeKDigits(String num, int k) {
		int digits = num.length() - k;
		char[] stk = new char[num.length()];
		int top = 0;
		
		// if the previous character in stk is larger than the current one
		// then removing it will get a smaller number
		// but we can only do so when k is larger than 0
		
		for (int i = 0; i < num.length(); ++i) {
			char c = num.charAt(i);
			while (top > 0 && stk[top-1] > c && k > 0) {
				top--;
				k--;
			}
			stk[top++] = c;
		}
		
		// find the index of first non-zero digit
		int idx = 0;
		while (idx < digits && stk[idx] == '0') idx++;  //skip zeros
		return idx == digits? "0": new String(stk, idx, digits - idx);  //starting index and number of characters
		
	}
	
	
	public String removeKDigitsStack(String num, int k) {
		if(k >= num.length()) return "0";
		Deque<Character> stack = new ArrayDeque<>();
		
		for(char c : num.toCharArray()) {
			while(k > 0 && !stack.isEmpty() && stack.peekLast() > c) {  // if current character < stack peek
				stack.removeLast(); //remove if from stack
				k--;  //reduce count
			}
			stack.addLast(c);  //add current character to stack
		}
		
		//if k is still > that mean more character needs to be removed then remove them
		while(k>0) {
	            stack.removeLast();
	            k--;
	        }
		
		// Remove all zeros from the front of the stack and then if stack is empty, return "0"
       while(!stack.isEmpty() && stack.peekFirst()== '0') stack.removeFirst();
       if(stack.isEmpty()) return "0";
       
		// build the number from the stack
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.removeFirst());
        }
        return sb.toString();
	}
	
	public static void main(String[] args) {
		M0402_RemoveKDigits cl = new M0402_RemoveKDigits();
		String num = "1432219";
		int k = 3;
		//String res = cl.removeKDigits(num,k);
		String res = cl.removeKDigitsStack(num,k);
		System.out.println(res);
	}
}
