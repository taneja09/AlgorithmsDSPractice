package LC_Medium;
/**
 
 Time Complexity - O(n^2) due to arraylist generationa nd randomizing
 Reason - Hash and arralist operations
 
 Space Complexity - O(n)
 Reason - map space & list space
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class M0384_ShuffleArray {
	private int[] array;
	private int[] original;
	private Random rand = new Random();
	
	public M0384_ShuffleArray(int[] nums) {
		array = nums;
		original = nums.clone();
	}
	
	
	/*
	This is to make sure the changes in "array" does not change the "original" as when we write array = original,
	we make both array and original to an array of integers present in the same memory location.
	
	hence original = original.clone(); will make original point to new array integers which is a clone of values pointed by original previously.
	Think of this as a deep copy.
	 */
	public int[] reset() {
		array = original;
		original = original.clone();
		return array;
		
	}
	
	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		List<Integer> aux = getArrayCopy();
		for (int i = 0; i < array.length; i++) {
			int removeIdx = rand.nextInt(aux.size()); // get a random pointer
			array[i] = aux.get(removeIdx);  // put value of random pointer from list and put in array for all values
			aux.remove(removeIdx);
			//newly created array will have shuffled values due to random assigning and
			// we are removing the index from arrayList as it should not duplicate value in array again.
		}
		
		return array;
	}
	private List<Integer> getArrayCopy() {
		List<Integer> asList = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			asList.add(array[i]);
		}
		return asList;
	}
	public static void main(String[] args) {
		M0384_ShuffleArray cl = new M0384_ShuffleArray(new int[]{1,2,3});
		System.out.println(Arrays.toString(cl.shuffle()));
		System.out.println(Arrays.toString(cl.reset()));
		System.out.println(Arrays.toString(cl.shuffle()));
	}
}
