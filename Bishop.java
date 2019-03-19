package Project3;

public class Bishop extends ChessPiece {

	/*****************************************************************
	 * Constructor for the bishop piece
	 *
	 * @param player the current player
	 *****************************************************************/
	public Bishop(Player player) {
		super(player);
	}

	/*****************************************************************
	 * Returns the type of chess piece the piece is
	 *
	 * @return bishop
	 *****************************************************************/
	public String type() {
		return "Bishop";
	}

	/*****************************************************************
	 * Determining valid moves for the selected bishop piece
	 * @param move the move
	 * @param board the chess board
	 * @return true if move is valid
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = false;

		for (int x = 1; x < 8; x++){
			//if (move.fromRow == move.toRow + x || move.fromRow == move.toRow - x)
			if (Math.abs(move.fromRow - move.toRow) == x)
				if (Math.abs(move.fromColumn - move.toColumn) == x) {
					if (!isOccupied(move, board, move.fromRow, move.fromColumn)) {
						valid = true;
						System.out.println("Moving BISHOP");
					}
				}
		}

		if (valid == false)
			System.out.println("Invalid move for BISHOP");

		return valid;
	}

	private boolean isOccupied(Move move, IChessPiece[][] board, int r, int c){

		if (r == move.toRow && c == move.toColumn)
			return false;

		// up left diagonal
		if (move.toRow < move.fromRow)
			if (move.toColumn < move.fromColumn){
				r--;
				c--;
				if (board[r][c] != null && r != move.toRow && c != move.toColumn) {
					System.out.println("Bishop is blocked");
					return true;
				}
				else
					isOccupied(move, board, r--, c--);
			}

		// up right diagonal
		if (move.toRow < move.fromRow)
			if (move.toColumn > move.fromColumn){
				r--;
				c++;
				if (board[r][c] != null && r != move.toRow && c != move.toColumn) {
					System.out.println("Bishop is blocked");
					return true;
				}
				else
					isOccupied(move, board, r--, c++);
			}

		// down left diagonal
		if (move.toRow > move.fromRow)
			if (move.toColumn < move.fromColumn){
				r++;
				c--;
				if (board[r][c] != null && r != move.toRow && c != move.toColumn){
					System.out.println("Bishop is blocked");
					return true;
				}
				else
					isOccupied(move, board, r++, c--);
			}

		// down right diagonal
		if (move.toRow > move.fromRow)
			if (move.toColumn > move.fromColumn){
				r++;
				c++;
				if (board[r][c] != null && r != move.toRow && c != move.toColumn){
					System.out.println("Bishop is blocked");
					return true;
				}
				else
					isOccupied(move, board, r++, c++);
			}

		return false;
	}

/*	public boolean isOccupied(Move move, IChessPiece[][] board){
		// try this recursively ??

		int distance = Math.abs(move.toRow - move.toColumn);
		int r = move.fromRow;
		int c = move.fromColumn;

		boolean occupied = false;

		// AMELA: FIRST ATTEMPT JUST IGNORE
		// up left diagonal
*//*		if (move.toRow < move.fromRow)
			if (move.toColumn < move.fromColumn){
				for (int r = move.fromRow - 1; r >= move.toRow; r--)
					for (int c = move.fromColumn; c >= move.toColumn; c--){
						if (board[r][c] != null) {
							System.out.println("Blocked");
							// AMELA: remove testing print statements later FIXME
							return true;
						}
					}
			}*//*

		// up left diagonal
		if (move.toRow < move.fromRow)
			if (move.toColumn < move.fromColumn){
				for (int i = 1; i < distance; i++){
					r--;
					c--;

					if (board[r][c] != null) {
						System.out.println("Bishop is blocked");
						occupied = true;
					}
				}
			}

		// up right diagonal
		if (move.toRow < move.fromRow)
			if (move.toColumn > move.fromColumn){
				for (int i = 1; i < distance; i++){
					r--;
					c++;

					if (board[r][c] != null) {
						System.out.println("Bishop is blocked");
						occupied = true;
					}
				}
			}

		// down left diagonal
		if (move.toRow > move.fromRow)
			if (move.toColumn < move.fromColumn){
				for (int i = 1; i < distance; i++){
					r++;
					c--;

					if (board[r][c] != null) {
						System.out.println("Bishop is blocked");
						occupied = true;
					}
				}
			}

		// down right diagonal
		if (move.toRow > move.fromRow)
			if (move.toColumn > move.fromColumn){
				for (int i = 1; i < distance; i++){
					r++;
					c++;

					if (board[r][c] != null) {
						System.out.println("Bishop is blocked");
						occupied = true;
					}
				}
			}

		return occupied;
	}*/
}
