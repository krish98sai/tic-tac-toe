import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by krish98sai on 11/20/2016.
 */
public class TTT3Dp {
    public static void main(String[] args) throws FileNotFoundException{

        //Initializing variables.
        Scanner file = new Scanner(new FileInputStream(args[0]));
        int[][][] board = new int[4][4][4];
        int turn;
        int level;
        int row;
        int column;
        int numberOfEntries;

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

                board = fillBoard(board, level, row, column, turn);
            }
        }

        //Print the game board.
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
