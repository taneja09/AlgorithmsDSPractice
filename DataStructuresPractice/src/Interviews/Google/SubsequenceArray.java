package Interviews.Google;

import java.util.*;

public class SubsequenceArray {
	public int findMissingInSequence(int[] A, int[] B){
		List<Integer> list = new ArrayList();
		for(int i = 0; i< B.length; i++){
			for(int j = 0; j< A.length; j++){
				if(A[j] == B[i]) list.add(j);
			}
		}
		int size = findLongestIncreasesSubsequence(list);
		return A.length - size;
	}
	
	private int findLongestIncreasesSubsequence(List<Integer> list){
		int[] dp = new int[list.size()];
	        int len = 0;
	        for (int num : list) {
	            int i = Arrays.binarySearch(dp, 0, len, num);
	            if (i < 0) {
	                i = -(i + 1);
	            }
	            dp[i] = num;
	            if (i == len) {
	                len++;
	            }
	        }
	        return len;
	}
	
	public static void main(String[] args) {
		int[] A = {1,2,3,4,5};
		int[] B = {2,5,6,4,9,12};
		
		SubsequenceArray obj = new SubsequenceArray();
		System.out.println(obj.findMissingInSequence(A,B));
	}
}
