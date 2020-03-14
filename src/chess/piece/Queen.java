package chess.piece;

import java.util.HashSet;
import java.util.Set;

import chess.logic.RegularMove;

public class Queen extends Piece
{	
	public Queen(boolean white)
	{
		super(white);
	}
	public Queen(Queen queen)
	{
		super(queen);
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
	public boolean canRepeatMoves() { return true; }
	@Override
	public String getWhiteImgFileName() { return "white_queen.png"; }
	@Override
	public String getBlackImgFileName() { return "black_queen.png"; }
	@Override
	public String notation() { return "Q"; }
	@Override
	public Queen copy() { return new Queen(this); }
}