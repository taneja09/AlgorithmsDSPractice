package Interviews.RobinHood;

public class Q8_SubstringDivisibleBy3 {
	
	public int divisibleByThree(String s) {
		// You really only need these numbers mod 3.
		int total = 0;
		int[] residue = {0, 0, 0};
		int[] previous_residue = {0, 0, 0};
		for ( int i = 0 ; i<s.length(); i++)
		{
			int r = (s.charAt(i) - '0') % 3;
			for(int j = 0; j<3; j++) {
				residue[j] = previous_residue[(3 - r + j) % 3];
			}
			residue[r] = residue[r] + 1;
			total += residue[0];
			for( int j = 0; j<3; j++) {
				previous_residue[j] = residue[j];
			}
		}
		
		return total;
	}
	
	public int divisibleByThreeEasy(String s) {
		int count = 0;
		for(int i = 0; i<s.length(); i++){
			int num = 0;
			for(int j = i ; j<s.length(); j++){
				num = num*10 + s.charAt(j) -'0';
				System.out.println(num);
				if(num%3 == 0){
					count++;
				}
			}
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		String s = "303";
		Q8_SubstringDivisibleBy3 cl = new Q8_SubstringDivisibleBy3();
		//System.out.println(cl.divisibleByThree(s));
		System.out.println(cl.divisibleByThreeEasy(s));
	}
}
