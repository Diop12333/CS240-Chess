package chess.specialmove;

import chess.logic.ChessGame;
import chess.logic.ChessGameLogic;
import chess.logic.Coordinate;
import chess.logic.Piece;

public interface SpecialMoveImplementation {
	boolean canDoMove(Piece piece, ChessGameLogic logic);
	
	// Does everything not handled by typical move handler
	// Assumes that move can be done - undefined behavior if cannot
	void doPreMoveEffect(Piece piece, ChessGame chessGame);
	void doPostMoveEffect(Piece piece, ChessGame chessGame);
}
