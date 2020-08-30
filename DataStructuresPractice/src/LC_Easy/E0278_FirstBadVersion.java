package LC_Easy;
/**
 Time Complexity -  O(n)
 Reason - single pass
 
 Space Complexity - O(1)
 Reason -
 */

public class E0278_FirstBadVersion {
    public int firstBadVersion(int n) {
        int first = 1;
        int last = n;

        while(first < last){
            int mid =  first + (last-first) / 2;
            if (!isBadVersion(mid)) first = mid+1;
            else last = mid;
        }
        return first;
    }

    public boolean isBadVersion(int x){ return true;} //defined in parent class

    public static void main(String[] args) {
        E0278_FirstBadVersion cl = new E0278_FirstBadVersion();
        int version = 5;
        int firstBad = cl.firstBadVersion(version);
        System.out.println(firstBad);
    }

}
