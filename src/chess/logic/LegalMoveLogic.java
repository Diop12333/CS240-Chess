package chess.logic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.piece.King;
import chess.piece.Piece;
import chess.specialmove.SpecialMove;
import chess.specialmove.SpecialMoveImplementation;

public class LegalMoveLogic {
	private Board board;
	public LegalMoveLogic(Board board) {
		this.board = board;
	}
	
	private static Coordinate coordAfterShift(
		Coordinate coord, XY shift, boolean fromBlackPerspective
	) {
		if (fromBlackPerspective) shift = new XY(shift.getX(), -shift.getY());
		return coord.shifted(shift);
	}
	private static Coordinate coordAfterRegularMove(
		Coordinate coord, RegularMove move, boolean fromBlackPerspective
	) {
		return coordAfterShift(coord, move.getShift(), fromBlackPerspective);
	}
	public static Coordinate pieceCoordAfterShift(Piece piece, XY shift) {
		return coordAfterShift(piece.getCoord(), shift, !piece.isWhite());
	}
	
	public Piece getPieceRelative(Piece piece, XY shift) {
		return board.getPiece(pieceCoordAfterShift(piece, shift));
	}
	
	// Neutral: ignore whether or not move is capture or non-capture
	private Set<Coordinate> generateCoordsFromRegularMove(
		Piece piece, RegularMove move, boolean neutral
	) {
		Set<Coordinate> moveCoords = new HashSet<>();
		
		Coordinate newCoord = piece.getCoord();
		do {
			newCoord = coordAfterRegularMove(newCoord, move, !piece.isWhite());
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
		} while (piece.canRepeatRegularMoves());
		
		return moveCoords;
	}
	
	public Set<Piece> colorPieces(boolean isWhite) {
		Set<Piece> colorPieces = new HashSet<>();
		for (Piece piece : board.getPieces()) {
			if (piece.isWhite() == isWhite) colorPieces.add(piece);
		}
		return colorPieces;
	}
	
	private Set<Coordinate> threatenedCoords(Piece piece) {
		Set<Coordinate> threatenedCoords = new HashSet<>();
		for (RegularMove move : piece.potentialCaptureMoves()) {
			threatenedCoords.addAll(generateCoordsFromRegularMove(piece, move, true));
		}
		return threatenedCoords;
	}
	
	private Set<Coordinate> allThreatenedCoords(boolean white) {
		Set<Coordinate> threatenedCoords = new HashSet<>();
		for (Piece boardPiece : colorPieces(!white)) {
			threatenedCoords.addAll(threatenedCoords(boardPiece));
		}
		return threatenedCoords;
	}
	
	public boolean kingInCheck(boolean kingIsWhite) {
		King king;
		if (kingIsWhite) king = board.getWhiteKing();
		else king = board.getBlackKing();
		
		return allThreatenedCoords(kingIsWhite).contains(king.getCoord());
	}
	
	public SpecialMoveImplementation getImplFromMove(StoredMove move) {
		return getImplFromMove(move.getPiece(), move.getNewCoord());
	}
	public SpecialMoveImplementation getImplFromMove(Piece piece, Coordinate coord) {
		Map<Coordinate, SpecialMoveImplementation> moveCoords = legalMoveCoords(piece);
		SpecialMoveImplementation impl;
		if (moveCoords.containsKey(coord)) {
			impl = moveCoords.get(coord);
		} else {
			throw new ChessGameException("Illegal move");
		}
		return impl;
	}
	
	private Board boardStateAfterMove(StoredMove move) {
		Board boardCopy = new Board(board);
		
		Piece pieceCopy = boardCopy.getPiece(move.getPiece().getCoord());
		
		StoredMove moveCopy = new StoredMove(pieceCopy, move.getNewCoord());
		
		boardCopy.getInterface().makeStoredMove(moveCopy);
		return boardCopy;
	}
	private Board boardStateAfterMove(Piece piece, Coordinate coord) {
		return boardStateAfterMove(piece, coord, null);
	}
	private Board boardStateAfterMove(
		Piece piece, Coordinate coord, SpecialMoveImplementation impl
	) {
		Board boardCopy = new Board(board);
		Piece pieceCopy = boardCopy.getPiece(piece.getCoord());
		
		boardCopy.getInterface().makeMove(pieceCopy, coord, impl);
		
		return boardCopy;
	}
	public Map<Coordinate, SpecialMoveImplementation> legalMoveCoords(Piece piece) {
		Map<Coordinate, SpecialMoveImplementation> legalCoords = new HashMap<>();
		
		for (Coordinate coord : legalRegularMoveCoords(piece)) {
			legalCoords.put(coord, null);
		}
		
		legalCoords.putAll(legalSpecialMoveCoords(piece));
		
		return legalCoords;
	}
	public Set<Coordinate> legalRegularMoveCoords(Piece piece) {
		Set<Coordinate> legalCoords = new HashSet<>();
		
		Set<RegularMove> moveUnion = new HashSet<>();
		moveUnion.addAll(piece.potentialNonCaptureMoves());
		moveUnion.addAll(piece.potentialCaptureMoves());
		for (RegularMove move : moveUnion) {
			Set<Coordinate> moveCoords = generateCoordsFromRegularMove(piece, move, false);
			
			for (Coordinate moveCoord : moveCoords) {
				Board newBoard = boardStateAfterMove(piece, moveCoord);
				
				if (!newBoard.getLogic().kingInCheck(piece.isWhite())) {
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
			
			if (implementation.checkExtraConditions(piece, board.getLogic())) {
				Coordinate moveCoord = pieceCoordAfterShift(piece, move.getShift());
				
				Board newBoard = boardStateAfterMove(piece, moveCoord, implementation);
				
				if (!newBoard.getLogic().kingInCheck(piece.isWhite())) {
					legalCoords.put(moveCoord, implementation);
				}
			}
		}
		
		return legalCoords;
	}
	
	public Set<StoredMove> legalMoves(boolean isWhite) {
		Set<StoredMove> moves = new HashSet<>();
		
		for (Piece piece : colorPieces(isWhite)) {
			for (Coordinate coord : legalMoveCoords(piece).keySet()) {
				Board boardCopy = boardStateAfterMove(piece, coord, getImplFromMove(piece, coord));
				
				if (boardCopy.getInterface().waitingForPromotion()) {
					for (PromotionPiece promPiece : PromotionPiece.values()) {
						moves.add(new StoredMove(piece, coord, promPiece));
					}
				} else {
					moves.add(new StoredMove(piece, coord));
				}
			}
		}
		
		return moves;
	}
	
	public Map<StoredMove, Board> legalBoardStates(boolean isWhite) {
		Map<StoredMove, Board> boards = new HashMap<>();
		for (StoredMove move : legalMoves(isWhite)) {
			boards.put(move, boardStateAfterMove(move));
		}
		return boards;
	}
	
	public boolean canMakeAMove(boolean isWhite) {
		for (Piece piece : colorPieces(isWhite)) {
			if (!legalMoveCoords(piece).isEmpty()) return true;
		}
		return false;
	}
	
	public boolean isCheckmated(boolean isWhite) {
		return !canMakeAMove(isWhite) && kingInCheck(isWhite);
	}
	public boolean isStalemated(boolean isWhite) {
		return !canMakeAMove(isWhite) && !kingInCheck(isWhite);
	}
	
	public int totalPoints(boolean isWhite) {
		int points = 0;
		
		for (Piece piece : colorPieces(isWhite)) {
			points += piece.pointValue();
		}
		
		return points;
	}
	public int value(boolean isWhite) {
		return totalPoints(isWhite) - totalPoints(!isWhite);
	}
}
