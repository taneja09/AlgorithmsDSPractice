package LC_Easy;


public class E0953_VerifyAnAlienDictionary {
	public boolean isAlienSorted(String[] words, String order) {
		int[] hash = new int[26];
		for(int i = 0; i<hash.length; i++){
			int idx = order.charAt(i) -'a';
			hash[idx] = i;
		}
		
		for (int i = 0; i < words.length -1; i++) {
			if(compare(words[i], words[i +1], hash) > 0)return false;  // first should be less than second
		}
		
		return true;
	}
	
	private int compare(String word1, String word2, int[] hash){
		int L1 = word1.length();
		int L2 = word2.length();
		int min = Math.min(L1,L2);
		
		for (int i = 0; i < min; i++) {
			int first = word1.charAt(i)-'a';
			int second = word2.charAt(i)-'a';
			if(first != second)  return hash[first] - hash[second];
		}
		
		return L1 == min ? -1 : 1;  //if first word is greater return false
	}
	public static void main(String[] args) {
		String[] words = {"hello","leetcode"};
		String order = "hlabcdefgijkmnopqrstuvwxyz";
		E0953_VerifyAnAlienDictionary cl = new E0953_VerifyAnAlienDictionary();
		System.out.println(cl.isAlienSorted(words,order));
	}
}
