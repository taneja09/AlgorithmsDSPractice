package LC_Medium;

/**
 
 Time Complexity - O(4^n)
 Reason - in worst case we have 4 letters for each digits(9) and you have n digits so.. 4x4x4..n times which is 4^n
 
 Space Complexity -O(4^n)
 Reason - space complexity, final output will take O(4^n)
*/

import java.util.*;

public class M0017_LetterCombinationPhoneNumber {
	
	/**********===========  Easy Version       =====================**/
	public List<String> letterCombinationsEasy(String digits) {
		LinkedList<String> ans = new LinkedList<String>();
		if (digits.isEmpty()) return ans;
		String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		ans.add("");
		while (ans.peek().length() != digits.length()) {
			String remove = ans.remove();
			String map = mapping[digits.charAt(remove.length()) - '0'];
			for (char c : map.toCharArray()) {
				ans.addLast(remove + c);
			}
			
		}
		return ans;
	}
		/**********===========  Done by myself       =====================**/
		public List<String> letterCombinations(String digits) {
			List<String> res = new ArrayList<>();
			if(digits.length() == 0) return res;
			Queue<StringBuilder> q = new LinkedList<>();
			String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
			
			String fstring = mapping[digits.charAt(0)-'0'];
			
			for(char c : fstring.toCharArray())
				q.offer(new StringBuilder().append(c));
			
			for(int i = 1; i<digits.length(); i++) {
				String current = mapping[digits.charAt(i)-'0'];
				while (q.peek().length() != i+1 ) {
					StringBuilder val = q.poll();
					for(char c : current.toCharArray())
						q.offer(new StringBuilder(val).append(c));
				}
				
			}
			while(!q.isEmpty()){
				res.add(q.poll().toString());
			}
			return res;
		}
		
		public static void main(String[] args) {
			String val = "234";
			M0017_LetterCombinationPhoneNumber cl = new M0017_LetterCombinationPhoneNumber();
			List<String> result = cl.letterCombinations(val);
//			for(String s : result)
//				System.out.println(s);
			
			List<String> result1 = cl.letterCombinationsEasy(val);
			for(String s : result1)
				System.out.println(s);
		}
	}
