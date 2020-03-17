package chess.logic;

import chess.piece.Piece;

public class StoredMove
{
	private Piece piece;
	private Coordinate prevCoord;
	private Coordinate newCoord;
	private PromotionPiece promPiece;
	
	public StoredMove(Piece piece, Coordinate newCoord)
	{
		this.piece = piece;
		prevCoord = piece.getCoord();
		this.newCoord = newCoord;
	}
	public StoredMove(Piece piece, Coordinate newCoord, PromotionPiece promPiece) {
		this(piece, newCoord);
		this.promPiece = promPiece;
	}
	
	public Piece getPiece() { return piece; }
	public Coordinate getPrevCoord() { return prevCoord; }
	public Coordinate getNewCoord() { return newCoord; }
	public PromotionPiece getPromPiece() { return promPiece; }
	
	public String toString() { return "TODO"; } // TODO: insert fancy chess notation here
}