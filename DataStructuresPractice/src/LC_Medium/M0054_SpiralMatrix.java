package LC_Medium;
/**
 
 Time Complexity - O(m*n)
 Reason - traversal required  for all elements
 
 Space Complexity - O(max(m,n))
 Reason - O(m*n) size of the result list
*/

import java.util.ArrayList;
import java.util.List;

public class M0054_SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList();
        int Rlen = matrix.length;
        int Clen = matrix[0].length;

        int[] Rdir = {0,1,0,-1};  // Row direction
        int[] Cdir = {1,0,-1,0};  // Columns direction
        boolean[][] visited =  new boolean[Rlen][Clen];


        int r = 0, c = 0, di = 0;  //di helps with direction change
        for(int i = 0; i< Rlen * Clen; i++ ){
            res.add(matrix[r][c]);
            visited[r][c] = true;

            int cr = r + Rdir[di];  //row pointer
            int cc = c + Cdir[di];  // column pointer

            if(cr>=0 && cr < Rlen && cc>=0 && cc< Clen && !visited[cr][cc]){
                r = cr; c = cc;
            }

            else{
                di = (di + 1) % 4;  // if all candidates are covered for a direction then our next position is the one after performing a clockwise turn.
                r += Rdir[di];
                c += Cdir[di];
            }
        }
        return res;
    }

    /**
     [[1, 1, 1, 1, 1, 1, 1],
     [1, 2, 2, 2, 2, 2, 1],
     [1, 2, 3, 3, 3, 2, 1],
     [1, 2, 2, 2, 2, 2, 1],
     [1, 1, 1, 1, 1, 1, 1]]

     top = c from c1 to c2
     right = r from r1+1 to r2
     bottom = c from c2-1 to c1+1
     left = r from r2-1 to r1+1

     */

    public List<Integer> spiralOrderLayerByLayer(int[][] matrix) {
        List res = new ArrayList();
        if (matrix.length == 0) return res;

        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;

        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) res.add(matrix[r1][c]);  //first row ---->
            for (int r = r1+1; r <= r2; r++) res.add(matrix[r][c2]); //right column | down

            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) res.add(matrix[r2][c]); //bottom row  <----
                for (int r = r2 ; r > r1; r--) res.add(matrix[r][c1]); //left column  | up
            }
            //move to inner layer

            r1++;
            r2--;
            c1++;
            c2--;
        }
        return res;
    }

    public List<Integer> spiralOrderLayerEasy(int[][] matrix) {
        List res = new ArrayList();
        if (matrix == null || matrix.length == 0) return res;

        int n = matrix.length, m = matrix[0].length;

        int top = 0, down = n - 1;
        int left = 0, right = m - 1;

        while (res.size() < n * m) {
            //right traversal
            for (int j = left; j <= right && res.size() < n * m; j++)
                res.add(matrix[top][j]);

            //down traversal
            for (int i = top + 1; i <= down - 1 && res.size() < n * m; i++)
                res.add(matrix[i][right]);

            //left traversal
            for (int j = right; j >= left && res.size() < n * m; j--)
                res.add(matrix[down][j]);

            //top traversal
            for (int i = down - 1; i >= top + 1 && res.size() < n * m; i--)
                res.add(matrix[i][left]);

            left++; right--; top++; down--;  //towards next layer inside
        }

        return res;
    }

    public static void main(String[] args) {
        //int[][] matrix = {{1, 2, 3 },{ 4, 5, 6 },{ 7, 8, 9 }};
        int[][] matrix = {{1, 2, 3, 4 },{ 5, 6, 7, 8},{9,10,11,12}};
        M0054_SpiralMatrix  cl = new M0054_SpiralMatrix();
        //List<Integer> res = cl.spiralOrder(matrix);
        //List<Integer> res = cl.spiralOrderLayerByLayer(matrix);
        List<Integer> res = cl.spiralOrderLayerEasy(matrix);
        for(int i : res) System.out.print(i + " ");
    }
}


/**
 Iteration 1

 int cr = r + Rdir[di];   di = 0 and hence cr = 0,0,0
 int cc = c + Cdir[di];   di = 1 and hence cc = 0,1,2

 which will give first row of matrix[0,0], matrix [0,1] , matrix [0,2]
 Arraylist => 1,2,3

 <--Iteration 2 -->
 we are at r = 0 and c = 2 => matrix[0,2] last element of first row
 di = (di + 1) % 4; => di = 1%4 =>  di = 1

 r += Rdir[1];  r = 0+1  r =1
 c += Cdir[1];  c = 2+0  c =2

 matrix[1][2] = 6  which is second row and last column and here we shifted tpo right column

 int cr = r + Rdir[di];   di = 1 and hence cr = 1,2
 int cc = c + Cdir[di];   di = 0 and hence cc = 2,2

 matrix[1][2] = 6
 matrix[2][2] = 9

 <--Iteration3 -->
 di = 2

 r += Rdir[2];  r = 2+0  r =2
 c += Cdir[2];  c = 2-1  c =1

 int cr = r + Rdir[di];   di = 0 and hence cr = 2,2
 int cc = c + Cdir[di];   di = -1 and hence cc = 1,0

 matrix[2][1] = 8
 matrix[2][0] = 7

 <--Iteration4 -->
 di = 3

 int cr = r + Rdir[di];   di = -1 and hence cr = 1,0
 int cc = c + Cdir[di];   di = 0 and hence cc = 0,0

 matrix[1][0] = 4
 matrix[0][0] = 1 already visited

 <--Iteration5 -->
 di = 0 because 4%4 = remainder 0

 int cr = r + Rdir[di];   di = 0 and hence cr = 1
 int cc = c + Cdir[di];   di = 1 and hence cc = 1

 matrix[1][1] = 5

 */