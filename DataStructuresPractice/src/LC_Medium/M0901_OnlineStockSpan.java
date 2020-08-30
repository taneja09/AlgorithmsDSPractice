package LC_Medium;

import java.util.Stack;

public class M0901_OnlineStockSpan {
	Stack<int[]> stack;
	
	public M0901_OnlineStockSpan() {
		stack = new Stack();
	}
	
	public int next(int price) {
		int span = 1;
		while(!stack.isEmpty() && stack.peek()[0]<= price){
			int[] curr = stack.pop();
			span += curr[1];
		}
		stack.push(new int[] {price, span});
		return span;
	}
	
	//***********************************************************//
//	Stack<Integer> prices, weights;
//
//	public M0901_OnlineStockSpan() {
//		prices = new Stack();
//		weights = new Stack();
//	}
//
//	public int next(int price) {
//		int w = 1;
//		while (!prices.isEmpty() && prices.peek() <= price) {
//			prices.pop();
//			w += weights.pop();
//		}
//
//		prices.push(price);
//		weights.push(w);
//		return w;
//	}
	
	public static void main(String[] args) {
		M0901_OnlineStockSpan obj = new M0901_OnlineStockSpan();
		System.out.println(obj.next(100));
		System.out.println(obj.next(80));
		System.out.println(obj.next(60));
		System.out.println(obj.next(70));
		System.out.println(obj.next(60));
		System.out.println(obj.next(75));
		System.out.println(obj.next(85));
		
		
		
	}
}
