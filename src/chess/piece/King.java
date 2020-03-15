package chess.piece;

import java.util.HashSet;
import java.util.Set;

import chess.logic.RegularMove;
import chess.specialmove.SpecialMove;

public class King extends HasMovedPiece
{
	public King(boolean isWhite)
	{
		super(isWhite);
	}
	public King(King king) {
		super(king);
	}
	
	@Override
	public Set<RegularMove> potentialMiscMoves()
	{
		Set<RegularMove> moves = new HashSet<>();
		
		moves.add(RegularMove.LEFT_UP);
		moves.add(RegularMove.UP);
		moves.add(RegularMove.RIGHT_UP);
		moves.add(RegularMove.LEFT);
		moves.add(RegularMove.RIGHT);
		moves.add(RegularMove.LEFT_DOWN);
		moves.add(RegularMove.DOWN);
		moves.add(RegularMove.RIGHT_DOWN);
		
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
}