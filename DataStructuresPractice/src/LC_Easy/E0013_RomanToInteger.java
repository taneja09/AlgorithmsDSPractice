package LC_Easy;
/**
 *  Time Complexity - O(n)
 *  Reason - only one time traversal
 *
 *  Space Complexity - O(1)
 *  Reason - No extra space required
 */
public class E0013_RomanToInteger {
	public int romanToInt(String s) {
  		if(s.length() == 0) return 0;
  		int result = 0;
  		char prev = ' ';
		for(char c : s.toCharArray()){
			if((prev == 'I' && (c == 'V' || c == 'X')) || (prev == 'X' && (c == 'L' || c == 'C')) || (prev == 'C' && (c == 'D' || c == 'M'))){
				int sub = getValue(prev);
				int val = getValue(c);
				result = result + val-2*sub;
				
			}else{
				result = result + getValue(c);
			}
			prev = c;
		}
		return result;
 	}
 	
 	private int getValue(char c){
		switch(c){
			case 'I':
				return 1;
			case 'V':
				return 5;
			case 'X':
				return 10;
			case 'L':
				return 50;
			case 'C':
				return 100;
			case 'D':
				return 500;
			case 'M':
				return 1000;
		}
		return 0;
	}
	public static void main(String[] args) {
		String val = "0";
		E0013_RomanToInteger cl = new E0013_RomanToInteger();
		System.out.println(cl.romanToInt(val));
	}
}
