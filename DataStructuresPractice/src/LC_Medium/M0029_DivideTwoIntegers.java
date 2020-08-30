package LC_Medium;

/**
 *  Time Complexity - O(logn)
 *  Reason - Binary Loop
 *
 *  Space Complexity - O(logn)
 *  Reason - variables & recursive call for remainder
 */

public class M0029_DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        if(dividend == 0 ) return 0;
        if(divisor == 0) return Integer.MAX_VALUE;

        int sign = (dividend > 0) ^ (divisor>0) ? -1: 1;  //^ will tell if there is difference in the sign

        long lDividend = Math.abs((long)dividend);
        long lDivisor = Math.abs((long)divisor);

        long lResult = lDivide(lDividend, lDivisor);
        int ans;

        if(lResult > Integer.MAX_VALUE) ans = sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        else ans = sign == 1 ? (int)lResult : -(int)lResult;

        return ans;
    }

    public long lDivide( long lDividend, long lDivisor) {
        if (lDividend < lDivisor) return 0;

        /**  Find the largest multiple so that (divisor * multiple <= dividend),
         whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
         Think this as a binary search. */

        long sum = lDivisor;
        long multiple = 1;
        while ((sum + sum) <= lDividend) {  // checking if 6 which is 3 + 3 <= 10 instead of 3 < = 10 for  performance  which saves one extra iteration
            sum += sum; // for next iteration sum = 6
            multiple += multiple;  //incremented the count because we were able to subtract double val =  2 times 3 can be subtracted from 10
        }
        // in the second iteration the loop will not run because (12 <= 10) false  then we need to check for remainder after iterations are completed till max multiples
        //lDividend - sum will tell us the remainder part
        return multiple + lDivide(lDividend - sum, lDivisor);

    }

    public static void main(String[] args) {
        M0029_DivideTwoIntegers cl = new M0029_DivideTwoIntegers();
        int  dividend = -1;
        int divisor = 1;
        int result = cl.divide(dividend,divisor);
        System.out.println(result);

    }
}
