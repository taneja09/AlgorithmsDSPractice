package LC_Easy;


import java.util.HashSet;

public class E0771_JewelStones {

    public int numJewelsInStones(String J, String S) {
        HashSet<Character> hs = new HashSet<>();
        int totJewel = 0;

        for(int i = 0; i< J.length(); i++)
            hs.add(J.charAt(i));

        for(int i = 0; i< S.length(); i++)
            if(hs.contains(S.charAt(i))) totJewel++;

        return totJewel;
    }

    public static void main(String[] args) {
        E0771_JewelStones cl = new E0771_JewelStones();
        String J = "aA";
        String S = "aAAbbbb";
        int count = cl.numJewelsInStones(J,S);
        System.out.println(count);
    }
}
