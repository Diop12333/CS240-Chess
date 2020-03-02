package chess.ui;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class Main extends Application {
	// TODO: make start method less crowded
	// TODO: implement menu actions
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		primaryStage.setTitle("Chess Game");
		
		BorderPane root = new BorderPane();
		
		MenuBar menuBar = new MenuBar();
        
        // Create menus
        Menu fileMenu = new Menu ("File");
        Menu historyMenu = new Menu("History");
        Menu scoresMenu = new Menu("Scores");
        Menu playMenu = new Menu("Play Again");
        
        // Create MenuItems
        MenuItem newItem = new MenuItem("New Game");
        MenuItem saveItem = new MenuItem("Save Game");
        MenuItem loadItem = new MenuItem("Load Game");
        
        MenuItem undoItem = new MenuItem("Undo Move");
        MenuItem redoItem = new MenuItem("Redo Move");
        
        MenuItem highItem = new MenuItem("Highest Score");
        MenuItem lowItem = new MenuItem("Lowest Score");
       
        MenuItem startItem = new MenuItem("Start Game");
        
        root.setTop(menuBar);
        
        // Add menuItems to the Menus
        fileMenu.getItems().addAll(newItem, saveItem, loadItem);
        historyMenu.getItems().addAll(undoItem, redoItem);
        scoresMenu.getItems().addAll(highItem,lowItem);
        playMenu.getItems().addAll(startItem);
        
        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, historyMenu, scoresMenu, playMenu);
        
		Label turnLabel = new Label();
		
		ChessGame chessGame = new ChessGame();
		Board board = chessGame.getBoard();
		BoardContainer boardContainer = new BoardContainer(board);
		
		VBox.setVgrow(boardContainer, Priority.ALWAYS);
		
		String whiteTurn = "It's white's turn.";
		String blackTurn = "It's black's turn.";
		turnLabel.setText(whiteTurn);
		chessGame.isWhiteTurnProperty().addListener((prop, oldVal, newVal) -> {
			if (newVal) {
				turnLabel.setText(whiteTurn);
			} else {
				turnLabel.setText(blackTurn);
			}
		});
		

		BorderPane.setAlignment(turnLabel, Pos.BOTTOM_CENTER);
		
		root.setCenter(boardContainer);
		
		Scene scene = new Scene(root, 500, 500);
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
