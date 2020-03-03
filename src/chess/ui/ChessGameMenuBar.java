package chess.ui;

import chess.logic.ChessGame;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ChessGameMenuBar extends MenuBar {
	private ChessGame chessGame;
	public ChessGameMenuBar(ChessGame chessGame) {
		this.chessGame = chessGame;
		
		// Create menus
		Menu fileMenu = new Menu ("File");
		Menu historyMenu = new Menu("History");
		Menu scoresMenu = new Menu("Scores");
		Menu modeMenu = new Menu("Game Mode");
		
		// Create MenuItems
		MenuItem newGameItem = new MenuItem("Start a New Game ");
		MenuItem saveGameItem = new MenuItem("Save Current Game");
		MenuItem loadGameItem = new MenuItem("Load Previous Game");
		MenuItem exitGameItem = new MenuItem("Exit Chess Game");
		
		MenuItem undoItem = new MenuItem("Color");
		MenuItem redoItem = new MenuItem("Number of Moves");
		
		MenuItem highItem = new MenuItem("Highest Score");
		MenuItem lowItem = new MenuItem("Lowest Score");
	   
		MenuItem classicItem = new MenuItem("Classic Mode");
		MenuItem blitzItem = new MenuItem("Blitz Mode");
		MenuItem lightingItem =new MenuItem("Lighting Mode");
		
		
		// Add menuItems to the Menus
		fileMenu.getItems().addAll(newGameItem, saveGameItem,loadGameItem,exitGameItem);
		historyMenu.getItems().addAll(undoItem, redoItem);
		scoresMenu.getItems().addAll(highItem,lowItem);
		modeMenu.getItems().addAll(classicItem,blitzItem,lightingItem);
		
		// Add Menus to the MenuBar
		getMenus().addAll(fileMenu, historyMenu, scoresMenu, modeMenu);
	}
}
