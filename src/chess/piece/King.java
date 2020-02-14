package chess.piece;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import chess.ui.Board;
import chess.ui.Coordinate;
import chess.ui.Piece;

public class King extends Piece
{
	private boolean hasMoved;
	
	public King(boolean white, Board board, Coordinate coord) throws FileNotFoundException
	{
		super(white, board, coord);
		hasMoved = false;
	}
	
	@Override
	public List<Coordinate> validMoves()
	{
		List<Coordinate> moves = new ArrayList<>();
		Coordinate coord = super.getCoord();
		
		/* 	Kings can move one square in any direction
		 (horizontally, vertically, or diagonally)
	
		add cases for capturing a piece */
		return moves; 
	} 
	
	@Override
	public String getWhiteImgFileName() { return "white_king.png"; }
	@Override
	public String getBlackImgFileName() { return "black_king.png"; }
	@Override
	public String toString() { return "K"; }
}