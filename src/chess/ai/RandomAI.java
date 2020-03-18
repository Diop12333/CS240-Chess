package chess.ai;

import java.util.Set;

import chess.logic.Board;
import chess.logic.StoredMove;
import chess.logic.Utility;

public class RandomAI implements AI {
	@Override
	public StoredMove getMove(Board board, boolean isWhite) {
		Set<StoredMove> legalMoves = board.getLogic().legalMoves(isWhite);
		return Utility.randSelect(legalMoves);
	}
}
