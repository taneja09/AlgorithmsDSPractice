package Interviews.RobinHood;

import java.util.Arrays;

public class Q2_PythagorianTriplet {
	
	public int[] pythagorianTriplets(int[] array){
		
		for(int i = 0; i< array.length ; i++) {
			array[i] = array[i] * array[i];
		}
		int[] result = new int[array.length-2];
		for(int i = 2; i< array.length; i++){
			if(array[i] == array[i-1] + array[i-2] || array[i-1] == array[i-2] + array[i] || array[i-2] == array[i-1] + array[i]){
				result[i-2] = 1;
			}
		}
		return result;
	}
	public static void main(String[] args) {
		int[] array = {0,5,5,0,5,13,12};
		Q2_PythagorianTriplet cl = new Q2_PythagorianTriplet();
		System.out.println(Arrays.toString(cl.pythagorianTriplets(array)));
	}
}
