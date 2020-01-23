package chess;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {
	private GridPane grid;
	
	public Board(int width, int length) {
		grid = new GridPane();
		
		boolean black = true;
		for (int x = 0; x < width; x++) {
			Color color;
			for (int y = 0; y < length; y++) {
				if (black) color = Color.BLACK; else color = Color.WHITE;
				Rectangle rect = new Rectangle(25, 25, color);
				
				grid.add(rect, x, y);
				
				black = !black;
			}
			black = !black;
		}
	}
	public Board() {
		this(8, 8);
	}
	
	public GridPane getGrid() { return grid; }
}
