package chess.logic;

import java.util.HashSet;
import java.util.Set;

import chess.specialmove.SpecialMove;

public class King extends Piece
{
	private boolean hasMoved = false;
	
	public King(boolean isWhite)
	{
		super(isWhite);
	}
	public King(King king) {
		super(king);
	}
	
	@Override
	public void move(Coordinate coord) {
		super.move(coord);
		hasMoved = true;
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
	public Set<SpecialMove> potentialSpecialMoves()
	{
		Set<SpecialMove> moves = new HashSet<>();
		
		moves.add(SpecialMove.CASTLE_QUEENSIDE);
		moves.add(SpecialMove.CASTLE_KINGSIDE);
		
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
	
	@Override
	public King copy() { return new King(this); }
	
	public boolean hasMoved() { return hasMoved; }
}