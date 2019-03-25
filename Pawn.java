package Project3;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Assume all comments were done by XUE unless otherwise stated
public class Pawn extends ChessPiece {
    public boolean moved;

    /* Checks if this is Pawn's first move */
    private boolean firstMove;

    public Pawn(Player player, boolean firstMove) {
        super(player);
        this.firstMove = firstMove;
    }

    public String type() {
        return "Pawn";
    }

    public boolean hasMoved() {
        return moved;
    }

    public void setHasMoved(boolean setMoved) {
        moved = setMoved;
    }



    // determines if the move is valid for a pawn piece
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
                        if ((move.toRow == move.fromRow + 1) || (move.toRow == move.fromRow + 2)
                                && board[move.toRow][move.toColumn] == null) {
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
                    if (move.toRow == 2 && move.fromColumn + 1 < 8 && move.toColumn == move.fromColumn + 1) {
                        if (board[3][move.toColumn] != null && board[3][move.toColumn].hasMoved())
                            valid = true;
                    }
                    if (move.toRow == 2 && move.fromColumn - 1 >= 0 && move.toColumn == move.fromColumn - 1) {
                        if (board[3][move.toColumn] != null && board[3][move.toColumn].hasMoved())
                            valid = true;
                    }
                }
            }
            if (player().equals(Player.BLACK)) {
                if (move.fromRow == 4 && board[move.fromRow][move.fromColumn] != null) {
                    if (move.toRow == 5 && move.fromColumn + 1 < 8 && move.toColumn == move.fromColumn + 1) {
                        if (board[4][move.toColumn] != null && board[4][move.toColumn].hasMoved())
                            valid = true;
                    }
                    if (move.toRow == 5 && move.fromColumn - 1 >= 0 && move.toColumn == move.fromColumn - 1) {
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

    private boolean ifPromote(Move move){
        if (player() == Player.WHITE && move.toRow == 0)
            return true;
        else if (player() == Player.BLACK && move.toRow == 7)
            return true;

        return false;
    }

    private void toPromote(Move move, IChessPiece[][] board){
        String[] choices = { "Queen", "Bishop", "Knight", "Rook"};
        String command = (String) JOptionPane.showInputDialog(null, "Choose now...",
                "Promoting the Pawn", JOptionPane.QUESTION_MESSAGE, null,
                choices, // Array of choices
                choices[0]); // Initial choice

        board[move.toRow][move.toColumn] = null;
        System.out.println(command);
        if (command.equals(choices[0])) {
            board[move.toRow][move.toColumn] = null;
            board[move.toRow][move.toColumn] = new Queen(player());
        } else if (command.equals("Bishop")) {
            board[move.toRow][move.toColumn] = new Bishop(player());
        }
        else if (command.equals("Knight")) {
            board[move.toRow][move.toColumn] = new Knight(player());
        }
        else if (command.equals("Rook")) {
            board[move.toRow][move.toColumn] = new Rook(player());
        }

    }

}

