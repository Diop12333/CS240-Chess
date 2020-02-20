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
			new Pawn(false, board, new Coordinate(i, 1));
			new Pawn(true, board, new Coordinate(i, 6));
		}
		
		new Rook(false, board, new Coordinate (0,0));
		new Knight(false, board, new Coordinate (1,0));
		new Bishop(false, board, new Coordinate (2,0));
		new King(false, board, new Coordinate (3,0));
		new Queen(false, board, new Coordinate (4,0));
		new Bishop(false, board, new Coordinate (5,0));
		new Knight(false, board, new Coordinate (6,0));
		new Rook(false, board, new Coordinate (7,0));
		
		new Rook(true, board, new Coordinate (0,7));
		new Knight(true, board, new Coordinate (1,7));
		new Bishop(true, board, new Coordinate (2,7));
		new King(true, board, new Coordinate (3,7));
		new Queen(true, board, new Coordinate (4,7));
		new Bishop(true, board, new Coordinate (5,7));
		new Knight(true, board, new Coordinate (6,7));
		new Rook(true, board, new Coordinate (7,7));
	}
	
	public void move(Piece piece, Square square) {
		if (piece == null) throw new IllegalArgumentException("Piece cannot be null");
		
		piece.move(square);
		isWhiteTurn = !isWhiteTurn;
	}
	
	public Board getBoard() { return board; }
	public boolean getIsWhiteTurn() { return isWhiteTurn; }
}
