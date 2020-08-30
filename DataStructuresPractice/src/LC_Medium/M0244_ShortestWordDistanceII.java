package LC_Medium;

import java.util.*;

public class M0244_ShortestWordDistanceII {
	HashMap<String, ArrayList<Integer>> hm = new HashMap();
	public M0244_ShortestWordDistanceII(String[] words) {
		for(int i = 0; i< words.length; i++){
			ArrayList<Integer> al = hm.getOrDefault(words[i],new ArrayList<Integer>());
			al.add(i);
			hm.put(words[i],al);
		}
	}
	
	public int shortest(String word1, String word2) {
		int l1 = -1, l2 =-1, len = Integer.MAX_VALUE;
		if(!hm.containsKey(word1) || !hm.containsKey(word2)) return -1;
		else{
			List<Integer> a = hm.get(word1);
			List<Integer> b = hm.get(word2);
			
			for(int i  = 0 ; i<a.size(); i++){
				for(int j = 0; j<b.size(); j++){
					len = Math.min(len, Math.abs(a.get(i)-b.get(j)));
				}
			}
		}
		
		return len;
	}
	public static void main(String[] args) {
		String[] dictionary = {"practice", "makes", "perfect", "coding", "makes"};
		String word1 = "makes";
		String word2 = "coding";
		M0244_ShortestWordDistanceII cl = new M0244_ShortestWordDistanceII(dictionary);
		System.out.println(cl.shortest(word1,word2));
	}
}
