package chess.logic;

import java.util.HashSet;
import java.util.Set;

import chess.specialmove.SpecialMove;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class Piece {
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
	
	public abstract Set<Move> potentialNonCaptureMoves();
	public Set<Move> potentialCaptureMoves() { return potentialNonCaptureMoves(); }
	public Set<SpecialMove> potentialSpecialMoves() { return new HashSet<>(); }
	
	protected final String getImgFolder() { return "img/"; }
	protected abstract String getWhiteImgFileName();
	protected abstract String getBlackImgFileName();
	public final String getImgFilePath() {
		if (isWhite) return getWhiteImgFilePath();
		else return getBlackImgFilePath();
	}
	private final String getWhiteImgFilePath() { return getImgFolder() + getWhiteImgFileName(); }
	private final String getBlackImgFilePath() { return getImgFolder() + getBlackImgFileName(); }
	
	// Only applies to regular moves
	public abstract boolean canRepeatMoves();
	public boolean isWhite() { return isWhite; }
	public Coordinate getCoord() { return coord.get(); }
	public ObjectProperty<Coordinate> coordProperty() { return coord; }
	
	public abstract String notation(); // return letter(s) for chess notation
	
	public abstract Piece copy();
}
