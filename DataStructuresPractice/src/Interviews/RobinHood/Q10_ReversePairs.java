package Interviews.RobinHood;

import java.util.*;

public class Q10_ReversePairs {
	private int reverseSum(int[] arr){
		int res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        
		//arr[i] - rev(arr[i]) =  arr[j] - rev(arr[j])
        for(int i = 0; i< arr.length; i++){
        	int k = arr[i] - rev(arr[i]);
			count.put(k, count.getOrDefault(k, 0) + 1);
			if (count.containsKey(k)) res += count.get(k);
		}
        return res;
	}
	
	private int rev(int x){
		int sum = 0;
		while (x > 0){
			sum *= 10;
			sum += x%10;
			x /= 10;
		}
		
		return sum;
	}
	public static void main(String[] args) {
		int[] arr = {1,20,2,11};
		Q10_ReversePairs cl = new Q10_ReversePairs();
		System.out.println(cl.reverseSum(arr));
	}
}
