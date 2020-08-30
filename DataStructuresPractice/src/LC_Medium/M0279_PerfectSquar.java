package LC_Medium;
/**
 Time Complexity -  O(n^2)
 Reason - 2 loops
 
 Space Complexity - O(n)
 Reason - dp array
 */
public class M0279_PerfectSquar {

    public int numSquares(int n) {
        int[] DP = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            DP[i] = i;  //initialize result for each number as number itself
        }
        for(int i = 2; i<= n ; i++){
            for(int j = 1; j * j <= i ; j++) {
                int next = j * j;
                int remain = i - next;
                DP[i] = Math.min(DP[i], (DP[remain] + 1)); // additional 1 id for j which is already counted and DP[remain] is for left out part
            }
        }
        return DP[n];
    }
    
    public static void main(String[] args) {
        M0279_PerfectSquar cl = new M0279_PerfectSquar();
        int target = 13;
        int res = cl.numSquares(target);
        System.out.print(res);
    }
}

// https://www.youtube.com/watch?v=l6fy78KP78g
/*
The idea is to create a dp array and initialize the min val as sum of 1^2 for the number
3 = 1^2 + 1^2 + 1^2 =  3,
2 = 1^2 + 1^2  = 2

example
[0,1,2,3,4,5] min number of ways
[0,1 2 3 4 5]  for every i loop through j from 1 to j*j <= i

for 2
dp[i] = 2
next  j * j = 1
remain = 2 - 1 = 1;
dp[remain] = 1;
Math.min (already provided val dp[i] = 2 , newly constructed value (dp[remain] + 1));
Math.min(2, 2) = 2;

 */