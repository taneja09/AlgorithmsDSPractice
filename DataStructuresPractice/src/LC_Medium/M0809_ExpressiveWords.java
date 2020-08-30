package LC_Medium;

public class M0809_ExpressiveWords {
	public int expressiveWords(String S, String[] words) {
		if (S == null || words == null) {
			return 0;
		}
		int count = 0;
		for(String word : words){
			if(isStretchy(S,word)) count++;
		}
		
		return count;
	}
	
	private boolean isStretchy(String s, String word){
		if (word == null) return false;
		int i = 0;
        int j = 0;
        
        while(i< s.length() && j< word.length()){
        	if(s.charAt(i) == word.charAt(j)){
				int len1 = getRepeatedLength(s, i);
				int len2 = getRepeatedLength(word,j);
				if((len1 < 3 && len1 != len2) || (len1 >= 3 && len1<len2)) return false;
				i += len1;  //jump to next character in both strings
				j += len2;
			}else{  //if character doesnt match at i and j then return false;
        		return false;
			}
		}
		return i == s.length() && j == word.length();  //if both strings are compared and word is stretchy to make s
	}
	
	private int getRepeatedLength(String s, int i){
		int j = i;
		while(j<s.length() && s.charAt(j) == s.charAt(i)) j++;
		return j-i;
	}
	public static void main(String[] args) {
		String[] words = {"hello", "hi", "helo"};
		String s = "heeellooo";
		M0809_ExpressiveWords cl = new M0809_ExpressiveWords();
		System.out.println(cl.expressiveWords(s,words));
	}
}
