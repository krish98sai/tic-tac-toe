/**
 * Created by krish98sai on 11/8/2016.
 */
public class TicTacToePvP {
    public static void main(String[] args){

        //Initialize variables
        String[][] gameBoard = {
                {"-", "-", "-"},
                {"-", "-", "-"},
                {"-", "-", "-"},
        };
        String playerMark = "O";
        boolean win = false;

        while(!win){
            printBoard(gameBoard);
            gameBoard = playTurn(gameBoard, playerMark);
            checkWin(gameBoard);
            playerMark = switchPlayer(playerMark);
        }

        //Announce winner.
        System.out.println("Player " + playerMark + " wins!");
    }

    public static void printBoard(String[][] board){

    }

    public static String[][] playTurn(String[][] board, String player){
        return board;
    }

    public static void checkWin(String[][] board){

    }

    public static String switchPlayer(String player){
        if(player.equals("X"))
            return "O";
        else
            return "X";
    }
}