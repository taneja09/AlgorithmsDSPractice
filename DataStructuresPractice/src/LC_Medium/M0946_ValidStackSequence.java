package LC_Medium;

import java.util.*;
/******** TC = O(n) && SC = O(n) ********************************/
public class M0946_ValidStackSequence {
	public boolean validateStackSequences(int[] pushed, int[] popped) {
		if(pushed.length != popped.length) return false;
		Stack<Integer> st = new Stack<>();
		int popIndex =0;
		
		for(int val : pushed){
			st.push(val);
			if(st.peek().equals(popped[popIndex])){
				while(!st.isEmpty() && popIndex < popped.length && st.peek().equals(popped[popIndex])){
					st.pop();
					popIndex++;
				}
			}
		}
		return st.isEmpty();
	}

	public static void main(String[] args) {
		int[] pushed = {48,70,284,568,870,142,32,1};
		int[] popped = {48,70,284,870,142,32,568,1};
		M0946_ValidStackSequence cl = new M0946_ValidStackSequence();
		System.out.println(cl.validateStackSequences(pushed,popped));
	}
}
