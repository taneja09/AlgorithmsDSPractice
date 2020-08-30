package LC_Medium;

import java.util.Arrays;
import java.util.Stack;

/**
 * Store currently unsolved elements, later if there is a bigger number, withdraw the unsolved elements and get the answer.
 */
public class M0739_DailyTemperature {
	
	//************ Efficient Solution  ******************//
	public int[] dailyTemperatures(int[] T) {
		int[] result = new int[T.length];
		Stack<Integer> stack = new Stack<>(); // Make it a stack of indices
		for (int i = 0; i < T.length; i++) {
			while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
				int index = stack.pop();
				result[index] = i - index;
			}
			stack.push(i);
		}
		return result;
	}
	
	//************ Brute force Solution  ******************//
		public int[] dailyTemperaturesBrute (int[] T) {
			int[] ans = new int[T.length];
			for(int i = 0; i < T.length; i++){
				for(int j = i + 1; j < T.length; j++){
					if(T[j] > T[i]){
						ans[i] = j - i;
						break;
					}
				}
			}
			return ans;
		}
	
	//************ TLE - time limit exceeds ******************//
	public int[] dailyTemperaturesTLE(int[] T) {
		int i = 1, j =0;
		int[] result = new int[T.length];
		
		while(i< T.length && j< T.length){
			if(T[i] > T[j]){
				result[j] = i-j;
				j++;
				i = j;
			}
			else
			{
				if(i == T.length-1){ j++; i = j;}
				else i++;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[] temperature  = {73,74,75,71,69,72,76,73};
		M0739_DailyTemperature cl = new M0739_DailyTemperature();
		//int[] result = cl.dailyTemperaturesTLE(temperature);
		int[] result = cl.dailyTemperatures(temperature);
		System.out.println(Arrays.toString(result));
	}
}
