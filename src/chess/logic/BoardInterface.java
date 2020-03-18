package chess.logic;

import chess.piece.Pawn;
import chess.piece.Piece;
import chess.specialmove.SpecialMoveImplementation;

public class BoardInterface {
	private Board board;
	
	private boolean waitingForPromotion = false;
	private Pawn promotionPawn;
	public BoardInterface(Board board) {
		this.board = board;
	}
	
	public void makeStoredMove(StoredMove storedMove) {
		getImplAndMakeMove(storedMove.getPiece(), storedMove.getNewCoord());
		if (waitingForPromotion && storedMove.getPromPiece() != null) {
			if (storedMove.getPromPiece() != null) promote(storedMove.getPromPiece());
			else throw new ChessGameException("Unable to promote");
		}
	}
	public void getImplAndMakeMove(Piece piece, Coordinate coord) {
		makeMove(piece, coord, board.getLogic().getImplFromMove(piece, coord));
	}
	public void makeMove(Piece piece, Coordinate coord, SpecialMoveImplementation impl) {
		// Make sure en passant can only occur turn after two-square move
		for (Piece p : board.getPieces()) {
			if (p instanceof Pawn) {
				Pawn pawn = (Pawn) p;
				pawn.setEnPassantable(false);
			}
		}
		
		if (impl != null) {
			impl.doPreMoveEffect(piece, board);
			piece.move(coord);
			impl.doPostMoveEffect(piece, board);
		} else {
			piece.move(coord);
		}
		
		int pieceY = piece.getCoord().getY();
		if (
			piece instanceof Pawn && (
				piece.isWhite() && pieceY == 0 ||
				!piece.isWhite() && pieceY == board.getDimensions().getY() - 1
			)
		) {
			waitingForPromotion = true;
			promotionPawn = (Pawn) piece;
		}
	}
	
	public void promote(PromotionPiece promPiece) {
		if (!waitingForPromotion) {
			throw new ChessGameException("promote called while not waiting for promotion");
		}
		
		board.addNewPiece(
			promotionPawn.getCoord(),
			promPiece.toRegularPiece(promotionPawn.isWhite())
		);
		waitingForPromotion = false;
	}
	
	public void reset() {
		waitingForPromotion = false;
		promotionPawn = null;
	}
	
	public Board getBoard() { return board; }
	public boolean waitingForPromotion() { return waitingForPromotion; }
	public Pawn getPromotionPawn() { return promotionPawn; }
}
