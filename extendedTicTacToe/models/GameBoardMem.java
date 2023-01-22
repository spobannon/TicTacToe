// Shane O'Bannon

package cpsc2150.extendedTicTacToe.models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class GameBoardMem extends AbsGameBoard implements IGameBoard{

    private int num_rows;

    private int num_cols;

    private int num_to_win;

    private Map<Character, List<BoardPosition>> board;

    /**
     * This constructor makes a new empty game board.
     *
     * @param r The number of rows for the board
     * @param c The number of columns for the board
     * @param ntw The number needed in a row to win
     * @pre MIN_SIZE <= r <= MAX_SIZE AND MIN_SIZE <= c <= MAX_SIZE AND MIN_TO_WIN <= ntw <= MAX_TO_WIN
     * @post board = empty hashmap
     */
    public GameBoardMem(int r, int c, int ntw){
        num_rows = r;
        num_cols = c;
        num_to_win = ntw;
        board = new HashMap<>();
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
        List<BoardPosition> positions;

        if(checkSpace(marker)) {
            // add marker to existing key value pair
            if (board.containsKey(player)) {
                board.get(player).add(marker);
            }
            // create new key value pair
            else {
                positions = new ArrayList<BoardPosition>();
                positions.add(marker);
                board.put(player, positions);
            }
        }
    }

    public char whatsAtPos(BoardPosition pos) {
        List<BoardPosition> positions;

        // return arbitrary value if out of bounds
        if(pos.getRow() < 0 || pos.getColumn() < 0 || pos.getRow() >= getNumRows() || pos.getColumn() >= getNumColumns()) {
            return INVALID_SPACE;
        }

        // look for pos in map
        for(Map.Entry<Character, List<BoardPosition>> m: board.entrySet()) {
            positions = m.getValue();
            if(positions.contains(pos)) {
                return m.getKey();
            }
        }
        return ' ';
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if(board.containsKey(player) && board.get(player).contains(pos)) {
            return true;
        }
        return false;
    }
}
