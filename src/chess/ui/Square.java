package chess.ui;

import chess.piece.Piece;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Square extends StackPane {
	private Coordinate coord;
	private Color defaultColor;
	private static Color HIGHLIGHT_COLOR = Color.GREEN;
	private Circle moveCircle = new Circle(5, HIGHLIGHT_COLOR);
	private Piece piece;
	private ImageView imgView = new ImageView();
	
	public Square(Coordinate coord, Color color) {
		this.coord = coord;
		this.defaultColor = color;
		setColor(color);
		
		imgView.fitWidthProperty().bind(this.prefWidthProperty());
		imgView.fitHeightProperty().bind(this.prefHeightProperty());
		imgView.setSmooth(true);
		//imgView.setPreserveRatio(true);
		
		moveCircle.setVisible(false);
		
		getChildren().add(imgView);
		getChildren().add(moveCircle);
	}
	
	public void setColor(Color color) {
		this.setBackground(
			new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY))
		);
	}
	
	public void showCircle() {
		moveCircle.setVisible(true);
	}
	public void unshowCircle() {
		moveCircle.setVisible(false);
	}
	
	public void highlight() {
		setColor(HIGHLIGHT_COLOR);
	}
	public void unhighlight() {
		setColor(defaultColor);
	}
	
	public void setPiece(Piece piece) {
		if (this.piece != null) getChildren().remove(this.piece);
		this.piece = piece;
		piece.fitWidthProperty().bind(this.prefWidthProperty());
		piece.fitHeightProperty().bind(this.prefHeightProperty());
		getChildren().add(piece);
	}
	
	public void clear() {
		if (piece != null) {
			getChildren().remove(piece);
			piece = null;
		}
	}
	
	public Board getBoard() { return (Board) getParent(); }
	public Coordinate getCoord() { return coord; }
	public Piece getPiece() { return piece; }
}
