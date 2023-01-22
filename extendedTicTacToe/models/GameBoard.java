// Shane O'Bannon

package cpsc2150.extendedTicTacToe.models;


/**
 * This is a simple class to represent a TicTacToe game board
 *
 * @invariant NUM_ROWS = #NUM_ROWS AND NUM_COLUMNS = #NUM_COLUMNS AND numToWin > 0
 *            AND [each board position contains " " OR "X" OR "O"]
 *
 * @correspondence number_of_rows = NUM_ROWS AND
 *                 number_of_columns = NUM_COLUMNS AND
 *                 self = board[0...NUM_ROWS-1][0...NUM_COLS-1]
 *
 */
public class GameBoard extends AbsGameBoard implements IGameBoard{

    private int num_rows;

    private int num_cols;

    private int num_to_win;

    private char[][] board;

    /**
     * This constructor makes a new empty game board.
     *
     * @post board = char[5][8] AND board is fully populated with " "
     */
    public GameBoard(int r, int c, int ntw){
        num_rows = r;
        num_cols = c;
        num_to_win = ntw;
        board = new char[getNumRows()][getNumColumns()];
        for(int i=0;i<getNumRows();i++) {
            for(int j=0;j<getNumColumns();j++) {
                board[i][j] = ' ';
            }
        }
    }

    public int getNumRows() {
        return num_rows;
    }

    public int getNumColumns() {
        return num_cols;
    }

    public int getNumToWin() {
        return num_to_win;
    }

    public void placeMarker(BoardPosition marker, char player) {
        int r = marker.getRow();
        int c = marker.getColumn();

        if(checkSpace(marker)) {
            board[r][c] = player;
        }
    }

    public char whatsAtPos(BoardPosition pos) {
        int r = pos.getRow();
        int c = pos.getColumn();

        // Check if pos is on the board
        if(r>=0 && r<getNumRows() && c>=0 && c<getNumColumns()) {
            return board[r][c];
        }
        return INVALID_SPACE;
    }


}
