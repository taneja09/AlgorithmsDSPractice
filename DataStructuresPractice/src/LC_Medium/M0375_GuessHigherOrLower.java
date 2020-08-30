package LC_Medium;
/*
Definition of dp[i][j]: minimum number of money to guarantee win for subproblem [i, j].
Target: dp[1][n]
Corner case: dp[i][i] = 0 (because the only element must be correct) and that is not 0 because we need to select a number from 1 to n

Equation: we can choose k (i<=k<=j) as our guess, and pay price k
After our guess, the problem is divided into two subproblems 1 to k-1 and k+1 to n
We will not pay for both sub problems but we need to pay for worst case
So dp[i][j] = min (i<=k<=j) { k + max(dp[i][k-1], dp[k+1][j]) } max because we pay worst case

 */
public class M0375_GuessHigherOrLower {
	public int getMoneyAmount(int n) {
		int[][] dp = new int[n + 2][n + 2];
		for (int len = 1; len < n; len++) {  //choose between 1 to n
			for (int from = 1, to = from + len; to <= n; from++, to++) {
				dp[from][to] = Integer.MAX_VALUE;
				for (int k = from; k <= to; k++)
					dp[from][to] = Math.min(dp[from][to], k + Math.max(dp[from][k - 1], dp[k + 1][to]));
			}
		}
		return dp[1][n];
	}
	public static void main(String[] args) {
		M0375_GuessHigherOrLower cl = new M0375_GuessHigherOrLower();
		System.out.println(cl.getMoneyAmount(1));
	}
}

/*
@nlackx nice demo, and what you are showing is a best strategy to guess the number with lowest cost.,So i guess what @grodrigues3 is saying is that the example as shown in the problem description is very confusing because it does not suppose to be in the description at all since it IS NOT A BEST STRATEGY WAY to guess the number at lowest cost.

The example description should be:

first introducebest strategyto guess:

for one number, like 1, best strategy is 0$
for two number, like 3,4, best strategy is 3$, which can be understood in this way: you have two way to guess: a) start by guess 4 is the target, (the worst case is) if wrong, you get charged $4, then immediately you know 3 is the target number, get get charged $0 by guessing that, and finally you get charged $4. b) similarly, if you start by 3, (the worst case is) if wrong, you get charged $3, then you immediately know that 4 is the target number, and get charged $0 for guessing this, and finally you get charged $3. In summary:
range ---------> best strategy cost
3, 4 ---------> $3
5, 6 ---------> $5
...
for three number, the best strategy is guess the middle number first, and (worst case is) if wrong, you get charged that middle number money, and then you immediately know what target number is by using "lower" or "higher" response, so in summary:
range ---------> best strategy cost
3, 4, 5 ---------> $4
7, 8, 9 ---------> $8
...
"for more numbers", it can simply be reduced them into smaller ranges, and here is why DP solution make more sense in solving this.
suppose the range is [start, end]
the strategy here is to iterate through all number possible and select it as the starting point, say for any k between start and end, the worst cost for this is: k+DP( start, k-1 ) + DP(k+1, end ), and the goal is minimize the cost, so you need the minimum one among all those k between start and end
 */