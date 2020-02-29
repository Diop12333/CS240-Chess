package chess.specialmove;

import chess.logic.ChessGame;
import chess.logic.ChessGameLogic;
import chess.logic.Pawn;
import chess.logic.Piece;
import chess.logic.XY;

public class PawnTwoUp implements SpecialMoveImplementation {
	public boolean canDoMove(Piece piece, ChessGameLogic logic) {
		if (!(piece instanceof Pawn)) return false;
		
		Pawn pawn = (Pawn) piece;
		
		Piece frontPiece = logic.getPieceRelative(piece, new XY(0, -1));
		Piece twoAheadPiece = logic.getPieceRelative(piece, new XY(0, -2));
		return !pawn.hasMoved() && frontPiece == null && twoAheadPiece == null;
	}
	
	public void doPreMoveEffect(Piece piece, ChessGame chessGame) {}
	public void doPostMoveEffect(Piece piece, ChessGame chessGame) {
		Pawn pawn = (Pawn) piece;
		pawn.setEnPassantable(true);
	}
}
