package LC_Medium;
/**
 
 Time Complexity - O(n)
 Reason - One pass
 
 Space Complexity - O(n)
 Reason - stack space
*/
import java.util.Stack;

/**
 * The idea behind is to use the stack and populate number and computed values
 * we are just going to perform + operation at the end once we go through the whole expression
 *
 * for - operator, we save value as negative 1 - 3 --> while pushing 3 we push -3 so that we can just to + for expression 1 + (-3) = -2
 * for * and / we perform the operations then and there by popping element from stack and save them back
 */

public class M0227_BasicCalculatorII {

    public int calculate(String s) {
        if(s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();

        int length = s.length();
        int num = 0;
        char prevSign = '+';   // there is no operator at the start of expression and hence assuming it to + to push the first element to stack

        for(int i=0; i<length; i++) {
            char val = s.charAt(i);
            if (Character.isDigit(val)) {
                num = num * 10 + val - '0'; //creating the number
            }
            if ((!(Character.isDigit(val)) && ' ' != val ) || i == length-1){
                if(prevSign == '-')
                    stack.push(-num);

                if(prevSign == '+')
                    stack.push(num);

                if(prevSign == '*')
                    stack.push(stack.pop()*num);

                if(prevSign == '/')
                    stack.push(stack.pop()/num);

                prevSign = val;   //assigning the current sign to prevSign  for next iteration
                num = 0;       // making num 0 for next iteration
            }
        }
        int result = 0;
        for(int i : stack)
            result += i;

        return result;
    }


    public static void main(String[] args) {

        M0227_BasicCalculatorII cl = new M0227_BasicCalculatorII();
        String expression = "3+2*2";
        int res = cl.calculate(expression);
        System.out.print(res);
    }
}
