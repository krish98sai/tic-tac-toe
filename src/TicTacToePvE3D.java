import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

/**
 * Created by krish98sai on 11/20/2016.
 */
public class TicTacToePvE3D {
    public static void main(String[] args) throws FileNotFoundException{

        //Initializing variables.
        Scanner file = new Scanner(new FileInputStream(args[0]));
        Scanner in = new Scanner(System.in);
        int[][][] board = new int[4][4][4];
        int turn;
        int level;
        int row;
        int column;
        int numberOfEntries;
        int userInput;

        //Flood the board with "0", a.k.a. empty spots.
        board = floodBoard(board, 0);

        //Checks for a startup file and uses it if there is one.
        if(file.hasNext()){
            numberOfEntries = file.nextInt();
            while(numberOfEntries > 0){
                level = (file.nextInt())%4;
                row = (file.nextInt())%4;
                column = (file.nextInt())%4;
                turn = file.nextInt();

                if(validTurn(level, row, column, turn))
                    board = fillBoard(board, level, row, column, turn);
                numberOfEntries--;
            }
        }

        //Start of actual game play
        turn = 5;

        //Print the game board.
        printBoard(board);


        //Player's turn
        while(true){
            System.out.println("Type your move as one three digit number (LRC).");
            userInput = in.nextInt();
            level = userInput / 100;

        }
    }

    //Print the game board
    public static void printBoard(int[][][] board) {
        String spacing;
        int toPrint;
        for(int i = 3; i >= 0; i--){
            spacing = "    ";
            for(int j = 3; j >= 0; j--){
                System.out.print(spacing + i + "" + j + "  ");
                for(int k = 0; k < 4; k++){
                    toPrint = board[i][j][k];
                    if(toPrint == 5)
                        System.out.print("X");
                    else if(toPrint == 1)
                        System.out.print("O");
                    else
                        System.out.print("_");
                    System.out.print(" ");
                }
                System.out.println();
                spacing = spacing.substring(0, spacing.length()-1);
            }
            System.out.println();
        }
        System.out.println("    0 1 2 3");
    }

    //Checks to see if the values entered are a valid turn.
    public static boolean validTurn(int level, int row, int column, int turn) {
        if(level < 4 && row < 4 && column < 4){
            if(turn == 1 || turn == 5)
                return true;
        }
        return false;
    }

    //Flood the board with a floodValue (0, 1, 5).
    public static int[][][] floodBoard(int[][][] board, int floodValue) {
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 4; k++){
                    board[i][j][k] = floodValue;
                }
            }
        }
        return board;
    }

    //Put a turn value in the level, row, column position on the board.
    public static int[][][] fillBoard(int[][][] board, int level, int row, int column, int turn) {
        board[level][row][column] = turn;
        return board;
    }
}