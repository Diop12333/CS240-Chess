package chess.logic;

import chess.ai.AI;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.ui.BoardDisplay;
import chess.ui.ChessGameMouseHandler;
import chess.ui.PromotionDisplay;
import chess.ui.Square;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ChessGame {
	private Board board = new Board();
	private BoardDisplay boardDisplay = new BoardDisplay(board);
	private ChessGameMouseHandler mouseHandler = new ChessGameMouseHandler(this);
	private BooleanProperty isWhiteTurn = new SimpleBooleanProperty(true);
	private ObjectProperty<ChessGameState> gameState =
		new SimpleObjectProperty<>(ChessGameState.NORMAL);
	
	private Square threatenedSquare;
	
	private PromotionDisplay promotionDisplay;
	
	private boolean waitingForGameState = true;
	
	// Set to null for human player
	private AI whiteAI;
	private AI blackAI;

	public ChessGame() {
		setUpBoard();
		detectGameState();
	}
	
	private void setUpBoard() {
		for (int i = 0; i <= 7; i++) {
			board.addNewPiece(i, 1, new Pawn(false));
			board.addNewPiece(i, 6, new Pawn(true));
		}
		
		board.addNewPiece(0, 0, new Rook(false));
		board.addNewPiece(1, 0, new Knight(false));
		board.addNewPiece(2, 0, new Bishop(false));
		board.addNewPiece(3, 0, new Queen(false));
		board.addNewPiece(4, 0, new King(false));
		board.addNewPiece(5, 0, new Bishop(false));
		board.addNewPiece(6, 0, new Knight(false));
		board.addNewPiece(7, 0, new Rook(false));
		
		board.addNewPiece(0, 7, new Rook(true));
		board.addNewPiece(1, 7, new Knight(true));
		board.addNewPiece(2, 7, new Bishop(true));
		board.addNewPiece(3, 7, new Queen(true));
		board.addNewPiece(4, 7, new King(true));
		board.addNewPiece(5, 7, new Bishop(true));
		board.addNewPiece(6, 7, new Knight(true));
		board.addNewPiece(7, 7, new Rook(true));
	}
	
	public void resetThreatenedSquare() {
		if (threatenedSquare != null) {
			threatenedSquare.setThreatened(false);
		}
	}
	public void reset() {
		Platform.runLater(() -> {
			resetThreatenedSquare();
			mouseHandler.reset();
			
			if (waitingForPromotion()) {
				Square promotionDisplaySq = (Square) promotionDisplay.getParent();
				promotionDisplaySq.getChildren().remove(promotionDisplay);
			}
			board.reset();
			setUpBoard();
			isWhiteTurn.set(true);
			gameState.set(ChessGameState.NORMAL);
			
			detectGameState();
		});
	}
	
	public void makeMove(Piece piece, Coordinate coord) {
		board.getInterface().getImplAndMakeMove(piece, coord);
		
		if (waitingForPromotion()) {
			detectGameState(); promotionSetup();
		}
		else {
			switchTurnAndDetectGameState();
		}
	}
	
	private void switchTurn() {
		isWhiteTurn.set(!isWhiteTurn());
	}
	private void switchTurnAndDetectGameState() {
		waitingForGameState = true;
		switchTurn();
		detectGameState();
	}
	private void detectGameState() {
		waitingForGameState = true;
		
		resetThreatenedSquare();
		
		King currKing = board.getKing(isWhiteTurn());
		King nonCurrKing = board.getKing(!isWhiteTurn());
		boardDisplay.getSquare(nonCurrKing.getCoord()).setThreatened(false);
		
		LegalMoveLogic logic = board.getLogic();
		
		if (logic.kingInCheck(isWhiteTurn())) {
			threatenedSquare = boardDisplay.getSquare(currKing.getCoord());
			threatenedSquare.setThreatened(true);
			
			if (logic.canMakeAMove(isWhiteTurn())) {
				gameState.set(ChessGameState.CHECK);
			} else {
				gameState.set(ChessGameState.CHECKMATE);
			}
		} else if (logic.canMakeAMove(isWhiteTurn())){
			gameState.set(ChessGameState.NORMAL);
		} else {
			gameState.set(ChessGameState.STALEMATE);
		}
		
		AI ai;
		if (isWhiteTurn() && whiteAI != null) ai = whiteAI;
		else if (!isWhiteTurn() && blackAI != null) ai = blackAI;
		else ai = null;
		
		if (ai != null) {
			Platform.runLater(() -> {
				StoredMove aiMove = ai.getMove(board, isWhiteTurn());
				if (aiMove != null) board.getInterface().makeStoredMove(aiMove);
				
				switchTurnAndDetectGameState();
				
				waitingForGameState = false;
			});
		} else {
			waitingForGameState = false;
		}
	}
	
	public void promote(PromotionPiece promPiece) {
		board.getInterface().promote(promPiece);
		
		Square promotionDisplaySq = (Square) promotionDisplay.getParent();
		promotionDisplaySq.getChildren().remove(promotionDisplay);
		
		switchTurnAndDetectGameState();
	}
	private void promotionSetup() {
		Pawn pawn = board.getInterface().getPromotionPawn();
		Square pawnSq = boardDisplay.getSquare(pawn.getCoord());
		pawnSq.setPiece(null);
		
		promotionDisplay = new PromotionDisplay(this, pawn.isWhite());
		promotionDisplay.prefWidthProperty().bind(pawnSq.prefWidthProperty());
		promotionDisplay.prefHeightProperty().bind(pawnSq.prefHeightProperty());
		pawnSq.getChildren().add(promotionDisplay);
	}
	
	public Board getBoard() { return board; }
	public BoardDisplay getBoardDisplay() { return boardDisplay; }
	public boolean isWhiteTurn() { return isWhiteTurn.get(); }
	public BooleanProperty isWhiteTurnProperty() { return isWhiteTurn; }
	public ObjectProperty<ChessGameState> gameStateProperty() { return gameState; }
	
	public boolean waitingForPromotion() { return board.getInterface().waitingForPromotion(); }
	public boolean waitingForGameState() { return waitingForGameState; }
	public boolean waiting() { return waitingForPromotion() || waitingForGameState(); }
	
	public AI getAI(boolean isWhite) {
		if (isWhite) return whiteAI;
		else return blackAI;
	}
	public void setWhiteAI(AI ai) { whiteAI = ai; }
	public void setBlackAI(AI ai) { blackAI = ai; }
	public void setAI(boolean isWhite, AI ai) {
		if (isWhite) setWhiteAI(ai);
		else setBlackAI(ai);
	}
}
