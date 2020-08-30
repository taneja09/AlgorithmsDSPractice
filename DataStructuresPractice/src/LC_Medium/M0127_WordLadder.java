package LC_Medium;
/**
 *
 * TC - O(m*n)
 * Reason - n is size of the dictionary and m is the length of the word
 *
 * SC - O(n)
 * Reason - Queue space for storing wordlist
 *
 * https://leetcode.com/problems/word-ladder/discuss/40711/Two-end-BFS-in-Java-31ms.
 */



/**
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 */


import java.util.ArrayList;
import java.util.*;


public class M0127_WordLadder {
    /**********************************Bidirectional BFS TC = O(b^d) ***************************************************/
    public int ladderLengthBFS2(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>(), visited = new HashSet<>(), wordListSet = new HashSet<>();
        int level = 1;
        for(String word : wordList)
            wordListSet.add(word);
        
        if(!wordListSet.contains(endWord)) return 0;
        beginSet.add(beginWord);
        endSet.add(endWord);
        
        while(!beginSet.isEmpty() && !endSet.isEmpty()){
            if(beginSet.size() > endSet.size()){
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            
            Set<String> temp = new HashSet<>();
            for(String currWord : beginSet){
                char[] current = currWord.toCharArray();
                for(int i = 0; i< current.length; i++){
                    char old = current[i];
                    for(char ch = 'a'; ch <= 'z'; ch++){
                        current[i] = ch;
                        String check = String.valueOf(current);
                        if(endSet.contains(check)) return level + 1;
                        if(!visited.contains(check) && wordListSet.contains(check)){
                            visited.add(check);
                            temp.add(check);
                        }
                        current[i] = old;
                    }
                }
            }
            beginSet = temp;
            level++;
        }
        
        return 0;
    }
    
    
    /**********************************1 way BFS TC = O(b^d) ***************************************************/
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // create set of word list so lookup is faster
        HashSet<String> set = new HashSet();
        for (String word : wordList) {
            set.add(word);
        }
        
        if(!set.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<String>();
        queue.add(beginWord);
        int level = 0;


        while (!queue.isEmpty()) {
            int size = queue.size();

            // loop over the queue and find words that are 1 edit distance apart of the current element and add it to queue
            for (int i = 0; i < size; i++) {
                String cur = queue.remove();
                if (cur.equals(endWord)) return level + 1;

                for (int j = 0; j < cur.length(); j++) { // cur = "hit"
                    char[] word = cur.toCharArray();  //array of curr word characters

                    // find words that are 1 edit distance apart of the current element and present in the word list
                    for(char ch = 'a'; ch <= 'z'; ch++){
                        word[j] = ch;   // ait, hat, hia  || bit, hbt, bit and so on..... there will be word.length * 26 character total combinations
                        String check = new String(word);
                        if(!check.equals(cur) && set.contains(check)) {  // formed word  != current word && should be present in wordList to be qualified
                            queue.add(check);  // for beginning word hit => hot will be added as that's 1 edit apart and present in wordList as well
                            // remove from List to avoid dup check as now we have already processed this element
                            set.remove(check);
                        }
                    }

                }
            }
            level++;
        }
        return 0;
    }
    public static void main(String[] args) {
        M0127_WordLadder cl = new M0127_WordLadder();
        String beginWord = "ymain";
        String endWord = "oecij";
        String[] arr = {"ymann","yycrj","oecij","ymcnj","yzcrj","yycij","xecij","yecij","ymanj","yzcnj","ymain"};
        List<String> wordList = new ArrayList<>();
        for(String val : arr)  wordList.add(val);
        int res = cl.ladderLength(beginWord,endWord,wordList);
        System.out.println(res);
    }
}
