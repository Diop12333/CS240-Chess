package chess.logic;

import java.util.HashSet;
import java.util.Set;

import chess.piece.Piece;





public class CapturedPieceDisplay
{
	private boolean color;
	private Set<Piece> piece;
	
	
	
	public CapturedPieceDisplay()
	{
		this.color = Board.getColorPieces();
		this.piece = Board.getCapturedPieces();
	}
	
	/*
	// Move Each White Captured Piece Over Rightwards 10 PIXEL UNITS on Top Left
	if (getColorPieces())
	{
		int capturedWhitePosition =+ 1;
		setCoord(capturedWhitePosition * 10, 0, pieces);
	}
	
	// Move Each Black Captured Piece Over Leftwards 10 PIXEL UNITS on Top Right
	else
	{
		int capturedBlackPosition =+ 1;
		setCoord(500 - capturedBlackPosition * 10, 0, pieces);
	}
	*/
}
// UNDER CONSTRUCTION