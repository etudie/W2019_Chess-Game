package ChessVersion4;

/**********************************************************************
 * Class that handles game manipulation of bishop
 *
 * @author Amela Aganovic, Emily Linderman, Xue Hua
 * @version Winter 2019
 *********************************************************************/
public class Bishop extends ChessPiece {

    /** Checks if bishop has moved */
    public boolean moved;

    /*****************************************************************
     * Constructor for the bishop piece
     * @param player the current player
     *****************************************************************/
    public Bishop(Player player) {
        super(player);
    }

    /*****************************************************************
     * Returns the type of chess piece the piece is as a String
     * @return "Bishop"
     *****************************************************************/
    public String type() {
        return "Bishop";
    }


    /*****************************************************************
     * Returns if bishop has moved
     * @return true if piece has moved
     *         false if piece has not moved
     *****************************************************************/
    public boolean hasMoved() {
        return false;
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

            for (int x = 1; x < 8; x++) {
                //if (move.fromRow == move.toRow + x || move.fromRow == move.toRow - x)
                if (Math.abs(move.fromRow - move.toRow) == x)
                    if (Math.abs(move.fromColumn - move.toColumn) == x) {
                        if (!isOccupied(move, board)) {
                            valid = true;
                        }
                    }
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
        int row = move.fromRow;
        int col = move.fromColumn;
        int origRow = row;
        int origCol = col;

        if (move.toRow < origRow && move.toColumn < origCol) {
            while (row - 1 > move.toRow && col - 1 > move.toColumn) {
                row--;
                col--;
                if (board[row][col] != null)
                    return true;
            }
        }

        if (move.toRow < origRow && move.toColumn > origCol) {
            while (row - 1 > move.toRow && col + 1 < move.toColumn) {
                row--;
                col++;
                if (board[row][col] != null)
                    return true;
            }
        }

        if (move.toRow > origRow && move.toColumn < origCol) {
            while (row + 1 < move.toRow && col - 1 > move.toColumn) {
                row++;
                col--;
                if (board[row][col] != null)
                    return true;
            }
        }


        if (move.toRow > origRow && move.toColumn > origCol) {
            while (row + 1 < move.toRow && col + 1 < move.toColumn) {
                row++;
                col++;
                if (board[row][col] != null)
                    return true;
            }
        }

        return false;
    }

}