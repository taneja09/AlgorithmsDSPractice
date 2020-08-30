package LC_Medium;

public class M0567_PermutationInString {
	public boolean checkInclusion(String s1, String s2) {
		if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) return false;
		int[] hash = new int[26];
		
		for (char s : s1.toCharArray())
			hash[s -'a']++;
		
		int left = 0, right = 0, count = 0;
		
		while (right < s2.length()) {
			if(hash[s2.charAt(right) -'a']>0){
				hash[s2.charAt(right) -'a']--;
				count++;
				right++;
				if(count == s1.length()) return true;
			}else{
				hash[s2.charAt(left)-'a']++;
				count--;
				left++;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		String s1 = "ab", s2 ="eidboaoo";
		M0567_PermutationInString cl = new M0567_PermutationInString();
		boolean res = cl.checkInclusion(s1,s2);
		System.out.println(res);
	}
}

