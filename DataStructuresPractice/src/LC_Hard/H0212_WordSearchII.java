package LC_Hard;

import java.util.ArrayList;
import java.util.*;

public class H0212_WordSearchII {
	/************** Trie Solution ****************************/
	public List<String> findWordsTrie(char[][] board, String[] words) {
		List<String> res = new ArrayList<>();
		TrieNode root = buildTrie(words);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				backtrack (board, i, j, root, res);
			}
		}
		return res;
	}
	
	private void backtrack(char[][] board, int i, int j, TrieNode node, List<String> res){
		char c = board[i][j];
		if (c == '#' || node.next[c - 'a'] == null) return;
		node = node.next[c-'a'];
		if(node.word != null){  // found the word in the trie
			res.add(node.word);
			node.word = null; // remove word so that it shouldn't duplicate
		}
		
		board[i][j] = '#';
		if(i > 0 ) backtrack(board,i-1,j,node,res);
		if(j > 0 ) backtrack(board,i,j-1,node,res);
		if(i < board.length -1 ) backtrack(board,i+1,j,node,res);
		if(j < board[0].length -1 ) backtrack(board,i,j+1,node,res);
		
		board[i][j] = c;
	}
	
	class TrieNode {
		TrieNode[] next = new TrieNode[26];
		String word;
	}
	
	private TrieNode buildTrie(String[] words){
		TrieNode root = new TrieNode();
		for(String word : words){
			TrieNode node = root;
			for (char c : word.toCharArray()) {
				int idx = c - 'a';
				if(node.next[idx] == null) node.next[idx] = new TrieNode();
				node = node.next[idx];
			}
			node.word = word;
		}
		return root;
	}
	/************** Brute Force Solution ****************************/
	public List<String> findWords(char[][] board, String[] words) {
		List<String>  result = new ArrayList<>();
		Set<String> subSet = new HashSet<>();
		if(board.length == 0 || words.length == 0)
			return result;
		
		for(int i = 0; i< board.length; i++) {
			for(int j = 0; j< board[0].length; j++) {
				for(String word : words){
					if(board[i][j] == word.charAt(0) && !subSet.contains(word)){
						if(dfs(word,i,j,board,0)) subSet.add(word);
					}
				}
			}
		}
		result.addAll(subSet);
		return result;
	}
	
	private boolean dfs(String word,int i, int j, char[][] board, int index){
		if(i< 0 || i > board.length-1 || j< 0 || j > board[0].length-1 || board[i][j] != word.charAt(index))  return false;
		if (index == word.length() - 1) return true;
		board[i][j] = ' ';
		boolean exist = false;
		exist = dfs(word,i+1,j,board,index+1) || dfs(word,i,j+1,board,index+1) ||
			dfs(word,i-1,j,board,index+1) || dfs(word,i,j-1,board,index+1);
		
		board[i][j] = word.charAt(index);
		return exist;
	}
	public static void main(String[] args) {
		char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
		String[] words = {"oath","pea","eat","rain"};
		H0212_WordSearchII cl = new H0212_WordSearchII();
		System.out.println(cl.findWordsTrie(board,words));
	}
}
