package EightQueens;

public class ChessBoard {

    // Using 2D array to represents the chessboard
    private int[][] boardSize;
    private int row;
    private int column;

    // Constructor to initialize the chessboard
    public ChessBoard(int row, int column) {
        this.boardSize = new int[row][column];
        this.row = row;
        this.column = column;
    }

    // This constructor is used to copy the solution board because object is a
    // reference type data
    public ChessBoard(ChessBoard solution) {
        this.row = solution.row;
        this.column = solution.column;
        this.boardSize = new int[row][column];

        // Copy the arrays which contain the position of each queens from the solution
        // because array is a reference type data
        for (int i = 0; i < row; i++) {
            System.arraycopy(solution.boardSize[i], 0, this.boardSize[i], 0, column);
        }
    }

    // Place a queen at the given coordinates only if there's no queen horizontally,
    // vertically, and diagonally. Returns true indicating the queen is placed,
    // false indicating the queen is not placed
    public boolean placeQueen(int row, int column) {
        if (checkQueensHorizontalMovement(row) &&
                checkQueensVerticalMovement(column) &&
                checkQueensDiagonalMovement(row, column)) {
            this.boardSize[row][column] = 1;
            return true;
        }

        return false;
    }

    // Removes the queen from the given coordinates
    public void removeQueen(int row, int column) {
        this.boardSize[row][column] = 0;
    }

    // Used to return the coordinates of perviously placed queen
    public int[] locatePreviousQueen() {
        int[] rowAndColumn = new int[2];
        for (int row = this.boardSize.length - 1; row >= 0; row--) {
            for (int column = 0; column < this.boardSize[row].length; column++) {
                if (this.boardSize[row][column] == 1) {
                    rowAndColumn[0] = row;
                    rowAndColumn[1] = column;
                    return rowAndColumn;
                }
            }
        }
        return null;
    }

    // Checks it there's a queen diagonally or not, returns true if there's no
    // queen, returns false if there's a queen
    private boolean checkQueensDiagonalMovement(int row, int column) {

        // Up left
        for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) {
            if (boardSize[i][j] == 1) {
                return false;
            }
        }

        // Up right
        for (int i = row, j = column; i >= 0 && j < boardSize[i].length; i--, j++) {
            if (boardSize[i][j] == 1) {
                return false;
            }
        }

        // Down left
        for (int i = row, j = column; i < boardSize.length && j >= 0; i++, j--) {
            if (boardSize[i][j] == 1) {
                return false;
            }
        }

        // Down right
        for (int i = row, j = column; i < boardSize.length && j < boardSize[i].length; i++, j++) {
            if (boardSize[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    // Checks if there's a queen vertically or not, returns true if there's no
    // queen, returns false if there's a queen
    private boolean checkQueensVerticalMovement(int column) {

        for (int row = 0; row < boardSize.length; row++) {
            if (boardSize[row][column] == 1) {
                return false;
            }
        }

        return true;
    }

    // Checks if there's a queen horizontally or not, returns true if there's no
    // queen, returns false if there's a queen
    private boolean checkQueensHorizontalMovement(int row) {

        for (int column = 0; column < boardSize.length; column++) {
            if (boardSize[row][column] == 1) {
                return false;
            }
        }

        return true;
    }

    // Print the chessboard with 0 representing an empty space and 1 representing
    // the queen
    public void printBoard() {
        for (int i = 0; i < this.boardSize.length; i++) {
            for (int j = 0; j < this.boardSize[i].length; j++) {
                System.out.print(this.boardSize[i][j] + " ");
            }
            System.out.println("");
        }
    }
}