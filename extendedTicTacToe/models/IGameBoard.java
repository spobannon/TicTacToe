// Shane O'Bannon

package cpsc2150.extendedTicTacToe.models;

/**
 * IGameBoard is abstractly a 2 dimensional grid of characters representing a TicTacToe board.
 * Indexing starts at 0.
 *
 * Initialization ensures:
 *      GameBoard contains only blank characters and is number_of_ros X number_of_columns
 *
 * Defines: number_of_rows: R
 *          number_of_columns: C
 *          number_to_win: N
 *
 * Constraints: number_of_rows > 0
 *              number_of_columns > 0
 */
public interface IGameBoard {

    public static final int MAX_SIZE = 100;

    public static final int MIN_SIZE = 3;


    public static final int MAX_TO_WIN = 25;

    public static final int MIN_TO_WIN = 3;

    public static final char INVALID_SPACE = 'z';

    /**
     * Returns the number of rows in the board
     *
     * @return the number of rows in the board
     * @post getNumRows = number_of_rows AND self = #self
     */
    public int getNumRows();

    /**
     * Returns the number of columns in the board
     *
     * @return the number of columns in the board
     * @post getNumColumns = number_of_columns AND self = #self
     */
    public int getNumColumns();

    /**
     * Returns the number of consecutive markers required to win the game
     *
     * @return the number of consecutive markers required to win the game
     * @post getNumToWin = number_to_win AND self = #self
     */
    public int getNumToWin();

    /**
     * Checks if a space on the gameboard is available
     *
     * @param pos the position of the space to check
     * @return true if the position on the board is available, false if not or if position is out of bounds
     * @pre pos is of type BoardPosition
     * @post checkSpace = True if pos is empty on the board, false otherwise AND board = #board AND
     *       NUM_ROWS = #NUM_ROWS AND NUM_COLS = #NUM_COLS
     */
    default boolean checkSpace(BoardPosition pos) {
        int r = pos.getRow();
        int c = pos.getColumn();

        if(r>=0 && r<getNumRows() && c>=0 && c<getNumColumns()) {
            if(whatsAtPos(pos) == ' ') {
                return true;
            }
        }
        return false;
    }

    /**
     * Places a player's symbol at the given BoardPosition if it is empty and valid
     *
     * @param marker the BoardPosition to place the marker
     * @param player the symbol of the player placing the marker
     * @pre (player = "X" OR player = "O") AND [marker is a valid BoardPosition]
     * @post board = #board, except specified character is at specified location
     *       AND NUM_ROWS = #NUM_ROWS AND NUM_COLS = #NUM_COLS
     */
    public void placeMarker(BoardPosition marker, char player);

