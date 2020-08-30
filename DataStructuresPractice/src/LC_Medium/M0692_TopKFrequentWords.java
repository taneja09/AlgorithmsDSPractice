package LC_Medium;

import java.util.*;

public class M0692_TopKFrequentWords {
	public List<String> topKFrequent(String[] words, int k) {
		List<String> result = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();
		
		for (String s : words)
			map.put(s, map.getOrDefault(s, 0) + 1);
		
		PriorityQueue<String> pq = new PriorityQueue<String>((a,b)-> map.get(a).equals(map.get(b)) ?
									b.compareTo(a): map.get(a)-map.get(b));
		
		for (String s : map.keySet()) {
			pq.add(s);
			if (pq.size() > k) pq.remove();
		}
		
		while (!pq.isEmpty())
			result.add(pq.remove());
		
		Collections.reverse(result);
		return result;
	}
	
	public static void main(String[] args) {
		String[] arr = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
		M0692_TopKFrequentWords cl = new M0692_TopKFrequentWords();
		List<String>  res = cl.topKFrequent(arr,2);
		System.out.print(res);
		
	}
}
