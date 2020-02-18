package ui;

import javafx.scene.layout.Region;
import javafx.scene.Node;

public class BoardContainer extends Region {
	private Board board;
	public BoardContainer(Board board) {
		this.board = board;
		this.getChildren().add(board);
	}
	
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
	
	public Board getBoard() { return board; }
}
