package chess.ai;

import chess.logic.Board;
import chess.logic.StoredMove;

public interface AI {
	StoredMove getMove(Board board, boolean color);
}
