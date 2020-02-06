package chess;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Square extends StackPane {
	ImageView imgView = new ImageView();
	
	public Square(Color c) {
		this.setBackground(
			new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY))
		);
		
		this.getChildren().add(imgView);
	}
	
	public void setImage(Image img) {
		imgView.setImage(img);
	}
}
