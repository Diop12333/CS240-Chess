package chess.piece;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import chess.ui.Board;
import chess.ui.Coordinate;
import chess.ui.Square;

public class Pawn extends Piece
{
	private boolean hasMoved;
	
	public Pawn(boolean white, Board board, Coordinate coord) throws FileNotFoundException
	{
		super(white, board, coord);
		hasMoved = false;
	}
	
	@Override
	public Set<Move> potentialNonCaptureMoves()
	{
		Set<Move> moves = new HashSet<>();
		
		moves.add(Move.UP);
		if (!hasMoved) moves.add(Move.TWO_UP);
		
		return moves;
	}
	
	@Override
	public Set<Move> potentialCaptureMoves()
	{
		Set<Move> moves = new HashSet<>();
		
		moves.add(Move.LEFT_UP);
		moves.add(Move.RIGHT_UP);
		
		return moves;
	}
	
	@Override
	public void move(Square newSquare)
	{
		super.move(newSquare);
		hasMoved = true;
	}
	
	@Override
	public boolean canRepeatMoves() { return false; }
	@Override
	public String getWhiteImgFileName() { return "white_pawn.png"; }
	@Override
	public String getBlackImgFileName() { return "black_pawn.png"; }
	@Override
	public String notation() { return ""; }
}
