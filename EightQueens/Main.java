package EightQueens;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Initialize the 8x8 chessboard object and create an arraylist to store
        // solutions
        int row = 8;
        int column = 8;
        ChessBoard chessBoard = new ChessBoard(row, column);
        ArrayList<ChessBoard> solutions = new ArrayList<>();

        // To store the coordinates of previously placed queen, index 0 is the row,
        // index 1 is the column
        int[] previousQueenCoordinates = new int[2];

        // Loop through the rows
        for (int i = 0; i < row; i++) {

            // Break the loop if the board is empty, because the only time when the board
            // other than at the start is empty is when there's no more solutions
            if (previousQueenCoordinates == null) {
                break;
            }

            // Loop through the columns
            for (int j = 0; j <= column; j++) {
                // When you cannot place a queen in a row, you remove the previous queen on the
                // previous row and try another combination starting from the place of the
                // previous queen
                if (j == column) {
                    previousQueenCoordinates = chessBoard.locatePreviousQueen();

                    // Break the loop if the board is empty, because the only time when the board
                    // other than at the start is empty is when there's no more solutions
                    if (previousQueenCoordinates == null) {
                        break;
                    }

                    chessBoard.removeQueen(previousQueenCoordinates[0], previousQueenCoordinates[1]);

                    i = previousQueenCoordinates[0];
                    j = previousQueenCoordinates[1];

                    continue;
                }

                // If you were able to place down the queen at the last row then it's a
                // solution, add it to the solution arraylist
                if (i == row - 1 && chessBoard.placeQueen(i, j)) {
                    solutions.add(new ChessBoard(chessBoard));

                    previousQueenCoordinates = chessBoard.locatePreviousQueen();
                    chessBoard.removeQueen(previousQueenCoordinates[0], previousQueenCoordinates[1]);

                    i = previousQueenCoordinates[0];
                    j = previousQueenCoordinates[1];

                    continue;
                }

                // Place queen and switch to the next row
                if (chessBoard.placeQueen(i, j)) {
                    break;
                }
            }
        }

        // Prints out all the solutions chessboard
        for (ChessBoard board : solutions) {
            board.printBoard();
            System.out.println("");
        }

        // Prints out the numbers of solutions
        System.out.println("Numbers of solutions: " + solutions.size());
    }
}