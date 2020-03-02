package chess.specialmove;

import chess.logic.Board;
import chess.logic.LegalMoveLogic;
import chess.logic.Coordinate;
import chess.logic.Pawn;
import chess.logic.Piece;
import chess.logic.XY;

// I feel like this code is ugly but I don't know how to make it nicer
public class EnPassant implements SpecialMoveImplementation {
	private int direction;
	// -1 for left, 1 for right
	public EnPassant(int direction) {
		this.direction = direction;
	}
	
	private Pawn getCapturePawn(Piece piece, LegalMoveLogic logic) {
		Piece testPiece = logic.getPieceRelative(piece, new XY(direction, 0));
		if (testPiece instanceof Pawn) return (Pawn) testPiece;
		else return null;
	}
	
	@Override
	public boolean canDoMove(Piece piece, LegalMoveLogic logic) {
		Pawn capturePawn = getCapturePawn(piece, logic);
		
		return
			capturePawn != null &&
			capturePawn.isWhite() != piece.isWhite() &&
			capturePawn.enPassantable();
	}
	
	@Override
	public void doPreMoveEffect(Piece piece, Board board) {
		Piece capturePawn = getCapturePawn(piece, board.getLogic());
		Coordinate capturePawnCoord = capturePawn.getCoord();
		board.setCoord(capturePawnCoord, null);
	}
	@Override
	public void doPostMoveEffect(Piece piece, Board board) {}
}
