package LC_Easy;

import java.util.*;

public class E0349_IntersectionOfTwoArrays {
	/********** Brute Force TC = O(n*m) sc = O(m) ********************/
	public int[] intersectionBrute(int[] nums1, int[] nums2) {
		Set<Integer> st = new HashSet<>();
		for(int i = 0; i<nums1.length; i++){
			for(int j = 0; j<nums2.length; j++)
				if(nums1[i] == nums2[j]) st.add(nums1[i]);
		}
		
		int[] res = new int[st.size()];
		int i = 0;
		for(int k : st) {
			res[i] = k;
			i++;
		}
		
		return res;
	}
	/********** Two Sets TC = O(n+m) sc = O(m) ********************/
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> st = new HashSet<>();
		Set<Integer> res = new HashSet<>();
		
		for(int j = 0; j<nums2.length; j++)
			st.add(nums2[j]);
		
		for(int i = 0; i< nums1.length; i++)
			if(st.contains(nums1[i])) res.add(nums1[i]);
		
		int[] res1 = new int[res.size()];
		int i = 0;
		for(int k : res) {
			res1[i] = k;
			i++;
		}
		return res1;
	}
	/********** Set Intersection TC = O(n) sc = O(n+m) ********************/
	public int[] intersectionFormula(int[] nums1, int[] nums2) {
		Set<Integer> st1 = new HashSet<>();
		Set<Integer> st2 = new HashSet<>();
		
		for (int i : nums1) st1.add(i);
		for	(int i : nums2)	st2.add(i);
		
		st1.retainAll(st2);
		
		int[] res = new int[st1.size()];
		int k = 0;
		for(int x : st1) res[k++] = x;
		return res;
	}
	public static void main(String[] args) {
		int[] nums1 = {1,2,2,1};
		int[] nums2 = {2,2};
		E0349_IntersectionOfTwoArrays cl = new E0349_IntersectionOfTwoArrays();
		System.out.println(Arrays.toString(cl.intersectionFormula(nums1,nums2)));
	}
}
