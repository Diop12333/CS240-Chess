package chess.specialmove;

import chess.logic.Board;
import chess.logic.LegalMoveLogic;
import chess.logic.Pawn;
import chess.logic.Piece;
import chess.logic.XY;

public class PawnTwoUp implements SpecialMoveImplementation {
	@Override
	public boolean canDoMove(Piece piece, LegalMoveLogic logic) {
		Pawn pawn = (Pawn) piece;
		
		Piece frontPiece = logic.getPieceRelative(piece, new XY(0, -1));
		Piece twoAheadPiece = logic.getPieceRelative(piece, new XY(0, -2));
		return !pawn.hasMoved() && frontPiece == null && twoAheadPiece == null;
	}
	
	@Override
	public void doPreMoveEffect(Piece piece, Board board) {}
	@Override
	public void doPostMoveEffect(Piece piece, Board board) {
		Pawn pawn = (Pawn) piece;
		pawn.setEnPassantable(true);
	}
}
