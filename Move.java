package ChessVersion4;

/**********************************************************************
 * Class that stores current piece movements throughout game.
 *
 * @author Amela Aganovic, Emily Linderman, Xue Hua
 * @version Winter 2019
 *********************************************************************/
public class Move {

    /** variables that store current and advancing co-ordinates */
    public int fromRow, fromColumn, toRow, toColumn;

    /**********************************************************************
     * Constructor for Move
     *********************************************************************/
    public Move() {
    }

    /**********************************************************************
     * Constructor for Move
     *
     * @param fromRow current row co-ords
     * @param fromColumn current col co-ords
     * @param toRow     advancing row co-ords
     * @param toColumn  advancing col co-ords
     *********************************************************************/
    public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;
    }

    /**********************************************************************
     * Helper method to return movements.
     *********************************************************************/
    @Override
    public String toString() {
        return "Move [fromRow=" + fromRow + ", fromColumn=" + fromColumn + ", toRow=" + toRow + ", toColumn=" + toColumn
                + "]";
    }


}
