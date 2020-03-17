package chess.ui;

import java.util.LinkedHashMap;
import java.util.Map;

import chess.ai.AI;
import chess.ai.RandomAI;
import chess.logic.ChessGame;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ChessGameMenuBar extends MenuBar {
	public ChessGameMenuBar(ChessGame chessGame) {
		// Create menus
		Menu fileMenu = new Menu("File");
		
		Map<String, AI> aiMap = new LinkedHashMap<>();
		aiMap.put("Random", new RandomAI());
		
		SetAIMenu whiteAIMenu = new SetAIMenu(
			"White AI", chessGame, aiMap, true
		);
		SetAIMenu blackAIMenu = new SetAIMenu(
			"Black AI", chessGame, aiMap, false
		);
		
		Menu historyMenu = new Menu("History");
		Menu scoresMenu = new Menu("Scores");
		Menu modeMenu = new Menu("Game Mode");
		
		// Create MenuItems
		MenuItem newGameItem = new MenuItem("Start a New Game");
		newGameItem.setOnAction(e -> chessGame.reset());
		MenuItem saveGameItem = new MenuItem("Save Current Game");
		saveGameItem.setOnAction(e -> System.out.println("save"));
		MenuItem loadGameItem = new MenuItem("Load Previous Game");
		loadGameItem.setOnAction(e -> System.out.println("load"));
		MenuItem exitGameItem = new MenuItem("Exit Chess Game");
		exitGameItem.setOnAction(e -> Platform.exit());
		
		MenuItem redoItem = new MenuItem("Redo a move");
		MenuItem undoItem = new MenuItem("Undo a move");
		
		MenuItem highItem = new MenuItem("Highest Score");
		MenuItem lowItem = new MenuItem("Lowest Score");
	   
		MenuItem classicItem = new MenuItem("Classic Mode");
		MenuItem blitzItem = new MenuItem("Blitz Mode");
		MenuItem lightingItem =new MenuItem("Lighting Mode");
		
		
		// Add menuItems to the Menus
		// fileMenu.getItems().addAll(newGameItem, saveGameItem,loadGameItem,exitGameItem);
		fileMenu.getItems().add(newGameItem);
		historyMenu.getItems().addAll(redoItem,undoItem);
		scoresMenu.getItems().addAll(highItem,lowItem);
		modeMenu.getItems().addAll(classicItem,blitzItem,lightingItem);
		
		// Add Menus to the MenuBar
		getMenus().addAll(fileMenu, whiteAIMenu, blackAIMenu);
	}
}
