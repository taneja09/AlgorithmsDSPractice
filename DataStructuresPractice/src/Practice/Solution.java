package Practice;
import java.util.*;

public class Solution {
	public List<Integer> coolFeature(int[]a , int[] b, int[][] query ){
		List<Integer> result = new ArrayList<>();
		for(int[] x : query){
			if(x[0] == 0){
				b[x[1]] = x[2];
			}else if(x[0] == 1){
				int pairCount = getPairs(a,b,x[1]);
				if(pairCount > 0) result.add(pairCount);
			}
		}
		return result;
	}
	
	private int getPairs(int[] a, int[]b, int val){
		int count = 0;
		Map<Integer,Integer> map = new HashMap<>();
		for (int i = 0; i < a.length; i++)
	           map.put(a[i],map.getOrDefault(a[i],0)+1);
		for (int j = 0; j < b.length; j++) {
			int key = val-b[j];
			if(map.containsKey(key) && map.get(key)>0){
				count += map.get(key);
			}
		}
		return count;
	}
	public static void main(String[] args) {
		Solution cl = new Solution();
		int[] a = {1,2,2};
		int[] b = {2,3};
		int[][] query = {{1,4},{0,0,3},{1,5}};
		System.out.println(cl.coolFeature(a,b,query));
	}
}



