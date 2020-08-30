package LC_Hard;
/*
https://leetcode.com/problems/concatenated-words/discuss/348972/Java-Common-template-Word-Break-I-Word-Break-II-Concatenated-Words


 */
import java.util.Arrays;
import java.util.*;

public class H0472_ConcatenatedWords {
	/************** Trie Solution **********************/
	private static Node root;
	class Node {
		Node[] children;
		boolean isWordEnd;
		
		public Node() {
			children = new Node[26];
			isWordEnd = false;
		}
	}
	
	public List<String> findAllConcatenatedWordsInADictTrie(String[] words){
		if (words == null || words.length == 0)
			return new ArrayList<>();
		
		root = new Node();
		buildTrie(words);// build the tree from given word list
		
		List<String> result = new LinkedList<>();
		for (String word : words) {
			if (isConcatenated(word, 0, 0))
				result.add(word);
		}
		
		return result;
	}
	// Return true if word starting from index is concatenated
	boolean isConcatenated(String word, int index, int countConcatenatedWords) {
		if (index == word.length())
			return countConcatenatedWords >= 2; //length of concatenated word should be greater than = 2
		Node ptr = root;
		for (int i = index; i < word.length(); i++) {
			if (ptr.children[word.charAt(i) - 'a'] == null)
				return false;
			ptr = ptr.children[word.charAt(i) - 'a'];
			if (ptr.isWordEnd) {
				if (isConcatenated(word, i + 1, countConcatenatedWords + 1)) return true;
			}
		}
		
		return false;
	}
	private void buildTrie(String[] words) {
		Node ptr;
		for (String word : words) {
			ptr = root;
			for (char ch : word.toCharArray()) {
				int val = ch - 'a';
				if (ptr.children[val] == null) {
					ptr.children[val] = new Node();
				}
				ptr = ptr.children[val];
			}
			ptr.isWordEnd = true;
		}
	}
	
	/************** Word Break Solution TC = O(n^3) n = avg length of each word **********************/
	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		//sort the array in asc order of word length, since longer words are formed by shorter words.
		Arrays.sort(words, (a, b) -> a.length() - b.length());
		List<String> result = new ArrayList<>();
		
		//list of shorter words
		HashSet<String> preWords = new HashSet<>();
		for(int i=0; i< words.length; i++) {
			//Word Break-I problem.
			if (wordBreak(words[i], preWords)) result.add(words[i]);
			preWords.add(words[i]);
		}
		return result;
	}
	
	private boolean wordBreak(String s, HashSet<String> preWords){
		if(preWords.isEmpty()) return false;
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;
		for(int i = 1; i <= s.length(); i++){
			for(int j = 0; j < i; j++){
				if(dp[j] && preWords.contains(s.substring(j, i))){
					dp[i] = true;
					break;
				}
			}
		}
		
		return dp[s.length()];
	}
	public static void main(String[] args) {
		String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
		H0472_ConcatenatedWords cl = new H0472_ConcatenatedWords();
		System.out.println(cl.findAllConcatenatedWordsInADict(words));
		System.out.println(cl.findAllConcatenatedWordsInADictTrie(words));
	}
}
