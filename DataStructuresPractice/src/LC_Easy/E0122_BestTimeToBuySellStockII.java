package LC_Easy;

/*
Option 1: Buy stock at valley[i] then sell at peak[i] makes profit A (peak[i] - valley[i]).
Then buy stock at valley [j] and sell at peak[j] makes profit B (peak[j] - valley[j]).
So the total profit of this trade option is A + B.

Option 2: Skip the intermediate trades, i.e,, we buy stock at valley[i] then sell at peak[j].
In this case, the total profit will be C (peak[j]-valley[i]).

Based on the graph shown below, A + B > C (if not, peak[i] and valley[j] won't exist).
So in order to maximize the profit, we can buy stock at valleys and then sell stock at peaks.
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/discuss/208241/Explanation-for-the-dummy-like-me.

 */

/*
          profit: maximum total profit.
          buy: the day when buy stock.
          sell: the day when sell stock.
          days: maximum trade days.
*/


public class E0122_BestTimeToBuySellStockII {
	
	/************* One Pass TC = O(n)  SC = O(1)****************/
	public int MaxProfit(int[] prices) {
		int profit = 0, buy = 0, sell = 0, days = prices.length;
		while(buy < days && sell < days) {
			
			while(buy < days - 1 && prices[buy + 1] < prices[buy])
				buy++; // find the valley of prices
			
			sell = buy; //set selling starting from buy
			
			while(sell < days - 1 && prices[sell + 1] > prices[sell])
				sell++; // find the valley of prices
			
			profit += prices[sell] - prices[buy];
			
			buy = sell + 1; //start buying from next day
		}
		
		return profit;
	}
	
	
	/********** Another Solution   TC = O(n) SC = O(1)********************************************/
	
	public int maxProfit(int[] prices) {
		int profit = 0;
		
		for (int i = 1; i < prices.length; i++)
			profit += Math.max(0, prices[i] - prices[i - 1]);
			//keep on calculating all the intervals 1-0, 2-1 , 3-2  etc  it will ultimately will give max profit
			//in case price[i] - price[i-1] is negative we took 0 so that we dont go negative while summing profit
		
		return profit;
	}
	
	public static void main(String[] args) {
		int [] price = {7,1,5,3,1,6};
		E0122_BestTimeToBuySellStockII cl = new E0122_BestTimeToBuySellStockII();
		System.out.println(cl.maxProfit(price));
	}
}
