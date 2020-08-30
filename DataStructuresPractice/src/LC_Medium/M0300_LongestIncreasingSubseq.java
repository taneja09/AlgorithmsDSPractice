package LC_Medium;
// https://leetcode.com/problems/longest-increasing-subsequence/discuss/723397/DP-or-Very-Small-and-Intuitive-or-With-Explanation-or-Java
// Patience Sort = https://www.cs.princeton.edu/courses/archive/spring13/cos423/lectures/LongestIncreasingSubsequence.pdf
// https://leetcode.com/problems/longest-increasing-subsequence/discuss/719887/3-ms-Java-Implementation-Based-On-Patience-Sort-Very-Intuitive

import java.util.Arrays;
import java.util.Stack;

/**
 * When loop over the input array, every number n can serve two purposes: (1) it can give a new max length record,
 * if n is greater than current highest record;
 *
 * (2) or it can lower the bar of a certain record so we can have better
 * chance in the future to get a higher/longer record.
 *
 * The values in the tails array are like state machine s0, s1, s2 and so on.
 * Thanks to its ascending order we can use binary search to find out whether n can give a new record or lower the bar of someone in logn time.
 * So the problem is all about establishing bar and lowering bar.
 */

public class M0300_LongestIncreasingSubseq {
    /*
    We want to explore all indexes and calculate the longest increasing subsequence upto that index only.
    We can use a dp[] array to store the value so that we can compare and get the max value.
    
    Example : [4,10,4,3,8,9]
    
    at index 0 => longest increasing sub seq has length = 1;
    at index 1 =>  length = 2
    at index 2 => length = 1
    at index 3 => length = 1
    at index 4 => length = 2
    at index 5 => length = 3
    
    return the max length as answer.
     */
    /***************** TC = O(n^2) && SC = O(n) *********************/
    public int lengthOfLISSquare(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int globalMax = 1;
        Arrays.fill(dp,1);
        for(int i = 0; i <nums.length; i++) {
            int currMax  = 1;
            for(int j = 0; j<= i; j++){
                if(nums[j] < nums[i]){
                    currMax =  Math.max(dp[j] + dp[i], currMax);
                }
            }
            dp[i] = currMax;
            globalMax = Math.max(dp[i], globalMax);
        }
        return globalMax;
    }
    
    /**************** Patience Sort TC = O(nlogn) SC = O(1) *****************/
    public int lengthOfLISPS(int[] nums) {
        Stack<Integer>[] piles = new Stack[nums.length];
        int len = 0;
        for (int i = 0; i < piles.length; i++)
            piles[i] = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            piles[binarySearch(piles, nums[i])].push(nums[i]);
            System.out.println(Arrays.toString(piles));
        }
         for(int i=0;i<piles.length;i++){
             if(!piles[i].isEmpty())
                 len++;
         }
         return len;
    }
    
    
    int binarySearch(Stack<Integer>[] piles,int val){
        int start=0;
        int end=piles.length-1;
        while(start<end){
            int mid=start+(end-start)/2;
            if(piles[mid].isEmpty() || piles[mid].peek()>=val)
                end=mid;
            else if(piles[mid].peek()<val)
                start=mid+1;
        }
        return end;
    }
    
    
    
    
    
    
    
    
    
    /**************************************************************************/
    
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {    // process element one by one
            int i = 0, j = size;  //j keeps track of the size of tails array
            while (i != j) {      //does binary search till the end of array
                int m = (i + j) / 2;   //mid point of array
                if (tails[m] < x)   // if current element > element at mid position in tails array that means the next element has to go after m pointer
                    i = m + 1;
                else
                    j = m;  //remains back the end of position in array
            }
            tails[i] = x;    // inserting element at m+1 if the processing element is greater
            if (i == size) ++size;  //if i get incremented to end of array , increase the size of tails array
        }
        //System.out.println(Arrays.toString(tails));
        return size ;
    }
    
    
    public static void main(String[] args) {
        M0300_LongestIncreasingSubseq cl = new M0300_LongestIncreasingSubseq();
        //int[]  array = {10,9,2,5,3,7,101,18,120};
        int[]  array = {4,10,4,3,8,9};
        int len = cl.lengthOfLISPS(array);
        System.out.println(len);
    }
}
