package LC_Easy;

public class E0121_BestTimeToBuySellStock {
	
	/************* Brute Force TC = O(n^2)  SC = O(1)****************/
	public int maxProfit(int[] prices) {
		if(prices == null || prices.length == 0) return 0;
		int max = 0;
		for(int i = 0; i< prices.length; i++){
			for(int j = i; j< prices.length; j++){
				int profit = prices[j]-prices[i];
				if(max < profit){
					max = profit;
				}
			}
		}
		
		return max;
	}
	
	/************* One Pass TC = O(n)  SC = O(1)****************/
	public int maxProfitOnePass(int[] prices) {
		if (prices == null || prices.length == 0) return 0;
		int minVal = Integer.MAX_VALUE;
		int maxProfit = 0;
		for (int i = 0; i < prices.length; i++) {
			if(prices[i] < minVal) minVal = prices[i];
			else if(prices[i] - minVal > maxProfit) maxProfit = prices[i] - minVal;
		}
		
		return maxProfit;
	}
	public static void main(String[] args) {
		int [] price = {1,0,6};
		E0121_BestTimeToBuySellStock cl = new E0121_BestTimeToBuySellStock();
		System.out.println(cl.maxProfit(price));
		System.out.println(cl.maxProfitOnePass(price));
	}
}
