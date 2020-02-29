package chess.logic;

import java.io.FileNotFoundException;

import chess.ui.BoardDisplay;
import chess.ui.ChessGameMouseHandler;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ChessGame {
	private Board board;
	private BoardDisplay boardDisplay;
	private ChessGameLogic logic;
	private BooleanProperty isWhiteTurn = new SimpleBooleanProperty();

	public ChessGame() throws FileNotFoundException {
		isWhiteTurn.set(true);
		setUp();
		logic = new ChessGameLogic(this);
		new ChessGameMouseHandler(this);
	}
	
	private void setUp() throws FileNotFoundException {
		board = new Board();
		
		for (int i = 0; i <= 7; i++) {
			board.setCoord(i, 1, new Pawn(false));
			board.setCoord(i, 6, new Pawn(true));
		}
		
		board.setCoord(0, 0, new Rook(false));
		board.setCoord(1, 0, new Knight(false));
		board.setCoord(2, 0, new Bishop(false));
		board.setCoord(3, 0, new King(false));
		board.setCoord(4, 0, new Queen(false));
		board.setCoord(5, 0, new Bishop(false));
		board.setCoord(6, 0, new Knight(false));
		board.setCoord(7, 0, new Rook(false));
		
		board.setCoord(0, 7, new Rook(true));
		board.setCoord(1, 7, new Knight(true));
		board.setCoord(2, 7, new Bishop(true));
		board.setCoord(3, 7, new King(true));
		board.setCoord(4, 7, new Queen(true));
		board.setCoord(5, 7, new Bishop(true));
		board.setCoord(6, 7, new Knight(true));
		board.setCoord(7, 7, new Rook(true));
		
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
	}
	
	public Board getBoard() { return board; }
	public BoardDisplay getBoardDisplay() { return boardDisplay; }
	public ChessGameLogic getLogic() { return logic; }
	public boolean isWhiteTurn() { return isWhiteTurn.get(); }
	public BooleanProperty isWhiteTurnProperty() { return isWhiteTurn; }
}
