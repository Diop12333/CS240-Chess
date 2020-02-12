package chess.ui;

import chess.piece.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Chess Board");
		
		Board board = new Board();
		Scene scene = new Scene(board, 500, 500);
		
		// test code
		//White pieces
		new Pawn(true, board, new Coordinate(0, 1));
		new Pawn(true, board, new Coordinate(1, 1));
		new Pawn(true, board, new Coordinate(2, 1));
		new Pawn(true, board, new Coordinate(3, 1));
		new Pawn(true, board, new Coordinate(4, 1));
		new Pawn(true, board, new Coordinate(5, 1));
		new Pawn(true, board, new Coordinate(6, 1));
		new Pawn(true, board, new Coordinate(7, 1));
		
		
		new Rook(true, board, new Coordinate (0,0));
		new Knight(true, board, new Coordinate (1,0));
		new Bishop(true, board, new Coordinate (2,0));
		new Queen(true, board, new Coordinate (3,0));
		new King(true, board, new Coordinate (4,0));
		new Bishop(true, board, new Coordinate (5,0));
		new Knight(true, board, new Coordinate (6,0));
		new Rook(true, board, new Coordinate (7,0));
		
		//Black pieces
		new Pawn(false, board, new Coordinate(0, 6));
		new Pawn(false, board, new Coordinate(1, 6));
		new Pawn(false, board, new Coordinate(2, 6));
		new Pawn(false, board, new Coordinate(3, 6));
		new Pawn(false, board, new Coordinate(4, 6));
		new Pawn(false, board, new Coordinate(5, 6));
		new Pawn(false, board, new Coordinate(6, 6));
		new Pawn(false, board, new Coordinate(7, 6));
		
		new Rook(false, board, new Coordinate (0,7));
		new Knight(false, board, new Coordinate (1,7));
		new Bishop(false, board, new Coordinate (2,7));
		new Queen(false, board, new Coordinate (3,7));
		new King(false, board, new Coordinate (4,7));
		new Bishop(true, board, new Coordinate (5,7));
		new Knight(false, board, new Coordinate (6,7));
		new Rook(false, board, new Coordinate (7,7));
		
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}