    /**
     * Checks to see if the last marker placed results in a winner
     *
     * @param lastPos the position of the last marker placed
     * @return true if the last marker placed results in a winner, false otherwise
     * @pre [lastPos is the BoardPosition of the latest play]
     * @post [checkForWinner = true if the placed marker results in the max # of consecutive markers to make up a wim]
     *       AND board = #board AND NUM_ROWS = #NUM_ROWS AND
     *       NUM_COLS = #NUM_COLS
     */
    default boolean checkForWinner(BoardPosition lastPos) {
        char player = whatsAtPos(lastPos);
        if(checkDiagonalWin(lastPos, player) || checkHorizontalWin(lastPos, player) || checkVerticalWin(lastPos, player)) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the current board is a draw
     *
     * @return true if the game is a draw, false otherwise
     * @pre checkForWinner = false
     * @post checkForDraw = true if the game is a draw AND board = #board AND NUM_ROWS = #NUM_ROWS AND
     *       NUM_COLS = #NUM_COLS
     */
    default boolean checkForDraw() {
        BoardPosition pos;
        for(int i=0;i<getNumRows();i++) {
            for(int j=0;j<getNumColumns();j++) {
                pos = new BoardPosition(i, j);
                if(checkSpace(pos)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the last marker placed results in a horizontal win for the player specified
     *
     * @param lastPos the position of the last marker placed
     * @param player the symbol of the player who placed the last marker
     * @return true if the last marker placed results in a horizontal win for specified player
     * @pre [lastPos is a valid BoardPosition] AND (player = "X" OR player = "O")
     * @post checkHorizontalWin = true if the specified player wins horizontally AND
     *       board = #board AND NUM_ROWS = #NUM_ROWS AND NUM_COLS = #NUM_COLS
     */

    default boolean checkHorizontalWin(BoardPosition lastPos, char player) {
        BoardPosition pos = lastPos;
        int count = 0;
        int row = lastPos.getRow();
        int col = lastPos.getColumn();

        // Count # of consecutive symbols to the left
        while(col>=0 && isPlayerAtPos(pos, player)) {
            count++;
            col--;
            pos = new BoardPosition(row, col);
        }

        // Count # of consecutive symbols to the right
        col = lastPos.getColumn() + 1;
        pos = new BoardPosition(row, col);
        while(col<getNumColumns() && isPlayerAtPos(pos, player)) {
            count++;
            col++;
            pos = new BoardPosition(row, col);
        }

        if(count>=getNumToWin()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the last marker placed results in a vertical win for the player specified
     *
     * @param lastPos the position of the last marker placed
     * @param player the symbol of the player who placed the last marker
     * @return true if the last marker placed results in a vertical win for specified player
     * @pre [lastPos is a valid BoardPosition] AND (player = "X" OR player = "O")
     * @post checkVerticalWin = true if the specified player wins vertically AND
     *       board = #board AND NUM_ROWS = #NUM_ROWS AND NUM_COLS = #NUM_COLS
     */

    default boolean checkVerticalWin(BoardPosition lastPos, char player) {
        BoardPosition pos = lastPos;
        int count = 0;
        int row = lastPos.getRow();
        int col = lastPos.getColumn();

        // Count # of consecutive symbols above
        while(row>=0 && isPlayerAtPos(pos, player)) {
            count++;
            row--;
            pos = new BoardPosition(row, col);
        }

        // Count # of consecutive symbols below
        row = lastPos.getRow() + 1;
        pos = new BoardPosition(row, col);
        while(row<getNumRows() && isPlayerAtPos(pos, player)) {
            count++;
            row++;
            pos = new BoardPosition(row, col);
        }

        if(count>=getNumToWin()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the last marker placed results in a diagonal win for the player specified
     *
     * @param lastPos the position of the last marker placed
     * @param player the symbol of the player who placed the last marker
     * @return true if the last marker placed results in a diagonal win for specified player
     * @pre [lastPos is a valid BoardPosition] AND (player = "X" OR player = "O")
     * @post checkDiagonalWin = true if the specified player wins diagonally AND
     *       board = #board AND NUM_ROWS = #NUM_ROWS AND NUM_COLS = #NUM_COLS
     */

    default boolean checkDiagonalWin(BoardPosition lastPos, char player) {
        int count = 1;
        int row = lastPos.getRow();
        int col = lastPos.getColumn();
        int i = 1;
        int j = 1;
        BoardPosition pos1 = lastPos;
        BoardPosition pos2 = lastPos;
        BoardPosition out_of_bounds = new BoardPosition(-1, -1);

        // check diagonal sloping down from left to right
        while(isPlayerAtPos(pos1, player) || isPlayerAtPos(pos2, player)) {
            if(row+i<getNumRows() && col+i<getNumColumns()) {
                pos1 = new BoardPosition(row+i, col+i);
            }
            else {
                pos1 = out_of_bounds;
            }
            if(col-j>=0 && row-j>=0) {
                pos2 = new BoardPosition(row-j, col-j);
            }
            else {
                pos2 = out_of_bounds;
            }
            if(isPlayerAtPos(pos1, player)) {
                count++;
                i++;
            }
            if(isPlayerAtPos(pos2, player)) {
                count++;
                j++;
            }
        }
        if(count >= getNumToWin()) {
            return true;
        }

        // reset variables to check other diagonal
        count = 1;
        row = lastPos.getRow();
        col = lastPos.getColumn();
        i = 1;
        j = 1;
        pos1 = lastPos;
        pos2 = lastPos;

        // check diagonal sloping up from left to right
        while(isPlayerAtPos(pos1, player) || isPlayerAtPos(pos2, player)) {
            if(row-i>=0 && col+i<getNumColumns()) {
                pos1 = new BoardPosition(row-i, col+i);
            }
            else {
                pos1 = out_of_bounds;
            }
            if(col-j>=0 && row+j<getNumRows()) {
                pos2 = new BoardPosition(row+j, col-j);
            }
            else {
                pos2 = out_of_bounds;
            }
            if(isPlayerAtPos(pos1, player)) {
                count++;
                i++;
            }
            if(isPlayerAtPos(pos2, player)) {
                count++;
                j++;
            }
        }
        if(count >= getNumToWin()) {
            return true;
        }
        return false;
    }

    /**
     * Checks what is at the specified position on the board
     *
     * @param pos position to check
     * @return character stored at specified position
     * @pre [pos is a valid BoardPosition]
     * @post whatAtPos = board[pos] AND board = #board AND NUM_ROWS = #NUM_ROWS AND NUM_COLS = #NUM_COLS
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * Checks if the specified player has a marker at the specified position
     *
     * @param pos position to check
     * @param player player symbol to check for
     * @return true if the specified player has a marker at the specified position
     * @pre (player = "X" OR player = "O") AND [pos is a valid BoardPosition]
     * @post isPlayerAtPos = true if specified player is at specified position AND board = #board AND
     *       NUM_ROWS = #NUM_ROWS AND NUM_COLS = #NUM_COLS
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player) {
        if(whatsAtPos(pos) == player) {
            return true;
        }
        return false;
    }
}


