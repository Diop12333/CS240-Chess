package chess.ai;

import java.util.Random;
import java.util.Set;

import chess.logic.Board;
import chess.logic.StoredMove;

public class RandomAI implements AI {
	private Random rand = new Random();
	@Override
	public StoredMove getMove(Board board, boolean color) {
		Set<StoredMove> legalMoves = board.getLogic().legalMoves(color);
		
		if (!legalMoves.isEmpty()) {
			int randIdx = rand.nextInt(legalMoves.size());
			
			int idx = 0;
			
			for (StoredMove move : legalMoves) {
				if (idx == randIdx) {
					return move;
				}
				
				idx++;
			}
			
			System.out.println("This shouldn't happen - check RandomAI");
		}
		
		return null;
	}
}
