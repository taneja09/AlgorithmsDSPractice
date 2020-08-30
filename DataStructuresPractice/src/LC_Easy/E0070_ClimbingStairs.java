package LC_Easy;

public class E0070_ClimbingStairs {
	
	/********************** Recursion Solution - TLE 2^n ****************** */
	public int climbStairs(int n) {
       return getClimbingSteps(0,n);
    }
    
    private int getClimbingSteps(int count, int tot){
		if(count > tot) return 0;
		if(count == tot) return 1;
		else return getClimbingSteps(count+1,tot) + getClimbingSteps(count+2,tot);
	}
	
	/********************** top Down approach ****************** */
	public int climbStairsTB(int n) {
		int[] dp = new int[n+1];
       return climbStairsTB(n,dp);
    }
    
    private int climbStairsTB(int count, int[] dp){
		if(count == 0) return 1;
		if(count < 0) return 0;
		if(dp[count]>0) return dp[count];
		else dp[count] =  climbStairsTB(count-1,dp) + climbStairsTB(count-2,dp);
		return dp[count];
	}
	
	/********************** Bottom Up approach ****************** */
	public int climbStairsBU(int n) {
		if (n <= 1) return n;
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		return dp[n];
	}
	
	public static void main(String[] args) {
		E0070_ClimbingStairs cl = new E0070_ClimbingStairs();
		System.out.println(cl.climbStairs(0));
		System.out.println(cl.climbStairsTB(44));
		System.out.println(cl.climbStairsBU(44));
	}
}
