package LC_Easy;

public class E0299_BullsAndCows {
	public String getHint(String secret, String guess) {
		StringBuilder res = new StringBuilder();
		if(secret.length() == 0 || guess.length() == 0 || secret.length() != guess.length() ) return res.toString();
		int[] arr = new int[10];
		int cow = 0;
		int bull = 0;
		
		
		for(int i = 0; i<secret.length(); i++){
			if(secret.charAt(i) == guess.charAt(i)) bull++;
			else{
				if(arr[secret.charAt(i)-'0']++ <0) cow++;    //if prev part of guess has curr digit in secret then we found a pair that has same digit with different position
				if(arr[guess.charAt(i)-'0']-- > 0) cow++;	  //if prev part of secret has curr digit in guess then we found a pair that has same digit with different position
			}
		}

		return res.append(bull).append("A").append(cow).append("B").toString();
	}
	public static void main(String[] args) {
		String secret = "1807";
		String guess = "7810";
		E0299_BullsAndCows cl = new E0299_BullsAndCows();
		System.out.println(cl.getHint(secret,guess));
	}
}
