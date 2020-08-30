package LC_Medium;
/**
 *  Time Complexity - O(n)
 *  Reason - checking in array with 2 pointer - only one time traversal
 *
 *  Space Complexity - O(1)
 *  Reason - No extra space required
 */

/**=============================================================================**/
/**
 * We can start with max width and keep on decreasing the width in search of larger height because then only
 * area will be maximum i.e either height or width is greater
 *
 * As we start with max width with 2 pointer , we move inside to check if there is larger height than the current we have
 * whichever height is shorter > check for its next pillar length and move forward
 */
public class M0011_ContainerWithMostWater {
	public int maxArea(int[] height) {
    	int start = 0;
    	int end = height.length-1;
    	int maxWater = 0;
    	
    	while(start < end ) {
			maxWater = Math.max(maxWater, Math.min(height[start], height[end]) * (end - start));
			if (height[start] == height[end]) {start++; end--;}
			else if (height[start] < height[end]) start++;
			else end--;
		}
    	return maxWater;
    }
	public static void main(String[] args) {
		int[] water = {1, 8, 6, 2, 5, 4, 8, 3, 7};
		M0011_ContainerWithMostWater cl = new M0011_ContainerWithMostWater();
		System.out.println(cl.maxArea(water));
	}
	
}
