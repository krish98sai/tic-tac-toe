
/*
Various routines for working with the key arrays: board, lines and sums in one
possible implementation of the TTT3D project.
Author: Delbert Bailey
 */
import java.io.*;
public class BoardTest{
    static int[][][] board = new int[4][4][4];
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
    //utility display game array
    static void printBoard(){
        for (int lev=3; lev >= 0; lev--){
            for (int row=3; row >= 0; row--){
                for (int col=0; col<4; col++){
                    System.out.printf("%1d ", board[lev][row][col]);
                }
                System.out.printf("\n");
            }
            System.out.printf("\n");
        }
    }
    //utility display sums array
    static void printSums(){
        for (int i=0; i<sums.length; i++){
            System.out.println("line " +i+ "is " + sums[i]);
        }
    }

    //is a cell at specified adr empty
    static boolean isEmpty(int[] celAdr){
        if (board[celAdr[0]][celAdr[1]][celAdr[2]] == 0){
            return true;
        }
        else{
            return false;
        }
    }

    //place a move on the board by cell adr array
    static void move(int[] celAdr, int val){
        move(celAdr[0], celAdr[1], celAdr[2], val);
    }

    //place a move on the board explicit coordinates
    static void move(int lev, int row, int col, int val){
        board[lev][row][col] = val;
    }

    //clear board to a value
    static void setAll(int val){
        for (int lev = 0; lev < 4; lev++){
            for (int row = 0; row < 4; row++){
                for (int col = 0; col < 4; col++){
                    move(lev, row, col, val);
                }
            }
        }
    }

    //are two cell addresses the same
    static boolean isEqual(int[] a, int[] b){
        for (int i=0; i<a.length; i++){
            if (a[i] != b[i]){
                return false;
            }
        }
        return true;
    }

    //fine empty cell in a line
    static int[] findEmptyCel(int[][] line){
        for (int i=0; i<4; i++){
            if ( isEmpty(line[i]) ) return line[i];
        }
        return null;
    }


    //find common empty cell to two lines
    static int[] findComMtCel(int[][] line1, int[][] line2){
        for (int i=0; i<line1.length; i++ ){
            for (int j=0; j<line1.length; j++ ){
                if (isEqual(line1[i], line2[j]) &&
                        isEmpty(line1[i]) &&
                        isEmpty(line2[j])) {

                    return line1[i];
                }
            }
        }
        return null;
    }

    static void compLineSums(){
        for (int i=0; i<sums.length; i++){
            sums[i] = 0;
            for (int j=0; j<4; j++){
                sums[i] += board[lines[i][j][0]]
                        [lines[i][j][1]]
                        [lines[i][j][2]];
            }
        }
    }

    //find line with a specific sum
    static int findLineSum(int sum){
        for (int i=0; i<sums.length; i++){
            if (sums[i] == sum) return i; // line i has the sum
        }
        return -1; //no such sum found
    }



    //main routine for testing
    public static void main(String[] args){

        //testing simple display of board
        printBoard();

        for (int line = 0; line < 76; line++) {
            setAll(0);
            for (int cell = 0; cell < 4; cell++) {
                move(lines[line][cell], 1);
            }
            System.out.println("line number " + line);
            printBoard();
        }

        for(int i : lines[0][1]){
            System.out.println(i);
        }
    }
}