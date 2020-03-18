package chess.ui;

import java.util.LinkedHashMap;
import java.util.Map;

import chess.ai.AI;
import chess.ai.AggressiveNaiveAI;
import chess.ai.RandomAI;
import chess.logic.ChessGame;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ChessGameMenuBar extends MenuBar {
	public ChessGameMenuBar(ChessGame chessGame) {
		// Create menus
		Menu gameMenu = new Menu("Game");
		
		Map<String, AI> aiMap = new LinkedHashMap<>();
		aiMap.put("Random", new RandomAI());
		aiMap.put("Aggressive + Naive", new AggressiveNaiveAI());
		
		SetAIMenu whiteOppMenu = new SetAIMenu(
			"White Opponent", chessGame, aiMap, true
		);
		SetAIMenu blackOppMenu = new SetAIMenu(
			"Black Opponent", chessGame, aiMap, false
		);
		
		// Create MenuItems
		MenuItem newGameItem = new MenuItem("Start a New Game");
		newGameItem.setOnAction(e -> chessGame.reset());
		
		
		// Add menuItems to the Menus
		gameMenu.getItems().add(newGameItem);
		
		// Add Menus to the MenuBar
		getMenus().addAll(gameMenu, whiteOppMenu, blackOppMenu);
	}
}
