package chess.ui;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Square extends StackPane {
	Piece piece;
	ImageView imgView = new ImageView();
	
	public Square(Color c) {
		this.setBackground(
			new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY))
		);
		imgView.fitWidthProperty().bind(this.prefWidthProperty());
		imgView.fitHeightProperty().bind(this.prefHeightProperty());
		//imgView.setPreserveRatio(true);
		getChildren().add(imgView);
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
		imgView.setImage(piece.getImg());
	}
	
	public void clear() {
		piece = null;
		imgView.setImage(null);
	}
	
	public Piece getPiece() { return piece; }
}
