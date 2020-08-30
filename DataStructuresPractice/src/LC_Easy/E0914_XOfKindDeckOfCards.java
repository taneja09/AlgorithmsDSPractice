package LC_Easy;

import java.util.HashMap;

public class E0914_XOfKindDeckOfCards {
	public boolean hasGroupsSizeX(int[] deck) {
		if(deck.length <=1) return false;
		HashMap<Integer,Integer> hm = new HashMap<>();
		
		for(int d : deck){
			hm.put(d,hm.getOrDefault(d,0)+1);
		}
		
		int hmSize = hm.size();
		if(deck.length % hmSize != 0) return false;
		
		int ref = -1;
		for(int key : hm.keySet()){
			if(ref == -1) ref = hm.get(key);
			else ref = gcd(ref, hm.get(key));
		}
		
		return ref >= 2;
	}
	
	public int gcd(int x, int y) {
        return x == 0 ? y : gcd(y%x, x);
    }
	public static void main(String[] args) {
		int[] arr = {1,1,2,2,2,2};
		E0914_XOfKindDeckOfCards cl = new E0914_XOfKindDeckOfCards();
		System.out.println(cl.hasGroupsSizeX(arr));
	}
}
