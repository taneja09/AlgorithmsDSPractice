package LC_Medium;

import java.util.HashMap;
/**
 Time Complexity -  O(n)
 Reason - Linear scan
 
 Space Complexity - O(n)
 Reason -  HashMap
 */
public class M0166_FractionDecimal {

    public String fractionToDecimal(int numerator, int denominator) {
            StringBuilder res = new StringBuilder();

            if (numerator == 0) {
                return "0";
            }

            // "+" or "-" positive or negative sign
            res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");

            //convert to long for division
            long num = Math.abs((long)numerator);
            long den = Math.abs((long)denominator);

            // integral part
            res.append(num / den);
            num %= den;  //num = num % den;  2/3 = 2
            if (num == 0) {
                return res.toString();
            }

            // fractional part
            res.append(".");
            HashMap<Long, Integer> map = new HashMap<Long, Integer>();
            map.put(num, res.length());  // for 2/3 case res = 0.  length = 2  and num = 2 => hashmap initialization (2,2)

            while (num != 0) {
                num *= 10;   // 2*10 = 20
                res.append(num / den);  //  20/3 = 6 => res = 0.6
                num %= den;   // 20%3 = 2   then num = 2

                if (map.containsKey(num)) {  //search for the index in res for repetative digit and insert brackets around
                    int index = map.get(num);   //index = 2
                    res.insert(index, "(");  //res = 0.(6
                    res.append(")");   //res = 0.(6)
                    break;
                }

                else
                map.put(num, res.length());

            }

            return res.toString();

    }



    public static void main(String[] args) {
        M0166_FractionDecimal c = new  M0166_FractionDecimal();
        String res = c.fractionToDecimal(4,333);
         System.out.println(res);


    }
}
