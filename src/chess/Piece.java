package chess;

import javafx.scene.image.Image;

public abstract class Piece {
	private static final String imgFolder = "img/";
	
	private static String whiteImgFileName;
	private static String blackImgFileName;
	
	private Board board;
	private Coordinate coord;
	
	private Image img;
	
	public Piece(Board board, Coordinate coord, boolean white) {
		this.board = board;
		this.coord = coord;
		
		// add piece to board here
		
		if (white) img = new Image(imgFolder + whiteImgFileName);
		else img = new Image(imgFolder + blackImgFileName);
	}
	
	public Image getImg() { return img; }
	
	public void move(Coordinate newCoord) {} // TODO
	
	// Returns coordinates piece can move to
	public abstract Coordinate[] validMoves();
}
