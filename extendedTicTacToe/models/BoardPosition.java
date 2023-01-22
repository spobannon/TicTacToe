// Shane O'Bannon

package cpsc2150.extendedTicTacToe.models;

/**
 * A simple class to represent a position on a tic-tac-toe board
 *
 * @invariant row >= 0 AND column >= 0
 */
public class BoardPosition {

    private int row;
    private int column;

    /**
     * This constructor makes a new board position.
     *
     * @param r row of board position
     * @param c column of board position
     *
     * @pre r >= 0 AND c >= 0
     * @post row = r AND column = c
     */
    public BoardPosition(int r, int c) {
        row = r;
        column = c;
    }

    /**
     * Returns an integer equal the row number of the BoardPosition instance
     *
     * @return integer representation of row
     * @post returns int getRow >= 0 AND row = #row AND column = #column
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns an integer equal to the column number of the BoardPosition instance
     *
     * @return integer representation of column
     * @post returns int getColumn >= 0 AND column = #column AND row = #row
     */
    public int getColumn() {
        return column;
    }

    /**
     * Compares two objects to see if they are equal board positions
     *
     * @param obj object to compare with
     *
     * @return true if obj is the same as this BoardPosition instance, false if not
     * @pre obj is not null
     * @post [equals returns true if this.row = obj.row AND this.column = obj.column] AND row = #row AND column = #column AND obj = #obj
     */
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof BoardPosition)) {
            return false;
        }
        if(((BoardPosition) obj).getRow() == row && ((BoardPosition) obj).getColumn() == column) {
            return true;
        }
        return false;
    }

    /**
     * Provides a string representation of the board position
     *
     * @return String representation of board position
     * @post toString = "<row>,<column>" AND row = #row AND column = #column
     */
    @Override
    public String toString() {
        return row + "," + column;

    }
}