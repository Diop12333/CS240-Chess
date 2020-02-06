package chess;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece
{
	private boolean hasMoved;
	
	public Queen(boolean white, Board board, Coordinate coord) throws FileNotFoundException
	{
		super(white, board, coord);
		hasMoved = false;
	}
	
	 public List<Coordinate> validMoves()
	{
		List<Coordinate> moves = new ArrayList<>();
		Coordinate coord = super.getCoord();
		
		/* Queens can move a straight line vertically, horizontally, 
		or diagonally any number of squares
		 
		add cases for capturing a piece */
		return moves; 
	} 
	
	public String getWhiteImgFileName() { return "white_queen.png"; }
	public String getBlackImgFileName() { return "black_queen.png"; }
}