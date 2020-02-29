package chess.piece;

import java.util.HashSet;
import java.util.Set;

import chess.ui.XY;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Board {
	private XY dimensions;
	private ObjectProperty<Piece>[][] pieces;
	private Set<Piece> capturedPieces = new HashSet<>();
	
	public Board() { this(new XY(8, 8)); }
	@SuppressWarnings("unchecked")
	public Board(XY dimensions) {
		this.dimensions = dimensions;
		
		pieces =
			(ObjectProperty<Piece>[][])
			new SimpleObjectProperty[dimensions.getY()][dimensions.getX()];
		
		for (int y = 0; y < dimensions.getY(); y++) {
			for (int x = 0; x < dimensions.getX(); x++) {
				pieces[y][x] = new SimpleObjectProperty<Piece>();
			}
		}
	}
	
	public void setPiece(Piece piece, int x, int y) {
		setPiece(piece, new Coordinate(x, y));
	}
	public void setPiece(Piece piece, Coordinate coord) {
		Piece currPiece = getPiece(coord);
		if (currPiece != null) {
			capturedPieces.add(currPiece);
		}
		
		pieces[coord.getY()][coord.getX()].set(piece);
		
		if (piece != null) {
			piece.coordProperty().addListener((prop, oldCoord, newCoord) -> {
				Piece newCoordPiece = getPiece(newCoord);
				if (newCoordPiece != null) {
					capturedPieces.add(newCoordPiece);
				}
				
				setPiece(piece, newCoord);
				
				if (oldCoord != null) setPiece(null, oldCoord);
			});
			
			piece.move(coord);
		}
	}
	
	public boolean isValidCoordinate(Coordinate coord) {
		return isValidCoordinate(coord.getX(), coord.getY());
	}
	public boolean isValidCoordinate(int x, int y) {
		boolean xValid = x >= 0 && x < dimensions.getX();
		boolean yValid = y >= 0 && y < dimensions.getY();
		return xValid && yValid;
	}
	
	public XY getDimensions() { return dimensions; }
	
	public Coordinate getCoord(Piece piece) {
		for (int y = 0; y < dimensions.getY(); y++) {
			for (int x = 0; x < dimensions.getX(); x++) {
				if (getPiece(x, y) == piece) return new Coordinate(x, y);
			}
		}
		return null;
	}
	public Piece getPiece(int x, int y) {
		return getPiece(new Coordinate(x, y));
	}
	public Piece getPiece(Coordinate coord) {
		return getPieceProperty(coord).get();
	}
	public ObjectProperty<Piece> getPieceProperty(Coordinate coord) {
		return getPieceProperty(coord.getX(), coord.getY());
	}
	public ObjectProperty<Piece> getPieceProperty(int x, int y) {
		return pieces[y][x];
	}
	
	public Set<Piece> getPieces() {
		Set<Piece> pieces = new HashSet<>();
		
		for (int y = 0; y < dimensions.getY(); y++) {
			for (int x = 0; x < dimensions.getX(); x++) {
				Piece piece = getPiece(new Coordinate(x, y));
				if (piece != null) {
					pieces.add(piece);
				}
			}
		}
		
		return pieces;
	}
}
