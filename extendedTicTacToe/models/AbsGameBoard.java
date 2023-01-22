// Shane O'Bannon

package cpsc2150.extendedTicTacToe.models;

/**
 * This abstract class overrides the toString method for a game board
 */
public abstract class AbsGameBoard implements IGameBoard{

    /**
     * Provides a string representation of the game board
     *
     * @return String representation of game board
     * @post toString = [ String representation of the game board ] and self = #self
     */
    @Override
    public String toString() {
        String out = "   ";
        BoardPosition pos;
        int i = 0;
        int j = 0;

        // show column numbers above board
        for(i=0;i<getNumColumns();i++) {
            if(i>=10) {
                out += i + "|";
            }
            else {
                out += " " + i + "|";
            }
        }

        out += "\n";
        i = 0;

        // add board to out row by row
        for(i=0;i<getNumRows();i++) {
            if(i>=10) {
                out += i + "|";
            }
            else {
                out += " " + i + "|";
            }
            for(j=0;j<getNumColumns();j++) {
                pos = new BoardPosition(i, j);
                out += whatsAtPos(pos) + " " + "|";
            }
            out += "\n";
        }
        return out;
    }
}
