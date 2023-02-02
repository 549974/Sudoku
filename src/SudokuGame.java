import java.util.Scanner;
/**
 * Runs a game of sudoku.
 * @author Jake A
 * @version 1.0
 * @since 2023-02-02
 */
public class SudokuGame {
    public static void main(String[] args)
    {
        int[][] puzzle = new int[][]{
                {0, 7, 1, 0, 0, 0, 0, 0, 6},
                {0, 0, 9, 0, 0, 0, 8, 0, 0},
                {5, 0, 4, 9, 0, 0, 0, 7, 1},
                {0, 0, 0, 0, 0, 0, 9, 0, 0},
                {0, 0, 0, 4, 3, 0, 0, 0, 0},
                {7, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 0, 0, 0, 6, 0, 4, 3, 5},
                {0, 0, 3, 1, 4, 0, 0, 0, 8},
                {6, 0, 0, 0, 0, 7, 0, 0, 0}
        };
        /* SOLUTION:
         int[][] puzzle = new int[][]{
                 {3, 7, 1, 8, 5, 4, 2, 9, 6},
                 {2, 6, 9, 7, 1, 3, 8, 5, 4},
                 {5, 8, 4, 9, 2, 6, 3, 7, 1},
                 {4, 5, 2, 6, 7, 8, 9, 1, 3},
                 {1, 9, 6, 4, 3, 2, 5, 8, 7},
                 {7, 3, 8, 5, 9, 1, 6, 4, 2},
                 {8, 1, 7, 2, 6, 9, 4, 3, 5},
                 {9, 2, 3, 1, 4, 5, 7, 6, 8},
                 {6, 4, 5, 3, 8, 7, 1, 2, 9}
         };*/
        SudokuBoard board = new SudokuBoard(puzzle);
        board.printBoard();
        Scanner input = new Scanner(System.in);
        while(!board.isGameOver()) {
            int guess = collectInput(input, "guess");
            int row = collectInput(input, "row");
            int column = collectInput(input, "column");
            if(guess > 0 && row > 0 && column > 0) {
                board.makeGuess(guess, row, column);
            } else {
                guess = 0;
                row = 0;
                column = 0;
            }
            board.printBoard();
        } System.out.println("Good game!");
        input.close();
    }
    /** This method collects input from the user
     * @param input Scanner for collection
     * @param inputType String to specify type
     * @return v Value after checks
     **/
    public static int collectInput(Scanner input, String inputType) {
        System.out.println("Enter "+inputType+": ");
        int v = 0;
        if(input.hasNextInt()) {
            v = input.nextInt();
            if(v>=1&&v<=9) {
                return v;
            }
            else {
                System.out.println("Invalid "+inputType+"! Must be between 1 and 9.\n");
                return -1;
            }
        }
        else if(input.hasNext()) {
            String invalid = input.next();
            System.out.println("Input must be a number!\n");
        } return -1;
    }
}