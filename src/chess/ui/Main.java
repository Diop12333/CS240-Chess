package chess.ui;

import java.io.FileNotFoundException;

import chess.logic.ChessGame;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	// TODO: make start method less crowded
		// TODO: implement menu actions
		@Override
		public void start(Stage primaryStage) throws FileNotFoundException {
			primaryStage.setTitle("Chess Game");
			
			VBox root = new VBox();
			root.setAlignment(Pos.CENTER);
			
			ChessGame chessGame = new ChessGame();
			BoardDisplay boardDisplay = chessGame.getBoardDisplay();
			BoardDisplayContainer boardDisplayContainer = new BoardDisplayContainer(boardDisplay);
			VBox.setVgrow(boardDisplayContainer, Priority.ALWAYS);
			
			ChessGameMenuBar menuBar = new ChessGameMenuBar(chessGame);
			
			root.getChildren().add(menuBar);
			
			ChessGameStateLabel gameStateLabel = new ChessGameStateLabel(chessGame);
			
			root.getChildren().add(gameStateLabel);
			root.getChildren().add(boardDisplayContainer);
			
			Scene scene = new Scene(root, 500, 500);
			
			primaryStage.setScene(scene);
			
			primaryStage.show();
		}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
