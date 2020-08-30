package LC_Easy;
/**
 
 Time Complexity - O(n) where x number of digits and base 10 for log
 Reason - We need to consider each digit in array
 
 Space Complexity - O(1)
 Reason - No extra space
*/

import java.util.Arrays;

public class E0066_PlusOne {
	public int[] plusOne(int[] digits) {
		if(digits.length == 0) return new int[]{1};
		int c = 0;
		for(int i = digits.length-1; i>=0 ; i--){
			int sum =0;
			if(i == digits.length-1) sum = 1 + digits[i] + c;
			else sum =  digits[i] + c;
			
			if(sum > 9){
				digits[i] = 0;
				c = 1;
			}else{
				digits[i] = sum;
				c = 0;
			}
		}
		
		if(c >0){
			int[] res = new int[digits.length+1];
			res[0] = c;
			int j = 1;
			for(int k : digits){
				res[j] = k;
				j++;
			}
			
			return res;
		}
		
		return digits;
   }
   
	public int[] plusOneEasy(int[] digits) {
       int n=digits.length;
       for(int i=n-1;i>=0;i--)
       {
           if(digits[i]<9)
           {
               digits[i]++;
               return digits;
           }
           digits[i]=0;
       }
       int[] newans=new int[n+1];
       newans[0]=1;
       return newans;
   }
	public static void main(String[] args) {
		int[] array = {1,2,9,9};
		E0066_PlusOne cl = new E0066_PlusOne();
		//System.out.println(Arrays.toString(cl.plusOne(array)));
		System.out.println(Arrays.toString(cl.plusOneEasy(array)));
	}
}
