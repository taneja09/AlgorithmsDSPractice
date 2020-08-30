package LC_Medium;

import java.util.*;

public class M0833_FindAndReplaceInString {
	public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
		Map<Integer, String[]> tm = new TreeMap<>();
		for(int i=0; i<indexes.length; i++) tm.put(indexes[i], new String[]{sources[i], targets[i]});
		
		StringBuilder sb = new StringBuilder();
		int start = 0;
		for(int idx : tm.keySet()) {
			if(S.indexOf(tm.get(idx)[0],idx) == idx) {
				sb.append(S.substring(start, idx)).append(tm.get(idx)[1]);
				start = idx + tm.get(idx)[0].length();
			}
		}
		if(start != S.length()) sb.append(S.substring(start));
		return sb.toString();
		
		
	}
	public static void main(String[] args) {
		String s = "abcd";
		int[] indexes = {0, 2};
		String[] sources  = {"a", "cd"};
		String[] targets = {"eee", "ffff"};
		M0833_FindAndReplaceInString cl = new M0833_FindAndReplaceInString();
		System.out.println(cl.findReplaceString(s,indexes,sources,targets));
	}
}
