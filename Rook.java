package ChessVersion4;

/**********************************************************************
 * Class that handles game manipulation of rook
 *
 * @author Amela Aganovic, Emily Linderman, Xue Hua
 * @version Winter 2019
 *********************************************************************/
public class Rook extends ChessPiece {

    /** Checks if rook has moved */
    public boolean moved;

    /** variable that indicates occupied space's player */
    private int occupiedPlayer;

    /*****************************************************************
     * Constructor for the rook piece
     * @param player the current player
     *****************************************************************/
    public Rook(Player player) {

        super(player);
        moved = false;

    }

    /*****************************************************************
     * Returns the type of chess piece the piece is as a String
     * @return "rook" 
     *****************************************************************/
    public String type() {

        return "Rook";

    }

    /*****************************************************************
     * Returns if rook has moved
     * @return true if piece has moved 
     *         false if piece has not moved
     *****************************************************************/
    public boolean hasMoved() {
        return moved;
    }

    /*****************************************************************
     * Helper method to set move state of piece
     * @param setMoved boolean argument to set state of piece
     *****************************************************************/
    public void setHasMoved(boolean setMoved) {
        moved = setMoved;
    }

    /*****************************************************************
     * Determining valid moves for the selected piece
     * @param move the move
     * @param board the chess board
     * @return true if move is valid
     *         false if move is invalid
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;

        if (super.isValidMove(move, board)) {

            occupiedPlayer = 0;

            if (Math.abs(move.toRow - move.fromRow) > 0 && move.toColumn - move.fromColumn == 0
                    && (!isOccupied(move, board) || occupiedPlayer == 1)) {
                valid = true;
                moved = true;
            }
            if (Math.abs(move.toColumn - move.fromColumn) > 0 && move.toRow - move.fromRow == 0
                    && (!isOccupied(move, board) || occupiedPlayer == 1)) {
                valid = true;
                moved = true;
            }

        }

        return valid;

    }

    /*****************************************************************
     * Checker method if space is occupied
     * @param move the move
     * @param board the chess board
     * @return true if space is occupied
     *         false if space is unoccupied
     *****************************************************************/
    private boolean isOccupied(Move move, IChessPiece[][] board) {
        boolean occupied = false;
        int occupation = 0;

        if (move.toRow == move.fromRow) {
            if (move.toColumn < move.fromColumn) {
                for (int c = move.fromColumn - 1; c >= move.toColumn; c--) {
                    if (board[move.toRow][c] != null) {
                        occupied = true;
                        occupation++;
                    }
                }
            } else {
                for (int c = move.fromColumn + 1; c <= move.toColumn; c++) {
                    if (board[move.toRow][c] != null) {
                        occupied = true;
                        occupation++;
                    }
                }
            }
        }

        if (move.toColumn == move.fromColumn) {
            if (move.toRow < move.fromRow) {
                for (int r = move.fromRow - 1; r >= move.toRow; r--) {
                    if (board[r][move.toColumn] != null) {
                        occupied = true;
                        occupation++;
                    }
                }
            } else {
                for (int r = move.fromRow + 1; r <= move.toRow; r++) {
                    if (board[r][move.toColumn] != null) {
                        occupied = true;
                        occupation++;
                    }
                }
            }
        }

        if (board[move.toRow][move.toColumn] != null && board[move.toRow]
                [move.toColumn].player().equals(player().next()) && occupation == 1)
            occupiedPlayer = 1;

        return occupied;
    }

}
