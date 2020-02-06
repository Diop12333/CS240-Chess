package chess;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece
{
	private boolean hasMoved;
	
	public Pawn(boolean white, Board board, Coordinate coord) throws FileNotFoundException
	{
		super(white, board, coord);
		hasMoved = false;
	}
	
	public List<Coordinate> validMoves()
	{
		List<Coordinate> moves = new ArrayList<>();
		Coordinate coord = super.getCoord();
		
		if (super.isWhite())
		{
			moves.add(coord.shift(0, -1));
			if (!hasMoved) moves.add(coord.shift(0, -2));
		}
		else
		{
			moves.add(coord.shift(0, 1));
			if (!hasMoved) moves.add(coord.shift(0, 2));
		}
		
		// add cases for capturing a piece
		return moves;
	}
	
	public String getWhiteImgFileName() { return "white_pawn.png"; }
	public String getBlackImgFileName() { return "black_pawn.png"; }
}
