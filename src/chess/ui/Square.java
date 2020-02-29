package chess.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import chess.logic.Coordinate;
import chess.logic.Piece;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
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
	
	private Piece piece;
	
	private Circle moveCircle = new Circle();
	private ImageView imgView = new ImageView();
	
	public Square(int x, int y, Color color) { this(new Coordinate(x, y), color); }
	public Square(Coordinate coord, Color color) {
		this.coord = coord;
		this.defaultColor = color;
		setColor(color);
		
		imgView.fitWidthProperty().bind(this.prefWidthProperty());
		imgView.fitHeightProperty().bind(this.prefHeightProperty());
		imgView.setSmooth(true);
		//imgView.setPreserveRatio(true);
		
		getChildren().add(imgView);
		
		moveCircle.setFill(HIGHLIGHT_COLOR);
		moveCircle.radiusProperty().bind(widthProperty().divide(10));
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
		
		if (piece != null) {
			try {
				imgView.setImage(new Image(new FileInputStream(piece.getImgFilePath())));
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException("Piece image could not be loaded");
			}
		} else {
			imgView.setImage(null);
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
	public void unhighlight() {
		setColor(defaultColor);
	}
	
	public Coordinate getCoord() { return coord; }
	public Piece getPiece() { return piece; }
	public BoardDisplay getBoard() { return (BoardDisplay) getParent(); }
}
