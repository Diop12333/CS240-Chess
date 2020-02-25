package chess.piece;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import chess.ui.Square;
import chess.ui.XY;

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
		if (!hasMoved && getPieceRelative(new XY(0, -1)) == null) moves.add(Move.TWO_UP);
		
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
		
		moves.add(SpecialMove.EN_PASSANT_LEFT);
		moves.add(SpecialMove.EN_PASSANT_RIGHT);
		
		return moves;
	}
	
	@Override
	public void move(Square newSquare)
	{
		if (!hasMoved && coordAfterMove(Move.TWO_UP).equals(newSquare.getCoord())) {
			enPassantable = true;
		}
		
		super.move(newSquare);
		hasMoved = true;
	}
	
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
