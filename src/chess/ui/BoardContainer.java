package chess.ui;

import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;

public class BoardContainer extends Region {
	Board board;
	public BoardContainer() {
		this.board = new Board();
		this.getChildren().add(board);
	}
	
	protected void layoutChildren() {
		super.layoutChildren();
		double smallSide = Math.min(getWidth(), getHeight());
		for (Node n : getChildren()) {
			if (getWidth() <= getHeight()) {
				n.resize(getWidth(), getWidth());
				n.relocate(0, getHeight() / 2 - getWidth() / 2);
			} else {
				n.resize(getHeight(), getHeight());
				n.relocate(getWidth() / 2 - getHeight() / 2, 0);
			}
		}
	}
	
	public Board getBoard() { return board; }
}
