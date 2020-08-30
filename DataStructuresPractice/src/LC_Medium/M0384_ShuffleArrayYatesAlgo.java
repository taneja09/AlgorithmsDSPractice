package LC_Medium;
/**
 
 Time Complexity - O(n)
 Reason - Traversal and swaping
 
 Space Complexity - O(n)
 Reason - etra arrays space
*/
import java.util.Arrays;
import java.util.Random;

public class M0384_ShuffleArrayYatesAlgo {
	private int[] array;
	private int[] original;
	private Random rand = new Random();
	
	public M0384_ShuffleArrayYatesAlgo(int[] nums) {
		array = nums;
		original = nums.clone();
	}
	
	private int randRange(int min, int max) {
		return rand.nextInt(max - min) + min;
	}
	
	private void swapAt(int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public int[] reset() {
		array = original;
		original = original.clone();
		return array;
		
	}
	
	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		for (int i = 0; i < array.length; i++) {
			swapAt(i, randRange(i, array.length));  // take two indexes one i and other random and swap them within array and return array
		}
		
		return array;
	}
	
	public static void main(String[] args) {
		M0384_ShuffleArrayYatesAlgo cl = new M0384_ShuffleArrayYatesAlgo(new int[]{1,2,3});
		System.out.println(Arrays.toString(cl.shuffle()));
		System.out.println(Arrays.toString(cl.reset()));
		System.out.println(Arrays.toString(cl.shuffle()));
	}
}
