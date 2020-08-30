package LC_Medium;

public class M0395_LongestSubStrWithKRepeating {
	public int longestSubstring(String s, int k) {
		return divideAndConcur(s.toCharArray(), 0, s.length(), k);
    }
    
    private int divideAndConcur(char[] chs, int start, int end , int k){
		if(end-start < k) return 0;  // there is no substring of length K with given conditions
		int[] count = new int[26];
		for(int i = start; i < end; i++)
      		count[chs[i]-'a']++;
		
		for(int i = start; i < end; i++) {
			if(count[chs[i]-'a'] < k) {
				int newStart = i+1;
				while(newStart < end && count[chs[newStart]-'a'] < k) newStart ++;  //keep on checking till we found a character that has >= k repeating characters
				return Math.max(divideAndConcur(chs, start, i, k), divideAndConcur(chs, newStart, end, k));  //once found, check on both sides
			}
		}
		
		return end - start;
	}
	
	public static void main(String[] args) {
		String s = "aaabb";
		int k = 3;
		M0395_LongestSubStrWithKRepeating cl = new M0395_LongestSubStrWithKRepeating();
		int res = cl.longestSubstring(s,k);
		System.out.println(res);
	}
}
