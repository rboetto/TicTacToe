//********************************************************************************
// STUDENT NAME:  Ricardo Boetto
// FIU EMAIL: rboet001@gmail.com
// CLASS: COP 2210 – Spring 2017
// ASSIGNMENT # 5
// DATE: 4/29/2017 (finally!)
//
// I hereby swear and affirm that this work is solely my own, and not the work 
// or the derivative of the work of someone else, except as outlined in the 
// assignment instructions.
//********************************************************************************

package tictactoe;

import javafx.application.Application;
import javafx.geometry.*;
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class TicTacToe extends Application {
    
    static String playerName;
    static boolean computerFirst = false;
    
    final static int ROOT_NUM_BTNS = 3;
    static TicTacButton[][] board = 
            new TicTacButton[ROOT_NUM_BTNS][ROOT_NUM_BTNS];
    
    @Override
    public void start(Stage secondaryStage) {
        
        GridPane gameHash = new GridPane();
        Insets gridPadding = new Insets(20, 20, 20, 20);
        gameHash.setPadding(gridPadding);
        gameHash.setHgap(10);
        gameHash.setVgap(10);
        
        Scene scene = new Scene(gameHash, 300, 300);
        
        for (int i = 0; i < ROOT_NUM_BTNS; ++i)
        {
            for (int j = 0; j < ROOT_NUM_BTNS; ++j)
            {
                board[i][j] = new TicTacButton();
                board[i][j].setStyle("-fx-font-size: 30px");
                gameHash.add(board[i][j], i, j);
                defineButtonActions(board[i][j]);
            }
        }      
        
        secondaryStage.setTitle("Play Tic-Tac-Toe");
        secondaryStage.setScene(scene);
        secondaryStage.show();
        
        if (computerFirst) {
            computerTurn();
        }
    }

    public static void main(String[] args) {
        playerName = JOptionPane.showInputDialog(null, "Welcome! What is your"
                + " name?");
        Random r = new Random();
        int i = r.nextInt();
        if (i % 2 == 0) {
            JOptionPane.showMessageDialog(null, "The computer will go first");
            computerFirst = true;
        }
        else {
            JOptionPane.showMessageDialog(null, "You will go first");
        }
        launch(args);
    }   

    /**
     * Sets a button to execute the players turn when pressed, which then
     * checks if a player has won, followed by the computers turn.
     * @param btn the button to be pressed
     */
    public static void defineButtonActions(TicTacButton btn){
        
        btn.setOnAction(
                event -> btn.Xgoes()
            );
    }
    
    /**
     * Executes the computer turn by randomly selecting a blank button and 
     * marking it with an O.
     */
    public static void computerTurn() {
        Random r = new Random();
        int rowNum, colNum;
        boolean movesAvail = checkBoard();
        boolean buttonIsBlank;
        if (movesAvail) {
            do {
                rowNum = r.nextInt(3);
                colNum = r.nextInt(3);
                buttonIsBlank = board[rowNum][colNum].isBlank();
                if (buttonIsBlank) {
                    board[rowNum][colNum].Ogoes();
                }
            } while (!buttonIsBlank);
        }
        
    }
    
    /**
     * Checks if the board has available moves
     * @return true if at least one button is blank
     */
    public static boolean checkBoard() {
        OUTER:
        for (int i = 0; i < ROOT_NUM_BTNS; ++i) {
            for (int j = 0; j < ROOT_NUM_BTNS; ++j) {
                if (board[i][j].isBlank()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Checks if X has won, then if O has won, and then declares a tie if
     * neither player won and there are no blank spaces left.
     */
    public static void winCon() {
        checkWinX();
        checkWinO();
        if (!checkBoard()) {
            playersTie();
        }     
    }
    
    /**
     * Checks all rows, columns, and diagonals for Xs to see if the player won.
     */
    public static void checkWinX() {
        for (int i = 0; i < ROOT_NUM_BTNS; ++i) {
            if (board[i][0].isX() && board[i][1].isX() && board[i][2].isX()) {
                winX();
            }
            if (board[0][i].isX() && board[1][i].isX() && board[2][i].isX()) {
                winX();
            }
            if (board[1][1].isX()) {
                if (board[0][0].isX() && board[2][2].isX()) {
                    winX();
                }
                if (board[2][0].isX() && board[0][2].isX()) {
                    winX();
                }
            }
        }
    }
    
    /**
     * Checks all rows, columns, and diagonals for Os to see if the computer won.
     */
    public static void checkWinO() {
        for (int i = 0; i < ROOT_NUM_BTNS; ++i) {
            if (board[i][0].isO() && board[i][1].isO() && board[i][2].isO()) {
                winO();
            }
            if (board[0][i].isO() && board[1][i].isO() && board[2][i].isO()) {
                winO();
            }
            if (board[1][1].isO()) {
                if (board[0][0].isO() && board[2][2].isO()) {
                    winO();
                }
                if (board[2][0].isO() && board[0][2].isO()) {
                    winO(); 
                }
            }
        }
    }
    
    /**
     * Congratulates the player for winning, then wipes the board.
     */
    public static void winX() {
        JOptionPane.showMessageDialog(null, playerName + " wins!");
        wipeBoard();
    }
    
    /**
     * Lets the player know he/she lost, then wipes the board.
     */
    public static void winO() {
        JOptionPane.showMessageDialog(null, "You lose.");
        wipeBoard();
    }
    
    /**
     * Informs the player that the game ended in a tie, then wipes the board.
     */
    public static void playersTie() {
        JOptionPane.showMessageDialog(null, "It's a tie!");
        wipeBoard();
    }
    
    /**
     * Sets all buttons to blank.
     */
    public static void wipeBoard() {
        int i = JOptionPane.showOptionDialog(null, "Play again?", 
                "Play Tic-Tac-Toe", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (i == 0) {
            for (TicTacButton[] rowBtns: board) {
            for (TicTacButton button : rowBtns) {
                button.clearButton();
            }
            }
        }
        else {
            System.exit(1);
        }
    }
}