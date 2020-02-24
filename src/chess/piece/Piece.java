package chess.piece;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.ui.Board;
import chess.ui.ChessGame;
import chess.ui.Coordinate;
import chess.ui.Square;
import chess.ui.XY;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Piece extends ImageView {
	private boolean isWhite;
	
	public Piece(boolean isWhite) throws FileNotFoundException {
		this.isWhite = isWhite;
		
		String path;
		if (isWhite) path = getWhiteImgFilePath();
		else path = getBlackImgFilePath();
		
		FileInputStream imgFileStream = new FileInputStream(path);
		setImage(new Image(imgFileStream));
	}
	
	public void move(Square newSquare) {
		getSquare().clear();
		newSquare.setPiece(this);
	}
	
	// Inverts y-axis for black
	protected Coordinate coordAfterMove(Move move) { return coordAfterMove(getCoord(), move); }
	protected Coordinate coordAfterMove(Coordinate initCoord, Move move) {
		return coordAfterShift(initCoord, move.getShift());
	}
	protected Coordinate coordAfterShift(XY shift) { return coordAfterShift(getCoord(), shift); }
	protected Coordinate coordAfterShift(Coordinate initCoord, XY shift) {
		if (!isWhite) shift = new XY(shift.getX(), -shift.getY());
		return initCoord.shifted(shift);
	}
	
	// Return moves for white - black's moves are automatically inverted
	// potentialNonCaptureMoves returns moves that can be made on an empty square
	// potentialCaptureMoves returns moves that can be made to capture
	// By default, no distinction made between non-capture and capture moves
	protected abstract Set<Move> potentialNonCaptureMoves();
	protected Set<Move> potentialCaptureMoves() { return potentialNonCaptureMoves(); }
	public Set<Coordinate> legalMoveCoords() {
		Set<Coordinate> legalCoords = new HashSet<>();
		
		Set<Move> moveUnion = new HashSet<>();
		moveUnion.addAll(potentialNonCaptureMoves());
		moveUnion.addAll(potentialCaptureMoves());
		for (Move move : moveUnion) {
			Coordinate newCoord = getCoord();
			do {
				newCoord = coordAfterMove(newCoord, move);
				if (!getBoard().isValidCoordinate(newCoord)) break;
				
				Piece coordPiece = getBoard().getPiece(newCoord);
				// If square to move to is empty and move is in non capture moves
				if (coordPiece == null && potentialNonCaptureMoves().contains(move)) {
					legalCoords.add(newCoord);
				// If square to move to is not empty
				} else if (coordPiece != null) {
					// If piece in square is different color and move is in capture moves
					if (
						isWhite != coordPiece.isWhite() && potentialCaptureMoves().contains(move)
					) {
						legalCoords.add(newCoord);
					}
					// Breaks regardless - can never move past piece
					break;
				}
			} while (canRepeatMoves());
		}
		
		return legalCoords;
	}
	public Set<SpecialMove> potentialSpecialMoves() { return new HashSet<>(); }
	public Map<Coordinate, SpecialMoveImplementation> legalSpecialMoveCoords() {
		Map<Coordinate, SpecialMoveImplementation> legalCoords = new HashMap<>();
		
		for (SpecialMove move : potentialSpecialMoves()) {
			SpecialMoveImplementation implementation = move.getImplementation();
			if (implementation.canDoMove(this)) {
				legalCoords.put(coordAfterShift(getCoord(), move.getShift()), implementation);
			}
		}
		
		return legalCoords;
	}
	
	protected final String getImgFolder() { return "img/"; }
	protected abstract String getWhiteImgFileName();
	protected abstract String getBlackImgFileName();
	protected final String getWhiteImgFilePath() { return getImgFolder() + getWhiteImgFileName(); }
	protected final String getBlackImgFilePath() { return getImgFolder() + getBlackImgFileName(); }
	
	protected abstract boolean canRepeatMoves();
	public boolean isWhite() { return isWhite; }
	public ChessGame getChessGame() { return getBoard().getChessGame(); }
	public Board getBoard() { return (Board) getSquare().getParent(); }
	public Square getSquare() { return (Square) getParent(); }
	public Coordinate getCoord() { return getSquare().getCoord(); }
	
	public Piece getPieceRelative(XY shift) {
		return getBoard().getPiece(coordAfterShift(getCoord(), shift));
	}
	
	protected abstract String notation(); // return letter(s) for chess notation
}
