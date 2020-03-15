package chess.ui;

import chess.logic.ChessGame;
import chess.logic.ChessGameState;
import javafx.scene.control.Label;

public class ChessGameStateLabel extends Label {
	private ChessGame chessGame;
	public ChessGameStateLabel(ChessGame chessGame) {
		this.chessGame = chessGame;
		
		setText(chessGame.gameStateProperty().get());
		
		chessGame.isWhiteTurnProperty().addListener(
			(__, ___, ____) -> setText(chessGame.gameStateProperty().get())
		);
		chessGame.gameStateProperty().addListener((__, ___, newVal) -> setText(newVal));
	}
	
	private static String boolToText(boolean isWhite) {
		if (isWhite) return "White";
		else return "Black";
	}
	
	private String determineText(ChessGameState gameState) {
		String currTurnText = boolToText(chessGame.isWhiteTurn());
		switch (gameState) {
			case NORMAL: return "It's " + currTurnText + "'s turn.";
			case CHECK: return currTurnText + " is in check.";
			case CHECKMATE: return currTurnText + " has been checkmated!";
			case STALEMATE: return currTurnText + " is in stalemate, the game is a draw!";
		}
		
		return "What the hell? This shouldn't appear! Check ChessGameStateLabel";
	}
	
	private void setText(ChessGameState gameState) {
		setText(determineText(gameState));
	}
}
