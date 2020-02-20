package chess.ui;

import javafx.scene.input.MouseEvent;

// WIP
public class BoardMouseHandler {
	Board board;
	Square clickedSquare;
	
	public BoardMouseHandler(Board board) {
		this.board = board;
		
		for (Square[] row : board.getSquares()) {
			for (Square sq : row) {
				sq.setOnMouseClicked((MouseEvent event) -> 
				System.out.println(sq.getCoord())
				);
			}
		}
	}
}
