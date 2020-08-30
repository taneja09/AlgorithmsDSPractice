package Algorithms.Graphs;

//public class UnionFind {
//    int[] parents;
//    int[] ranks;
//    public int setCount = 0;
//
//    public UnionFind(char[][] board) {
//        int rows = board.length, cols = board[0].length;
//        int n = rows * cols + 1;
//        parents = new int[n];  //root array
//        ranks = new int[n];    // weight array
//        setCount = rows * cols; // initializing that all elements are disjoined that means all are islands
//        for (int i = 0; i < n; i++) //initialize parents with their own index
//            parents[i] = i;
//
//    }
//    public int find(int x) {
//        // Index 0,1,2,3,4,5,6,7,8,9
//        // value   [0,1,9,4,9,5,6,8,8,9]
//        //find(3) => 3 != 4 then find(4) =>  4 !=9  then find(9) => 9 == 9 return 9 as parent of 3
//        if (x != parents[x])
//            x = find(parents[x]);
//        return parents[x];
//    }
//    //from above indexes data parents array
//    /*
//    0   1   5   6   8    9
//                    |    | \
//                    7    4  2
//                         |
//                         3
//     */
//    // union of (4,7)
//    //union of 2 indexes
//    public void union(int x, int y) {
//        int px = find(x);   //parents of both indexes px = 9 and py = 8
//        int py = find(y);
//        if (px != py) {   //if parents are different
//            if (ranks[px] > ranks[py]) {  //check weights of both indexes if x has longer tree then make x's root as y's parent and hence 8 will come under 9
//                parents[py] = px;  // parents[8] = 9
//                ranks[px]++;  //
//            } else {
//                parents[px] = py;
//                ranks[py]++;
//            }
//            setCount--; // elements are union and count is reduced
//        }
//    }
//}


public class UnionFind {
    public int[] parents;
    public int[] ranks;
    
    public UnionFind(int n) {
        parents = new int[n];  //parent relation
        ranks = new int[n];   //takes care of size
        for(int i = 0; i < n; i++) parents[i] = i;
    }
    
    public int find(int x) {
        if(x != parents[x]) x = find(parents[x]);
        return parents[x];
    }
    
    public boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if(px == py) return false;
        if(ranks[px] > ranks[py]) {   // if py has less size put it under larger branch px
            parents[py] = px;   //make px of larger length as parent of py
            ranks[px] ++;    //size of px increase
        } else{
            parents[px] = py;
            ranks[py] ++;
        }
        return true;
    }
}