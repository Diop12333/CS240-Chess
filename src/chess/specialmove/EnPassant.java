package chess.specialmove;

import chess.logic.ChessGame;
import chess.logic.ChessGameLogic;
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
	
	private Pawn getCapturePawn(Piece piece, ChessGameLogic logic) {
		Piece testPiece = logic.getPieceRelative(piece, new XY(direction, 0));
		if (testPiece instanceof Pawn) return (Pawn) testPiece;
		else return null;
	}
	
	public boolean canDoMove(Piece piece, ChessGameLogic logic) {
		Pawn capturePawn = getCapturePawn(piece, logic);
		
		return (
			piece instanceof Pawn &&
			capturePawn != null &&
			capturePawn.isWhite() != piece.isWhite() &&
			capturePawn.enPassantable()
		);
	}
	
	public void doPreMoveEffect(Piece piece, ChessGame chessGame) {
		Piece capturePawn = getCapturePawn(piece, chessGame.getLogic());
		Coordinate capturePawnCoord = capturePawn.getCoord();
		chessGame.getBoard().setCoord(capturePawnCoord, null);
	}
	public void doPostMoveEffect(Piece piece, ChessGame chessGame) {}
}
