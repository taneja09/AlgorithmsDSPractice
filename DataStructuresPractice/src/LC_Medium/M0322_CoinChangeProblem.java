package LC_Medium;
/*
https://leetcode.com/problems/coin-change/discuss/77368/*Java*-Both-iterative-and-recursive-solutions-with-explanations


 */
public class M0322_CoinChangeProblem {

    public int coinChangeRecursive(int[] coins, int amount) {
        if(amount <1) return 0;
        return helper(coins, amount, new int[amount]);
    }

    public int helper(int[] coins, int rem, int[] count){
        if(rem < 0) return -1;
        if(rem == 0) return 0;
        if(count[rem-1] != 0) return count[rem-1];  //check last value if exist then return for calculation
        int min = Integer.MAX_VALUE;
        for(int coin : coins){
            int res = helper(coins,rem-coin,count);
            if(res>=0 && res <min) { //check if the returned value is valid 0 or count[res-1] and less than MAX value
                min = res + 1;   // +1 is denoting current coin we used + res result because that accounts for remaining number of coins used
            }
        }
        count[rem-1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem-1];
    }

    //Bottom up approach start from 0 and keep incrementing the min value till dp[amount]
    public int coinChangeIterative(int[] coins, int amount){

        if(amount<1) return 0;
        int[] dp = new int[amount+1];
        int sum = 1;

        while(sum<=amount) {
            int min = -1;
            for(int coin : coins) {
                if(sum >= coin && dp[sum-coin]!=-1) { // coin value can be used as its less than sum &&  dp[sum - coin ] is available
                    // coins [1,2,3] sum 4 and till now we have dp [1,2,..] for the last coin 3 , 4> 3 && dp[4-3] = 1 hence dp[1] + 1
                    int temp = dp[sum - coin] + 1;
                    min = min<0 ? temp : (temp < min ? temp : min);  //do it for all coins till we find min for dp[i]
                }
            }
            dp[sum] = min; //adding the last val to dp array
            sum++; //check for next sum value
        }

        return dp[amount];
    }

    public int coinChangeEasy(int[] coins, int amount){
        int dp[]=new int[amount+1];
        dp[0]=0;

        for(int i=1;i<=amount;i++)
            dp[i]=amount+1;  // initialize with all amount+1 values which is max because we need to find min we need a reference

        for(int i=1;i<=amount;i++) {
            for (int j = 0; j < coins.length; j++) {  // for a given amount check all coins possibility and pick the min
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        if(dp[dp.length-1]==amount+1) // for a given amount min value wasn't found
            return -1;

        return dp[dp.length-1];  //found min value
    }

    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 11;
        M0322_CoinChangeProblem cl = new M0322_CoinChangeProblem();
       // int res = cl.coinChange(coins,amount);
        // int res = cl.coinChangeIterative(coins,amount);
         int res = cl.coinChangeEasy(coins,amount);
        System.out.println(res);


    }
}

/**
 Example
 {1,2,5} and amount = 11

 helper iteration 1 - coins , rem = 11, count array with all 0
 rem = 11
 index=  [0,1,2,3,4,5,6,7,8,9,10]
 count = [0,0,0,0,0,0,0,0,0,0,0]

 count[rem-1] != 0 then return count[rem-1] where count[10] = 0  so cant return

 take coin one by one
 coin = 1
 res = (coins, rem -coin, count) => (coins, 10, count) will continue till rem-coin = 0 because the count[0] = 0 is returned



 */
