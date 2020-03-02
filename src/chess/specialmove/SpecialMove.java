package chess.specialmove;

import chess.logic.XY;

public enum SpecialMove {
	PAWN_TWO_UP (0, -2, new PawnTwoUp()),
	
	EN_PASSANT_LEFT (-1, -1, new EnPassant(-1)),
	EN_PASSANT_RIGHT (1, -1, new EnPassant(1));
	
	private XY shift;
	private SpecialMoveImplementation implementation;
	
	SpecialMove(int xShift, int yShift, SpecialMoveImplementation implementation) {
		this(new XY(xShift, yShift), implementation);
	}
	SpecialMove(XY shift, SpecialMoveImplementation implementation) {
		this.shift = shift;
		this.implementation = implementation;
	}
	
	public XY getShift() { return shift; }
	public SpecialMoveImplementation getImplementation() { return implementation; }
}
