package LC_Easy;

/**
 * Consider trust as a graph, all pairs are directed edge.
 * The point with in-degree - out-degree = N - 1 become the judge.
 */

public class E0997_JudgeOFTown {

    public int findJudge(int N, int[][] trust) {
        int[] count = new int[N+1];
        for (int[] t: trust) {
            count[t[0]]--;
            count[t[1]]++;
        }

        for (int i = 1; i <= N; ++i) {
            if (count[i] == N - 1) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        E0997_JudgeOFTown cl = new E0997_JudgeOFTown();

        int numPeople = 3;
        int[][]  trust = {{1,3},{2,3}};
        int res = cl.findJudge(numPeople,trust);
        System.out.print(res);
    }
}
