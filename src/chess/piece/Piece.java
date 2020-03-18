package chess.piece;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import chess.logic.ChessGameException;
import chess.logic.Coordinate;
import chess.logic.RegularMove;
import chess.specialmove.SpecialMove;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public abstract class Piece {
	private static final String IMG_FOLDER = "img/";
	
	private boolean isWhite;
	private ObjectProperty<Coordinate> coord = new SimpleObjectProperty<>();
	
	public Piece(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	public Piece(Piece piece) {
		this.isWhite = piece.isWhite();
		setCoord(piece.getCoord());
	}
	
	public void setCoord(Coordinate newCoord) {
		this.coord.set(newCoord);
	}
	// Meant to potentially have special behavior
	public void move(Coordinate newCoord) { setCoord(newCoord); }
	
	public Set<RegularMove> potentialMiscMoves() { return new HashSet<>(); }
	public Set<RegularMove> potentialNonCaptureMoves() { return potentialMiscMoves(); }
	public Set<RegularMove> potentialCaptureMoves() { return potentialMiscMoves(); }
	public Set<SpecialMove> potentialSpecialMoves() { return new HashSet<>(); }
	
	protected final String getImgFolder() { return IMG_FOLDER; }
	protected abstract String getWhiteImgFileName();
	protected abstract String getBlackImgFileName();
	public final String getImgFilePath() {
		if (isWhite) return getWhiteImgFilePath();
		else return getBlackImgFilePath();
	}
	private final String getWhiteImgFilePath() { return getImgFolder() + getWhiteImgFileName(); }
	private final String getBlackImgFilePath() { return getImgFolder() + getBlackImgFileName(); }
	public final Image makeImg() {
		try {
			return new Image(new FileInputStream(getImgFilePath()));
		} catch (FileNotFoundException e) {
			// Is this clunky? Maybe
			// But I don't think there's any reason to use a checked exception
			// A FileNotFoundException here would be an error in the program, not user data
			throw new ChessGameException(e.getMessage());
		}
	}
	
	public abstract boolean canRepeatRegularMoves();
	
	public abstract int pointValue();
	
	public boolean isWhite() { return isWhite; }
	public Coordinate getCoord() { return coord.get(); }
	public ObjectProperty<Coordinate> coordProperty() { return coord; }
	
	public abstract Piece copy();
}
