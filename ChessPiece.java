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

    public Player currentPlayer(){
        return ChessModel.player;
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        // Check turn
        if(player() == currentPlayer())
            return true;

        // Check if same place
        return false;
    }




    // return true if boards are different
    public boolean compareBoard(Move move) {
        return false;
    }
}
