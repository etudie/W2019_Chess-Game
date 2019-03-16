package chess;
// XUE : DO NOT TOUCH YOU ARE NOT ALLOWED TO EDIT THIS
public enum Player {
	BLACK, WHITE;

	/**
	 * Return the {@code Player} whose turn is next.
	 *
	 * @return the {@code Player} whose turn is next
	 */
	public Player next() {
		if (this == BLACK)
			return WHITE;
		else
		 	return BLACK;
		
	//	return this == BLACK ? WHITE : BLACK;
	}
}