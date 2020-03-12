package chess.logic;

import java.util.HashSet;
import java.util.Set;

import chess.specialmove.SpecialMoveImplementation;
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
			addNewPiece(piece.getCoord(), piece.copy());
		}
		
		for (Piece piece : board.getCapturedPieces()) {
			capturedPieces.add(piece.copy());
		}
	}
	
	public boolean withinDimensions(Coordinate coord) {
		return withinDimensions(coord.getX(), coord.getY());
	}
	public boolean withinDimensions(int x, int y) {
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
		setCoord(x, y, piece, true);
	}
	public void setCoord(int x, int y, Piece piece, boolean processCapture) {
		setCoord(new Coordinate(x, y), piece, processCapture);
	}
	public void setCoord(Coordinate coord, Piece piece) {
		setCoord(coord, piece, true);
	}
	// processCapture: if piece on coord is replaced or deleted, add it to capturedPieces
	public void setCoord(Coordinate coord, Piece piece, boolean processCapture) {
		Piece currPiece = getPiece(coord);
		if (processCapture && currPiece != null) {
			capturedPieces.add(currPiece);
		}
		
		if (piece != null) {
			piece.setCoord(coord);
		}
		
		pieces[coord.getY()][coord.getX()].set(piece);
	}
	
	public void addNewPiece(int x, int y, Piece piece) {
		addNewPiece(new Coordinate(x, y), piece);
	}
	public void addNewPiece(Coordinate coord, Piece piece) {
		if (piece instanceof King) {
			King king = (King) piece;
			if (king.isWhite()) whiteKing = king;
			else blackKing = king;
		}
		
		setCoord(coord, piece, false);
		
		piece.coordProperty().addListener((prop, oldCoord, newCoord) -> {
			Piece newCoordPiece = getPiece(newCoord);
			if (newCoordPiece != null) {
				capturedPieces.add(newCoordPiece);
			}
			
			setCoord(newCoord, piece, true);
			
			if (oldCoord != null) setCoord(oldCoord, null, false);
		});
	}
	
	public void reset() {
		capturedPieces.clear();
		whiteKing = null;
		blackKing = null;
		for (int y = 0; y < dimensions.getY(); y++) {
			for (int x = 0; x < dimensions.getX(); x++) {
				pieces[y][x].set(null);
			}
		}
	}
	
	public void makeMove(Piece piece, Coordinate coord) { makeMove(piece, coord, null); }
	public void makeMove(Piece piece, Coordinate coord, SpecialMoveImplementation impl) {
		// Make sure en passant can only occur turn after two-square move
		for (Piece p : getPieces()) {
			if (p instanceof Pawn) {
				Pawn pawn = (Pawn) p;
				pawn.setEnPassantable(false);
			}
		}
		
		if (impl != null) {
			impl.doPreMoveEffect(piece, this);
			piece.move(coord);
			impl.doPostMoveEffect(piece, this);
		} else {
			piece.move(coord);
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
		if (withinDimensions(x, y)) return pieces[y][x];
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
