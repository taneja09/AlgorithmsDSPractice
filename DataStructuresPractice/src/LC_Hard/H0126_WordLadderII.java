package LC_Hard;

import java.util.*;

public class H0126_WordLadderII {
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> result = new ArrayList<>();
		HashSet<String> dict = new HashSet<String>(wordList);
		Map<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();// Neighbors for every node
		Set<String> startSet = new HashSet<>();
		if(!dict.contains(endWord)) return result;
		startSet.add(beginWord);
		bfs(startSet,endWord,nodeNeighbors,dict);
		
		List<String> ls = new ArrayList<>();
		ls.add(beginWord);
		dfs(result,ls,beginWord,endWord,nodeNeighbors);
		return result;
	}
	private void dfs(List<List<String>> result, List<String> ls,String word, String endWord,Map<String, ArrayList<String>> nodeNeighbors ){
		if(word.equals(endWord)) {
			result.add(new ArrayList<>(ls));
		}
		if(nodeNeighbors.get(word) == null) return;
		for(String next : nodeNeighbors.get(word)){
			ls.add(next);
			dfs(result,ls,next,endWord,nodeNeighbors);
			ls.remove(ls.size()-1);
		}
	}
	private void bfs(Set<String> startSet, String endWord, Map<String, ArrayList<String>> nodeNeighbors  ,HashSet<String> dict ) {
		
		while (!startSet.isEmpty()) {
			Set<String> temp = new HashSet<>();
			dict.removeAll(startSet);
			
			for (String s : startSet) {
				char[] chs = s.toCharArray();
				for (int i = 0; i < chs.length; i++) {
					char old = chs[i];
					for (char ch = 'a'; ch <= 'z'; ch++) {
						chs[i] = ch;
						String check = String.valueOf(chs);
						if (dict.contains(check)) {
							temp.add(check);
							
							if (nodeNeighbors.get(s) == null)
								nodeNeighbors.put(s, new ArrayList<>());
							nodeNeighbors.get(s).add(check);
						}
						chs[i] = old;
					}
				}
			}
			startSet = temp;
		}
	}
	
	public static void main(String[] args) {
		H0126_WordLadderII cl = new H0126_WordLadderII();
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
