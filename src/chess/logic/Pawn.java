package chess.logic;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import chess.specialmove.SpecialMove;
import chess.ui.Square;

public class Pawn extends Piece
{
	private boolean hasMoved;
	private boolean enPassantable;
	
	public Pawn(boolean isWhite) throws FileNotFoundException
	{
		super(isWhite);
		hasMoved = false;
		enPassantable = false;
	}
	
	@Override
	public Set<Move> potentialNonCaptureMoves()
	{
		Set<Move> moves = new HashSet<>();
		
		moves.add(Move.UP);
		
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
	public Set<SpecialMove> potentialSpecialMoves()
	{
		Set<SpecialMove> moves = new HashSet<>();
		
		moves.add(SpecialMove.PAWN_TWO_UP);
		moves.add(SpecialMove.EN_PASSANT_LEFT);
		moves.add(SpecialMove.EN_PASSANT_RIGHT);
		
		return moves;
	}
	
	@Override
	public void move(Coordinate coord)
	{	
		super.move(coord);
		hasMoved = true;
	}
	
	public boolean hasMoved() { return hasMoved; }
	
	public boolean enPassantable() { return enPassantable; }
	
	public void setEnPassantable(boolean newVal) {
		enPassantable = newVal;
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
