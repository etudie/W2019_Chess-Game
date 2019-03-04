package chess;

public abstract class ChessPiece implements IChessPiece {

    private Player owner;

    protected ChessPiece(Player player) {
        this.owner = player;
    }

    public abstract String type();

    public Player player() {
        return owner;
    }


    public boolean isValidMove(Move move, IChessPiece[][] board) {//  XUE : ignore because it is inherited

        return true;
    }




    // return true if boards are different
    public boolean compareBoard(Move move) {
        return false;
    }
}
