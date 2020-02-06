package chess;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece
{
	private boolean hasMoved;
	
	public Bishop(boolean white, Board board, Coordinate coord) throws FileNotFoundException
	{
		super(white, board, coord);
		hasMoved = false;
	}
	
	 public List<Coordinate> validMoves()
	{
		List<Coordinate> moves = new ArrayList<>();
		Coordinate coord = super.getCoord();
		
		/* 
		 
		Bishops can move diagonally for however many spaces 
		they like
		 
		
		add cases for capturing a piece */
		return moves; 
	} 
	
	public String getWhiteImgFileName() { return "white_bishop.png"; }
	public String getBlackImgFileName() { return "black_bishop.png"; }
}
