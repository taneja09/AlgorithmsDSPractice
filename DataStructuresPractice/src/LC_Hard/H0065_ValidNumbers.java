package LC_Hard;

public class H0065_ValidNumbers {
	public boolean isNumber(String s) {
		s = s.trim();  /*removed leading and trailing spaces*/
		if(s.isEmpty() || s.charAt(0) == ' ') return false;
		boolean exp = false; /* for exponential 'e'*/
		boolean sign = false;  /* for '+' and '-'*/
		boolean digit = false;  /* for a digit 0- 9*/
		boolean dot = false;  /* for '.' character*/
		int len = s.length();
		
		for(int i = 0; i< len; i++){
			char c = s.charAt(i);
			if(c == ' ') return false; /* If any space is there in between "1 2" -> false;*/
			if(Character.isLetter(c) && c != 'e') return false; /* Any character apart from 'e' => return false*/
			
			/*  Sign '+' OR '-'
				1. if its at starting , we got the sign of our string  "-123" or "+123" etc
				2. signs '"+" "-" are allowed only after 'e' and it shouldn't be last character "4e+" => false  but "4e+2" => true
				3. If sign already seen (++6 => false) , if digit is already seen (6+6) => false, if dot is already seen (6.57+) => false;
			 */
			if( c == '+' || c == '-'){
				if( i == 0) sign = true;
				else if(i-1 >= 0 && s.charAt(i-1) == 'e' && i != len-1) continue;
				else if(sign || digit || dot) return false;
			}
			/*
				Digit is found 0-9
				1. if its first digit && i = 0 that means sign = true example : "123" sign = + so mark it seen
				2. mark digit seen in all cases even if i != 0
			 */
			if(Character.isDigit(c)){
				if( i == 0) sign = true;
				digit = true;
			}
			/*
				'.' is found
				1. if dot seen already ..2 => false, if 'e' is seen already 6e2.5 => false, if string is just "." => false
				2. else mark it seen
			
			 */
			if(c == '.'){
				if(dot || (exp || (!digit && i == len-1))) return false;
				else dot = true;
			}
			/*
				'e' is found
				1. if 'e'  before a digit e34 => false,  no digit after 'e' 64e => false, or 'e' already seen 6ee3 => false
				2. else mark it seen
			 */
			if(c == 'e') {
				if (!digit || i == len-1 || exp ) return false;
				else exp = true;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		String s = " 005047e+6";
		H0065_ValidNumbers cl = new H0065_ValidNumbers();
		System.out.println(cl.isNumber(s));
	}
}
