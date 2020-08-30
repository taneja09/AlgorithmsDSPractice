package LC_Hard;

import java.util.*;

public class H0126_WordLadderIIBFS2 {
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> result = new ArrayList<>();
		HashSet<String> dict = new HashSet<String>(wordList);
		Map<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();// Neighbors for every node
		Set<String> startSet = new HashSet<>();
		Set<String> endSet = new HashSet<>();
		if(!dict.contains(endWord)) return result;
		startSet.add(beginWord);
		endSet.add(endWord);
		bfs1(startSet,endSet,endWord,nodeNeighbors,dict);
		
		List<String> ls = new ArrayList<>();
		ls.add(beginWord);
		dfs1(result,ls,beginWord,endWord,nodeNeighbors);
		return result;
	}
	private void dfs1(List<List<String>> result, List<String> ls,String word, String endWord,Map<String, ArrayList<String>> nodeNeighbors ){
		if(word.equals(endWord)) {
			result.add(new ArrayList<>(ls));
		}
		if(nodeNeighbors.get(word) == null) return;
		for(String next : nodeNeighbors.get(word)){
			ls.add(next);
			dfs1(result,ls,next,endWord,nodeNeighbors);
			ls.remove(ls.size()-1);
		}
	}
	private void bfs1(Set<String> startSet,Set<String> endSet, String endWord, Map<String, ArrayList<String>> nodeNeighbors  ,HashSet<String> dict ) {
		boolean found = false;
		boolean reverse = false;
		while (!startSet.isEmpty() && !endSet.isEmpty()) {
			dict.removeAll(startSet);
			dict.removeAll(endSet);
			if(startSet.size() > endSet.size()){
				reverse = !reverse;
				Set<String> set = startSet;
				startSet = endSet;
				endSet = set;
			}
			Set<String> temp = new HashSet<>();
			dict.removeAll(startSet);
			
			for (String s : startSet) {
				char[] chs = s.toCharArray();
				for (int i = 0; i < chs.length; i++) {
					char old = chs[i];
					for (char ch = 'a'; ch <= 'z'; ch++) {
						chs[i] = ch;
						String check = String.valueOf(chs);
						if (dict.contains(check) || endSet.contains(check)) {
							String key = reverse ? check : s;
							String val = reverse ? s : check;
							nodeNeighbors.putIfAbsent(key, new ArrayList<>());
							nodeNeighbors.get(key).add(val);
							
							if (endSet.contains(check)) found = true;
							else temp.add(check);
						}
						chs[i] = old;
					}
				}
			}
			startSet = temp;
			if (found) break;
		}
	}
	
	public static void main(String[] args) {
		H0126_WordLadderIIBFS2 cl = new H0126_WordLadderIIBFS2();
		String beginWord = "hit";
		String endWord = "cog";
		String[] arr = {"hot","dot","dog","lot","log","cog"};
		List<String> wordList = new ArrayList<>();
		for(String val : arr)  wordList.add(val);
		List<List<String>> res = cl.findLadders(beginWord,endWord,wordList);
		for(List<String> x : res)
			System.out.println(x);
	}
}
