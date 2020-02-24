package chess.ui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.piece.Piece;
import chess.piece.SpecialMoveImplementation;

public class ChessGameMouseHandler implements EventHandler<MouseEvent> {
	private ChessGame chessGame;
	private Board board;
	private Square storedSquare;
	private Set<Square> moveSquares = new HashSet<>();
	private Map<Square, SpecialMoveImplementation> specialMoveSquares = new HashMap<>();
	
	public ChessGameMouseHandler(ChessGame chessGame) {
		this.chessGame = chessGame;
		board = chessGame.getBoard();
		
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
		
		for (Square sq : moveSquares) sq.unshowCircle();
		moveSquares.clear();
		
		for (Square sq : specialMoveSquares.keySet()) sq.unshowCircle();
		specialMoveSquares.clear();
	}
	
	public void handle(MouseEvent e) {
		Square clickedSquare = (Square) e.getSource();
		Piece clickedSquarePiece = clickedSquare.getPiece();
		
		// If clicked square has piece with same color as current turn
		if (
			clickedSquarePiece != null &&
			clickedSquarePiece.isWhite() == chessGame.getIsWhiteTurn()
		) {
			if (clickedSquare == storedSquare) reset();
			else {
				Set<Coordinate> moveCoords = clickedSquarePiece.legalMoveCoords();
				Map<Coordinate, SpecialMoveImplementation> specialMoveCoords =
					clickedSquarePiece.legalSpecialMoveCoords();
				
				// If piece can move
				if (!moveCoords.isEmpty() || !specialMoveCoords.isEmpty()) {
					reset();
					storedSquare = clickedSquare;
					storedSquare.highlight();
					
					for (Coordinate coord : moveCoords) {
						Square moveSquare = board.getSquare(coord);
						moveSquares.add(moveSquare);
						moveSquare.showCircle();
					}
					
					for (
						Map.Entry<Coordinate, SpecialMoveImplementation> entry :
						specialMoveCoords.entrySet()
					) {
						Coordinate coord = entry.getKey();
						Square specialMoveSquare = board.getSquare(coord);
						specialMoveSquares.put(specialMoveSquare, entry.getValue());
						specialMoveSquare.showCircle();
					}
				}
			}
		// If piece square is stored, and piece can move to clicked square
		} else if (storedSquare != null) {
			Piece storedPiece = storedSquare.getPiece();
			if (moveSquares.contains(clickedSquare)) {
				chessGame.move(storedPiece, clickedSquare);
				reset();
			} else if (specialMoveSquares.containsKey(clickedSquare)) {
				specialMoveSquares.get(clickedSquare).doMoveEffect(storedPiece);
				chessGame.move(storedPiece, clickedSquare);
				reset();
			}
		}
	}
}
