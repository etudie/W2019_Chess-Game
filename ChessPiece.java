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

	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = false;

		
		if (((move.fromRow == move.toRow) && (move.fromColumn == move.toColumn)) == false)
			return valid;

		return false;
	}
	// return true if occupied by any other piece
	public boolean isOccupied(IChessPiece[][] board){
		return false ;
	}

	// return true if boards are different
	public boolean compareBoard(Move move){
		return false;
	}
}
