package chess.logic;

public enum PromotionPiece {
	QUEEN,
	KNIGHT,
	ROOK,
	BISHOP;
	
	public Piece toRegularPiece(boolean isWhite) {
		switch(this) {
			case QUEEN: return new Queen(isWhite);
			case KNIGHT: return new Knight(isWhite);
			case ROOK: return new Rook(isWhite);
			case BISHOP: return new Bishop(isWhite);
		}
		
		// Shouldn't happen
		return null;
	}
}
