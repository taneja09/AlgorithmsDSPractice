package LC_Hard;
/*
Idea : Notice that in order to find the amount of water that was trapped in a particular bar,
we would take the the height of the bar and subtract it from the level of the water.

Level of water around the bar would be Min height of its either side of walls
|
|		|
|	|	|
|___|___|
Total water which can be trapped in this container is  min (maxheight at left , max height at right) = min (4,3) = 3
Now at middle bar how much water can be collected = tot water - height of bar itself
3-2 = 1 unit of water

Trapped water at height[i] is equal to min(leftMax, rightMax) - height[i]

 */
public class H0042_TrappingRainWater {
	/*************** 2 pointer TC = 0(n) SC = O(n) ******************/
	public int trap2Pointer(int[] height) {
		if (height.length == 0) return 0;
		int left = 0; int right = height.length-1;
		int tempMaxLeft = height[left], tempMaxRight = height[right];
		int totalWater = 0;
		while (left <= right) {
			tempMaxLeft = Math.max(tempMaxLeft, height[left]);
			tempMaxRight = Math.max(tempMaxRight, height[right]);
			if (tempMaxLeft <= tempMaxRight) {
				totalWater += tempMaxLeft - height[left++];
			}else{
				totalWater += tempMaxRight - height[right--];
			}
		}
		return totalWater;
	}
	
	
	/*************** DP TC = 0(n) SC = O(n) ******************/
	public int trapDP(int[] height) {
		if (height.length == 0) return 0;
		int[] leftMax = new int[height.length];
		int[] rightMax = new int[height.length];
		//leftmax
		int currentLeftMax = height[0];
		for (int i = 0; i < height.length; i++) {
			currentLeftMax = Math.max(currentLeftMax, height[i]);
			leftMax[i] = currentLeftMax;
		}
		//right max
		int currentRightMax = height[height.length - 1];
		for(int i = height.length-1; i>= 0; i--){
			currentRightMax = Math.max(height[i],currentRightMax);
			rightMax[i] = currentRightMax;
		}
		
		//total water
		int totWater = 0;
		for (int i = 0; i < height.length; i++) {
			totWater += Math.min(leftMax[i],rightMax[i]) - height[i];
		}
		return totWater;
	}
	
	
	/*************** Brute Force TC = 0(n^2) SC = O(1) ******************/
	public int trapBF(int[] height) {
		int res = 0;
		for (int i = 0; i < height.length; i++){
			int leftMax = 0, rightMax = 0;
			
			for (int k = i; k >= 0; k--){
				leftMax = Math.max(leftMax, height[k]);
			}
			for (int j = i; j < height.length; j++){
				rightMax = Math.max(rightMax, height[j]);
			}
			res += Math.min(leftMax, rightMax) - height[i];
		}
		
		return res;
	}
	public static void main(String[] args) {
		int[] tank = {0,1,0,2,1,0,1,3,2,1,2,1};
		H0042_TrappingRainWater cl = new H0042_TrappingRainWater();
		System.out.println(cl.trapDP(tank));
	}
}
