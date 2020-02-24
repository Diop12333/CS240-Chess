package chess.piece;

import chess.ui.Coordinate;

public class StoredMove
{
	private Piece piece;
	private Coordinate prevLocation;
	private Coordinate newLocation;
	
	public StoredMove(Piece piece, Coordinate prevLocation, Coordinate newLocation)
	{
		this.piece = piece;
		this.prevLocation = prevLocation;
		this.newLocation = newLocation;
	}
	
	public Piece getPiece() { return piece; }
	public Coordinate getPrevLocation() { return prevLocation; }
	public Coordinate getNewLocation() { return newLocation; }
	
	public String toString() { return "TODO"; } // insert fancy chess notation here
}