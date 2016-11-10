/**
 * Created by krish98sai on 11/8/2016.
 */

import java.util.Scanner;

public class TicTacToePvP {
    public static void main(String[] args){

        //Initialize variables
        String[][] gameBoard = {
                {"-", "-", "-"},
                {"-", "-", "-"},
                {"-", "-", "-"},
        };
        String playerMark = "O";
        Scanner in = new Scanner(System.in);
        int input = 0;
        int row = 0;
        int column = 0;


        while(true){
            printBoard(gameBoard);

            //Get player input.
            while(true) {
                System.out.println("Player " + playerMark + ", choose where to place your mark. In the form \"RC\"");
                input = in.nextInt();
                row = (input % 10);
                column = (input / 10);

                //Checks if response is valid
                if(row >= 3 || column >= 3)
                    System.out.println("That response is invalid");
                else if(checkPlace(row, column, gameBoard))
                    System.out.println("That spot is taken");
                else if(row <= 2 && column <= 2)
                    break;
            }

            gameBoard = playTurn(gameBoard, playerMark, row, column);
            if(checkWin(gameBoard, playerMark))
                break;
            playerMark = switchPlayer(playerMark);
        }

        //Announce winner.
        System.out.println("Player " + playerMark + " wins!");
    }

    //Print the current state of the tic tac toe game board.
    public static void printBoard(String[][] board){
        for(int i = 0; i < 3; i++){
            System.out.print("\n" + i + " ");
            for(int j = 0; j < 3; j++){
                System.out.print(board[j][i] + " ");
            }
        }
        System.out.println("\n  0 1 2");
    }

    //Player places their mark wherever they choose.
    public static String[][] playTurn(String[][] board, String player, int row, int column){
        board[row][column] = player;
        return board;
    }

    //Checks if that position on the board is taken or not.
    public static boolean checkPlace(int row, int column, String[][] board){
        if ((board[row][column]).equals("-"))
            return false;
        else
            return true;
    }

    //Check rows, columns, and diagonals to see if someone has won.
    public static boolean checkWin(String[][] board, String player){
        //Check rows and columns
        String toCheckRow = "";
        String toCheckColumn = "";
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                toCheckRow += board[i][j];
                toCheckColumn += board[j][i];
            }
            if(toCheckRow.equals("XXX") || toCheckRow.equals("OOO") || toCheckColumn.equals("XXX") || toCheckColumn.equals("OOO"))
                return true;
            toCheckRow = "";
            toCheckColumn = "";
        }

        //Check diagonals


        return false;
    }

    //Sets the marker from "O" to "X", vise versa.
    public static String switchPlayer(String player){
        if(player.equals("X"))
            return "O";
        else
            return "X";
    }
}