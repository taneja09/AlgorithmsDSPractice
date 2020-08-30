package LC_Easy;
/**
 
 Time Complexity - O(n)
 Reason - traversal required from 1 ....n
 
 Space Complexity - O(n)
 Reason - arraylist space
*/
import java.util.ArrayList;
import java.util.List;

public class E0412_FizzBuzz {
	public List<String> fizzBuzz(int n) {
		List<String> al = new ArrayList();
		for(int i = 1; i<=n ;i++){
			if(i%15 == 0) al.add("FizzBuzz");
			else if(i%5 == 0) al.add("Buzz");
			else if(i%3 == 0) al.add("Fizz");
			else{
				al.add(Integer.toString(i));
			}
		}
		
		return al;
	}
	public static void main(String[] args) {
		E0412_FizzBuzz cl = new E0412_FizzBuzz();
		List<String> res = cl.fizzBuzz(15);
		for(String s: res)
			System.out.println(s);
	}
}
