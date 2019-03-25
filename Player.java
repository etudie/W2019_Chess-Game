package ChessVersion4;
/**********************************************************************
 * A graphical representation of a chess game.
 *
 * @author Amela Aganovic, Emily Linderman, Xue Hua
 * @version Winter 2019
 *********************************************************************/
public enum Player {
    BLACK, WHITE;

    /**
     * Return the {@code Player} whose turn is next.
     *
     * @return the {@code Player} whose turn is next
     */
    public Player next() {
        if (this == BLACK)
            return WHITE;
        else
            return BLACK;

        //	return this == BLACK ? WHITE : BLACK;
    }
}