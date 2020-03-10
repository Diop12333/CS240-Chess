package chess.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

// There might be a better way to do this, but I can't be bothered right now
// or maybe ever
public class ResizableImageViewContainer extends StackPane {
	private ImageView imageView;
	public ResizableImageViewContainer() {
		imageView = new ImageView();
		imageView.fitWidthProperty().bind(prefWidthProperty());
		imageView.fitHeightProperty().bind(prefHeightProperty());
		getChildren().add(imageView);
	}
	public ResizableImageViewContainer(Image image) {
		this();
		imageView.setImage(image);
	}
	
	public ImageView getImageView() { return imageView; }
}
