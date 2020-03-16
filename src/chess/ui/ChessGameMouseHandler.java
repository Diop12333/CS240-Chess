package chess.ui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.logic.ChessGame;
import chess.logic.LegalMoveLogic;
import chess.piece.Piece;
import chess.logic.Coordinate;
import chess.specialmove.SpecialMoveImplementation;

public class ChessGameMouseHandler implements EventHandler<MouseEvent> {
	private ChessGame chessGame;
	private BoardDisplay boardDisplay;
	private LegalMoveLogic logic;
	private Square storedSquare;
	private Set<Square> moveSquares = new HashSet<>();
	
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
	
	public void reset() {
		if (storedSquare != null) {
			storedSquare.setHighlighted(false);
			storedSquare = null;
		}
		
		for (Square sq : moveSquares) sq.removeCircle();
		moveSquares.clear();
	}
	
	public void handle(MouseEvent e) {
		if (chessGame.waiting()) return;
		
		Square clickedSquare = (Square) e.getSource();
		Piece clickedSquarePiece = clickedSquare.getPiece();
		
		// If clicked square has piece with same color as current turn
		if (
			clickedSquarePiece != null &&
			clickedSquarePiece.isWhite() == chessGame.isWhiteTurn()
		) {
			if (clickedSquare == storedSquare) reset();
			else {
				Map<Coordinate, SpecialMoveImplementation> moveCoords =
					logic.legalMoveCoords(clickedSquarePiece);
				
				// If piece can move
				if (!moveCoords.isEmpty()) {
					reset();
					storedSquare = clickedSquare;
					storedSquare.setHighlighted(true);
					
					for (Coordinate coord : moveCoords.keySet()) {
						Square moveSquare = boardDisplay.getSquare(coord);
						moveSquares.add(moveSquare);
						moveSquare.addCircle();
					}
				}
			}
		// If piece square is stored, and piece can move to clicked square
		} else if (storedSquare != null) {
			Piece storedPiece = storedSquare.getPiece();
			if (moveSquares.contains(clickedSquare)) {
				chessGame.makeMove(storedPiece, clickedSquare.getCoord());
				reset();
			}
		}
	}
}
