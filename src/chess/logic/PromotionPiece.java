package chess.logic;

import chess.piece.Bishop;
import chess.piece.Knight;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;

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
