package chess;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Square extends StackPane {
	// TODO: capability to contain transparent image of chess piece
	// or maybe contain label with chess piece character
	// fuck if i know, gotta discuss this
	
	public Square(Color c) {
		this.setBackground(
			new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY))
		);
	}
}
