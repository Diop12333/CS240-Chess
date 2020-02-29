package chess.ui;

import javafx.scene.layout.Region;
import javafx.scene.Node;

public class BoardDisplayContainer extends Region {
	private BoardDisplay board;
	public BoardDisplayContainer(BoardDisplay board) {
		this.board = board;
		this.getChildren().add(board);
	}
	
	// Maintains 1:1 aspect ratio, keeps board centered
	// TODO: allow non-1:1 aspect ratios (important for custom boards)
	protected void layoutChildren() {
		for (Node n : getChildren()) {
			if (getWidth() <= getHeight()) {
				double side = getWidth();
				n.resize(side, side);
				n.relocate(0, getHeight() / 2 - side / 2);
			} else {
				double side = getHeight();
				n.resize(side, side);
				n.relocate(getWidth() / 2 - side / 2, 0);
			}
		}
	}
	
	public BoardDisplay getBoard() { return board; }
}
