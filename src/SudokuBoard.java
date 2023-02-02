import java.util.Arrays;
/**
 * Represents a Sudoku board. All rows and columns are numbered
 * from 1 to 9. Boxes are numbered from the top left across
 * and then down starting at 1. A 0 in the board represents a
 * blank space.
 * @author Jake A
 */
public class SudokuBoard
{
    private int[][] board;
    /**
     * Constructor
     **/
    public SudokuBoard(int[][] arr) {
        board = arr;
    }
    /**
     * Mutator method for the user to make a guess
     * @param guess Value
     * @param row Location
     * @param column Location
     **/
    public void makeGuess(int guess, int row, int column) {
        board[row-1][column-1] = guess;
    }
    /**
     * Checks rows for duplicate entries
     * @param row Number of row accessed (0-8)
     * @return boolean Valid or not
     **/
    public boolean isValidRow(int row) {
        int[] temp = board[row];
        for(int i=0; i<temp.length; i++) {
            for(int j=i+1; j<temp.length; j++) {
                if(temp[i] == temp[j]) {
                    return false;
                }
            }
        } return true;
    }
    /**
     * Checks columns for duplicate entries
     * @param column Number of column accessed (0-8)
     * @return boolean Valid or not
     **/
    public boolean isValidColumn(int column){
        int[][] flipped = new int[board.length][board.length];
        for(int x=0; x<board.length; x++) {
            for(int y=0; y<board.length; y++) {
                flipped[x][y] = board[y][x];
            }
        } int[] flipTemp = flipped[column];
        Arrays.sort(flipTemp);
        for(int i=0; i<flipTemp.length; i++) {
            for(int j=i+1; j<flipTemp.length; j++) {
                if(flipTemp[i] == flipTemp[j]) {
                    return false;
                }
            }
        } return true;
    }
    /**
     * Checks if a 3x3 box contains no duplicates
     * @param box Number of the box accessed (0-8)
     * @return boolean Valid or not
     **/
    public boolean isValidBox(int box) {
        int x = 0;
        int y = 0;
        switch(box) {
            case 0:
                x = 1;
                y = 1;
            case 1:
                x = 1;
                y = 4;
            case 2:
                x = 1;
                y = 7;
            case 3:
                x = 4;
                y = 1;
            case 4:
                x = 4;
                y = 4;
            case 5:
                x = 4;
                y = 7;
            case 6:
                x = 7;
                y = 1;
            case 7:
                x = 7;
                y = 4;
            case 8:
                x = 7;
                y = 7;
        }
        int[][] dup = {
                {board[x-1][y-1], board[x-1][y], board[x-1][y+1]},
                {board[x][y-1], board[x][y], board[x][y+1]},
                {board[x+1][y-1], board[x+1][y], board[x+1][y+1]}
        };
        for (int[] ints : dup) {
            for (int j = 0; j < ints.length; j++) {
                int num = ints[j];
                for (int other = j + 1; other < ints.length; other++) {
                    if (num == ints[other]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * Checks if the game is completed, and board is correct
     * @return boolean Complete or not
     **/
    public boolean isGameOver(){
        for (int[] ints : board) {
            for (int num : ints) {
                if (num == 0) {
                    return false;
                }
            }
        }
        int count = 0;
        for(int a = 0; a<9; a++) {
            if(isValidBox(a)&&isValidColumn(a)&&isValidRow(a)) {
                count++;
                if(count==8) {
                    return true;
                }
            }
        } return false;
    }

    /**
     * Prints the current board state.
     */
    public void printBoard() {
        System.out.println("-------------------------");
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                if(c == 0){
                    System.out.print("|");
                }
                if (c % 3 == 0) {
                    if(board[r][c] == 0){
                        System.out.print(" _ ");
                    }
                    else{
                        System.out.print(" " + board[r][c] + " ");
                    }
                }
                else{
                    if(board[r][c] == 0){
                        System.out.print("_ ");
                    }
                    else{
                        System.out.print(board[r][c] + " ");
                    }
                }
                if(c == 8){
                    System.out.println("|");
                }
                else if (c % 3 == 2) {
                    System.out.print("|");
                }
            }
            if(r % 3 == 2){
                System.out.println("-------------------------");
            }
        }
        System.out.println();
    }
}