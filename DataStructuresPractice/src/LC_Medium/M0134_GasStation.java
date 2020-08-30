package LC_Medium;
/*
The reason why I think this works:
1, if sum of gas is more than sum of cost, then there must be a solution. And the question guaranteed that the solution is unique(The first one I found is the right one).
2, The tank should never be negative, so restart whenever there is a negative number.
 */

/**
 Time Complexity -  O(N)
 Reason - Linear scan
 
 Space Complexity - O(1)
 Reason -  No extra space usage
 */
public class M0134_GasStation {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		if(gas.length != cost.length) return -1;
		if(gas == null || cost == null) return -1;
		
		int sumGas = 0;
		int sumCost = 0;
		int start = 0;
		int tank = 0;
		
		for(int i = 0; i< gas.length; i++ ){
			sumGas = gas[i];
			sumCost = cost[i];
			tank += gas[i] - cost[i];
			if(tank < 0 ){
				start = i+1;
				tank = 0;
			}
		}
		if(sumCost > sumGas) return -1;
		else return start;
	}
	
	
	public static void main(String[] args) {
		int[] gas = {5,1,2,3,4};
		int[] cost = {4,4,1,5,1};
		M0134_GasStation cl = new M0134_GasStation();
		System.out.println(cl.canCompleteCircuit(gas,cost));
	}
}
