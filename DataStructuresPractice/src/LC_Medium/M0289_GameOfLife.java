package LC_Medium;

public class M0289_GameOfLife {
    
    /**************** Extra Space Solution TC 0(m*n) SC = O(m*n) *************************/
    public void gameOfLife(int[][] board) {
        int length = board.length;
        int width = board[0].length;
        
        int[][] result = new int[length][width];
        int[][] directions = {{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {-1, -1}, {0, -1}, {1, -1}};
        
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                int count = 0;
                
                for (int[] dir : directions) {
                    int row = i + dir[0];
                    int col = j + dir[1];
                    if (row >= 0 && col <= width - 1 && row <= length - 1 && col >= 0 && board[row][col] == 1) count++;
                }
                
                if (board[i][j] == 0) {
                    result[i][j] = count == 3 ? 1 : 0;
                } else {
                    if (count < 2) result[i][j] = 0;
                    else if (count == 2 || count == 3) result[i][j] = 1;
                    else result[i][j] = 0;
                }
                
            }
        }
        
        for (int i = 0; i < length; i++) {
            System.out.println("====");
            for (int j = 0; j < width; j++) {
                System.out.print(result[i][j]);
            }
        }
        
        // for first approach copy result board to main board
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = result[i][j];
            }
        }
        
    }
    
    /**************** In Place easy Solution TC 0(m*n) SC = O(1) *************************/
    public void gameOfLifeEasy(int[][] board){
        if(board ==null||board.length ==0)return;
        int[][] dir = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};
        
        for(int i = 0; i<board.length;i++) {
            for (int j = 0; j < board[0].length; j++) {
                int ones = 0;
                for (int[] d : dir) {
                    int x = i + d[0];
                    int y = j + d[1];
                    if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) continue;
                    if (board[x][y] == 1 || board[x][y] == -2) ones++;
                }
                if (board[i][j] == 0 && ones == 3) board[i][j] = 2;
                if (board[i][j] == 1 && (ones < 2 || ones > 3)) board[i][j] = -2;
            }
        }
        for(int i = 0; i<board.length;i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 2) board[i][j] = 1;
                if (board[i][j] == -2) board[i][j] = 0;
            }
        }
        
    }
    
    /**************** In Place bit Complex Solution TC 0(m*n) SC = O(1) *************************/
    public void gameOfLifeInPlace(int[][] board) {
        int length = board.length;
        int width = board[0].length;
        
        int[][] directions = {{-1,1},{0,1},{1,1},{-1,0},{1,0},{-1,-1},{0,-1},{1,-1}};
        
        for(int i = 0; i< length; i++) {
            for (int j = 0; j < width; j++) {
                int count = 0;
                
                for(int[] dir : directions){
                    int row = i+ dir[0];
                    int col = j + dir[1];
                    //Cell changes  1 to 0 => -1 and 0 to 1 => 2
                    //Counting the cells with original value as 1 (contains both 1 and -1)
                    if( row>=0 && col<= width-1 && row<= length-1 && col>=0 && (board[row][col] == 1 || board[row][col] == -1)) count++;
                }
                
                if(board[i][j] == 0 || board[i][j] == 2){  // for the rule of dead cells ( with original value as 0)
                    board[i][j] = count == 3 ? 2 : 0;
                }else{ // for rules of active cells ( with original value as 1)
                    if (count < 2) board[i][j] = -1;
                    else if (count == 2 || count == 3) board[i][j] = 1;
                    else board[i][j] = -1;
                }
                
            }
        }
        
        // change the value of -1 and 2 to 0 and 1 respectively.
        for(int i = 0; i< length; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j]  = board[i][j] > 0 ? 1 : 0;
            }
        }
        
    }
    
    
    
    public static void main(String[] args) {
        M0289_GameOfLife cl = new M0289_GameOfLife();
        int[][]  matrix = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        //cl.gameOfLife(matrix);
        cl.gameOfLifeEasy(matrix);
    }
}
