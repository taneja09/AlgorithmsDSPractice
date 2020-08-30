package LC_Hard;
/*
https://www.youtube.com/watch?v=uR3RElKnrkU
 */

import java.util.*;

public class H0140_WordBreakII {
	public List<String> wordBreak(String s, List<String> wordDict) {
		return dfs(s,wordDict,new HashMap<String, List<String>>());
	}
	
	private List<String> dfs(String s,List<String> wordDict, HashMap<String,List<String>> map){
		if(map.containsKey(s)) return map.get(s);
		List<String> result = new ArrayList<String>();
		
		if (s.length() == 0) {
			result.add("");
			return result;
		}
		
		for(String word: wordDict){
			if(!s.startsWith(word)) continue;   // string does not start with this word?
			String next = s.substring(word.length());
			List<String>sublist = dfs(next,wordDict,map);
			for (String sub : sublist)
				result.add(word + (sub.isEmpty() ? "" : " ") + sub);
		}
		map.put(s, result);
		return result;
	}
	public static void main(String[] args) {
		String word = "catsanddog";
		ArrayList<String> sl = new ArrayList();
		sl.add("cat"); sl.add("cats");sl.add("and"); sl.add("sand"); sl.add("dog");
		H0140_WordBreakII cl = new H0140_WordBreakII();
		System.out.println(cl.wordBreak(word,sl));
	}
}
