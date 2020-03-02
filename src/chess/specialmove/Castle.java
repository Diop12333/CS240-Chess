package chess.specialmove;

import chess.logic.Board;
import chess.logic.Coordinate;
import chess.logic.King;
import chess.logic.LegalMoveLogic;
import chess.logic.Piece;
import chess.logic.Rook;
import chess.logic.XY;

public class Castle implements SpecialMoveImplementation {
	private int direction;
	
	// -1 for left, 1 for right
	public Castle(int direction) {
		this.direction = direction;
	}
	
	public Rook getCastleRook(Piece piece, LegalMoveLogic logic) {
		Piece potentialCastleRook;
		
		if (direction > 0) {
			potentialCastleRook = logic.getPieceRelative(piece, new XY(3, 0));
		} else {
			potentialCastleRook = logic.getPieceRelative(piece, new XY(-4, 0));
		}
		
		if (potentialCastleRook instanceof Rook) return (Rook) potentialCastleRook;
		else return null;
	}
	
	@Override
	public boolean canDoMove(Piece piece, LegalMoveLogic logic) {
		King king = (King) piece;
		
		Rook castleRook = getCastleRook(king, logic);
		
		XY shift1 = new XY(direction, 0);
		XY shift2 = new XY(direction * 2, 0);
		XY shift3 = new XY(-3, 0);
		
		Coordinate shifted1 = LegalMoveLogic.pieceCoordAfterShift(king, shift1);
		
		return
			!king.hasMoved() &&
			castleRook != null &&
			!castleRook.hasMoved() &&
			logic.getPieceRelative(king, shift1) == null &&
			LegalMoveLogic.containsCoord(logic.legalMoveCoords(king), shifted1) &&
			logic.getPieceRelative(king, shift2) == null &&
			direction > 0 || logic.getPieceRelative(king, shift3) == null;
	}
	
	
	@Override
	public void doPreMoveEffect(Piece piece, Board board) {
		Rook castleRook = getCastleRook(piece, board.getLogic());
		
		castleRook.move(LegalMoveLogic.pieceCoordAfterShift(piece, new XY(direction, 0)));
	}
	@Override
	public void doPostMoveEffect(Piece piece, Board board) {}
}
