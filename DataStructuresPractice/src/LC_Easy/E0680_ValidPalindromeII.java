package LC_Easy;

public class E0680_ValidPalindromeII {
	public boolean validPalindrome(String s) {
		int start = 0;
		int end = s.length()-1;
		while (start <= end) {
			if(s.charAt(start) == s.charAt(end)) {
				start++;
				end--;
			}
			else return (isPalindrome(s.substring(start,end)) || isPalindrome(s.substring(start+1,end+1)));
		}
		return true;
	}
	
	private boolean isPalindrome(String str){
		int start = 0; int end = str.length()-1;
		while(start<= end) {
			if (str.charAt(start) != str.charAt(end)) return false;
			start++;
			end--;
		}
		return true;
	}
	
	public static void main(String[] args) {
		String s = "abbbcba";
		E0680_ValidPalindromeII cl = new E0680_ValidPalindromeII();
		System.out.println(cl.validPalindrome(s));
	}
	
}
