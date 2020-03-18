package chess.piece;

import java.util.HashSet;
import java.util.Set;

import chess.logic.RegularMove;
import chess.specialmove.SpecialMove;

public class Pawn extends HasMovedPiece
{
	private boolean enPassantable;
	
	public Pawn(boolean isWhite)
	{
		super(isWhite);
		enPassantable = false;
	}
	public Pawn(Pawn pawn) {
		super(pawn);
		enPassantable = pawn.enPassantable();
	}
	
	@Override
	public Set<RegularMove> potentialNonCaptureMoves()
	{
		Set<RegularMove> moves = new HashSet<>();
		
		moves.add(RegularMove.UP);
		
		return moves;
	}
	
	@Override
	public Set<RegularMove> potentialCaptureMoves()
	{
		Set<RegularMove> moves = new HashSet<>();
		
		moves.add(RegularMove.LEFT_UP);
		moves.add(RegularMove.RIGHT_UP);
		
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
	
	public boolean enPassantable() { return enPassantable; }
	
	public void setEnPassantable(boolean newVal) {
		enPassantable = newVal;
	}
	
	@Override
	public boolean canRepeatRegularMoves() { return false; }
	@Override
	public int pointValue() { return 1; }
	
	@Override
	public String getWhiteImgFileName() { return "white_pawn.png"; }
	@Override
	public String getBlackImgFileName() { return "black_pawn.png"; }

	@Override
	public Pawn copy() { return new Pawn(this); }
}
