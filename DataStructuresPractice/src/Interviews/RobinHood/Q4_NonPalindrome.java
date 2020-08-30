package Interviews.RobinHood;

public class Q4_NonPalindrome {
	public String nonPalindromicSubString(String s){
		if(s.length() == 0) return "";
		String pre = getPrefixPalindrome(s);
		if(pre.length() > 1){
			return nonPalindromicSubString(s.substring(pre.length()));
		}else
			return s;
	}
	
	private String getPrefixPalindrome(String str){
		String temp = str + '?';
		str = reverse(str);
		temp += str;
		int n = temp.length();
		
		int []lps = new int[n];
		for(int i = 1; i < n; i++){
			int len = lps[i - 1];
			while (len > 0 && temp.charAt(len) != temp.charAt(i)){
				len = lps[len - 1];
			}
			if (temp.charAt(i) == temp.charAt(len)) len++;
			lps[i] = len;
		}
		return temp.substring(0, lps[n - 1]);
	}
	
	private String reverse(String str){
		char[] a = str.toCharArray();
		int l, r = a.length - 1;
		
		for(l = 0; l < r; l++, r--) {
			char temp = a[l];
			a[l] = a[r];
			a[r] = temp;
		}
		return String.valueOf(a);
	}
	public static void main(String[] args) {
		String s = "abbab";
		Q4_NonPalindrome cl = new Q4_NonPalindrome();
		System.out.println(cl.nonPalindromicSubString(s));
	}
}
