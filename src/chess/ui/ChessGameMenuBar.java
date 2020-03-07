package chess.ui;

import chess.logic.ChessGame;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ChessGameMenuBar extends MenuBar {
	private ChessGame chessGame;
	public ChessGameMenuBar(ChessGame chessGame) {
		this.chessGame = chessGame;
		EventHandler<ActionEvent> action = ActionSelection();
		// Create menus
		Menu fileMenu = new Menu ("File");
		Menu historyMenu = new Menu("History");
		Menu scoresMenu = new Menu("Scores");
		Menu modeMenu = new Menu("Game Mode");
		
		// Create MenuItems
		MenuItem newGameItem = new MenuItem("Start a New Game");
		newGameItem.setOnAction(action);
		MenuItem saveGameItem = new MenuItem("Save Current Game");
		saveGameItem.setOnAction(action);
		MenuItem loadGameItem = new MenuItem("Load Previous Game");
		loadGameItem.setOnAction(action);
		MenuItem exitGameItem = new MenuItem("Exit Chess Game");
		exitGameItem.setOnAction(action);
		
		MenuItem redoItem = new MenuItem("Number of Moves");
		
		MenuItem highItem = new MenuItem("Highest Score");
		MenuItem lowItem = new MenuItem("Lowest Score");
	   
		MenuItem classicItem = new MenuItem("Classic Mode");
		MenuItem blitzItem = new MenuItem("Blitz Mode");
		MenuItem lightingItem =new MenuItem("Lighting Mode");
		
		
		// Add menuItems to the Menus
		fileMenu.getItems().addAll(newGameItem, saveGameItem,loadGameItem,exitGameItem);
		historyMenu.getItems().addAll(redoItem);
		scoresMenu.getItems().addAll(highItem,lowItem);
		modeMenu.getItems().addAll(classicItem,blitzItem,lightingItem);
		
		// Add Menus to the MenuBar
		getMenus().addAll(fileMenu, historyMenu, scoresMenu, modeMenu);
	}
    private EventHandler<ActionEvent> ActionSelection() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	MenuItem mItem = (MenuItem) event.getSource();
                String side = mItem.getText();
                if (side.equals("Start a New Game")) {
                	chessGame.reset();
                }else if (side.equals("Save Current Game")) {
                    System.out.println("save");
                }else if (side.equals("Load Previous Game")) {
                	System.out.println("load");
                }else if (side.equals("Exit Chess Game")) {
                	Platform.exit();
                }
            }
        };
    }
}
