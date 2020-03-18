package chess.ai;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.logic.Board;
import chess.logic.StoredMove;
import chess.logic.Utility;

// Always tries to checkmate
// If it can't, will make move that leaves it with the best board value
// Board value = sum of own pieces' values - sum of opponent's pieces' values
// TODO: fix promotion
public class AggressiveNaiveAI implements AI {
	@Override
	public StoredMove getMove(Board board, boolean isWhite) {
		int bestValue = 0;
		Set<StoredMove> bestMoves = new HashSet<>();
		
		for (
			Map.Entry<StoredMove, Board> entry :
			board.getLogic().legalBoardStates(isWhite).entrySet()
		) {
			StoredMove move = entry.getKey();
			Board boardState = entry.getValue();
			
			if (boardState.getLogic().isCheckmated(!isWhite)) return move;
			
			int boardValue = boardState.getLogic().value(isWhite);
			
			if (bestMoves.isEmpty() || boardValue > bestValue) {
				bestMoves.clear();
				bestMoves.add(move);
				bestValue = boardValue;
			} else if (boardValue == bestValue) {
				bestMoves.add(move);
			}
		}
		
		return Utility.randSelect(bestMoves);
	}
}
