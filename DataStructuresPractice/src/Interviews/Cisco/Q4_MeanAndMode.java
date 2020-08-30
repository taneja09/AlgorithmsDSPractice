package Interviews.Cisco;

import java.util.HashMap;

public class Q4_MeanAndMode {
	public double getMean(int[] array){
		int sum = 0;
		for(int x: array)
			sum += x;
		return (double)sum/(array.length);
	}
	
	public int getMode(int[] array) {
		HashMap<Integer, Integer> hm = new HashMap();
		int max1 = 0;
		int max2 = 0;
		int count1 = 0;
		int count2 = 0;
		
		for (int x : array) {
			int newCount = hm.getOrDefault(x, 0) + 1;
			if(newCount > count1) {
				max2 = max1;
				count2 = count1;
				max1 = x;
				count1 = newCount;
				
			}else if(newCount > count2){
				max2 = x;
				count2 = newCount;
			}
			hm.put(x, newCount);
		}
		return count1 == count2 ? Math.min(max1, max2) : max1;
	}
	
	public static void main(String[] args) {
		Q4_MeanAndMode cl = new Q4_MeanAndMode();
		int[] x = {-1,2,-1,2,3,5};
		System.out.printf("%.4f",cl.getMean(x));
		System.out.println(" ");
		System.out.println(cl.getMode(x));
	}
}
