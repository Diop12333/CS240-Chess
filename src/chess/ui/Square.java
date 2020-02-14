package chess.ui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Square extends StackPane {
	private Coordinate coord;
	private Color defaultColor;
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
		getChildren().add(imgView);
		
		setOnMouseClicked(
			new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					System.out.println("Clicked at " + coord);
				}
			}
		);
	}
	
	public void setColor(Color color) {
		this.setBackground(
			new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY))
		);
	}
	public void setIsHighlighted(boolean b) {
		if (b) setColor(Color.GREEN);
		else setColor(defaultColor);
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
		imgView.setImage(piece.getImg());
	}
	
	public void clear() {
		piece = null;
		imgView.setImage(null);
	}
	
	public Coordinate getCoord() { return coord; }
	public Piece getPiece() { return piece; }
}
