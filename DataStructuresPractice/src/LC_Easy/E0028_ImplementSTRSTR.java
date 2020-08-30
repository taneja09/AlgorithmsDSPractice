package LC_Easy;

public class E0028_ImplementSTRSTR {
	
	/********************** Run time - 2ms
	 * Run Time - O(m*n) for every index in haystack we are looking for length of needle
	 *
	 * Space complexity = 0(1)
	 *
	 * ************/
	
	public int strStr(String haystack, String needle) {
		int result = -1;
		if(needle.length() == 0 || needle.equals(haystack)) return 0;
		if(needle.length() > haystack.length()) return result;
		
		for(int i = 0; i< haystack.length() ; i++) {
			if (haystack.charAt(i) == needle.charAt(0)) {
				result = i;
				if(valid(i,haystack,needle)) return result;
				else result = -1;
			}
		}
		return result;
	}
 
	private boolean valid( int start , String haysack, String needle){
		int val = start+needle.length();
		if(val > haysack.length()) return false;
		else return haysack.substring(start,val).equals(needle);
	}
	
	/********************** Check window(size of needle) for each index in haysack -0ms
	 * Run Time - O(m*n) for every index in haystack we are looking for length of needle
	 *
	 * Space complexity = 0(1)
	 *
	 * ************/
	
	public int strStr1(String haystack, String needle) {
		int i=0;
		if(needle.length() == 0) return 0;
		if(haystack.length() < needle.length()) return -1;
		while (i < haystack.length() - needle.length() + 1){
			if(haystack.substring(i, needle.length()+i).equals(needle)) return i;
			i++;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String haystack = "mississippi";
		String needle = "issipi";
		E0028_ImplementSTRSTR cl = new E0028_ImplementSTRSTR();
		System.out.println(cl.strStr(haystack,needle));
	}
}
