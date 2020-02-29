package chess.piece;

import chess.ui.XY;

public enum Move {
	LEFT_UP (-1, -1),
	UP (0, -1),
	RIGHT_UP (1, -1),
	LEFT (-1, 0),
	RIGHT (1, 0),
	LEFT_DOWN (-1, 1),
	DOWN (0, 1),
	RIGHT_DOWN (1, 1),
	
	// Largest direction written first
	KNIGHT_UP_LEFT (-1, -2),
	KNIGHT_UP_RIGHT (1, -2),
	KNIGHT_LEFT_UP (-2, -1),
	KNIGHT_RIGHT_UP (2, -1),
	KNIGHT_LEFT_DOWN (-2, 1),
	KNIGHT_RIGHT_DOWN (2, 1),
	KNIGHT_DOWN_LEFT (-1, 2),
	KNIGHT_DOWN_RIGHT (1, 2);
	
	private XY shift;
	Move(int moveX, int moveY) { this(new XY(moveX, moveY)); }
	Move(XY shift) { this.shift = shift; }
	
	public XY getShift() { return shift; }
}
