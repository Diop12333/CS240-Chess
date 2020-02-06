package chess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import javafx.scene.image.Image;

public abstract class Piece {
	private boolean white;
	private Board board;
	private Coordinate coord;
	private Image img;
	
	public Piece(boolean white, Board board, Coordinate coord) throws FileNotFoundException {
		this.white = white;
		this.board = board;
		this.coord = coord;
		
		String path;
		if (white) path = getWhiteImgFilePath();
		else path = getBlackImgFilePath();
		
		FileInputStream imgFileStream;
		imgFileStream = new FileInputStream(path);
		img = new Image(imgFileStream);
		
		board.getSquare(coord).setImage(img);
	}
	
	public void move(Coordinate newCoord) {} // TODO
	
	// Returns coordinates piece can move to
	public abstract List<Coordinate> validMoves();
	
	public final String getImgFolder() { return "img/"; }
	public abstract String getWhiteImgFileName();
	public abstract String getBlackImgFileName();
	public String getWhiteImgFilePath() { return getImgFolder() + getWhiteImgFileName(); }
	public String getBlackImgFilePath() { return getImgFolder() + getBlackImgFileName(); }
	
	public boolean isWhite() { return white; }
	public Board getBoard() { return board; }
	public Coordinate getCoord() { return coord; }
	public Image getImg() { return img; }
	
	public String toString() { return null; } // return letter for chess notation
}
