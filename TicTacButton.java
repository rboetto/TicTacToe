//********************************************************************************
// STUDENT NAME:  Ricardo Boetto
// FIU EMAIL: 2519915
// CLASS: COP 2210 – Spring 2017
// ASSIGNMENT # 5
// DATE: 4/29/2017
//
// I hereby swear and affirm that this work is solely my own, and not the work 
// or the derivative of the work of someone else, except as outlined in the 
// assignment instructions.
//********************************************************************************

package tictactoe;

import javafx.scene.control.Button;

/**
 *
 * @author Sebas
 */
public class TicTacButton extends Button {
    
    /**
     * The three possible values for any particular tic-tac-toe button. Can be
     * X, O, or blank.
     */
    public enum TicTacMove {
        BLANK, X, O
    }
    
    /**
     * Tracks whether this button has an X, O, or is blank.
     */
    public  TicTacMove buttonStatus;
    
    final private double btnSides;
    
    TicTacButton()
    {
        this.btnSides = 80.0;
        buttonStatus = TicTacMove.BLANK;
    }
    
    /**
     * Sets a button to a desired status
     * @param status TicTacMove: X, O, or blank.
     */
    public void setButtonStatus (TicTacMove status) {
        this.buttonStatus = status;
        if (buttonStatus == TicTacMove.BLANK) {    
            setText("");
        }
        else {
            setText(buttonStatus.toString());
        }
    }
    
    /**
     * Checks if the button is blank.
     * @return true if buttonStatus is blank.
     */
    public boolean isBlank() {
        return buttonStatus == TicTacMove.BLANK;
    }
    
    /**
     * Checks if the button is marked with an X
     * @return true if buttonStatus is X
     */
    public boolean isX() {
        return buttonStatus == TicTacMove.X;
    }
    
    /**
     * Checks if the button is marked with an O
     * @return true if buttonStatus is O
     */
    public boolean isO() {
        return buttonStatus == TicTacMove.O;
    }
    
    /**
     * The player's turn. Marks a button with an X if it is blank, then
     * checks if the player won, then triggers the computers turn.
     */
    public void Xgoes() {
        if (this.isBlank()) {
            buttonStatus = TicTacMove.X;
            setText(buttonStatus.toString());
            TicTacToe.winCon();
            TicTacToe.computerTurn();
        }
    }
    
    /**
     * The computer's turn. Marks the button with O if it is blank, then checks
     * if a player has won.
     */
    public void Ogoes() {
        if (this.isBlank()) {
            buttonStatus = TicTacMove.O;
            setText(buttonStatus.toString());
            TicTacToe.winCon();
        }
    }
    
    /**
     * Clears the button, returning it to a blank state.
     */
    public void clearButton() {
        buttonStatus = TicTacMove.BLANK;
        setText("");
    }
    
    @Override
    protected double computePrefHeight(double width)
    {
        return btnSides;
    }
    
    @Override
    protected double computePrefWidth(double height)
    {
        return btnSides;
    }
    
}
