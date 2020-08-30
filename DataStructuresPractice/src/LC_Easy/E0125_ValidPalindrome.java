package LC_Easy;
/**
 *  Time Complexity - O(n)
 *  Reason - only one time traversal
 *
 *  Space Complexity - O(1)
 *  Reason - No extra space required
 */

public class E0125_ValidPalindrome {
	public boolean isPalindrome(String s) {
		if (s == null || s.length() == 0) return true;
		int start = 0;
		int end = s.length() - 1;
		
		while (start <= end) {
			char c1 = s.charAt(start);
			char c2 = s.charAt(end);
			
			if (Character.isLetterOrDigit(c1) && Character.isLetterOrDigit(c2)) {
				c1 = Character.toLowerCase(c1);
				c2 = Character.toLowerCase(c2);
				if (c1 != c2) return false;
				else {
					start++;
					end--;
				}
			} else if (!Character.isLetterOrDigit(c1)) start++;
			else end--;
		}
		return true;
	}
	public static void main(String[] args) {
		String s = "race a  ecar";
		E0125_ValidPalindrome cl = new E0125_ValidPalindrome();
		System.out.println(cl.isPalindrome(s));
	}
}
