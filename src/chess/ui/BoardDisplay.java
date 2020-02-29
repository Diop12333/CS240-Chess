package chess.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import chess.piece.Board;
import chess.piece.Coordinate;
import chess.piece.Piece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

// TODO: arbitrary square colors
public class BoardDisplay extends GridPane {
	private Board board;
	// y goes down
	private Square[][] squares;
	
	public BoardDisplay(Board board) { this(board, Color.SADDLEBROWN, Color.ANTIQUEWHITE); }
	public BoardDisplay(Board board, Color color1, Color color2) {
		XY dimensions = board.getDimensions();
		int xSqAmount = dimensions.getX();
		int ySqAmount = dimensions.getY();
		
		squares = new Square[ySqAmount][xSqAmount];
		
		for (int y = 0; y < ySqAmount; y++) {
			Color color;
			boolean useColor1 = y % 2 == 0;
			
			for (int x = 0; x < xSqAmount; x++) {
				if (useColor1) color = color1; else color = color2;
				
				Square sq = new Square(x, y, color);
				sq.prefWidthProperty().bind(widthProperty().divide(xSqAmount));
				sq.prefHeightProperty().bind(heightProperty().divide(ySqAmount));
				
				sq.setPiece(board.getPiece(x, y));
				board.getPieceProperty(x, y).addListener(
					(prop, oldPiece, newPiece) -> sq.setPiece(newPiece)
				);
				
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
}
