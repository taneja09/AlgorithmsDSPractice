package LC_Medium;

public class M0918_MaxSubarrayCircular {
	
	public int maxSubarraySumCircular(int[] A) {
		int total = 0, maxSum = -30000, curMax = 0, minSum = 30000, curMin = 0;
		for (int a : A) {
		            curMax = Math.max(curMax + a, a);
		            maxSum = Math.max(maxSum, curMax);
		            curMin = Math.min(curMin + a, a);
		            minSum = Math.min(minSum, curMin);
		            total += a;
		        }
		return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
	}
	
	public static void main(String[] args) {
		M0918_MaxSubarrayCircular cl = new M0918_MaxSubarrayCircular();
		int[] array = {1,3,-2,3,4,5};
		int res = cl.maxSubarraySumCircular(array);
		System.out.println(res);
	}
}
