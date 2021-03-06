/**
 * Assignment #4.
 * This program is a game of 3D four by four TicTacToe.
 * The game is between a user and an intelligent computer
 * that detects potential wins and blockages.
 *
 * Author: Saikrishna Rao (sakrao@ucsc.edu)
 *
 * Borrowed code:
 * The lines 3D array is borrowed from example_lines_table.java by Delbert Bailey.
 * Some methods are inspired by BoardTest.java by Delbert Bailey.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class TicTacToePvE3D {

    static Random rand = new Random();
    static int[] sums = new int[76];
    static final int[][][] lines = {
            {{0,0,0},{0,0,1},{0,0,2},{0,0,3}},  //lev 0; row 0   rows in each level
            {{0,1,0},{0,1,1},{0,1,2},{0,1,3}},  //       row 1
            {{0,2,0},{0,2,1},{0,2,2},{0,2,3}},  //       row 2
            {{0,3,0},{0,3,1},{0,3,2},{0,3,3}},  //       row 3
            {{1,0,0},{1,0,1},{1,0,2},{1,0,3}},  //lev 1; row 0
            {{1,1,0},{1,1,1},{1,1,2},{1,1,3}},  //       row 1
            {{1,2,0},{1,2,1},{1,2,2},{1,2,3}},  //       row 2
            {{1,3,0},{1,3,1},{1,3,2},{1,3,3}},  //       row 3
            {{2,0,0},{2,0,1},{2,0,2},{2,0,3}},  //lev 2; row 0
            {{2,1,0},{2,1,1},{2,1,2},{2,1,3}},  //       row 1
            {{2,2,0},{2,2,1},{2,2,2},{2,2,3}},  //       row 2
            {{2,3,0},{2,3,1},{2,3,2},{2,3,3}},  //       row 3
            {{3,0,0},{3,0,1},{3,0,2},{3,0,3}},  //lev 3; row 0
            {{3,1,0},{3,1,1},{3,1,2},{3,1,3}},  //       row 1
            {{3,2,0},{3,2,1},{3,2,2},{3,2,3}},  //       row 2
            {{3,3,0},{3,3,1},{3,3,2},{3,3,3}},  //       row 3
            {{0,0,0},{0,1,0},{0,2,0},{0,3,0}},  //lev 0; col 0   columns in each level
            {{0,0,1},{0,1,1},{0,2,1},{0,3,1}},  //       col 1
            {{0,0,2},{0,1,2},{0,2,2},{0,3,2}},  //       col 2
            {{0,0,3},{0,1,3},{0,2,3},{0,3,3}},  //       col 3
            {{1,0,0},{1,1,0},{1,2,0},{1,3,0}},  //lev 1; col 0
            {{1,0,1},{1,1,1},{1,2,1},{1,3,1}},  //       col 1
            {{1,0,2},{1,1,2},{1,2,2},{1,3,2}},  //       col 2
            {{1,0,3},{1,1,3},{1,2,3},{1,3,3}},  //       col 3
            {{2,0,0},{2,1,0},{2,2,0},{2,3,0}},  //lev 2; col 0
            {{2,0,1},{2,1,1},{2,2,1},{2,3,1}},  //       col 1
            {{2,0,2},{2,1,2},{2,2,2},{2,3,2}},  //       col 2
            {{2,0,3},{2,1,3},{2,2,3},{2,3,3}},  //       col 3
            {{3,0,0},{3,1,0},{3,2,0},{3,3,0}},  //lev 3; col 0
            {{3,0,1},{3,1,1},{3,2,1},{3,3,1}},  //       col 1
            {{3,0,2},{3,1,2},{3,2,2},{3,3,2}},  //       col 2
            {{3,0,3},{3,1,3},{3,2,3},{3,3,3}},  //       col 3
            {{0,0,0},{1,0,0},{2,0,0},{3,0,0}},  //cols in vert plane in front
            {{0,0,1},{1,0,1},{2,0,1},{3,0,1}},
            {{0,0,2},{1,0,2},{2,0,2},{3,0,2}},
            {{0,0,3},{1,0,3},{2,0,3},{3,0,3}},
            {{0,1,0},{1,1,0},{2,1,0},{3,1,0}},  //cols in vert plane one back
            {{0,1,1},{1,1,1},{2,1,1},{3,1,1}},
            {{0,1,2},{1,1,2},{2,1,2},{3,1,2}},
            {{0,1,3},{1,1,3},{2,1,3},{3,1,3}},
            {{0,2,0},{1,2,0},{2,2,0},{3,2,0}},  //cols in vert plane two back
            {{0,2,1},{1,2,1},{2,2,1},{3,2,1}},
            {{0,2,2},{1,2,2},{2,2,2},{3,2,2}},
            {{0,2,3},{1,2,3},{2,2,3},{3,2,3}},
            {{0,3,0},{1,3,0},{2,3,0},{3,3,0}},  //cols in vert plane in rear
            {{0,3,1},{1,3,1},{2,3,1},{3,3,1}},
            {{0,3,2},{1,3,2},{2,3,2},{3,3,2}},
            {{0,3,3},{1,3,3},{2,3,3},{3,3,3}},
            {{0,0,0},{0,1,1},{0,2,2},{0,3,3}},  //diags in lev 0
            {{0,3,0},{0,2,1},{0,1,2},{0,0,3}},
            {{1,0,0},{1,1,1},{1,2,2},{1,3,3}},  //diags in lev 1
            {{1,3,0},{1,2,1},{1,1,2},{1,0,3}},
            {{2,0,0},{2,1,1},{2,2,2},{2,3,3}},  //diags in lev 2
            {{2,3,0},{2,2,1},{2,1,2},{2,0,3}},
            {{3,0,0},{3,1,1},{3,2,2},{3,3,3}},  //diags in lev 3
            {{3,3,0},{3,2,1},{3,1,2},{3,0,3}},
            {{0,0,0},{1,0,1},{2,0,2},{3,0,3}},  //diags in vert plane in front
            {{3,0,0},{2,0,1},{1,0,2},{0,0,3}},
            {{0,1,0},{1,1,1},{2,1,2},{3,1,3}},  //diags in vert plane one back
            {{3,1,0},{2,1,1},{1,1,2},{0,1,3}},
            {{0,2,0},{1,2,1},{2,2,2},{3,2,3}},  //diags in vert plane two back
            {{3,2,0},{2,2,1},{1,2,2},{0,2,3}},
            {{0,3,0},{1,3,1},{2,3,2},{3,3,3}},  //diags in vert plane in rear
            {{3,3,0},{2,3,1},{1,3,2},{0,3,3}},
            {{0,0,0},{1,1,0},{2,2,0},{3,3,0}},  //diags left slice
            {{3,0,0},{2,1,0},{1,2,0},{0,3,0}},
            {{0,0,1},{1,1,1},{2,2,1},{3,3,1}},  //diags slice one to right
            {{3,0,1},{2,1,1},{1,2,1},{0,3,1}},
            {{0,0,2},{1,1,2},{2,2,2},{3,3,2}},  //diags slice two to right
            {{3,0,2},{2,1,2},{1,2,2},{0,3,2}},
            {{0,0,3},{1,1,3},{2,2,3},{3,3,3}},  //diags right slice
            {{3,0,3},{2,1,3},{1,2,3},{0,3,3}},
            {{0,0,0},{1,1,1},{2,2,2},{3,3,3}},  //cube vertex diags
            {{3,0,0},{2,1,1},{1,2,2},{0,3,3}},
            {{0,3,0},{1,2,1},{2,1,2},{3,0,3}},
            {{3,3,0},{2,2,1},{1,1,2},{0,0,3}}
    };

    public static void main(String[] args) throws FileNotFoundException{

        //Initializing variables.
        Scanner file = new Scanner(new FileInputStream(args[0]));
        Scanner in = new Scanner(System.in);
        int turn;
        int level;
        int row;
        int column;
        int numberOfEntries;
        int userInput;
        int count = 0;
        boolean win = false;
        int[][][] board = new int[4][4][4];

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

                if(validTurn(level, row, column, turn)) {
                    board = fillBoard(board, level, row, column, turn);
                    count++;
                }
                numberOfEntries--;
            }
        }

        //Start of actual game play
        turn = 5;

        while(!win){

            if(turn == 5){

                //Print the game board.
                printBoard(board);

                //Player's turn.
                while(true){
                    System.out.println("Type your move as one three digit number (LRC).");
                    userInput = in.nextInt();
                    level = userInput / 100;
                    userInput = userInput % 100;
                    row = userInput / 10;
                    column = userInput % 10;

                    if(!validTurn(level, row, column, turn))
                        System.out.println("That response is invalid.");
                    else if (checkPlace(board, level, row, column))
                        System.out.println("That spot is taken.");
                    else{
                        board = fillBoard(board, level, row, column, turn);
                        break;
                    }
                }
            }
            else{
                //Computer's turn.
                board = computerTurn(board, turn);
            }

            //Get the line sums
            collectLineSums(board);

            //Check win
            if(checkWin(turn)){
                if (turn == 5)
                    System.out.println("Player X wins!");
                else
                    System.out.println("Player O wins!");
                break;
            }

            //Check draw
            if(checkDraw(count)){
                System.out.println("It's a tie!");
                break;
            }

            //Switch players
            turn = switchPlayer(turn);

            count++;
        }
    }

    private static int[][][] computerTurn(int[][][] board, int turn) {
        board = findComputerWin(board, turn);
        return board;
    }

    private static int[][][] findComputerWin(int[][][] board, int turn) {
        int position = findLineSum(turn*3, 0);
        if(position < 99){
            int[] pointToTake = findEmptyCell(lines[position], board);
            int level = pointToTake[0];
            int row = pointToTake[1];
            int column = pointToTake[2];
            board = fillBoard(board, level, row, column, turn);
        }
        else
            board = findBlock(board, turn);
        return board;
    }

    private static int[][][] findBlock(int[][][] board, int turn) {
        int position = findLineSum(15, 0);
        if(position < 99){
            int[] pointToTake = findEmptyCell(lines[position], board);
            int level = pointToTake[0];
            int row = pointToTake[1];
            int column = pointToTake[2];
            board = fillBoard(board, level, row, column,turn);
        }
        else
            board = findComputerFork(board, turn);
        return board;
    }

    private static int[][][] findComputerFork(int[][][] board, int turn) {
        int position1;
        int position2;
        int[] pointToTake = null;
        int i;
        for(i = 0; i < 76; i++){
            position1 = findLineSum(turn*2, i);
            position2 = findLineSum(turn*2, position1);

            if (position1 < 99 && position2 < 99) {
                pointToTake = findCommonEmptyCell(board, lines[position1], lines[position2]);
                if(pointToTake != null)
                    break;
            }
        }

        if(i != 76){
            int level = pointToTake[0];
            int row = pointToTake[1];
            int column = pointToTake[2];
            board = fillBoard(board, level, row, column,turn);
        }
        else
            board = findUserFork(board, turn);
        return board;
    }

    private static int[][][] findUserFork(int[][][] board, int turn) {
        int position1;
        int position2;
        int[] pointToTake = null;
        int i;
        for(i = 0; i < 76; i++){
            position1 = findLineSum(10, i);
            position2 = findLineSum(10, position1);
            if(position1 < 99 && position2 < 99){
                pointToTake = findCommonEmptyCell(board, lines[position1], lines[position2]);
                if(pointToTake != null)
                    break;
            }
        }

        if(i != 76){
            int level = pointToTake[0];
            int row = pointToTake[1];
            int column = pointToTake[2];
            board = fillBoard(board, level, row, column,turn);
        }
        else
            board = findNotDead(board, turn);
        return board;
    }

    private static int[][][] findNotDead(int[][][] board, int turn) {
        int position1 = findLineSum(turn, 0);
        int position2 = findLineSum(turn*2, 0);
        if(position2 < 99){
            int[] pointToTake = findEmptyCell(lines[position2],board);
            int level = pointToTake[0];
            int row = pointToTake[1];
            int column = pointToTake[2];
            board = fillBoard(board, level, row, column, turn);
        }
        else if(position1 < 99){
            int[] pointToTake = findEmptyCell(lines[position1],board);
            int level = pointToTake[0];
            int row = pointToTake[1];
            int column = pointToTake[2];
            board = fillBoard(board, level, row, column, turn);
        }
        else
            board = findRandomly(board, turn);
        return board;
    }

    private static int[][][] findRandomly(int[][][] board, int turn) {
        int randomNum;
        int[] pointToTake;
        while(true){
            randomNum = rand.nextInt(76);
            pointToTake = findEmptyCell(lines[randomNum], board);

            if(pointToTake != null){
                int level = pointToTake[0];
                int row = pointToTake[1];
                int column = pointToTake[2];
                board = fillBoard(board, level, row, column, turn);
                break;
            }
        }
        return board;
    }

    //Checks to see if the player whose turn it is has won.
    public static boolean checkWin(int turn) {
        int winPosition = findLineSum(4*turn, 0);

        if(winPosition < 99)
            return true;
        return false;
    }

    public static boolean checkDraw(int count) {
        if(count == 64)
            return true;
        return false;
    }

    //Checks if that position on the board is taken or not.
    public static boolean checkPlace(int[][][] board, int level, int row, int column) {
        if ((board[level][row][column]) == 0)
            return false;
        else
            return true;
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

    //Sets the marker from "O" to "X", vise versa.
    public static int switchPlayer(int turn){
        if(turn == 5)
            return 1;
        else
            return 5;
    }

    //Methods beyond this point are inspired by BoardTest.java by Delbert Bailey

    //Gets the sums on all of the line combinations on the board and puts them in a sum array.
    public static void collectLineSums(int[][][] board){
        for (int i = 0; i < 76; i++){
            sums[i] = 0;
            for (int j = 0; j < 4; j++){
                sums[i] += board[lines[i][j][0]][lines[i][j][1]][lines[i][j][2]];
            }
        }
    }

    //Finds a line with a certain sum and returns its position.
    public static int findLineSum(int sum, int startAt){
        for (int i = startAt; i < 76; i++){
            if (sums[i] == sum)
                return i;
        }
        return 100;
    }

    public static int[] findEmptyCell(int[][] line, int[][][] board){
        for (int i = 0; i < 4; i++){
            if (isCellEmpty(line[i], board))
                return line[i];
        }
        return null;
    }

    public static boolean isCellEmpty(int[] celAdr, int[][][] board){
        if (board[celAdr[0]][celAdr[1]][celAdr[2]] == 0)
            return true;
        else
            return false;
    }

    public static int[] findCommonEmptyCell(int[][][] board, int[][] line1, int[][] line2){
        for (int i = 0; i < line1.length; i++ ){
            for (int j = 0; j < line1.length; j++ ){
                if (isEmpty(line1[i], board) && isEmpty(line2[j], board) && isEqual(line1[i], line2[j]))
                    return line1[i];
            }
        }
        return null;
    }

    //Checks if two cells are in the same position.
    public static boolean isEqual(int[] a, int[] b){
        for (int i = 0; i < a.length; i++){
            if (a[i] != b[i])
                return false;
        }
        return true;
    }

    //Checks if the cell is empty.
    public static boolean isEmpty(int[] celAdr, int[][][] board){
        if (board[celAdr[0]][celAdr[1]][celAdr[2]] == 0)
            return true;
        else
            return false;
    }
}