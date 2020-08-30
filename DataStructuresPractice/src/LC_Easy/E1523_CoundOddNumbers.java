package LC_Easy;

public class E1523_CoundOddNumbers {
	public int countOdds(int low, int high) {
        if(low%2 == 0 && high%2 == 0) return high-low/2;
        else if(low%2 == 0 && high%2 == 0) return high-low/2 +1;
        else return high-low/2 +1;
    }
	public static void main(String[] args) {
		int low = 3;
		int high = 7;
		E1523_CoundOddNumbers cl = new E1523_CoundOddNumbers();
		cl.countOdds(low,high);
	}
	
}
