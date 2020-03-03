package chess.logic;

import chess.ui.BoardDisplay;
import chess.ui.ChessGameMouseHandler;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ChessGame {
	private Board board;
	private BoardDisplay boardDisplay;
	private LegalMoveLogic logic;
	private BooleanProperty isWhiteTurn = new SimpleBooleanProperty(true);
	private ObjectProperty<ChessGameState> gameState =
		new SimpleObjectProperty<>(ChessGameState.NORMAL);
	
	private boolean waitingForPromotion = false;
	private Pawn promotionPawn;

	public ChessGame() {
		isWhiteTurn.set(true);
		setUp();
		logic = new LegalMoveLogic(board);
		new ChessGameMouseHandler(this);
		
		detectGameState();
	}
	
	private void setUp() {
		board = new Board();
		
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
		
		boardDisplay = new BoardDisplay(board);
	}
	
	public void move(Piece piece, Coordinate coord) {
		// Make sure en passant can only occur turn after two-square move
		for (Piece p : board.getPieces()) {
			if (p instanceof Pawn) {
				Pawn pawn = (Pawn) p;
				pawn.setEnPassantable(false);
			}
		}
		
		piece.move(coord);
		isWhiteTurn.set(!isWhiteTurn.get());
		
		detectGameState();
	}
	
	private void detectGameState() {
		King currKing = board.getKing(isWhiteTurn());
		King nonCurrKing = board.getKing(!isWhiteTurn());
		boardDisplay.getSquare(nonCurrKing.getCoord()).resetColor();
		
		if (logic.kingInCheck(isWhiteTurn())) {
			boardDisplay.getSquare(currKing.getCoord()).showThreatened();
			
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
	
	public void waitForPromotion(Pawn pawn) {
		waitingForPromotion = true;
		promotionPawn = pawn;
	}
	public void promotePawn(PromotionPiece promPiece) {
		// TODO
		
		promotionPawn = null;
		waitingForPromotion = false;
	}
	
	public Board getBoard() { return board; }
	public BoardDisplay getBoardDisplay() { return boardDisplay; }
	public LegalMoveLogic getLogic() { return logic; }
	public boolean isWhiteTurn() { return isWhiteTurn.get(); }
	public BooleanProperty isWhiteTurnProperty() { return isWhiteTurn; }
	public ObjectProperty<ChessGameState> gameStateProperty() { return gameState; }
	
	public boolean waitingForPromotion() { return waitingForPromotion; }
}
