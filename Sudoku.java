public class Sudoku {
    //practicing using double int Array[row[column];
    //practicing using nested for-loops
    //a practice in Recursion;
    private static final int GRID_SIZE = 9; // declares the size of grid.

    public static void main(String[] args) {
        //using an int double Array; first[] = rows, second[] = columns.
        // both rows and columns are called by their name and index.e.g. row[1], column[2].
        // zeroes (0) represents the blank spaces.
        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };
        printBoard(board);

        if(solveBoard(board)){
        System.out.println("Solved Successfully");
        }else{
            System.out.println("Unsolvable board!");
        }
        printBoard(board);
    }

    //create 3 helper methods to determine is certain numberInRow already exist in that square.
    private static boolean isNumberInRow(int[][] board, int number, int row){
        for (int i = 0; i < GRID_SIZE; i += 1) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }
    private static boolean isNumberInColumn(int[][]board, int number, int column){
        for(int i=0; i<GRID_SIZE; i+=1){
            if(board[i][column] == number){
                return true;
            }
        }
        return false;
    }
    private static boolean isNumberInBox(int[][] board, int number, int row, int column){
        //create local variables for the rows and columns;
        // these local variables ("int localRow", "int localColumn") represent the Indices in the Array/Grid.
        // the local variables allow for us to transverse the local Row and local columns in a nested for-loop.
        int localBoxRow = row - row %3;
        int localBoxColumn = column - column %3;

        for(int i = localBoxRow; i<localBoxRow +3; i+=1){
            for(int j = localBoxColumn; j<localBoxColumn +3; j+=1){
                //now we test if the number in the row and column == the number passed into the Method
                if(board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }
    //this one Method checks to see if ALL Methods are Valid; meaning they all return FALSE.
    //if ALL return FALSE, this means the selected number to be place can be placed in that position on the Board
    private static boolean isValidPlacement(int[][]board, int number, int row, int column){
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }
    //using Recursion to solve test and solve the Sudoku Board
    //the nested for-loop goes thru for the rows and columns to determine is the number placed is equal to "0" which is a blank spot
    //if the both the row and column ==0; then using  another for-loop to cycle thru valid numbers (1-9) based on the size of the Grid (Grid Size:9)
    //call "isValidPlacement()" Method to determine if the number DOES NOT exist in the Grid, IF "numberToTry" does NOT exist, the number is VALID and placed.
    //a Recursive call is made to solveBoard() to determine if Puzzle is solved, If solved return "true" is NOT then board[row][column]==0; and the placement
    private static boolean solveBoard(int [][] board){
        for(int row=0; row <GRID_SIZE; row +=1){
            for(int column=0; column <GRID_SIZE; column+=1){
                if(board[row][column] ==0){
                    for(int numberToTry =1; numberToTry <= GRID_SIZE; numberToTry +=1){
                        if(isValidPlacement(board, numberToTry, row, column)){//numberToTry is being tested again the numbers at [row][column]
                            board[row][column] = numberToTry;

                            if(solveBoard(board)){//recursion method called
                                return true;
                            }else{
                                board[row][column]=0; //if recursion fails to solve, the board is reset to "0" (blank spaces)
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private static void printBoard(int[][]board){
        for(int row=0; row<GRID_SIZE; row+=1){
            if(row %3 ==0 && row!=0){
                System.out.println("----------");
            }
            for(int column=0; column<GRID_SIZE; column+=1){
                if(column %3==0 && column !=0){
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }
}
