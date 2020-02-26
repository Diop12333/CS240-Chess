package chess.ui;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Pos;
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
		
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		
		MenuBar menuBar = new MenuBar();
        
        // Create menus
        Menu playerMenu = new Menu ("Player 1");
        Menu player2Menu = new Menu("Player 2");
        Menu scoresMenu = new Menu("Scores");
        Menu playMenu = new Menu("Play Again");
        
        // Create MenuItems
        MenuItem colorItem = new MenuItem("Color");
        MenuItem movesItem = new MenuItem("Number of Moves");
        
        MenuItem colors2Item = new MenuItem("Color");
        MenuItem moves2Item = new MenuItem("Number of Moves");
        
        MenuItem highItem = new MenuItem("Highest Score");
        MenuItem lowItem = new MenuItem("Lowest Score");
       
        MenuItem startItem = new MenuItem("Start Game");
        
        root.getChildren().add(menuBar);
        
        // Add menuItems to the Menus
        playerMenu.getItems().addAll(colorItem, movesItem);
        player2Menu.getItems().addAll(colors2Item, moves2Item);
        scoresMenu.getItems().addAll(highItem,lowItem);
        playMenu.getItems().addAll(startItem);
        
        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(playerMenu, player2Menu, scoresMenu, playMenu);
        
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
		
		root.getChildren().add(turnLabel);
		root.getChildren().add(boardContainer);
		
		Scene scene = new Scene(root, 500, 500);
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
