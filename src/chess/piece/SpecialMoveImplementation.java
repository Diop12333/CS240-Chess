package chess.piece;

public interface SpecialMoveImplementation {
	boolean canDoMove(Piece piece);
	
	// Does everything not handled by typical move handler
	// Assumes that move can be done - undefined behavior if cannot
	void doMoveEffect(Piece piece);
}
