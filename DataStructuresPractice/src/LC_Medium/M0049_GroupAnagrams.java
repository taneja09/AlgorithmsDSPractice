package LC_Medium;

import java.util.*;

public class M0049_GroupAnagrams {
	/*===================================  Sorting Method ================= */
	/**
	 
	 Time Complexity - O(NKlogK)
	 Reason -O(N) iterating through all elements & for each element we are sorting O(KlogK) where K is length of each string
	 
	 Space Complexity - O(NK)
	 Reason - N is number of elements and K is length of each string
	 */
	public List<List<String>> groupAnagrams1(String[] strs) {
		HashMap<String, ArrayList<String>> hm = new HashMap<>();
		List<List<String>> result = new ArrayList<>();
		
		for(String s : strs){
			
			char [] c = s.toCharArray();
			Arrays.sort(c);
			String SortedStr = new String(c);
			
			if(hm.containsKey(SortedStr)){
				hm.get(SortedStr).add(s);
			}else{
				ArrayList<String> abc = new ArrayList<>();
				abc.add(s);
				hm.put(SortedStr,abc);
			}
		}
		
		for(String s : hm.keySet()){
			result.add(hm.get(s));
		}
		
		return result;
	}
	/*===================================  Count Method for Characters ================= */
	
	/**
	 
	 Time Complexity - O(NKlogK)
	 Reason -O(N) iterating through all elements & for each element we are sorting O(KlogK) where K is length of each string
	 
	 Space Complexity - O(NK)
	 Reason - N is number of elements and K is length of each string
	 */
	
	public List<List<String>> groupAnagrams(String[] strs) {
		if (strs.length == 0) return new ArrayList();
		List<List<String>> result = new ArrayList();
		Map<String, List> ans = new HashMap<String, List>();
		int[] count = new int[26];
		
		for (String s : strs) {
			Arrays.fill(count, 0);
			for (char c : s.toCharArray()) count[c - 'a']++;  // counting character for each string
			StringBuilder sb = new StringBuilder("");
			for (int i = 0; i < 26; i++) {
				sb.append('#');
				sb.append(count[i]);  //count of characters 1#2#3#0#0# for abbccc string will be the key
			}
			
			String key = sb.toString();
			if (!ans.containsKey(key)) ans.put(key, new ArrayList());
			ans.get(key).add(s);
		}
		
		return new ArrayList(ans.values());
	}
	
	
	public static void main(String[] args) {
		String[] array = {"eat","tea","tan","ate","nat","bat"};
		M0049_GroupAnagrams cl = new M0049_GroupAnagrams();
		List<List<String>> result = cl.groupAnagrams(array);
		for(List s : result){
			for(String k : (ArrayList<String>)s)
				System.out.print(k + " ");
			System.out.println();
		}
	}
}
