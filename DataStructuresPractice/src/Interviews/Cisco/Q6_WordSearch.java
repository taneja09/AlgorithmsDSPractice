package Interviews.Cisco;

import java.util.*;

public class Q6_WordSearch {
	public String[] isValidWord(char[][] board,String[] words ){
		String[] result = new String[words.length];
		TrieNode root = buildTrie(words);
		List<String> res = new ArrayList<>();
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				backtrack (board, i, j, root, res);
			}
		}
		
		int i = 0;
		for(String word : words){
			if(res.contains(word))
				result[i] = "Yes";
			else
				result[i] = "No";
			
			i++;
		}
		return  result;
	}
	
	private void backtrack(char[][] board, int i, int j, TrieNode node, List<String> res){
		char c = board[i][j];
		if (c == '#' || node.next[c - 'A'] == null) return;
		node = node.next[c-'A'];
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
	private TrieNode buildTrie(String[] words){
		TrieNode root = new TrieNode();
		for(String word : words){
			TrieNode node = root;
			for (char c : word.toCharArray()) {
				int idx = c - 'A';
				if(node.next[idx] == null) node.next[idx] = new TrieNode();
				node = node.next[idx];
			}
			node.word = word;
		}
		return root;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int matrixLen = sc.nextInt();
		char[][] matrix = new char[matrixLen][matrixLen];
		
		for(int i = 0; i< matrixLen; i++){
			for(int j = 0; j< matrixLen; j++){
				matrix[i][j] = sc.next().charAt(0);
			}
		}
		
		 int wordListLength = sc.nextInt();
		 sc.nextLine();
		 String[] words = sc.nextLine().split(" ");
//		 List<String> list = new ArrayList();
//
//		 for(int i = 0; i< wordListLength; i++){
//		 	 list.add(sc.nextLine());
//		 }
//		System.out.println(list);
		Q6_WordSearch cl = new Q6_WordSearch();
		//System.out.println(Arrays.toString(cl.isValidWord(matrix,words)));
	}
}

class TrieNode{
	TrieNode[] next = new TrieNode[26];
	String word;
}
