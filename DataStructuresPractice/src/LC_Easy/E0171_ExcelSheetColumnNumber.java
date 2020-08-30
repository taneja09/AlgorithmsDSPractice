package LC_Easy;

import java.util.HashMap;

public class E0171_ExcelSheetColumnNumber {
	/***************** Easy Method TC - O(n) SC - O(1) ***************/
	public int titleToNumberEasy(String s) {
		if (s == null || s.length() == 0) return 0;
		int sum = 0;
		
		for(int i =0; i< s.length(); i++){
			sum = sum*26 + (s.charAt(i)-'A'+1);
		}
		
		return sum;
	}
	/***************** TC - O(n) SC - O(n) ***************/
	public int titleToNumber(String s) {
		if(s == null || s.length() ==0) return 0;
		int sum = 1;
		HashMap<Character,Integer> hm = new HashMap<>();
		for(char ch = 'A'; ch <= 'Z'; ch++){
			hm.put(ch,sum);
			sum++;
		}
		sum = 0;
		for(int i = 0; i<s.length(); i++){
			char ch = s.charAt(i);
			sum= sum*26 + hm.get(ch);
		}
		
		return sum;
	}
	public static void main(String[] args) {
		String s= "Z";
		E0171_ExcelSheetColumnNumber cl = new E0171_ExcelSheetColumnNumber();
		System.out.println(cl.titleToNumber(s));
		System.out.println(cl.titleToNumberEasy(s));
	}
}
