package chess.ui;

import chess.piece.Piece;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Square extends StackPane {
	private Coordinate coord;
	private Color defaultColor;
	private static Color HIGHLIGHT_COLOR = Color.GREEN;
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
	}
	
	public void setColor(Color color) {
		this.setBackground(
			new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY))
		);
	}
	public void highlight() { setColor(HIGHLIGHT_COLOR); }
	public void unhighlight() { setColor(defaultColor); }
	
	public void setPiece(Piece piece) {
		if (this.piece != null) getChildren().remove(this.piece);
		this.piece = piece;
		getChildren().add(piece);
	}
	
	public void clear() {
		piece = null;
		imgView.setImage(null);
	}
	
	public Board getBoard() { return (Board) getParent(); }
	public Coordinate getCoord() { return coord; }
	public Piece getPiece() { return piece; }
}
