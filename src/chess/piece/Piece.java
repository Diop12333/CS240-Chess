package chess.piece;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import chess.ui.Board;
import chess.ui.Coordinate;
import chess.ui.Square;
import chess.ui.XY;
import javafx.scene.image.Image;

public abstract class Piece {
	private boolean isWhite;
	private Board board;
	private Coordinate coord;
	private Image img;
	
	public Piece(boolean isWhite, Board board, Coordinate coord) throws FileNotFoundException {
		this.isWhite = isWhite;
		this.board = board;
		this.coord = coord;
		
		String path;
		if (isWhite) path = getWhiteImgFilePath();
		else path = getBlackImgFilePath();
		
		FileInputStream imgFileStream = new FileInputStream(path);
		img = new Image(imgFileStream);
		
		board.getSquare(coord).setPiece(this);
	}
	
	public void move(Square newSquare) {
		board.getSquare(coord).clear();
		coord = newSquare.getCoord();
		newSquare.setPiece(this);
	}
	
	private Coordinate coordAfterMove(Move move) { return coordAfterMove(coord, move); }
	private Coordinate coordAfterMove(Coordinate initCoord, Move move) {
		XY shift = move.getShift();
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
		Set<Coordinate> legalMoveCoords = new HashSet<>();
		
		Set<Move> moveUnion = new HashSet<>();
		moveUnion.addAll(potentialNonCaptureMoves());
		moveUnion.addAll(potentialCaptureMoves());
		for (Move move : moveUnion) {
			Coordinate newCoord = coord;
			do {
				newCoord = coordAfterMove(newCoord, move);
				if (!board.isValidCoordinate(newCoord)) break;
				
				Piece coordPiece = board.getPiece(newCoord);
				// If square to move to is empty and move is in non capture moves
				if (coordPiece == null && potentialNonCaptureMoves().contains(move)) {
					legalMoveCoords.add(newCoord);
				// If square to move to is not empty
				} else if (coordPiece != null) {
					// If piece in square is different color and move is in capture moves
					if (isWhite != coordPiece.getIsWhite() && potentialCaptureMoves().contains(move)) {
						legalMoveCoords.add(newCoord);
					}
					// Breaks regardless - can never move past piece
					break;
				}
			} while (canRepeatMoves());
		}
		
		return legalMoveCoords;
	}
	
	protected final String getImgFolder() { return "img/"; }
	protected abstract String getWhiteImgFileName();
	protected abstract String getBlackImgFileName();
	protected final String getWhiteImgFilePath() { return getImgFolder() + getWhiteImgFileName(); }
	protected final String getBlackImgFilePath() { return getImgFolder() + getBlackImgFileName(); }
	
	protected abstract boolean canRepeatMoves();
	public boolean getIsWhite() { return isWhite; }
	public Board getBoard() { return board; }
	public Coordinate getCoord() { return coord; }
	public Image getImg() { return img; }
	
	protected abstract String notation(); // return letter(s) for chess notation
}
