package ChessVersion4;

/**********************************************************************
 * Class that handles all chess pieces. Implements IChessPiece.
 *
 * @author Amela Aganovic, Emily Linderman, Xue Hua
 * @version Winter 2019
 *********************************************************************/
public abstract class ChessPiece implements IChessPiece {

    /** Variable that represents owner of chess piece*/
    private Player owner;

    /**********************************************************************
     * protected method that returns player of chess piece.
     *********************************************************************/
    protected ChessPiece(Player player) {
        this.owner = player;
    }

    /**********************************************************************
     * abstract method that should return type of piece
     *********************************************************************/
    public abstract String type();

    /**********************************************************************
     * Helper method that returns owner of piece.
     * @return owner owner of piece
     *********************************************************************/
    public Player player() {
        return owner;
    }

    /**********************************************************************
     * Abstract method that should verify if piece hasMoved. Useful
     * for pawn and castling.
     *********************************************************************/
    public abstract boolean hasMoved();

    /**********************************************************************
     * Abstract method that returns if piece has moved.
     * @return true if piece has moved
     *          false if piece has not moved
     *********************************************************************/
    public abstract void setHasMoved(boolean setMoved);

    /**********************************************************************
     * Checker method that verifies if manouver is valid. Includes universal
     * rules for all pieces.
     * @param   move location of move
     * @param board location of piece
     * @return true if move is valid
     *          false if move is invalid
     *********************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        if (move.toRow >= 8 || move.toColumn >= 8)
            return false;
        if (move.toRow == move.fromRow && move.toColumn == move.fromColumn)
            return false;
        if (player() == Player.WHITE) {
            if (board[move.toRow][move.toColumn] != null &&
                    !board[move.toRow][move.toColumn].player().equals(Player.BLACK))
                return false;
        }
        if (player() == Player.BLACK) {
            if (board[move.toRow][move.toColumn] != null &&
                    !board[move.toRow][move.toColumn].player().equals(Player.WHITE))
                return false;
        }
        return true;
    }
}
