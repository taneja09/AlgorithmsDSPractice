package Interviews.RobinHood;

import java.util.*;

public class Q12_RemoveMinPeak {
	public int[] getMeanPeaks(int[] arr){
		int[] result = new int[arr.length];
		int n = arr.length;
		
		List<Integer> list=new ArrayList<>();
		for(int i: arr)  list.add(i);
		
		for(int i = 0 ;i < n; i++) {
			int min = Integer.MAX_VALUE;
			int index = -1;
			int size = list.size();
			for(int j = 0;j < size;j++){                          //O(N)
				if(j == 0 && j+1 < size) {
					if (list.get(j) > list.get(j + 1) && min > list.get(j)) {
						min = list.get(j);
						index = j;
					}
				}else if(j==size-1 && j-1>=0) {
					if (list.get(j) > list.get(j - 1) && min > list.get(j)) {
						min = list.get(j);
						index = j;
					}
				}else if(size==1){
					min=list.get(j);
					index=j;
				}else if(list.get(j)>list.get(j-1) && list.get(j)>list.get(j+1) && min>list.get(j)) {
					min=list.get(j);
					index=j;
				}
			}
			list.remove(index);                                       //O(N)
			result[i]=min;
		}
		return result;
	}
	public static void main(String[] args) {
		int[] arr = {2,7,8,5,1,6,3,9,4};
		Q12_RemoveMinPeak cl = new Q12_RemoveMinPeak();
		System.out.println(Arrays.toString(cl.getMeanPeaks(arr)));
	}
}
