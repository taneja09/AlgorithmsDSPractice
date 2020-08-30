package LC_Easy;

/**
 
 Time Complexity - O(n * 2^n)
 Reason - n number of iteration and each successive number can have at most twice as many digits as the previous number
 [21 -> 1211] 2 will become 4 digits hence 2^n
 
 Space Complexity - O(2^n)
 Reason -  generaed nth term for a number n can have a length of at most 2n.
*/

public class E0038_CountAndSay {
	public String countAndSay(int n) {
		String s = "1";
		for(int i = 1; i < n; i++){
			s = countIdx(s);
		}
		return s;
	}
	
	public String countIdx(String s){
		StringBuilder sb = new StringBuilder();
		char c = s.charAt(0);
		int count = 1;
		for(int i = 1; i< s.length(); i++){
			if(s.charAt(i) == c){
				count++;
			}else{
				sb.append(count);
				sb.append(c);
				c = s.charAt(i);
				count = 1;
			}
		}
		
		return sb.append(count).append(c).toString();
	}
	
	public static void main(String[] args) {
		E0038_CountAndSay cl = new E0038_CountAndSay();
		System.out.println(cl.countAndSay(3));
	}
}
