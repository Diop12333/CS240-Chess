package chess.ui;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;

public class ChessGame {
	private Board board;
	private boolean isWhiteTurn;
	
	private Set<Piece> capturedPieces;

	public ChessGame() throws FileNotFoundException {
		setUp();
		new ChessGameMouseHandler(this);
	}
	
	private void setUp() throws FileNotFoundException {
		isWhiteTurn = true;
		capturedPieces = new HashSet<>();
		board = new Board(this);
		
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
	}
	
	public void move(Piece piece, Square square) {
		if (square.getPiece() != null) addCapturedPiece(square.getPiece());
		
		piece.move(square);
		isWhiteTurn = !isWhiteTurn;
	}
	
	public void addCapturedPiece(Piece piece) {
		capturedPieces.add(piece);
	}
	
	public Board getBoard() { return board; }
	public boolean getIsWhiteTurn() { return isWhiteTurn; }
}
