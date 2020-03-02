package chess.logic;

import java.util.HashSet;
import java.util.Set;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Board {
	private XY dimensions;
	private ObjectProperty<Piece>[][] pieces;
	private Set<Piece> capturedPieces = new HashSet<>();
	
	private King whiteKing;
	private King blackKing;
	
	private LegalMoveLogic logic;
	
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
		
		logic = new LegalMoveLogic(this);
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
	
	public boolean isValidCoordinate(Coordinate coord) {
		return isValidCoordinate(coord.getX(), coord.getY());
	}
	public boolean isValidCoordinate(int x, int y) {
		boolean xValid = x >= 0 && x < dimensions.getX();
		boolean yValid = y >= 0 && y < dimensions.getY();
		return xValid && yValid;
	}
	
	public XY getDimensions() { return dimensions; }
	
	public King getWhiteKing() { return whiteKing; }
	public King getBlackKing() { return blackKing; }
	public King getKing(boolean isWhite) {
		if (isWhite) return whiteKing;
		else return blackKing;
	}
	
	public void setCoord(int x, int y, Piece piece) {
		setCoord(new Coordinate(x, y), piece);
	}
	public void setCoord(Coordinate coord, Piece piece) {
		// WARNING: Doesn't check for duplicate kings
		if (piece instanceof King) {
			King king = (King) piece;
			if (king.isWhite()) whiteKing = king;
			else blackKing = king;
		}
		
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
	public Set<Piece> getColorPieces(boolean white) {
		Set<Piece> colorPieces = new HashSet<>();
		for (Piece piece : getPieces()) {
			if (piece.isWhite() == white) colorPieces.add(piece);
		}
		return colorPieces;
	}
	
	public Set<Piece> getCapturedPieces() { return capturedPieces; }
	
	public LegalMoveLogic getLogic() { return logic; }
}
