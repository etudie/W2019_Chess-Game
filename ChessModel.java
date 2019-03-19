package Project3;

public class ChessModel implements IChessModel {
    private IChessPiece[][] board; // ask about, probs shouldn't be static
    private Player player;

    // declare other instance variables as needed

    public ChessModel() {
        board = new IChessPiece[8][8];
        player = Player.WHITE;

        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight (Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);
        board[6][0] = new Pawn(Player.WHITE, true);
        board[6][1] = new Pawn(Player.WHITE, true);
        board[6][2] = new Pawn(Player.WHITE, true);
        board[6][3] = new Pawn(Player.WHITE, true);
        board[6][4] = new Pawn(Player.WHITE, true);
        board[6][5] = new Pawn(Player.WHITE, true);
        board[6][6] = new Pawn(Player.WHITE, true);
        board[6][7] = new Pawn(Player.WHITE, true);

        board[0][0] = new Rook(Player.BLACK);
        board[0][1] = new Knight(Player.BLACK);
        board[0][2] = new Bishop(Player.BLACK);
        board[0][3] = new Queen(Player.BLACK);
        board[0][4] = new King(Player.BLACK);
        board[0][5] = new Bishop(Player.BLACK);
        board[0][6] = new Knight (Player.BLACK);
        board[0][7] = new Rook(Player.BLACK);
        board[1][0] = new Pawn(Player.BLACK, true);
        board[1][1] = new Pawn(Player.BLACK, true);
        board[1][2] = new Pawn(Player.BLACK, true);
        board[1][3] = new Pawn(Player.BLACK, true);
        board[1][4] = new Pawn(Player.BLACK, true);
        board[1][5] = new Pawn(Player.BLACK, true);
        board[1][6] = new Pawn(Player.BLACK, true);
        board[1][7] = new Pawn(Player.BLACK, true);
    }

    public void newGame(){

        System.out.println("NEW GAME");
        player = Player.WHITE;
        for (int r = 0; r < 8; r++) {
            if(r == 1)
                for (int c = 0; c < 8; c++) {
                    setPiece(r, c, new Pawn(Player.BLACK, true));
                }
            if(r == 6)
                for (int c = 0; c < 8; c++) {
                    setPiece(r, c, new Pawn(Player.WHITE, true));
                }
            if(r < 6 && r >1)
                for (int c = 0; c < 8; c++) {
                    setPiece(r, c, null);
                }
        }
        setPiece(7, 0, new Rook(Player.WHITE));
        setPiece(7, 1, new Knight(Player.WHITE));
        setPiece(7, 2, new Bishop(Player.WHITE));
        setPiece(7, 3, new Queen(Player.WHITE));
        setPiece(7, 4, new King(Player.WHITE));
        setPiece(7, 5, new Bishop(Player.WHITE));
        setPiece(7, 6, new Knight (Player.WHITE));
        setPiece(7, 7, new Rook (Player.WHITE));

        setPiece(0, 0, new Rook(Player.BLACK));
        setPiece(0, 1, new Knight(Player.BLACK));
        setPiece(0, 2, new Bishop(Player.BLACK));
        setPiece(0, 3, new Queen(Player.BLACK));
        setPiece(0, 4, new King(Player.BLACK));
        setPiece(0, 5, new Bishop(Player.BLACK));
        setPiece(0, 6, new Knight (Player.BLACK));
        setPiece(0, 7, new Rook (Player.BLACK));


    }

    public boolean isComplete() {
        boolean valid = false;
        if (inCheck(currentPlayer())) {
            valid = true;
        }

        return valid;
    }

    public boolean isValidMove(Move move) {

        if (board[move.fromRow][move.fromColumn] != null)
            if ((board[move.fromRow][move.fromColumn].player().equals(currentPlayer()))
                    && board[move.fromRow][move.fromColumn].isValidMove(move, board))
                return true;

        return false;
    }

    public void move(Move move) {
        board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
        board[move.fromRow][move.fromColumn] = null;
    }

