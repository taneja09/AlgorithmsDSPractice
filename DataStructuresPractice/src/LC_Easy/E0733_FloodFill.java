package LC_Easy;

import java.util.Arrays;

public class E0733_FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        int dir[][] = {{0,1},{0,-1},{1,0},{-1,0}};
        int curr = image[sr][sc];
        if(curr == newColor) return image;
        floodFillHelper(image,sr,sc,newColor,dir, curr);
        return image;
    }

    public void floodFillHelper(int[][] image, int sr, int sc, int newColor,int[][] dir,int curr){
        if(sr <0 || sr > image.length-1 || sc <0 || sc >image[0].length-1 || image[sr][sc] != curr) return;
        image[sr][sc] = newColor;
        for (int[] d : dir) {
            int row = sr + d[0];
            int col = sc + d[1];
            floodFillHelper(image, row, col, newColor, dir, curr);
        }
    }

    public static void main(String[] args) {
        //int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
        int[][] image = {{0,0,0},{0,1,1}};
        int sr = 1, sc = 1, newColor = 1;
        E0733_FloodFill cl = new E0733_FloodFill();
        cl.floodFill(image,sr,sc,newColor);
        for(int[] val : image) System.out.println(Arrays.toString(val));

    }
}
