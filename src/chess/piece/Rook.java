package chess.piece;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece
{	
	public Rook(boolean white) throws FileNotFoundException
	{
		super(white);
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
}