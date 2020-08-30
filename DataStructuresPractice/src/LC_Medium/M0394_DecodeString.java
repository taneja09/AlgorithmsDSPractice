package LC_Medium;
/**
 
 Time Complexity - O(n)
 Reason - Traversal of String
 
 Space Complexity - O(n * m)
 Reason - n = number of character * m count of repetition
*/
import java.util.Stack;

public class M0394_DecodeString {
    public String decodeString(String s) {
        if(s== null || s.length()==0) return "";
        Stack<Integer> count = new Stack<>();
        Stack<StringBuilder> result = new Stack<>();
        result.push(new StringBuilder());
        int multiplier = 0;

        for(int i = 0; i< s.length(); i++){
            char val = s.charAt(i);

            if(Character.isDigit(val))
                 multiplier = multiplier * 10 + Character.getNumericValue(val);// works for more than 1 digit of number like 10, 12 etc
            else if (val == '[')
            {
                count.push(multiplier);
                multiplier = 0;
                result.push(new StringBuilder());
            }
            else if (val == ']') {
                StringBuilder str2Multiply = result.pop();
                int times = count.pop();
                StringBuilder multipliedStr = new StringBuilder();
                for(int j = 0; j < times; j++)
                    multipliedStr.append(str2Multiply);
                result.push(result.pop().append(multipliedStr));
            }
               else
                    result.push(result.pop().append(val));
        }
        return result.pop().toString();
    }
    public static void main(String[] args) {
        String encode = "2[abc]3[cd]ef";
        M0394_DecodeString cl = new M0394_DecodeString();
        String decode = cl.decodeString(encode);
        System.out.println(decode);
    }
}
