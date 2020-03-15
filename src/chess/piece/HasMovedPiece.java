package chess.piece;

import chess.logic.Coordinate;

public abstract class HasMovedPiece extends Piece {
	private boolean hasMoved = false;
	
	public HasMovedPiece(boolean isWhite) {
		super(isWhite);
	}
	public HasMovedPiece(HasMovedPiece piece) {
		super(piece);
		hasMoved = piece.hasMoved();
	}
	
	@Override
	public void move(Coordinate newCoord) {
		super.move(newCoord);
		hasMoved = true;
	}
	
	public boolean hasMoved() { return hasMoved; }
}
