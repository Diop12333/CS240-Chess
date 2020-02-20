package chess.ui;

import java.io.FileNotFoundException;

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

	public ChessGame() throws FileNotFoundException {
		setUpBoard();
		isWhiteTurn = true;
		
		new ChessGameMouseHandler(this);
	}
	
	private void setUpBoard() throws FileNotFoundException {
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
	}
	
	public void move(Piece piece, Square square) {
		if (piece == null) throw new IllegalArgumentException("Piece cannot be null");
		
		piece.move(square);
		isWhiteTurn = !isWhiteTurn;
	}
	
	public Piece getPieceAbsolute(Coordinate coord, boolean fromBlackPerspective) {
		if (fromBlackPerspective) coord = new Coordinate(coord.getX(), -coord.getY());
		return board.getPiece(coord);
	}
	public Piece getPieceRelative(Piece piece, XY shift, boolean fromBlackPerspective) {
		if (fromBlackPerspective) shift = new XY(shift.getX(), -shift.getY());
		return board.getPiece(piece.getCoord().shifted(shift));
	}
	
	public Board getBoard() { return board; }
	public boolean getIsWhiteTurn() { return isWhiteTurn; }
}
