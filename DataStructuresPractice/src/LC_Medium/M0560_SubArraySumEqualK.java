package LC_Medium;
/*
https://www.youtube.com/watch?v=HbbYPQc-Oo4
 */
import java.util.HashMap;

/**
 
 Time Complexity - O(n)
 Reason - linear scan
 
 Space Complexity - O(1)
 Reason -
*/

public class M0560_SubArraySumEqualK {
	public int subarraySum(int[] nums, int k) {
		HashMap<Integer, Integer> hm = new HashMap<>();
		hm.put(0, 1);  //why to add this ..suppose we have single element 2 == k(2) hence we need sum-k 2-2 = 0 to give us 1 because single 2 = k which is a subarray
		int count = 0;
		int Sum =0;
		
		for(int num : nums){
			Sum += num;
			if(hm.containsKey(Sum-k))
				count += hm.get(Sum-k);
			hm.put(Sum,hm.getOrDefault(Sum,0)+1);
		}
		return count;
	}
	public static void main(String[] args) {
		M0560_SubArraySumEqualK cl = new M0560_SubArraySumEqualK();
		int[] arr = {1,1,1,2};
		System.out.println(cl.subarraySum(arr,2));
	}
}
