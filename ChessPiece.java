package Project3;

public abstract class ChessPiece implements IChessPiece {

    private Player owner;

    protected ChessPiece(Player player) {
        this.owner = player;
    }

    public abstract String type();

    public Player player() {
        return owner;
    }

    // verify piece location
    //verify the piece at wanted location doesn't belong to same player
    public boolean isValidMove(Move move, IChessPiece[][] board) {
//        boolean valid = false;

        if (move.toRow >= 8 || move.toColumn >= 8)
            return false;
        if (move.toRow == move.fromRow && move.toColumn == move.fromColumn)
             return false;
        if (board[move.toRow][move.toColumn] != null &&
                !board[move.toRow][move.toColumn].player().equals(player().next()))
            return false;


        return true;
    }

    // return true if boards are different
    public boolean compareBoard(Move move) {
        return false;
    }
}
