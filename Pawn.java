package ChessVersion4;

/**********************************************************************
 * Class that handles game manipulation of pawn
 *
 * @author Amela Aganovic, Emily Linderman, Xue Hua
 * @version Winter 2019
 *********************************************************************/

// Assume all comments were done by XUE unless otherwise stated
public class Pawn extends ChessPiece {

    /** Checks if pawn has moved */
    public boolean moved;

    /* Checks if this is Pawn's first move */
    private boolean firstMove;

    /*****************************************************************
     * Constructor for the pawn piece
     * @param player the current player
     *****************************************************************/
    public Pawn(Player player, boolean firstMove) {
        super(player);
        this.firstMove = firstMove;
    }

    /*****************************************************************
     * Returns the type of chess piece the piece is as a String
     * @return "pawn"
     *****************************************************************/
    public String type() {
        return "Pawn";
    }

    /*****************************************************************
     * Returns if pawn has moved
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


            //  MOVING FORWARD //
            if (move.toColumn == move.fromColumn) {
                if (player() == Player.WHITE) {
                    if (firstMove) {    // move 2 spaces if want to
                        if (((move.toRow == move.fromRow - 1) || (move.toRow == move.fromRow - 2))
                                && board[move.toRow][move.toColumn] == null) {
                            valid = true;
                            if ((move.toRow == move.fromRow - 2))
                                board[move.fromRow][move.fromColumn].setHasMoved(true);
                            firstMove = false;      // disables the flag for remaining of the game
                        }

                    } else {        // move once each time
                        if (move.toRow == move.fromRow - 1) {
                            if (board[move.toRow][move.toColumn] == null) {
                                valid = true;
                            }
                        }
                    }
                }

                if (player() == Player.BLACK)
                    if (firstMove) {
                        if ((move.toRow == move.fromRow + 1) || (move.toRow == move.fromRow + 2)) {
                            valid = true;
                            if ((move.toRow == move.fromRow + 2))
                                board[move.fromRow][move.fromColumn].setHasMoved(true);
                            firstMove = false;
                        }

                    } else {
                        if (move.toRow == move.fromRow + 1) {
                            if (board[move.toRow][move.toColumn] == null) {
                                valid = true;

                            }
                        }
                    }
            }
            if (player().equals(Player.WHITE)) {
                if (move.fromRow == 3 && board[move.fromRow][move.fromColumn] != null) {
                    if (move.fromColumn + 1 < 8 && move.toColumn == move.fromColumn + 1) {
                        if (board[3][move.toColumn] != null && board[3][move.toColumn].hasMoved())
                            valid = true;
                    }
                    if (move.fromColumn - 1 >= 0 && move.toColumn == move.fromColumn - 1) {
                        if (board[3][move.toColumn] != null && board[3][move.toColumn].hasMoved())
                            valid = true;
                    }
                }
            }
            if (player().equals(Player.BLACK)) {
                if (move.fromRow == 4 && board[move.fromRow][move.fromColumn] != null) {
                    if (move.fromColumn + 1 < 8 && move.toColumn == move.fromColumn + 1) {
                        if (board[4][move.toColumn] != null && board[4][move.toColumn].hasMoved())
                            valid = true;
                    }
                    if (move.fromColumn - 1 >= 0 && move.toColumn == move.fromColumn - 1) {
                        if (board[4][move.toColumn] != null && board[4][move.toColumn].hasMoved())
                            valid = true;
                    }
                }
            }

            // MOVING TO CAPTURE //
            if (ifCapture(move, board))
                valid = true;

        }

        return valid;
    }

    /*****************************************************************
     determines if the move is valid for a pawn piece
     @param move the length of each side in pixels
     @param board
     *****************************************************************/
    private boolean ifCapture(Move move, IChessPiece[][] board) {
        if (player() == Player.BLACK) {
            if (move.toRow == move.fromRow + 1)
                if ((move.toColumn == move.fromColumn - 1) || (move.toColumn == move.fromColumn + 1)) {
                    if (board[move.toRow][move.toColumn] != null &&
                            board[move.toRow][move.toColumn].player() == Player.WHITE) {
                        return true;
                    }
                }
        }
        if (player() == Player.WHITE) {
            if (move.toRow == move.fromRow - 1)
                if ((move.toColumn == move.fromColumn - 1) || (move.toColumn == move.fromColumn + 1)) {
                    if (board[move.toRow][move.toColumn] != null &&
                            board[move.toRow][move.toColumn].player() == Player.BLACK) {
                        return true;
                    }
                }
        }
        return false;
    }


}




