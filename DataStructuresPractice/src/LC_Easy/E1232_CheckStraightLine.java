package LC_Easy;

public class E1232_CheckStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {
        if(coordinates.length <= 1) return false;

        float prevSlope = 0;
        for(int i = 0; i<coordinates.length-1; i++){
            for(int j=0; j<coordinates[0].length-1; j++){
                float slope = Math.abs(((float)coordinates[i+1][j+1] - (float)coordinates[i][j+1])/ ((float)coordinates[i+1][j] - (float)coordinates[i][j]));
                //System.out.println(coordinates[i+1][j+1] + " " + coordinates[i][j+1] + " " + coordinates[i+1][j] + " " + coordinates[i][j] + " " + slope);
                if(prevSlope == 0) prevSlope = slope;
                else{if(prevSlope != slope) return false;}
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //int[][] coordinates = {{1,2},{2,3},{3,4},{4,5},{5,6},{6,7}};
        int[][] coordinates = {{-4,-3},{1,0},{3,-1},{0,-1},{-5,2}};
        E1232_CheckStraightLine cl = new E1232_CheckStraightLine();
        boolean res = cl.checkStraightLine(coordinates);
        System.out.println(res);
    }
}
