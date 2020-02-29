package chess.piece;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import chess.ui.BoardDisplay;
import chess.ui.ChessGameMouseHandler;
import chess.ui.Square;
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
			board.setPiece(new Pawn(false), i, 1);
			board.setPiece(new Pawn(true), i, 6);
		}
		
		board.setPiece(new Rook(false), 0, 0);
		board.setPiece(new Knight(false), 1, 0);
		board.setPiece(new Bishop(false), 2, 0);
		board.setPiece(new King(false), 3, 0);
		board.setPiece(new Queen(false), 4, 0);
		board.setPiece(new Bishop(false), 5, 0);
		board.setPiece(new Knight(false), 6, 0);
		board.setPiece(new Rook(false), 7, 0);
		
		board.setPiece(new Rook(true), 0, 7);
		board.setPiece(new Knight(true), 1, 7);
		board.setPiece(new Bishop(true), 2, 7);
		board.setPiece(new King(true), 3, 7);
		board.setPiece(new Queen(true), 4, 7);
		board.setPiece(new Bishop(true), 5, 7);
		board.setPiece(new Knight(true), 6, 7);
		board.setPiece(new Rook(true), 7, 7);
		
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
