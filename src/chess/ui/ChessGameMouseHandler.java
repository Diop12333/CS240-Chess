package chess.ui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.logic.ChessGame;
import chess.logic.LegalMoveLogic;
import chess.logic.Coordinate;
import chess.logic.Piece;
import chess.specialmove.SpecialMoveImplementation;

public class ChessGameMouseHandler implements EventHandler<MouseEvent> {
	private ChessGame chessGame;
	private BoardDisplay boardDisplay;
	private LegalMoveLogic logic;
	private Square storedSquare;
	private Set<Square> moveSquares = new HashSet<>();
	private Map<Square, SpecialMoveImplementation> specialMoveSquares = new HashMap<>();
	
	public ChessGameMouseHandler(ChessGame chessGame) {
		this.chessGame = chessGame;
		boardDisplay = chessGame.getBoardDisplay();
		logic = chessGame.getBoard().getLogic();
		
		for (Square[] row : boardDisplay.getSquares()) {
			for (Square sq : row) {
				sq.setOnMouseClicked(this);
			}
		}
	}
	
	private void reset() {
		if (storedSquare != null) {
			storedSquare.resetColor();
			storedSquare = null;
		}
		
		Set<Square> sqUnion = new HashSet<>();
		sqUnion.addAll(moveSquares);
		sqUnion.addAll(specialMoveSquares.keySet());
		for (Square sq : sqUnion) sq.removeCircle();
		moveSquares.clear();
		specialMoveSquares.clear();
	}
	
	public void handle(MouseEvent e) {
		if (chessGame.waitingForPromotion()) return;
		
		Square clickedSquare = (Square) e.getSource();
		Piece clickedSquarePiece = clickedSquare.getPiece();
		
		// If clicked square has piece with same color as current turn
		if (
			clickedSquarePiece != null &&
			clickedSquarePiece.isWhite() == chessGame.isWhiteTurn()
		) {
			if (clickedSquare == storedSquare) reset();
			else {
				Set<Coordinate> moveCoords = logic.legalMoveCoords(clickedSquarePiece);
				Map<Coordinate, SpecialMoveImplementation> specialMoveCoords =
					logic.legalSpecialMoveCoords(clickedSquarePiece);
				
				// If piece can move
				if (!moveCoords.isEmpty() || !specialMoveCoords.isEmpty()) {
					reset();
					storedSquare = clickedSquare;
					storedSquare.highlight();
					
					for (Coordinate coord : moveCoords) {
						Square moveSquare = boardDisplay.getSquare(coord);
						moveSquares.add(moveSquare);
						moveSquare.addCircle();
					}
					
					for (
						Map.Entry<Coordinate, SpecialMoveImplementation> entry :
						specialMoveCoords.entrySet()
					) {
						Coordinate coord = entry.getKey();
						Square specialMoveSquare = boardDisplay.getSquare(coord);
						specialMoveSquares.put(specialMoveSquare, entry.getValue());
						specialMoveSquare.addCircle();
					}
				}
			}
		// If piece square is stored, and piece can move to clicked square
		} else if (storedSquare != null) {
			Piece storedPiece = storedSquare.getPiece();
			if (moveSquares.contains(clickedSquare)) {
				chessGame.move(storedPiece, clickedSquare.getCoord());
				reset();
			} else if (specialMoveSquares.containsKey(clickedSquare)) {
				SpecialMoveImplementation implementation = specialMoveSquares.get(clickedSquare);
				
				implementation.doPreMoveEffect(storedPiece, chessGame.getBoard());
				chessGame.move(storedPiece, clickedSquare.getCoord());
				implementation.doPostMoveEffect(storedPiece, chessGame.getBoard());
				
				reset();
			}
		}
	}
}
