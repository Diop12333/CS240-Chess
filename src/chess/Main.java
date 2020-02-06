package chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Chess Board");
		
		Board board = new Board();
		Scene scene = new Scene(board.getGrid(), 500, 500);
		
		// test code
		new Pawn(true, board, new Coordinate(0, 0));
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}