package LC_Easy;

public class E0367_ValidPerfectSquar {
    public boolean isPerfectSquare(int num) {
        long start = 1, end = num;
        while (start <= end) {
            long mid = (start + end) / 2;
            if(mid * mid == num) return true;
            if( mid * mid < num) start = mid +1;
            else end = mid-1;
        }
        return false;
    }

    public static void main(String[] args) {
        int num = 808201;
        E0367_ValidPerfectSquar cl = new E0367_ValidPerfectSquar();
        boolean res = cl.isPerfectSquare(num);
        System.out.println(res);

    }
}
