package chess.piece;

import java.util.HashSet;
import java.util.Set;

import chess.logic.RegularMove;

public class Rook extends HasMovedPiece
{	
	public Rook(boolean white)
	{
		super(white);
	}
	public Rook(Rook rook)
	{
		super(rook);
	}
	
	@Override
	public Set<RegularMove> potentialMiscMoves()
	{
		Set<RegularMove> moves = new HashSet<>();
		
		moves.add(RegularMove.UP);
		moves.add(RegularMove.LEFT);
		moves.add(RegularMove.RIGHT);
		moves.add(RegularMove.DOWN);
		
		return moves; 
	} 
	
	@Override
	public boolean canRepeatRegularMoves() { return true; }
	@Override
	public int pointValue() { return 5; }
	
	@Override
	public String getWhiteImgFileName() { return "white_rook.png"; }
	@Override
	public String getBlackImgFileName() { return "black_rook.png"; }
	@Override
	public Rook copy() { return new Rook(this); }
}