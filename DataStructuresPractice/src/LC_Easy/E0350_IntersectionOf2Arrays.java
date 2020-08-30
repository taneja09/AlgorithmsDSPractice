package LC_Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class E0350_IntersectionOf2Arrays {
	
	/*************** HashMap TC = O(m+n) SC = O(m+n) ************************/
	public int[] intersect(int[] nums1, int[] nums2) {
		if(nums1.length == 0 || nums2.length == 0) return new int[0];
		List<Integer> al = new ArrayList();
		HashMap<Integer,Integer> hm = new HashMap<>();
		for(int num : nums1){
			hm.put(num,hm.getOrDefault(num,0)+1);
		}
		
		for(int num : nums2) {
			if (hm.containsKey(num) && hm.get(num) > 0) {
				al.add(num);
				hm.put(num, hm.get(num) - 1);
			}
		}
		
		int[] res = new int[al.size()];
		for(int i = 0; i< res.length; i++) res[i] = al.get(i);
		return res;
	}
	
	
	/*************** Sorted Arrays TC = O(m+n) SC = O(m+n) ************************/
	public int[] intersectSortedInput(int[] nums1, int[] nums2) {
		if(nums1.length == 0 || nums2.length == 0) return new int[0];
		List<Integer> al = new ArrayList();
		int i = 0;
		int j = 0;
		
		while(i< nums1.length && j < nums2.length){
			if(nums1[i] == nums2[j]){
				al.add(nums1[i]);
				i++;
				j++;
			}else if(nums1[i] < nums2[j]){
				i++;
			}else {
				j++;
			}

		}
		int[] res = new int[al.size()];
		for(int k = 0; k< res.length; k++) res[k] = al.get(k);
		return res;
		
	}
	public static void main(String[] args) {
		int[] nums1 = {4,4,5,9};
		int[] nums2 = {4,4,8,9,9};
		E0350_IntersectionOf2Arrays cl  = new E0350_IntersectionOf2Arrays();
		System.out.println(Arrays.toString(cl.intersectSortedInput(nums1,nums2)));
		
		
	}
}
