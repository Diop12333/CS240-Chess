package chess.logic;

public class StoredMove
{
	private Piece piece;
	private Coordinate prevCoord;
	private Coordinate newCoord;
	
	public StoredMove(Piece piece, Coordinate newCoord)
	{
		this.piece = piece;
		prevCoord = piece.getCoord();
		this.newCoord = newCoord;
	}
	
	public Piece getPiece() { return piece; }
	public Coordinate getPrevCoord() { return prevCoord; }
	public Coordinate getNewCoord() { return newCoord; }
	
	public String toString() { return "TODO"; } // insert fancy chess notation here
}