package LC_Easy;

public class E0242_ValidAnagram {
	public boolean isAnagram(String s, String t) {
		if(s == null || t== null || s.length() != t.length()) return false;
		int[] arr = new int[26];
		for(int i = 0; i<s.length(); i++){
			char ch= s.charAt(i);
			char tch = t.charAt(i);
			arr[ch -'a']++;
			arr[tch-'a']--;
		}
		
		for(int i : arr){
			if(i != 0 ) return false;
		}
		
		return true;
	}
	public static void main(String[] args) {
		String s1 = "anagram";
		String s2 ="nagarak";
		E0242_ValidAnagram cl = new E0242_ValidAnagram();
		System.out.println(cl.isAnagram(s1,s2));
	}
}
