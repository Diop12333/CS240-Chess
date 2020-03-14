package chess.ui;

import chess.logic.ChessGame;
import chess.logic.PromotionPiece;
import chess.piece.Piece;

public class PromotionDisplay extends DynamicEqualGridPane {
	public PromotionDisplay(ChessGame chessGame, boolean isWhite) {
		super(2, 2);
		
		PromotionPiece[][] promPieces = new PromotionPiece[2][2];
		promPieces[0][0] = PromotionPiece.QUEEN;
		promPieces[0][1] = PromotionPiece.KNIGHT;
		promPieces[1][0] = PromotionPiece.ROOK;
		promPieces[1][1] = PromotionPiece.BISHOP;
		
		for (int y = 0; y < 2; y++) {
			for (int x = 0; x < 2; x++) {
				PromotionPiece promPiece = promPieces[y][x];
				Piece regularPiece = promPiece.toRegularPiece(isWhite);
				ResizableImageViewContainer imgContainer =
					new ResizableImageViewContainer(regularPiece.makeImg());
				imgContainer.setOnMouseClicked(e -> chessGame.promote(promPiece));
				
				add(imgContainer, x, y);
			}
		}
	}
	
	
}
