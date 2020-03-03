package chess.ui;

import java.io.FileNotFoundException;

import chess.logic.ChessGame;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
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
		
		BorderPane root = new BorderPane();
		VBox test=new VBox();
		
		ChessGame chessGame = new ChessGame();
		BoardDisplay boardDisplay = chessGame.getBoardDisplay();
		BoardDisplayContainer boardDisplayContainer = new BoardDisplayContainer(boardDisplay);
		VBox.setVgrow(boardDisplayContainer, Priority.ALWAYS);
		
		ChessGameMenuBar menuBar = new ChessGameMenuBar(chessGame);
		
		ChessGameStateLabel gameStateLabel = new ChessGameStateLabel(chessGame);
		test.getChildren().add(menuBar);
		test.getChildren().add(gameStateLabel);
		test.setAlignment(Pos.CENTER);
		root.setTop(test);
		BorderPane.setAlignment(gameStateLabel, Pos.BOTTOM_CENTER);
		root.setCenter(boardDisplayContainer);
		
		Scene scene = new Scene(root, 500, 500);
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
