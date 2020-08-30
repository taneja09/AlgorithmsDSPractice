package LC_Easy;
/*
 a = 1 & b = 3
 
 a = 0001
 b = 0011
 
 carry = 1&1  = 0001;
 a^=b = 0010
 b = carry <<1 => 0010;

next iteration 0010 + 0010 => 0100 (4) ans = 4
then loop again and carry will be added to second left which is correct
keep on doing this unless carry = 0

 */
public class E0371_SumOfTwoNumbers {
	public int getSum(int a, int b) {
       while (b != 0) {
		   int carry = a & b;
           a ^= b;
           b = carry <<1;
       }
       return a;
   }
	public static void main(String[] args) {
		E0371_SumOfTwoNumbers cl = new E0371_SumOfTwoNumbers();
		System.out.println(cl.getSum(2,3));
	}
}
