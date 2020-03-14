package chess.piece;

import java.util.HashSet;
import java.util.Set;

import chess.logic.RegularMove;

public class Bishop extends Piece
{
	public Bishop(boolean isWhite)
	{
		super(isWhite);
	}
	public Bishop(Bishop bishop)
	{
		super(bishop);
	}
	
	@Override
	public Set<RegularMove> potentialMiscMoves()
	{
		Set<RegularMove> moves = new HashSet<>();
		
		moves.add(RegularMove.LEFT_UP);
		moves.add(RegularMove.RIGHT_UP);
		moves.add(RegularMove.LEFT_DOWN);
		moves.add(RegularMove.RIGHT_DOWN);
		
		return moves; 
	} 
	
	@Override
	public boolean canRepeatMoves() { return true; }
	@Override
	public String getWhiteImgFileName() { return "white_bishop.png"; }
	@Override
	public String getBlackImgFileName() { return "black_bishop.png"; }
	@Override
	public String notation() { return "B"; }
	
	@Override
	public Bishop copy() { return new Bishop(this); }
}
