import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static final String EMPTY = " ";
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        boolean playAgain;

        do {
            clearBoard();
            currentPlayer = "X";
            int moves = 0;
            boolean gameWon = false;

            while (moves < ROWS * COLS) {
                displayBoard();
                System.out.println("Player " + currentPlayer + ", it's your turn.");

                int row = SafeInput.getRangedInt(console, "Enter row", 1, 3) - 1;
                int col = SafeInput.getRangedInt(console, "Enter column", 1, 3) - 1;

                if (!isValidMove(row, col)) {
                    System.out.println("Invalid move! Try again.");
                    continue;
                }

                board[row][col] = currentPlayer;
                moves++;

                if (isWin(currentPlayer)) {
                    displayBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameWon = true;
                    break;
                }

                if (isTie()) {
                    displayBoard();
                    System.out.println("It's a tie!");
                    break;
                }

                currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
            }

            playAgain = SafeInput.getYNConfirm(console, "Would you like to play again?");
        } while (playAgain);

        console.close();
    }

    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void displayBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + (j < COLS - 1 ? " | " : ""));
            }
            System.out.println();
            if (i < ROWS - 1) System.out.println("--+---+--");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(EMPTY);
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int i = 0; i < COLS; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].equals(EMPTY)) return false;
            }
        }
        return true;
    }
}
