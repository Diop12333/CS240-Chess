package chess;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

// TODO: capability to contain transparent image of chess piece
// TODO: arbitrary square colors
public class Board {
	private GridPane grid;
	
	// row/column indices
	// rows go down, columns go right
	private Square[][] squares;
	
	public Board(int width, int length) {
		grid = new GridPane();
		squares = new Square[length][width];
		
		boolean black = true;
		for (int y = 0; y < length; y++) {
			Color color;
			for (int x = 0; x < width; x++) {
				if (black) color = Color.BLACK; else color = Color.WHITE;
				
				Square sq = new Square(color);
				sq.prefWidthProperty().bind(grid.widthProperty().divide(width));
				sq.prefHeightProperty().bind(grid.heightProperty().divide(length));
				
				grid.add(sq, x, y);
				squares[y][x] = sq;
				
				black = !black;
			}
			black = !black;
		}
	}
	public Board() {
		this(8, 8);
	}
	
	public GridPane getGrid() { return grid; }
	public Square[][] getSquares() { return squares; }
}
