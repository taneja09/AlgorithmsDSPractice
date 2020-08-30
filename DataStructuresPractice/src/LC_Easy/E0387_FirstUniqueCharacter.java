package LC_Easy;

public class E0387_FirstUniqueCharacter {

    public int firstUniqChar(String s) {

        int[] array = new int[26];
        for(int i= 0; i< s.length(); i++){
            char val = s.charAt(i);
            array[val-'a']++;
        }

        for(int i= 0; i< s.length(); i++){
            char val = s.charAt(i);
            if(array[val-'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        E0387_FirstUniqueCharacter cl = new E0387_FirstUniqueCharacter();
        String s = "leetcode";
        int res = cl.firstUniqChar(s);
        System.out.println(res);

    }
}
