/**
 * TicTacToePvP
 * This program is a game of TicTacToe between a Player and a Computer.
 *
 * Author: Saikrishna Rao (sakrao@ucsc.edu)
 */

import java.util.Scanner;

public class TicTacToePvE {

    private static Scanner in = new Scanner(System.in);
    private static int size = in.nextInt();

    public static void main(String[] args){

        //Initialize variables.
        String[][] gameBoard = new String[size][size];
        String playerMark = "O";
        int input;
        int row;
        int column;
        int count = 0;

        //Fill gameBoard.
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                gameBoard[i][j] = "-";
            }
        }

        while(true) {
            //Prints game board.
            printBoard(gameBoard);

            //Get player input.
            while (true) {
                System.out.println("Player " + playerMark + ", choose where to place your mark. In the form \"RC\"");
                input = in.nextInt();
                row = (input % 10);
                column = (input / 10);

                //Checks if response is valid
                if (row >= size || column >= size)
                    System.out.println("That response is invalid");
                else if (checkPlace(row, column, gameBoard))
                    System.out.println("That spot is taken");
                else if (row <= (size - 1) && column <= (size - 1))
                    break;
            }

            gameBoard = playTurn(gameBoard, playerMark, row, column);
            if (checkWin(gameBoard, playerMark)){
                //Announce winner.
                System.out.println("Player " + playerMark + " wins!");
                break;
            }
            playerMark = switchPlayer(playerMark);

            //Checks if there is a draw.
            if(checkDraw(count)) {
                System.out.println("It's a tie!");
                break;
            }
            count++;
        }
    }

    //Print the current state of the tic tac toe game board.
    public static void printBoard(String[][] board){
        for(int i = 0; i < size; i++){
            System.out.print("\n" + i + " ");
            for(int j = 0; j < size; j++)
                System.out.print(board[j][i] + " ");
        }
        System.out.println("");
        System.out.print(" ");
        for(int i = 0; i < size; i++)
            System.out.print(" " + i);
        System.out.println();
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
        int toCheckRow = 0;
        int toCheckColumn = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(board[i][j].equals(player))
                    toCheckRow++;
                if(board[j][i].equals(player))
                    toCheckColumn++;
            }
            if(toCheckRow == size || toCheckColumn == size)
                return true;
            toCheckRow = 0;
            toCheckColumn = 0;
        }

        //Check diagonals
        for(int i = 0; i < size; i++){
            if(board[i][i].equals(player))
                toCheckRow++;
            if(board[i][(size-1)-i].equals(player))
                toCheckColumn++;
        }
        if(toCheckRow == size || toCheckColumn == size)
            return true;

        return false;
    }

    //Sets the marker from "O" to "X", vise versa.
    public static String switchPlayer(String player){
        if(player.equals("X"))
            return "O";
        else
            return "X";
    }

    public static boolean checkDraw(int count){
        if (count == ((size*size)-1))
            return true;
        return false;
    }
}