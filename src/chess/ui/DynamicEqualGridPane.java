package chess.ui;

import chess.logic.XY;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class DynamicEqualGridPane extends GridPane {
	private XY dimensions;
	
	public DynamicEqualGridPane(XY dimensions) {
		this.dimensions = dimensions;
	}
	
	// Uses Region instead of Node to access prefWidth/Height properties
	public void add(Region child, int x, int y) {
		super.add(child, x, y);
		
		child.prefWidthProperty().bind(widthProperty().divide(dimensions.getX()));
		child.prefHeightProperty().bind(heightProperty().divide(dimensions.getY()));
	}
}
