package chess.ui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class BoardMouseHandler implements EventHandler<MouseEvent> {
	private Square storedSquare;
	
	public BoardMouseHandler(Board board) {
		for (Square[] row : board.getSquares()) {
			for (Square sq : row) {
				sq.setOnMouseClicked(this);
			}
		}
	}
	
	public void handle(MouseEvent e) {
		Square sq = (Square) e.getSource();
		
		if (storedSquare == null && sq.getPiece() != null) {
			storedSquare = sq;
			storedSquare.highlight();
		} else if (storedSquare != null && sq != storedSquare) {
			storedSquare.getPiece().move(sq.getCoord());
			storedSquare.unhighlight();
			storedSquare = null;
		}
	}
}
