package chess.specialmove;

import chess.logic.Board;
import chess.logic.LegalMoveLogic;
import chess.logic.Piece;

public interface SpecialMoveImplementation {
	boolean checkExtraConditions(Piece piece, LegalMoveLogic logic);
	
	// Does everything not handled by typical move handler
	// Assumes that move can be done - undefined behavior if cannot
	void doPreMoveEffect(Piece piece, Board board);
	void doPostMoveEffect(Piece piece, Board board);
}