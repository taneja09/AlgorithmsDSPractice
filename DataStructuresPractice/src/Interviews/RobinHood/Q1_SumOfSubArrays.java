package Interviews.RobinHood;

public class Q1_SumOfSubArrays {
	public int subArraysSumCombinations(int[] array){
		int len = array.length;
		if(len <= 2) return 0;
		long totalSum = 0;
		int count = 0;
		
		for(int val : array)
			totalSum += val;
		
		long sum1 = 0;
		long sum2 = 0;
		for(int i = 0; i< array.length-2; i++){
			sum1 += array[i];
			sum2 = 0;
			for(int j = i+1; j< array.length-1 ; j++){
				sum2 += array[j];
				if( (sum1<= sum2) && (sum2 <= totalSum - sum1 - sum2)) count++;
			}
		}
		return count;
	}
	public static void main(String[] args) {
		int[] array = {1,2,2,2,5,0};
		Q1_SumOfSubArrays obj = new Q1_SumOfSubArrays();
		System.out.println(obj.subArraysSumCombinations(array));
	}
}
