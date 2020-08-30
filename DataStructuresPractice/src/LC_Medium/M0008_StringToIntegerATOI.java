package LC_Medium;

/**
 
 Time Complexity - O(n)
 Reason - traversal of string
 
 Space Complexity - O(1)
 Reason - No extra space
*/

public class M0008_StringToIntegerATOI {
	public int myAtoi(String str) {
		int index = 0, sign = 1, total = 0;
		if (str.length() == 0) return 0;
		
		while (index < str.length() && str.charAt(index) == ' ')
			index++;
		
		if (index < str.length() && (str.charAt(index) == '+' || str.charAt(index) == '-')) {
			sign = str.charAt(index) == '+' ? 1 : -1;
			index++;
		}
		
		if (index < str.length() && !Character.isDigit(str.charAt(index))) return 0;
		
		int result = 0;
		
		while (index < str.length()) {
			if (!Character.isDigit(str.charAt(index))) break;
			char current = str.charAt(index++);
			int val = (current - '0');
			if (result > Integer.MAX_VALUE / 10 || result == Integer.MAX_VALUE / 10 && val > 7) {
				return sign < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
			} else result = result * 10 + val;
		}
		
		return result* sign;
	}
	
	public static void main(String[] args) {
		String str = " 12-";
		M0008_StringToIntegerATOI cl = new M0008_StringToIntegerATOI();
		System.out.println(cl.myAtoi(str));
	}
}
