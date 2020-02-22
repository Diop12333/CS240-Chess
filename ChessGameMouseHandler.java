package chess.ui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

import chess.piece.Piece;

public class ChessGameMouseHandler implements EventHandler<MouseEvent> {
	private ChessGame chessGame;
	private Board board;
	private Square storedSquare;
	private Set<Square> moveSquares;
	private static Color HIGHLIGHT_COLOR = Color.GREEN;
	
	public ChessGameMouseHandler(ChessGame chessGame) {
		this.chessGame = chessGame;
		board = chessGame.getBoard();
		moveSquares = new HashSet<>();
		
		for (Square[] row : board.getSquares()) {
			for (Square sq : row) {
				sq.setOnMouseClicked(this);
			}
		}
	}
	
	private void reset() {
		if (storedSquare != null) {
			storedSquare.unhighlight();
			storedSquare = null;
		}
		
		for (Square sq : moveSquares) sq.unhighlight();
		moveSquares.clear();
	}
	
	public void handle(MouseEvent e) {
		Square clickedSquare = (Square) e.getSource();
		Piece clickedSquarePiece = clickedSquare.getPiece();
		
		// If clicked square has piece with same color as current turn
		if (
			clickedSquarePiece != null &&
			clickedSquarePiece.getIsWhite() == chessGame.getIsWhiteTurn()
		) {
			if (clickedSquare == storedSquare) reset();
			else {
				Set<Coordinate> moveCoords = clickedSquarePiece.legalMoveCoords();
				
				// If piece can move
				if (!moveCoords.isEmpty()) {
					reset();
					storedSquare = clickedSquare;
					storedSquare.setColor(HIGHLIGHT_COLOR);
					for (Coordinate coord : clickedSquarePiece.legalMoveCoords()) {
						Square moveSquare = board.getSquare(coord);
						moveSquares.add(moveSquare);
						moveSquare.highlight();
					}
				}
			}
		// If piece square is stored, and piece can move to clicked square
		} else if (storedSquare != null && moveSquares.contains(clickedSquare)) {
			chessGame.move(storedSquare.getPiece(), clickedSquare);
			reset();
		}
	}
}
