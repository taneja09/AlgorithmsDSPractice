package LC_Medium;

import java.util.*;

public class M0763_PartitionLabel {
	public List<Integer> partitionLabelsEasy(String S) {
		if (S == null || S.length() == 0) {
			return null;
		}
		int[] lastIndex = new int[26];
		
		//record last indices
		for (int i = 0; i < S.length(); i++) {
			lastIndex[S.charAt(i) - 'a'] = i;
		}
		List<Integer> res = new ArrayList<>();
		int last = 0;
		int start = 0;
		for (int i = 0; i < S.length(); i++) {
			last = Math.max(last, lastIndex[S.charAt(i) - 'a']);
			if (last == i) {
				res.add(last - start + 1);
				start = last + 1;
			}
		}
		
		return res;
	}
		public List<Integer> partitionLabels(String S) {
			List<Integer> res = new ArrayList<Integer>();
			// one edge case when we don't need to split into partitions
			if(S.charAt(0) == S.charAt(S.length() - 1))
			{
				res.add(S.length());
				return res;
			}
			// map to store last index of every character
			Map<Character, Integer> map = new HashMap<>();
			for(int i=0; i<S.length(); i++){
				map.put(S.charAt(i), i);
			}
			int firstIndex = 0, lastIndex = 0;
			// loop again to find all partitions
			for(int i=0; i<S.length(); i++) {
				lastIndex = Math.max(lastIndex, map.get(S.charAt(i)));
				// if we have traversed reached last Index of all the characters occured till now. We have one partition
				if(i==lastIndex){
					res.add(lastIndex-firstIndex+1);
					firstIndex = lastIndex+1;
				}
			}
			
			return res;
		}
		public static void main(String[] args) {
			String s = "ababcbacadefegdehijhklij";
			M0763_PartitionLabel cl = new M0763_PartitionLabel();
			List<Integer> res = cl.partitionLabels(s);
			System.out.print(res);
		}
	}
