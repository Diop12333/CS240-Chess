package chess.ui;

import chess.logic.Coordinate;
import chess.logic.Piece;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Square extends ResizableImageViewContainer {
	private Coordinate coord;
	private Color defaultColor;
	private static Color HIGHLIGHT_COLOR = Color.GREEN;
	private static Color THREATENED_COLOR = Color.RED;
	
	private Piece piece;
	
	private Circle moveCircle = new Circle();
	
	public Square(int x, int y, Color color) { this(new Coordinate(x, y), color); }
	public Square(Coordinate coord, Color color) {
		this.coord = coord;
		this.defaultColor = color;
		setColor(color);
		
		moveCircle.setFill(HIGHLIGHT_COLOR);
		moveCircle.radiusProperty().bind(widthProperty().divide(10));
		
		getImageView().setSmooth(true);
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
		
		if (piece != null) {
			getImageView().setImage(piece.makeImg());
		} else {
			getImageView().setImage(null);
		}
	}
	
	public void setColor(Color color) {
		this.setBackground(
			new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY))
		);
	}
	
	public void addCircle() {
		getChildren().add(moveCircle);
	}
	public void removeCircle() {
		getChildren().remove(moveCircle);
	}
	
	public void highlight() {
		setColor(HIGHLIGHT_COLOR);
	}
	// TODO: prevent threatened color from disappearing when highlighted and unhighlighted
	public void showThreatened() {
		setColor(THREATENED_COLOR);
	}
	
	public void resetColor() {
		setColor(defaultColor);
	}
	
	public Coordinate getCoord() { return coord; }
	public Piece getPiece() { return piece; }
	public BoardDisplay getBoard() { return (BoardDisplay) getParent(); }
}
