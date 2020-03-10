package chess.ui;

import javafx.scene.layout.Region;

public class BoardDisplayContainer extends Region {
	private BoardDisplay board;
	public BoardDisplayContainer(BoardDisplay board) {
		this.board = board;
		this.getChildren().add(board);
	}
	
	// Maintains 1:1 aspect ratio, keeps board centered
	// TODO: allow non-1:1 aspect ratios (important for custom boards)
	protected void layoutChildren() {
		double side;
		if (getWidth() <= getHeight()) {
			side = getWidth();
			board.relocate(0, getHeight() / 2 - side / 2);
		} else {
			side = getHeight();
			board.relocate(getWidth() / 2 - side / 2, 0);
		}
		board.resize(side, side);
		board.prefWidthProperty().set(side);
		board.prefHeightProperty().set(side);
	}
	
	public BoardDisplay getBoard() { return board; }
}
