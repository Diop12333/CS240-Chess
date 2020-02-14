package chess.piece;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import chess.ui.Board;
import chess.ui.Coordinate;
import chess.ui.Piece;

public class Queen extends Piece
{	
	public Queen(boolean white, Board board, Coordinate coord) throws FileNotFoundException
	{
		super(white, board, coord);
	}
	
	@Override
	public List<Coordinate> validMoves()
	{
		List<Coordinate> moves = new ArrayList<>();
		Coordinate coord = super.getCoord();
		
		/* Queens can move a straight line vertically, horizontally, 
		or diagonally any number of squares
		 
		add cases for capturing a piece */
		return moves; 
	} 
	
	@Override
	public String getWhiteImgFileName() { return "white_queen.png"; }
	@Override
	public String getBlackImgFileName() { return "black_queen.png"; }
	@Override
	public String toString() { return "Q"; }
}