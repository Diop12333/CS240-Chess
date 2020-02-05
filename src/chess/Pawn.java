package chess;

import java.util.*;

public class Pawn extends Piece
{
	public Pawn()
	{
		
	}
	
	public Coordinate validMoves()
	{
		if (white)
		{
			return coord + 1;
		}
		
		if (!white)
		{
			return coord - 1;
		}
		
		return -1;
	}
}
