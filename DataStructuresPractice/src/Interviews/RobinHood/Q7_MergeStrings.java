package Interviews.RobinHood;

public class Q7_MergeStrings {
	private String mergeStrings(String s, String t){
		if(s == null || t == null || s.length() == 0 || t.length() == 0) return new String();
		if(s == null ^ t == null) return s == null ? t : s;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < s.length() || i < t.length() ; i++){
			if(i < s.length()) sb.append(s.charAt(i));
			if(i < t.length()) sb.append(t.charAt(i));
		}
		
		return sb.toString();
	}
	public static void main(String[] args) {
		String s = "aaaaaaa";
		String t = "bbb";
		Q7_MergeStrings cl = new Q7_MergeStrings();
		System.out.println(cl.mergeStrings(s,t));
	}
}
