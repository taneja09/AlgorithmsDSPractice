package LC_Medium;
/**
 
 Time Complexity - O(1) - get random, delete and insert
 Reason - Hash and arralist operations
 
 Space Complexity - O(n)
 Reason - map space & list space
*/
import java.util.*;

public class M0380_InsertDeleteGetRandom {
	
	ArrayList<Integer> nums;
	HashMap<Integer, Integer> locs;
	java.util.Random rand = new java.util.Random();
	
	/** Initialize your data structure here. */
	public M0380_InsertDeleteGetRandom() {
		nums = new ArrayList<>();
		locs = new HashMap<>();
	}
	
	/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	public boolean insert(int val) {
		boolean contain = locs.containsKey(val);
		if ( contain ) return false;
		locs.put( val, nums.size());
		nums.add(val);
		return true;
	}
	
	/** Removes a value from the set. Returns true if the set contained the specified element. */
	public boolean remove(int val) {
		boolean contain = locs.containsKey(val);
		if ( ! contain ) return false;
		int loc = locs.get(val);
		
		if (loc < nums.size() - 1 ) { // not the last one than swap the last one with this val
	            int lastone = nums.get(nums.size() - 1 );
	            nums.set(loc , lastone );
	            locs.put(lastone, loc);
	        }
		
		locs.remove(val);
		nums.remove(nums.size() - 1);
		return true;
		
	}
	
	/** Get a random element from the set. */
	public int getRandom() {
		return nums.get(rand.nextInt(nums.size()));
	}
	
	public static void main(String[] args) {
		
		M0380_InsertDeleteGetRandom cl = new M0380_InsertDeleteGetRandom();
		System.out.println(cl.insert(1));
		System.out.println(cl.remove(2));
		System.out.println(cl.insert(2));
		System.out.println(cl.getRandom());
		System.out.println(cl.remove(1));
		System.out.println(cl.insert(2));
		System.out.println(cl.getRandom());
	}
}
