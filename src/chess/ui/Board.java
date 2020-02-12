package chess.ui;

import javafx.geometry.Orientation;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

// TODO: capability to contain transparent image of chess piece
// TODO: arbitrary square colors
public class Board extends GridPane {
	// y goes down
	private Square[][] squares;
	
	public Board() { this(8, 8); }
	public Board(int xSqAmount, int ySqAmount) {
		this(xSqAmount,ySqAmount, Color.SADDLEBROWN, Color.ANTIQUEWHITE);
	}
	public Board(int xSqAmount, int ySqAmount, Color color1, Color color2) {
		squares = new Square[ySqAmount][xSqAmount];
		
		for (int y = 0; y < ySqAmount; y++) {
			Color color;
			boolean useColor1 = y % 2 == 0;
			
			for (int x = 0; x < xSqAmount; x++) {
				if (useColor1) color = color1; else color = color2;
				
				Square sq = new Square(color);
				sq.prefWidthProperty().bind(widthProperty().divide(xSqAmount));
				sq.prefHeightProperty().bind(heightProperty().divide(ySqAmount));
				
				add(sq, x, y);
				squares[y][x] = sq;
				
				useColor1 = !useColor1;
			}
		}
	}
	
	public Square[][] getSquares() { return squares; }
	
	public Square getSquare(Coordinate coord) {
		return squares[coord.getY()][coord.getX()];
	}
	
	// Initializing Mouse Movement
	EventHandler<MouseEvent> interactMove = new EventHandler<MouseEvent>()
	{
		@Override
		
		// Output of Interaction
		public void handle(MouseEvent m)
		{
			// [INSERT CODE]
		}
		// End of Output
	};
	// End of Initialization
}
