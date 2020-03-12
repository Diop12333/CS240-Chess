package chess.logic;

import chess.specialmove.SpecialMoveImplementation;
import chess.ui.BoardDisplay;
import chess.ui.ChessGameMouseHandler;
import chess.ui.PromotionDisplay;
import chess.ui.Square;
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
		new SimpleObjectProperty<ChessGameState>(ChessGameState.NORMAL);
	
	private Square threatenedSquare;
	
	private boolean waitingForPromotion = false;
	private Pawn promotionPawn;
	private PromotionDisplay promotionDisplay;
	
	private boolean waitingForAIMove = false;

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
			threatenedSquare.resetColor();
		}
	}
	public void reset() {
		resetThreatenedSquare();
		mouseHandler.reset();
		
		if (waitingForPromotion) {
			boardDisplay.getSquare(promotionPawn.getCoord()).getChildren().remove(promotionDisplay);
			waitingForPromotion = false;
		}
		board.reset();
		setUpBoard();
		isWhiteTurn.set(true);
		gameState.set(ChessGameState.NORMAL);
	}
	
	public void move(Piece piece, Coordinate coord) { move(piece, coord, null); }
	public void move(Piece piece, Coordinate coord, SpecialMoveImplementation impl) {
		board.makeMove(piece, coord, impl);
		
		int pieceY = piece.getCoord().getY();
		if (
			piece instanceof Pawn && (
				piece.isWhite() && pieceY == 0 ||
				!piece.isWhite() && pieceY == board.getDimensions().getY() - 1
			)
		) {
			waitForPromotion((Pawn) piece);
		} else {
			isWhiteTurn.set(!isWhiteTurn.get());
		}
		
		detectGameState();
	}
	
	private void detectGameState() {
		resetThreatenedSquare();
		
		King currKing = board.getKing(isWhiteTurn());
		King nonCurrKing = board.getKing(!isWhiteTurn());
		boardDisplay.getSquare(nonCurrKing.getCoord()).resetColor();
		
		LegalMoveLogic logic = board.getLogic();
		
		if (logic.kingInCheck(isWhiteTurn())) {
			threatenedSquare = boardDisplay.getSquare(currKing.getCoord());
			threatenedSquare.showThreatened();
			
			if (logic.canMakeAMove(isWhiteTurn())) {
				gameState.set(ChessGameState.CHECK);
			} else {
				gameState.set(ChessGameState.CHECKMATE);
			}
		} else {
			if (logic.canMakeAMove(isWhiteTurn())) {
				gameState.set(ChessGameState.NORMAL);
			} else {
				gameState.set(ChessGameState.STALEMATE);
			}
		}
	}
	
	private void waitForPromotion(Pawn pawn) {
		if (waitingForPromotion) {
			System.out.println("ERROR: waitForPromotion called while waiting for promotion");
			return;
		}
		
		waitingForPromotion = true;
		
		promotionPawn = pawn;
		Square pawnSq = boardDisplay.getSquare(pawn.getCoord());
		pawnSq.setPiece(null);
		
		promotionDisplay = new PromotionDisplay(this, pawn.isWhite());
		promotionDisplay.prefWidthProperty().bind(pawnSq.prefWidthProperty());
		promotionDisplay.prefHeightProperty().bind(pawnSq.prefHeightProperty());
		pawnSq.getChildren().add(promotionDisplay);
	}
	public void promote(PromotionPiece promPiece) {
		if (!waitingForPromotion) {
			System.out.println("ERROR: promotePawn called while not waiting for promotion");
			return;
		}
		
		Coordinate pawnCoord = promotionPawn.getCoord();
		Square promotionSq = boardDisplay.getSquare(pawnCoord);
		promotionSq.getChildren().remove(promotionDisplay);
		
		board.addNewPiece(pawnCoord, promPiece.toRegularPiece(promotionPawn.isWhite()));
		
		isWhiteTurn.set(!isWhiteTurn.get());
		
		waitingForPromotion = false;
	}
	
	public Board getBoard() { return board; }
	public BoardDisplay getBoardDisplay() { return boardDisplay; }
	public boolean isWhiteTurn() { return isWhiteTurn.get(); }
	public BooleanProperty isWhiteTurnProperty() { return isWhiteTurn; }
	public ObjectProperty<ChessGameState> gameStateProperty() { return gameState; }
	
	public boolean waitingForPromotion() { return waitingForPromotion; }
}
