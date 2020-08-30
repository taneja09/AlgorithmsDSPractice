package LC_Easy;

public class E0243_ShortestWordDistance {
	/*********** Brute force TC = O(N^2) SC = O(1) *******************/
	public int shortestDistance(String[] words, String word1, String word2) {
		int len = words.length;
		for(int i = 0; i< words.length; i++){
			if(words[i] == word1){
				for(int j = 0; j< words.length; j++){
					if(words[j].equals(word2))
						len = Math.min(len, Math.abs(i-j));
				}
			}
		}
		
		return len;
	}
	
	/*********** Two Pointers TC = O(N) SC = O(1) *******************/
	public int shortestDistanceTwoPointer(String[] words, String word1, String word2) {
	    int i1 = -1, i2 = -1;
	    int minDistance = words.length;
	    for (int i = 0; i < words.length; i++) {
	        if (words[i].equals(word1)) {
	            i1 = i;
	        } else if (words[i].equals(word2)) {
	            i2 = i;
	        }
	
	        if (i1 != -1 && i2 != -1) {
	            minDistance = Math.min(minDistance, Math.abs(i1 - i2));
	        }
	    }
	    return minDistance;
	}
	public static void main(String[] args) {
		String[] dictionary = {"practice", "makes", "perfect", "coding", "makes"};
		String word1 = "makes";
		String word2 = "coding";
		E0243_ShortestWordDistance cl = new E0243_ShortestWordDistance();
		System.out.println(cl.shortestDistanceTwoPointer(dictionary,word1,word2));
	}
}
