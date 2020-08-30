package LC_Easy;

public class E0383_RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] array = new int[126];
        for(int i = 0; i < magazine.length(); i++) {
            char val = magazine.charAt(i);
            array[val]++;
        }

        for(int i = 0; i < ransomNote.length(); i++) {
            char val = ransomNote.charAt(i);
            array[val]--;
            if(array[val] < 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        E0383_RansomNote cl = new E0383_RansomNote();
        String ransomNote = "a";
        String magazine = "aab";
        boolean res = cl.canConstruct(ransomNote,magazine);
        System.out.println(res);
    }
}
