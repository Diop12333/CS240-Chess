package chess;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece
{
	private boolean hasMoved;
	
	public King(boolean white, Board board, Coordinate coord) throws FileNotFoundException
	{
		super(white, board, coord);
		hasMoved = false;
	}
	
	 public List<Coordinate> validMoves()
	{
		List<Coordinate> moves = new ArrayList<>();
		Coordinate coord = super.getCoord();
		
		/* 	Kings can move one square in any direction
		 (horizontally, vertically, or diagonally)
	
		add cases for capturing a piece */
		return moves; 
	} 
	
	public String getWhiteImgFileName() { return "white_king.png"; }
	public String getBlackImgFileName() { return "black_king.png"; }
}