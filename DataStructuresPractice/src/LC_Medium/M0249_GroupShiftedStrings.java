package LC_Medium;

import java.util.*;
/*
Consider acf and pru. Now notice the differnce between each characters.
acf = 0->2->3, and pru = 0->2->3. So these two form same group. So in this case, you can simply use integers ASCII difference to form key.
 */

public class M0249_GroupShiftedStrings {
	
	public List<List<String>> groupStrings(String[] strings) {
		HashMap<String, List<String>> hm = new HashMap<>();
		for(String s : strings){
			String key = getKey(s);
			List<String> list = hm.getOrDefault(key,new ArrayList<>());
			list.add(s);
			hm.put(key,list);
		}
		return new ArrayList<>(hm.values());
    }
    
    private String getKey(String s){
		char[] chars = s.toCharArray();
		String key = "";
		for(int i = 1; i < chars.length; i++) {
			int diff = chars[i] - chars[i-1];
			key += diff < 0 ? diff + 26 : diff;
			key += ",";
		}
		
		return key;
	}
	public static void main(String[] args) {
		String arr[] = {"abc","bcd","acef","xyz","az","ba","a","z"};
		M0249_GroupShiftedStrings cl = new M0249_GroupShiftedStrings();
		List<List<String>> res = cl.groupStrings(arr);
		for(List<String> x : res) {
			System.out.print(x+"\n");
			
		}
	}
}
