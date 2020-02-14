package chess.ui;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

// TODO: capability to contain transparent image of chess piece
// TODO: arbitrary square colors
public class Board extends GridPane {
	private XY dimensions;
	// y goes down
	private Square[][] squares;
	
	public Board() { this(new XY(8, 8)); }
	public Board(XY dimensions) {
		this(dimensions, Color.SADDLEBROWN, Color.ANTIQUEWHITE);
	}
	public Board(XY dimensions, Color color1, Color color2) {
		this.dimensions = dimensions;
		int xSqAmount = dimensions.getX();
		int ySqAmount = dimensions.getY();
		
		squares = new Square[ySqAmount][xSqAmount];
		
		for (int y = 0; y < ySqAmount; y++) {
			Color color;
			boolean useColor1 = y % 2 == 0;
			
			for (int x = 0; x < xSqAmount; x++) {
				if (useColor1) color = color1; else color = color2;
				
				Square sq = new Square(new Coordinate(x, y), color);
				sq.prefWidthProperty().bind(widthProperty().divide(xSqAmount));
				sq.prefHeightProperty().bind(heightProperty().divide(ySqAmount));
				
				add(sq, x, y);
				squares[y][x] = sq;
				
				useColor1 = !useColor1;
			}
		}
		
		new BoardMouseHandler(this);
	}
	
	public XY getDimensions() { return dimensions; }
	public Square[][] getSquares() { return squares; }
	public Square getSquare(Coordinate coord) {
		return squares[coord.getY()][coord.getX()];
	}
}
