package chess.logic;

import java.util.HashSet;
import java.util.Set;

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
	public boolean canRepeatMoves() { return true; }
	@Override
	public String getWhiteImgFileName() { return "white_rook.png"; }
	@Override
	public String getBlackImgFileName() { return "black_rook.png"; }
	@Override
	public String notation() { return "R"; }
	@Override
	public Rook copy() { return new Rook(this); }
}