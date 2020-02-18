package chess.ui;

import chess.piece.Piece;
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
	}
	
	public boolean isValidCoordinate(Coordinate coord) {
		boolean xValid = coord.getX() >= 0 && coord.getX() < dimensions.getX();
		boolean yValid = coord.getY() >= 0 && coord.getY() < dimensions.getY();
		return xValid && yValid;
	}
	
	public XY getDimensions() { return dimensions; }
	// Returns null if no piece on coord or coord is not on board
	public Piece getPiece(Coordinate coord) {
		Square coordSq = getSquare(coord);
		if (coordSq != null) return coordSq.getPiece();
		else return null;
	}
	public Square[][] getSquares() { return squares; }
	// Returns null if coord is not on board
	public Square getSquare(Coordinate coord) {
		if (isValidCoordinate(coord)) return squares[coord.getY()][coord.getX()];
		else return null;
	}
}
