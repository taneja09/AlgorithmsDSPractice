package LC_Easy;

public class E1009_ComplimentNumber {

    public int findComplement(int num) {

        String org = Integer.toBinaryString(num);
        StringBuilder s  = new StringBuilder();
        for(int i = 0; i< org.length(); i++){
            char val = org.charAt(i);
            val = val == '0' ? '1' :'0';
            s.append(val);
        }
        return Integer.parseInt(s.toString(),2);
    }
    public static void main(String[] args) {
        E1009_ComplimentNumber cl = new E1009_ComplimentNumber();
        int result = cl.findComplement(5);
        System.out.println(result);
    }
}
