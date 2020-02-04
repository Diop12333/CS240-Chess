package chess;

import javafx.scene.image.Image;

public abstract class Piece {
	private static final String imgFolder = "img/";
	
	private static String whiteImgFileName;
	private static String blackImgFileName;
	
	private static Image img;
	
	public Piece(boolean white) {
		if (white) img = new Image(imgFolder + whiteImgFileName);
		else img = new Image(imgFolder + blackImgFileName);
	}
	
	public Image getImg() { return img; }
	
	// Returns coordinates piece can move to
	public abstract Coordinate[] validMoves();
}
