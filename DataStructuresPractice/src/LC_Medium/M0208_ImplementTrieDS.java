package LC_Medium;

import Helpers.TrieNode;
/**
 Time Complexity - O(n)
 Reason - Accessing all elements
 
 Space Complexity - O(n)
 Reason - n is length of provided word
 */
public class M0208_ImplementTrieDS {
    private TrieNode root;

    /** Initialize your data structure here. */
    public M0208_ImplementTrieDS() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {  //if character doesn't exist, create it add to root
                node.put(currentChar, new TrieNode());
            }
            // if character exists, get the current node and
            // check next character going deeper into this node to match remaining characters
            node = node.get(currentChar);
        }
        node.setEnd(); //setting isEnd property of trie node
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();  // node.isEnd() makes sure that the complete word is found
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;   //just check if something is returned and not necessary to complete word found
    }

      /**search a prefix or whole key in trie and
      returns the node where search ends */

    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.containsKey(curLetter)) {  // keep moving as the characters are found
                node = node.get(curLetter);
            } else {
                return null; //once there is no match, it search stops and return the node till the point of match
            }
        }
        return node;
    }

    public static void main(String[] args) {
        M0208_ImplementTrieDS cl = new M0208_ImplementTrieDS();
        String[] actionArray = {"Trie","insert","search","search","startsWith","insert","search"};
        String[][] values = {{},{"apple"},{"apple"},{"app"},{"app"},{"app"},{"app"}};
    }
}


