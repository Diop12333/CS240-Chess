package chess.specialmove;

import chess.logic.Board;
import chess.logic.BoardLogic;
import chess.logic.Piece;

public interface SpecialMoveImplementation {
	boolean canDoMove(Piece piece, BoardLogic logic);
	
	// Does everything not handled by typical move handler
	// Assumes that move can be done - undefined behavior if cannot
	void doPreMoveEffect(Piece piece, Board board);
	void doPostMoveEffect(Piece piece, Board board);
}