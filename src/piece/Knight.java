package piece;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import ui.Board;
import ui.Coordinate;

public class Knight extends Piece 
{	
	public Knight(boolean white, Board board, Coordinate coord) throws FileNotFoundException
	{
		super(white, board, coord);
	}
	
	@Override
	public Set<Move> potentialNonCaptureMoves()
	{
		Set<Move> moves = new HashSet<>();
		
		moves.add(Move.KNIGHT_UP_LEFT);
		moves.add(Move.KNIGHT_UP_RIGHT);
		moves.add(Move.KNIGHT_LEFT_UP);
		moves.add(Move.KNIGHT_RIGHT_UP);
		moves.add(Move.KNIGHT_LEFT_DOWN);
		moves.add(Move.KNIGHT_RIGHT_DOWN);
		moves.add(Move.KNIGHT_DOWN_LEFT);
		moves.add(Move.KNIGHT_DOWN_RIGHT);
		
		return moves;
	}
	
	@Override
	public boolean canRepeatMoves() { return false; }
	@Override
	public String getWhiteImgFileName() { return "white_knight.png"; }
	@Override
	public String getBlackImgFileName() { return "black_knight.png"; }
	@Override
	public String notation() { return "N"; }
}

