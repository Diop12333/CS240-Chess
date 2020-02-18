package chess.ui;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		primaryStage.setTitle("Chess Board");
		
		ChessGame chessGame = new ChessGame();
		Board board = chessGame.getBoard();
		BoardContainer boardContainer = new BoardContainer(board);
		
		Scene scene = new Scene(boardContainer, 500, 500);
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}