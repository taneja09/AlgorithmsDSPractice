package LC_Easy;
/**
 Time Complexity -  O(n)
 Reason - single pass
 
 Space Complexity - O(1)
 Reason -
 */
import java.util.Arrays;

public class E0344_ReverseString {
	public void reverseString(char[] s) {
     if(s.length < 1) return ;
     int start = 0;
     int end = s.length-1;
     
     while(start <= end){
         char ch = s[end];
         s[end--] = s[start];
         s[start++] = ch;
     }
     
 }
	public static void main(String[] args) {
		E0344_ReverseString cl = new E0344_ReverseString();
		char[] array = {'h','e','l','l','o'};
		cl.reverseString(array);
		System.out.println(Arrays.toString(array));
	}
}
