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
		
		// Add menuItems to the Menus
		playerMenu.getItems().addAll(colorItem, movesItem);
		player2Menu.getItems().addAll(colors2Item, moves2Item);
		scoresMenu.getItems().addAll(highItem,lowItem);
		playMenu.getItems().addAll(startItem);
		
		// Add Menus to the MenuBar
		getMenus().addAll(playerMenu, player2Menu, scoresMenu, playMenu);
	}
}
