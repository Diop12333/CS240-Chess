package chess.ui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import chess.piece.Piece;

public class ChessGameMouseHandler implements EventHandler<MouseEvent> {
	private ChessGame chessGame;
	private Board board;
	private Square storedSquare;
	private Set<Square> moveSquares;
	
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
		storedSquare.unhighlight();
		for (Square sq : moveSquares) sq.unhighlight();
		storedSquare = null;
		moveSquares.clear();
	}
	
	public void handle(MouseEvent e) {
		Square clickedSquare = (Square) e.getSource();
		Piece clickedSquarePiece = clickedSquare.getPiece();
		
		if (
			storedSquare == null &&
			clickedSquarePiece != null &&
			clickedSquarePiece.getIsWhite() == chessGame.getIsWhiteTurn()
		) {
			Set<Coordinate> moveCoords = clickedSquarePiece.legalMoveCoords();
			
			if (!moveCoords.isEmpty()) {
				storedSquare = clickedSquare;
				storedSquare.highlight();
				for (Coordinate coord : clickedSquarePiece.legalMoveCoords()) {
					Square moveSquare = board.getSquare(coord);
					moveSquares.add(moveSquare);
					moveSquare.highlight();
				}
			}
		} else if (storedSquare != null && moveSquares.contains(clickedSquare)) {
			chessGame.move(storedSquare.getPiece(), clickedSquare);
			reset();
		}
	}
}
