package chess;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

// TODO: capability to contain transparent image of chess piece
// TODO: arbitrary square colors
public class Board {
	private GridPane grid;
	
	// y goes down
	private Map<Coordinate, Square> squares;
	
	public Board(int xSqAmount, int ySqAmount) {
		grid = new GridPane();
		squares = new HashMap<Coordinate, Square>();
		
		Color color1 = Color.BLACK;
		Color color2 = Color.WHITE;
		for (int y = 0; y < ySqAmount; y++) {
			Color color;
			boolean useColor1 = y % 2 == 0;
			
			for (int x = 0; x < xSqAmount; x++) {
				if (useColor1) color = color1; else color = color2;
				
				Square sq = new Square(color);
				sq.prefWidthProperty().bind(grid.widthProperty().divide(xSqAmount));
				sq.prefHeightProperty().bind(grid.heightProperty().divide(ySqAmount));
				
				grid.add(sq, x, y);
				squares.put(new Coordinate(x, y), sq);
				
				useColor1 = !useColor1;
			}
		}
	}
	public Board() { this(8, 8); }
	
	public GridPane getGrid() { return grid; }
	public Map<Coordinate, Square> getSquares() { return squares; }
}
