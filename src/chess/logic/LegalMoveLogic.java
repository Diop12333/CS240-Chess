package chess.logic;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.specialmove.SpecialMove;
import chess.specialmove.SpecialMoveImplementation;

public class LegalMoveLogic {
	private Board board;
	public LegalMoveLogic(Board board) {
		this.board = board;
	}
	
	public static Coordinate coordAfterShift(
		Coordinate coord, XY shift, boolean fromBlackPerspective
	) {
		if (fromBlackPerspective) shift = new XY(shift.getX(), -shift.getY());
		return coord.shifted(shift);
	}
	public static Coordinate coordAfterMove(
		Coordinate coord, Move move, boolean fromBlackPerspective
	) {
		return coordAfterShift(coord, move.getShift(), fromBlackPerspective);
	}
	public static Coordinate pieceCoordAfterShift(Piece piece, XY shift) {
		return coordAfterShift(piece.getCoord(), shift, !piece.isWhite());
	}
	public static Coordinate pieceCoordAfterMove(Piece piece, Move move) {
		return coordAfterMove(piece.getCoord(), move, !piece.isWhite());
	}
	
	public Piece getPieceRelative(Piece piece, XY shift) {
		return board.getPiece(pieceCoordAfterShift(piece, shift));
	}
	
	// Neutral: ignore whether or not move is capture or non-capture
	private Set<Coordinate> generateCoordsFromMove(Piece piece, Move move, boolean neutral) {
		Set<Coordinate> moveCoords = new HashSet<>();
		
		Coordinate newCoord = piece.getCoord();
		do {
			newCoord = coordAfterMove(newCoord, move, !piece.isWhite());
			if (!board.withinDimensions(newCoord)) break;
			
			Piece coordPiece = board.getPiece(newCoord);
			// If square to move to is empty and move is in non capture moves
			if (
				coordPiece == null &&
				(neutral || piece.potentialNonCaptureMoves().contains(move))
			) {
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
	
	public Set<Coordinate> threatenedCoords(Piece piece) {
		Set<Coordinate> threatenedCoords = new HashSet<>();
		for (Move move : piece.potentialCaptureMoves()) {
			threatenedCoords.addAll(generateCoordsFromMove(piece, move, true));
		}
		return threatenedCoords;
	}
	
	public Set<Coordinate> allThreatenedCoords(boolean white) {
		Set<Coordinate> threatenedCoords = new HashSet<>();
		for (Piece boardPiece : board.getColorPieces(!white)) {
			threatenedCoords.addAll(threatenedCoords(boardPiece));
		}
		return threatenedCoords;
	}
	
	public static boolean containsCoord(Collection<Coordinate> c, Coordinate coord) {
		for (Coordinate collCoord : c) {
			if (collCoord.equals(coord)) return true;
		}
		return false;
	}
	
	public boolean kingInCheck(boolean kingIsWhite) {
		King king;
		if (kingIsWhite) king = board.getWhiteKing();
		else king = board.getBlackKing();
		
		return containsCoord(allThreatenedCoords(kingIsWhite), king.getCoord());
	}
	
	public Set<Coordinate> legalMoveCoords(Piece piece) {
		Set<Coordinate> legalCoords = new HashSet<>();
		
		Set<Move> moveUnion = new HashSet<>();
		moveUnion.addAll(piece.potentialNonCaptureMoves());
		moveUnion.addAll(piece.potentialCaptureMoves());
		for (Move move : moveUnion) {
			Set<Coordinate> moveCoords = generateCoordsFromMove(piece, move, false);
			
			for (Coordinate moveCoord : moveCoords) {
				Board boardCopy = new Board(board);
				boardCopy.getPiece(piece.getCoord()).move(moveCoord);
				
				if (!boardCopy.getLogic().kingInCheck(piece.isWhite())) {
					legalCoords.add(moveCoord);
				}
			}
		}
		
		return legalCoords;
	}
	
	public Map<Coordinate, SpecialMoveImplementation> legalSpecialMoveCoords(Piece piece) {
		Map<Coordinate, SpecialMoveImplementation> legalCoords = new HashMap<>();
		
		for (SpecialMove move : piece.potentialSpecialMoves()) {
			SpecialMoveImplementation implementation = move.getImplementation();
			
			if (implementation.canDoMove(piece, board.getLogic())) {
				Board boardCopy = new Board(board);
				Piece pieceCopy = boardCopy.getPiece(piece.getCoord());
				
				Coordinate moveCoord = pieceCoordAfterShift(pieceCopy, move.getShift());
				
				implementation.doPreMoveEffect(pieceCopy, boardCopy);
				pieceCopy.move(moveCoord);
				implementation.doPostMoveEffect(pieceCopy, boardCopy);
				
				if (!boardCopy.getLogic().kingInCheck(piece.isWhite())) {
					legalCoords.put(pieceCoordAfterShift(piece, move.getShift()), implementation);
				}
			}
		}
		
		return legalCoords;
	}
	
	public boolean canMakeAMove(boolean white) {
		for (Piece piece : board.getColorPieces(white)) {
			if (!legalMoveCoords(piece).isEmpty() || !legalSpecialMoveCoords(piece).isEmpty()) {
				return true;
			}
		}
		return false;
	}
}
