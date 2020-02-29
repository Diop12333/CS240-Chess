package chess.piece;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.ui.XY;

public class ChessGameLogic {
	private ChessGame chessGame;
	public ChessGameLogic(ChessGame chessGame) {
		this.chessGame = chessGame;
	}
	
	private static Coordinate pieceCoordAfterMove(Piece piece, Move move) {
		return pieceCoordAfterShift(piece, move.getShift());
	}
	private static Coordinate pieceCoordAfterShift(Piece piece, XY shift) {
		if (!piece.isWhite()) shift = new XY(shift.getX(), -shift.getY());
		return piece.getCoord().shifted(shift);
	}
	
	public Piece getPieceRelative(Piece piece, XY shift) {
		return chessGame.getBoard().getPiece(pieceCoordAfterShift(piece, shift));
	}
	
	// Neutral: ignore whether or not move is capture or non-capture
	private Set<Coordinate> generateCoordsFromMove(Piece piece, Move move, boolean neutral) {
		Set<Coordinate> moveCoords = new HashSet<>();
		
		Coordinate newCoord = piece.getCoord();
		Board board = chessGame.getBoard();
		do {
			newCoord = pieceCoordAfterMove(piece, move);
			if (!board.isValidCoordinate(newCoord)) break;
			
			Piece coordPiece = board.getPiece(newCoord);
			// If square to move to is empty and move is in non capture moves
			if (coordPiece == null && (neutral || piece.potentialNonCaptureMoves().contains(move))) {
				moveCoords.add(newCoord);
			// If square to move to is not empty
			} else if (coordPiece != null) {
				// If piece in square is different color and move is in capture moves
				if (
					piece.isWhite() != coordPiece.isWhite() &&
					(neutral || piece.potentialCaptureMoves().contains(move))
				) {
					moveCoords.add(newCoord);
				}
				// Breaks regardless - can never move past piece
				break;
			}
		} while (piece.canRepeatMoves());
		
		return moveCoords;
	}
	
	public Set<Coordinate> legalMoveCoords(Piece piece) {
		Set<Coordinate> legalCoords = new HashSet<>();
		
		Set<Move> moveUnion = new HashSet<>();
		moveUnion.addAll(piece.potentialNonCaptureMoves());
		moveUnion.addAll(piece.potentialCaptureMoves());
		for (Move move : moveUnion) {
			legalCoords.addAll(generateCoordsFromMove(piece, move, false));
		}
		
		return legalCoords;
	}
	
	public Map<Coordinate, SpecialMoveImplementation> legalSpecialMoveCoords(Piece piece) {
		Map<Coordinate, SpecialMoveImplementation> legalCoords = new HashMap<>();
		
		for (SpecialMove move : piece.potentialSpecialMoves()) {
			SpecialMoveImplementation implementation = move.getImplementation();
			if (implementation.canDoMove(piece, chessGame)) {
				legalCoords.put(pieceCoordAfterShift(piece, move.getShift()), implementation);
			}
		}
		
		return legalCoords;
	}
}
