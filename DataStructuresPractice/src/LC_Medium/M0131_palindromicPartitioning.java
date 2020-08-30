package LC_Medium;

/**
 Time Complexity - O(n*(2^n))
 Reason - O(n) is for palindrome and 2^n is for backtracking
 
 Space Complexity - O(2^n)
 Reason -  All possible combinations inserted into the list and result
 */

/*
Check notes for more details on Backtracking
 */

import java.util.*;

public class M0131_palindromicPartitioning {
	public List<List<String>> partition(String s) {
		List<List<String>> result = new ArrayList();
		List<String> list = new ArrayList();
		partitionHelper(result,list,s,0);
		return result;
	}
	private void partitionHelper(List<List<String>> result,List<String> list,String s, int index){
		if(index == s.length())
			result.add(new ArrayList(list));
		
		else{
			for(int i = index; i< s.length(); i++){
				if(isPalindrome(s,index,i)){
					list.add(s.substring(index,i+1));  //choose  'a'
					partitionHelper(result,list,s,i+1);  //explore the remaining string 'ab' because now index start from 1
					list.remove(list.size()-1);  //unchoose and try new combination
				}
			}
		}
	}
	
	public boolean isPalindrome(String s, int low, int high){
		while(low <= high){
			if(s.charAt(low) != s.charAt(high))
				return false;
			else {
				low++;
				high--;
			}
		}
		
		return true;
	}
	public static void main(String[] args) {
		String s = "aab";
		M0131_palindromicPartitioning cl = new M0131_palindromicPartitioning();
		System.out.println(cl.partition(s));
	}
}
