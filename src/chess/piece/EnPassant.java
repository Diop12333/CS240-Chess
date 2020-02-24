package chess.piece;

import chess.ui.XY;

// I feel like this code is ugly but I don't know how to make it nicer
public class EnPassant implements SpecialMoveImplementation {
	private int direction;
	// -1 for left, 1 for right
	public EnPassant(int direction) {
		this.direction = direction;
	}
	
	private Pawn getCapturePawn(Piece piece) {
		Piece testPiece = piece.getPieceRelative(new XY(direction, 0));
		if (testPiece instanceof Pawn) return (Pawn) testPiece;
		else return null;
	}
	
	public boolean canDoMove(Piece piece) {
		Pawn capturePawn = getCapturePawn(piece);
		
		return (
			piece instanceof Pawn &&
			capturePawn != null &&
			capturePawn.isWhite() != piece.isWhite() &&
			capturePawn.isEnpassantable()
		);
	}
	
	public void doMoveEffect(Piece piece) {
		Piece capturePawn = getCapturePawn(piece);
		capturePawn.getSquare().clear();
		piece.getChessGame().addCapturedPiece(capturePawn);
	}
}
