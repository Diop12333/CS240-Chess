package chess.piece;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import chess.ui.Board;
import chess.ui.Coordinate;

public class King extends Piece
{	
	public King(boolean white, Board board, Coordinate coord) throws FileNotFoundException
	{
		super(white, board, coord);
	}
	
	@Override
	public Set<Move> potentialNonCaptureMoves()
	{
		Set<Move> moves = new HashSet<>();
		
		moves.add(Move.LEFT_UP);
		moves.add(Move.UP);
		moves.add(Move.RIGHT_UP);
		moves.add(Move.LEFT);
		moves.add(Move.RIGHT);
		moves.add(Move.LEFT_DOWN);
		moves.add(Move.DOWN);
		moves.add(Move.RIGHT_DOWN);
		
		return moves;
	} 
	
	@Override
	public boolean canRepeatMoves() { return false; }
	@Override
	public String getWhiteImgFileName() { return "white_king.png"; }
	@Override
	public String getBlackImgFileName() { return "black_king.png"; }
	@Override
	public String notation() { return "K"; }
}