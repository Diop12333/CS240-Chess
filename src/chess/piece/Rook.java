package chess.piece;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import chess.ui.Board;
import chess.ui.Coordinate;
import chess.ui.Piece;

public class Rook extends Piece
{
	private boolean hasMoved;
	
	public Rook(boolean white, Board board, Coordinate coord) throws FileNotFoundException
	{
		super(white, board, coord);
		hasMoved = false;
	}
	
	@Override
	public List<Coordinate> validMoves()
	{
		List<Coordinate> moves = new ArrayList<>();
		Coordinate coord = super.getCoord();
		
		
		/* 
		 
		 Rooks can move horizontally or vertically in any direction for 
		 however many spaces they choose
		 
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
		
		// add cases for capturing a piece */
		return moves; 
	} 
	
	@Override
	public String getWhiteImgFileName() { return "white_rook.png"; }
	@Override
	public String getBlackImgFileName() { return "black_rook.png"; }
	@Override
	public String toString() { return "R"; }
}