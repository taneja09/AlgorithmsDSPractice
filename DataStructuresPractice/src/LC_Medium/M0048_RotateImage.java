package LC_Medium;

/**
 
 Time Complexity - O(R*C) = O(n^2)
 Reason - accessing all elements
 
 Space Complexity - O(1)
 Reason - constant space
*/

/*=========================== Template ==============================================*/
/**
 * clockwise rotate
 * first reverse up to down, then swap the symmetry
 * 1 2 3     7 8 9     7 4 1
 * 4 5 6  => 4 5 6  => 8 5 2
 * 7 8 9     1 2 3     9 6 3
 */

/**
 * anticlockwise rotate
 * first reverse left to right, then swap the symmetry
 * 1 2 3     3 2 1     3 6 9
 * 4 5 6  => 6 5 4  => 2 5 8
 * 7 8 9     9 8 7     1 4 7
 */

import java.util.Arrays;

public class M0048_RotateImage {

    /**Clockwise rotation*/
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int row = matrix.length;
        int col = matrix[0].length;

        /**first reverse up to down*/
        for (int first = 0, last = row - 1; first < last; first++, last--) {
            int[] tmp = matrix[first];   //array reference tmp points to first row  123
            matrix[first] = matrix[last];  // assign last row to first row
            matrix[last] = tmp; //assign first row to last
            //this will happen for all n rows it will be upside down until first == last or first > last because of first++, last --
        }

        /**then swap the symmetry*/
        symmetrySwap(matrix,row,col);
    }

    /**Anti-Clockwise rotation*/
    public void antiRotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int row = matrix.length;
        int col = matrix[0].length;

        /**first reverse left right columns*/
        for(int first=0, last=col-1; first<last; first++,last--) {
            for(int i=0; i<matrix.length; i++) {
                int tmp = matrix[i][first];
                matrix[i][first] = matrix[i][last];
                matrix[i][last] = tmp;
            }
        }
        /**then swap the symmetry*/
        symmetrySwap(matrix,row,col);
    }

    public void symmetrySwap(int[][] matrix, int row, int col){

        for(int i = 0; i < row; i++){
            for(int j = i+1; j < col; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3 },{ 4,5, 6},{7,8,9}};
        M0048_RotateImage  cl = new M0048_RotateImage();
        cl.rotate(matrix);
        //cl.antiRotate(matrix);
        for(int[] arr : matrix){
            System.out.println(Arrays.toString(arr));
        }
    }
}
