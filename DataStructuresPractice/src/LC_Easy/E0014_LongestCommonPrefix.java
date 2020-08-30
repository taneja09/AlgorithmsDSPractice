package LC_Easy;


public class E0014_LongestCommonPrefix {
	
	/**
	 *  Time Complexity - 0(n) where n length of array
	 *  Reason - accessing all elements of array with all characters of strings
	 *
	 *  Space Complexity - 0(1)
	 *  Reason - Constant space
	 */
	public String longestCommonPrefix(String[] strs) {
		if(strs.length == 0) return "";
		String min = strs[0];
		for(int i =0 ; i< strs.length; i++){
			if(min.length() > strs[i].length()) min = strs[i];
		}
		
		int x = 0;
		
			while(x < min.length()){
				for(String s: strs){
					if(min.equals(s)) continue;
					else if(s.charAt(x) != min.charAt(x)) return s.substring(0,x);
			}
				x++;
		}
		return min.substring(0,x);
	}
	/**============================== Horizontal Scanning =====================================**/
	/**
		 *  Time Complexity - 0(S)  where S is the sum of all characters in all strings.
		 *  Reason - accessing all elements of array with all characters of strings
		 *
		 *  Space Complexity - 0(1)
		 *  Reason - Constant space
	 */
	
	/**
	 * Compare character one by one between 2 strings till end and get the result
	 * S1,S2,S3,S4,S5 --> First compare S1 and S2 and get biggest common
	 * then compare that common with S3 and get max prefix
	 * then compare that common with S4 and THEN S5 to get max common prefix this is horizontal scanning
	 */
	
	private String longestCommonPrefixHS(String[] strs){
		if (strs.length == 0) return "";
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++){
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty()) return "";
			}
		}
		return prefix;
	}
	
	/**============================== Vertical Scanning =====================================**/
	/**
		 *  Time Complexity - 0(S) where S is the sum of all characters in all strings - worst case
	     *	else it will be minLen at which prefix exist
		 *  Reason -
		 *
		 *  Space Complexity - 0(1)
		 *  Reason - Constant space
	 */
	
	/**
	 * Compare 1 caharcter one by one for all strings and if there is no much return the current result
	 */
	
	private String longestCommonPrefixVS(String[] strs){
		if (strs == null || strs.length == 0) return "";
		    for (int i = 0; i < strs[0].length() ; i++){
		        char c = strs[0].charAt(i);
		        for (int j = 1; j < strs.length; j ++) {
		            if (i == strs[j].length() || strs[j].charAt(i) != c)
		                return strs[0].substring(0, i);
		        }
		    }
		    return strs[0];
	}
	
	
	public static void main(String[] args) {
		String[] str = {"flower","flow","flight"};
		E0014_LongestCommonPrefix cl = new E0014_LongestCommonPrefix();
		System.out.println(cl.longestCommonPrefix(str));
		System.out.println(cl.longestCommonPrefixHS(str));
		System.out.println(cl.longestCommonPrefixVS(str));
	}
}
