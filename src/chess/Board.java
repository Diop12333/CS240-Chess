package chess;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

// TODO: capability to contain transparent image of chess piece
// TODO: arbitrary square colors
public class Board {
	private GridPane grid;
	
	// y goes down
	private Square[][] squares;
	
	public Board() { this(8, 8); }
	public Board(int xSqAmount, int ySqAmount) {
		this(xSqAmount,ySqAmount, Color.SADDLEBROWN, Color.ANTIQUEWHITE);
	}
	public Board(int xSqAmount, int ySqAmount, Color color1, Color color2) {
		grid = new GridPane();
		squares = new Square[ySqAmount][xSqAmount];
		
		for (int y = 0; y < ySqAmount; y++) {
			Color color;
			boolean useColor1 = y % 2 == 0;
			
			for (int x = 0; x < xSqAmount; x++) {
				if (useColor1) color = color1; else color = color2;
				
				Square sq = new Square(color);
				sq.prefWidthProperty().bind(grid.widthProperty().divide(xSqAmount));
				sq.prefHeightProperty().bind(grid.heightProperty().divide(ySqAmount));
				
				grid.add(sq, x, y);
				squares[y][x] = sq;
				
				useColor1 = !useColor1;
			}
		}
	}
	
	public GridPane getGrid() { return grid; }
	public Square[][] getSquares() { return squares; }
	
	public Square getSquare(Coordinate coord) {
		return squares[coord.getY()][coord.getX()];
	}
}
