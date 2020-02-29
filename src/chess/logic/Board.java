package chess.logic;

import java.util.HashSet;
import java.util.Set;

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
	public Board(Board board) {
		this(board.getDimensions());
		
		for (Piece piece : board.getPieces()) {
			setCoord(piece.getCoord(), piece.copy());
		}
		
		for (Piece piece : board.getCapturedPieces()) {
			capturedPieces.add(piece.copy());
		}
	}
	
	public void setCoord(int x, int y, Piece piece) {
		setCoord(new Coordinate(x, y), piece);
	}
	public void setCoord(Coordinate coord, Piece piece) {
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
				
				setCoord(newCoord, piece);
				
				if (oldCoord != null) setCoord(oldCoord, null);
			});
			
			piece.setCoord(coord);
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
		if (isValidCoordinate(x, y)) return pieces[y][x];
		else return new SimpleObjectProperty<Piece>();
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
	
	public Set<Piece> getCapturedPieces() { return capturedPieces; }
}
