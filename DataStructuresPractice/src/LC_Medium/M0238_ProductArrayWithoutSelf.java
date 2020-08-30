package LC_Medium;

import java.util.Arrays;
/**
 Time Complexity -  O(n)
 Reason - single pass
 
 Space Complexity - O(n)
 Reason -   Extra space for 2 arrays
 */
public class M0238_ProductArrayWithoutSelf {

    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] L = new int[length];
        int[] R = new int[length];
        int[] answer = new int[length];
        //Example [1,2,3,4]
        L[0] = 1;

        for(int i= 1; i< length; i++)
            L[i] = nums[i-1] * L[i-1];
        //L = [1,1,2,6]

        R[length -1] = 1;

        //R = [24,12,4,1]
        for(int i = length-2; i>=0; i--)
            R[i] = nums[i+1] * R[i+1];

        for(int i = 0; i< length; i++)
            answer[i] = L[i] * R[i];

        return answer;
    }


    public static void main(String[] args) {

        M0238_ProductArrayWithoutSelf cl = new M0238_ProductArrayWithoutSelf();
        int[] array = {1,2,3,4};
        int[] res = cl.productExceptSelf(array);
        System.out.print(Arrays.toString(res));
    }
}