    public boolean inCheck(Player p) {
        boolean valid = false;
        int kingRow = 0;
        int kingCol = 0;

        for (int r = 0; r < numRows(); r++) {
            for (int c = 0; c < numColumns(); c++) {
                if (board[r][c] != null && board[r][c].type().equals("King")
                        && board[r][c].player().equals(p)) {
                    kingRow = r;
                    kingCol = c;
                    break;
                }
            }
        }
        if (checkForPawn(p, kingRow, kingCol))
            valid = true;
        if (checkForKnight(p, kingRow, kingCol))
            valid = true;
        if (checkForRook(p, kingRow, kingCol))
            valid = true;
        if (checkForBishop(p, kingRow, kingCol))
            valid = true;

        return valid;
    }

    private boolean checkForPawn(Player p, int row, int col) {

        if (p.equals(Player.WHITE)) {
            if (row-1 >= 0 && col-1 >= 0 && board[row-1][col-1] != null
             && board[row-1][col-1].type().equals("Pawn") &&
                    board[row-1][col-1].player().equals(Player.BLACK)) {
                return true;
            }
            else if (row-1 >= 0 && board[row-1][col] != null && board[row-1][col].type().equals("Pawn")
            && board[row-1][col].player().equals(Player.BLACK))
                return true;
            else return row - 1 >= 0 && col + 1 < numColumns() && board[row - 1][col + 1] != null
                        && board[row - 1][col + 1].type().equals("Pawn") && board[row - 1][col + 1].player().equals(Player.BLACK);
        }

        if (p.equals(Player.BLACK)) {
            if (row+1 < numRows() && col-1 >= 0 && board[row+1][col-1] != null
                    && board[row+1][col-1].type().equals("Pawn") &&
                    board[row+1][col-1].player().equals(Player.WHITE)) {
                return true;
            }
            else if (row+1 >= 0 && board[row+1][col] != null && board[row+1][col].type().equals("Pawn")
                    && board[row+1][col].player().equals(Player.WHITE))
                return true;
            else return row + 1 >= 0 && col + 1 < numColumns() && board[row + 1][col + 1] != null
                        && board[row + 1][col + 1].type().equals("Pawn") && board[row + 1][col + 1].player().equals(Player.WHITE);
        }

        return false;
    }

    private boolean checkForKnight(Player p, int row, int col) {
        if (row-2 >= 0 && col-1 >= 0 && board[row-2][col-1] != null &&
                board[row-2][col-1].type().equals("Knight") &&
                !board[row-2][col-1].player().equals(p)) {
            return true;
        }
        else if (row-2 >= 0 && col+1 < numColumns() && board[row-2][col+1] != null
                && board[row-2][col+1].type().equals("Knight") &&
                !board[row-2][col+1].player().equals(p)) {
            return true;
        }
        else if (row-1 >= 0 && col-2 >= 0 && board[row-1][col-2] != null
                && board[row-1][col-2].type().equals("Knight")
                && !board[row-1][col-2].player().equals(p)) {
            return true;
        }
        else if (row-1 >= 0 && col+2 < numColumns() && board[row-1][col+2] != null
                && board[row-1][col+2].type().equals("Knight")
                && !board[row-1][col+2].player().equals(p)) {
            return true;
        }
        else if (row+2 < numRows() && col-1 >= 0 && board[row+2][col-1] != null &&
                board[row+2][col-1].type().equals("Knight") &&
                !board[row+2][col-1].player().equals(p)) {
            return true;
        }
        else if (row+2 < numRows() && col+1 < numColumns() && board[row+2][col+1] != null
                && board[row+2][col+1].type().equals("Knight") &&
                !board[row+2][col+1].player().equals(p)) {
            return true;
        }
        else if (row+1 < numRows() && col-2 >= 0 && board[row+1][col-2] != null
                && board[row+1][col-2].type().equals("Knight")
                && !board[row+1][col-2].player().equals(p)) {
            return true;
        }
        else return row + 1 < numRows() && col + 2 < numColumns()
                    && board[row + 1][col + 2] != null
                    && board[row + 1][col + 2].type().equals("Knight")
                    && !board[row + 1][col + 2].player().equals(p);


    }

