package LC_Medium;
/**
 Time Complexity - O(n)
 Reason - Accessing all elements
 
 Space Complexity - O(n)
 Reason - n is length of provided word
 */
public class M0211_WordDictionary {
	private TrieNode root = new TrieNode();
	private class TrieNode{
		boolean isEnd;
		TrieNode[] links = new TrieNode[26]; // a-z
	}
	
	public void addWord(String word) {
		TrieNode node = root;
		for(int i = 0; i< word.length(); i++){
			char ch = word.charAt(i);
			if(node.links[ch - 'a'] == null) {
				node.links[ch - 'a'] = new TrieNode();
			}
			node = node.links[ch-'a'];
		}
		node.isEnd = true;
	}
	
	public boolean search(String word) {
		return search(word,root,0);
	}
	
	public boolean search(String word,TrieNode node,int index){
		if(node == null) return false;
		if(index == word.length()) return node.isEnd;
		char ch = word.charAt(index);
		
		if(ch == '.'){   //if this '.' that means we need to check next index in children and skip it
			for(TrieNode next : node.links) {  //checking in children for index+1
				if (search(word, next, index + 1))
					return true;
			}
			return false;
		}else{
			TrieNode next = node.links[ch -'a'];
			return search(word,next,index+1);
		}
	}
	
	public static void main(String[] args) {
		M0211_WordDictionary cl = new M0211_WordDictionary();
		cl.addWord("bad");
		cl.addWord("dad");
		cl.addWord("mad");
		System.out.println(cl.search("pad"));
		System.out.println(cl.search("bad"));
		System.out.println(cl.search(".ad"));
		System.out.println(cl.search("b.."));
	}
}
