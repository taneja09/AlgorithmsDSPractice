package LC_Easy;

import java.util.*;

public class E0819_MostCommonWords {
	public String mostCommonWord(String paragraph, String[] banned) {
		String normalizedStr = paragraph.replaceAll("[^a-zA-Z0-9 ]", " ").toLowerCase();
		//when the letter is not from a-z a-z 0-9 .. replace it with space
		
		// 2). split the string into words
		String[] words = normalizedStr.split("\\s+");
		Set<String> bannedWords = new HashSet();
		for (String word : banned)
			bannedWords.add(word);
		
		Map<String, Integer> wordCount = new HashMap();
		
		for (String word : words) {
			if (!bannedWords.contains(word))
				wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
		}
		return Collections.max(wordCount.entrySet(), Map.Entry.comparingByValue()).getKey();
	}
	
	public String mostCommonWord1(String paragraph, String[] banned) {
		String[] words = paragraph.toLowerCase().split("[ !?',;.]+");
		HashMap<String, Integer> map = new HashMap<>();
		for(String word : words) map.put(word, map.getOrDefault(word, 0) + 1);
		for(String word : banned) if(map.containsKey(word)) map.remove(word);
		String res = null;
		for(String word : map.keySet())
			if(res == null || map.get(word) > map.get(res))
				res = word;
		return res;
	}
	public static void main(String[] args) {
		String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
		String[] banned = {"hit"};
		E0819_MostCommonWords cl = new E0819_MostCommonWords();
		System.out.println(cl.mostCommonWord1(paragraph,banned));
	}
}