    private boolean checkForRook(Player p, int row, int col) {
        int origRow = row;
        int origCol = col;

        while(row+1 < numRows() && board[row+1][col] == null)
            row++;
        if (row+1 < numRows() && (board[row+1][col].type().equals("Rook")
                || board[row+1][col].type().equals("Queen"))
                && !board[row+1][col].player().equals(p))
            return true;

        row = origRow;

        while (row-1 >= 0 && board[row-1][col] == null)
            row--;
        if (row-1 >= 0 && (board[row-1][col].type().equals("Rook")
                || board[row-1][col].type().equals("Queen"))
                && !board[row-1][col].player().equals(p))
            return true;

        row = origRow;

        while (col+1 < numColumns() && board[row][col+1] == null)
            col++;
        if (col+1 < numColumns() && (board[row][col+1].type().equals("Rook")
                || board[row][col+1].type().equals("Queen"))
                && !board[row][col+1].player().equals(p))
            return true;

        col = origCol;

        while (col-1 >= 0 && board[row][col-1] == null)
            col--;
        if (col-1 >= 0 && (board[row][col-1].type().equals("Rook")
                || board[row][col-1].type().equals("Queen"))
                && !board[row][col-1].player().equals(p))
            return true;

        return false;
    }

    private boolean checkForBishop(Player p, int row, int col) {
        int origRow = row;
        int origCol = col;

        while (row+1 < numRows() && col+1 < numColumns() && board[row+1][col+1] == null) {
            row++;
            col++;
        }
        if (row+1 < numRows() && col+1 < numColumns() && board[row+1][col+1] != null
                && (board[row+1][col+1].type().equals("Bishop")
                || board[row+1][col+1].type().equals("Queen"))
                && !board[row+1][col+1].player().equals(p))
            return true;

        row = origRow;
        col = origCol;

        while (row-1 >= 0 && col-1 >= 0 && board[row-1][col-1] == null) {
            row--;
            col--;
        }
        if (row-1 >= 0 && col-1 >= 0 && board[row-1][col-1] != null
                && !board[row-1][col-1].player().equals(p)
                && (board[row-1][col-1].type().equals("Bishop")
                || board[row-1][col-1].type().equals("Queen")))
            return true;

        row = origRow;
        col = origCol;

        while (row+1 < numRows() && col-1 >= 0 && board[row+1][col-1] == null) {
            row++;
            col--;
        }
        if (row+1 < numRows() && col-1 >= 0 && board[row+1][col-1] != null
                && !board[row+1][col-1].player().equals(p)
                && (board[row+1][col-1].type().equals("Bishop")
                || board[row+1][col-1].type().equals("Queen")))
            return true;

        row = origRow;
        col = origCol;

        while (row-1 >= 0 && col+1 < numColumns() && board[row-1][col+1] == null) {
            row--;
            col++;
        }
        if (row-1 >= 0 && col+1 < numColumns() && board[row-1][col+1] != null
                && !board[row-1][col+1].player().equals(p)
                && (board[row-1][col+1].type().equals("Bishop")
                || board[row-1][col+1].type().equals("Queen")))
            return true;

        return false;
    }

    public Player currentPlayer() {
        return player;
    }

    public int numRows() {
        return 8;
    }

    public int numColumns() {
        return 8;
    }

    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }

    public void setNextPlayer() {
        player = player.next();
    }

    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

    public void AI() {
        /*
         * Write a simple AI set of rules in the following order.
         * a. Check to see if you are in check.
         * 		i. If so, get out of check by moving the king or placing a piece to block the check
         *
         * b. Attempt to put opponent into check (or checkmate).
         * 		i. Attempt to put opponent into check without losing your piece
         *		ii. Perhaps you have won the game.
         *
         *c. Determine if any of your pieces are in danger,
         *		i. Move them if you can.
         *		ii. Attempt to protect that piece.
         *
         *d. Move a piece (pawns first) forward toward opponent king
         *		i. check to see if that piece is in danger of being removed, if so, move a different piece.
         */

    }
}
