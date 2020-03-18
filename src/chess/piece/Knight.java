package chess.piece;

import java.util.HashSet;
import java.util.Set;

import chess.logic.RegularMove;

public class Knight extends Piece 
{	
	public Knight(boolean isWhite)
	{
		super(isWhite);
	}
	public Knight(Knight knight)
	{
		super(knight);
	}
	
	@Override
	public Set<RegularMove> potentialMiscMoves()
	{
		Set<RegularMove> moves = new HashSet<>();
		
		moves.add(RegularMove.KNIGHT_UP_LEFT);
		moves.add(RegularMove.KNIGHT_UP_RIGHT);
		moves.add(RegularMove.KNIGHT_LEFT_UP);
		moves.add(RegularMove.KNIGHT_RIGHT_UP);
		moves.add(RegularMove.KNIGHT_LEFT_DOWN);
		moves.add(RegularMove.KNIGHT_RIGHT_DOWN);
		moves.add(RegularMove.KNIGHT_DOWN_LEFT);
		moves.add(RegularMove.KNIGHT_DOWN_RIGHT);
		
		return moves;
	}
	
	@Override
	public boolean canRepeatRegularMoves() { return false; }
	@Override
	public int pointValue() { return 3; }
	
	@Override
	public String getWhiteImgFileName() { return "white_knight.png"; }
	@Override
	public String getBlackImgFileName() { return "black_knight.png"; }
	@Override
	public Knight copy() { return new Knight(this); }
}

