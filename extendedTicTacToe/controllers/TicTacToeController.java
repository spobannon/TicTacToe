// Shane O'Bannon
package cpsc2150.extendedTicTacToe.controllers;

import cpsc2150.extendedTicTacToe.models.*;
import cpsc2150.extendedTicTacToe.views.*;

/**
 * <p>
 * The {@link TicTacToeController} class will handle communication between our {@link TicTacToeView}
 * and our model ({@link IGameBoard} and {@link BoardPosition})
 * </p>
 *
 * <p>
 * This is where you will write code
 * <p>
 *
 * You will need to include your {@link BoardPosition} class, the {@link IGameBoard} interface
 * and both of the {@link IGameBoard} implementations from Project 4.
 * If your code was correct you will not need to make any changes to your {@link IGameBoard} implementation class.
 *
 * @version 2.0
 */
public class TicTacToeController {

    /**
     * <p>
     * The current game that is being played
     * </p>
     */
    private IGameBoard curGame;

    /**
     * <p>
     * The screen that provides our view
     * </p>
     */
    private TicTacToeView screen;

    /**
     * <p>
     * Constant for the maximum number of players.
     * </p>
     */
    public static final int MAX_PLAYERS = 10;

    /**
     * <p>
     * The number of players for this game. Note that our player tokens are hard coded.
     * </p>
     */
    private int numPlayers;

    private char[] players = {'X', 'O', 'T', 'G', 'P', 'K', 'L', 'V', 'I', 'E'};

    private int playerIndex = 0;

    private boolean isGameOver = false;

    /**
     * <p>
     * This creates a controller for running the Extended TicTacToe game
     * </p>
     *
     * @param model
     *      The board implementation
     * @param view
     *      The screen that is shown
     * @param np
     *      The number of players for this game.
     *
     * @post [ the controller will respond to actions on the view using the model. ]
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;

        // Some code is needed here.
    }

    /**
     * <p>
     * This processes a button click from the view.
     * </p>
     *
     * @param row
     *      The row of the activated button
     * @param col
     *      The column of the activated button
     *
     * @post [ will allow the player to place a marker in the position if it is a valid space, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button ]
     */
    public void processButtonClick(int row, int col) {
        // Your code goes here.

        //Check if game is over
        if(isGameOver) {
            newGame();
        }
        else {
            BoardPosition pos = new BoardPosition(row, col);
            // Place marker and check results if selection is valid
            if (curGame.checkSpace(pos)) {
                curGame.placeMarker(pos, players[playerIndex]);
                screen.setMarker(row, col, players[playerIndex]);

                // Check if game is over based on last move
                if (curGame.checkForWinner(pos)) {
                    screen.setMessage("Player " + players[playerIndex] + " wins!\nPress any button to play again.");
                    isGameOver = true;
                } else if (curGame.checkForDraw()) {
                    screen.setMessage("Draw. Press any button to play again.");
                    isGameOver = true;
                } else {
                    changePlayer();
                    screen.setMessage("It is " + players[playerIndex] + "\'s turn. ");
                }
            } else {
                screen.setMessage("Invalid space. It is still " + players[playerIndex] + "\'s turn. ");
            }
        }
    }

    /**
     * <p>
     * This method will start a new game by returning to the setup screen and controller
     * </p>
     *
     * @post [ a new game gets started ]
     */
    private void newGame() {
        //close the current screen
        screen.dispose();

        //start back at the set up menu
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
        isGameOver = false;
    }

    private void changePlayer() {
        playerIndex = (playerIndex + 1) % numPlayers;
    }
}