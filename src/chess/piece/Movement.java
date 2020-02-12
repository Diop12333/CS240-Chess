package chess.piece;

import chess.ui.Board;
import chess.ui.Coordinate;
import chess.ui.Piece;

public class Movement
{
	private Piece piece;
	private Board board;
	private Coordinate prevLocation;
	private Coordinate newLocation;
	
	public Movement(Piece piece, Coordinate prevLocation, Coordinate newLocation)
	{
		this.piece = piece;
		this.board = piece.getBoard();
		this.prevLocation = prevLocation;
		this.newLocation = newLocation;
	}
	
	public Piece getPiece() { return piece; }
	public Coordinate getPrevLocation() { return prevLocation; }
	public Coordinate getNewLocation() { return newLocation; }
	
	public String toString() { return null; } // insert fancy chess notation here
}