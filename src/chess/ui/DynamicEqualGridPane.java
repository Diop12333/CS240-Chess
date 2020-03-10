package chess.ui;

import chess.logic.XY;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

public class DynamicEqualGridPane extends GridPane {
	private XY dimensions;
	
	public DynamicEqualGridPane(int x, int y) {
		this(new XY(x, y));
	}
	public DynamicEqualGridPane(XY dimensions) {
		this.dimensions = dimensions;
		for (int x = 0; x < dimensions.getX(); x++) {
			ColumnConstraints col = new ColumnConstraints();
			col.setPercentWidth(100 / (double) dimensions.getX());
			col.setHgrow(Priority.ALWAYS);
			getColumnConstraints().add(col);
		}
		for (int y = 0; y < dimensions.getY(); y++) {
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(100 / (double) dimensions.getY());
			row.setVgrow(Priority.ALWAYS);
			getRowConstraints().add(row);
		}
	}
	
	// Uses Region instead of Node to access prefWidth/Height properties
	public void add(Region child, int x, int y) {
		super.add(child, x, y);
		
		DoubleBinding dividedWidthBinding = prefWidthProperty().divide(dimensions.getX());
		DoubleBinding dividedHeightBinding = prefHeightProperty().divide(dimensions.getY());
		child.prefWidthProperty().bind(dividedWidthBinding);
		child.prefHeightProperty().bind(dividedHeightBinding);
		
		setHgrow(child, Priority.ALWAYS);
		setVgrow(child, Priority.ALWAYS);
	}
}
