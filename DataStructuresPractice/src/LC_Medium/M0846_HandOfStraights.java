package LC_Medium;

import java.util.Queue;
import java.util.*;

public class M0846_HandOfStraights {
	public boolean isNStraightHand(int[] hand, int W) {
		TreeMap<Integer, Integer> count = new TreeMap();
		for(int card : hand){
			count.put(card,count.getOrDefault(card,0)+1);
		}
		
		Queue<Integer> start = new LinkedList<>();
		int last_checked = -1, opened = 0;
		
		for(int card : count.keySet()){
			if ((opened > 0 && card > last_checked + 1)|| opened > count.get(card)) return false;
			start.add(count.get(card) - opened);
			last_checked = card; opened = count.get(card);
			if (start.size() == W) opened -= start.remove();
			
		}
		return opened == 0;
	}
	public static void main(String[] args) {
		int[] hand = {1,2,3,6,2,3,4,7,8};
		int k = 3;
		M0846_HandOfStraights cl = new M0846_HandOfStraights();
		System.out.println(cl.isNStraightHand(hand,k));
	}
}
