package Interviews.Google;

import java.util.*;

public class Q1 {
	public int fn(String text1, String text2, int k ){
		int[] common   =  getLongestCommonSequence(text1, text2);
		
		 String a = text1.substring(common[0]+1);
		 String b = text2.substring(common[1]+1);
		 int commonLen = common[2];
		 return commonLen >= k ? 0 : getmaxCount(a, b, k - commonLen);
			
		
	}
	
	private int getmaxCount(String x , String y, int len){
		List<Integer> list = new ArrayList();
		int count = 0;
		for (int i = 0; i< x.length(); i++){
			for(int j = 0; j< y.length(); j++){
				int ascii = Math.abs(x.charAt(i) - y.charAt(j));
				list.add(ascii);
			}
		}
		
		Collections.sort(list);
		for(int i = 0 ; i< len ; i++ ){
			count += list.get(i);
		}
		return count;
	}

	public int[] getLongestCommonSequence(String a, String b){
		int m = a.length();
				int n = b.length();
				int maxlen = 0;
				int[][] table = new int[m+1][n+1];
				int x = -1, y = -1;
		
				for(int i = 0; i <= m;i++){
					for(int j = 0;j <= n;j++){
		
						if(i == 0 || j == 0){
							table[i][j] = 0;
						}
						else if(a.charAt(i-1) == b.charAt(j-1)){
							table[i][j] = table[i-1][j-1] + 1;
						}
						else{
							table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
						}
					}
				}
				maxlen = table[m][n];
		
				// Following code is used to print LCS
				int index = table[m][n];
				// Create a character array to store the lcs string
				char lcs[] = new char[index+1];
				// Start from the right-most-bottom-most corner and one by one store characters in lcs[]
				int i = m, j = n;
		
				while (i > 0 && j > 0){
					// If current character in X[] and Y are same, then current character is part of LCS
					if (a.charAt(i-1) == b.charAt(j-1)){
						lcs[index-1] = a.charAt(i-1); // Put current character in result
						x = Math.max(x, i-1);
						y = Math.max(y, j-1);
						i--; j--; index--;     // reduce values of i, j and index
					}
					// If not same, then find the larger of two and go in the direction of larger value
					else if (table[i-1][j] > table[i][j-1])
						i--;
					else
						j--;
				}
		
				// Print the lcs
				System.out.println("LCS is: "+new String(lcs));
				return new int[] {x,y,maxlen};
	}
	public static void main(String[] args) {
		Q1  obj = new Q1();
		System.out.println(obj.fn("abcba","acyx",3));
	}
}
