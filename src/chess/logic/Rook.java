package chess.logic;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece
{
	private boolean hasMoved = false;
	
	public Rook(boolean white)
	{
		super(white);
	}
	public Rook(Rook rook)
	{
		super(rook);
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
		
		moves.add(Move.UP);
		moves.add(Move.LEFT);
		moves.add(Move.RIGHT);
		moves.add(Move.DOWN);
		
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
	
	public boolean hasMoved() { return hasMoved; }
}