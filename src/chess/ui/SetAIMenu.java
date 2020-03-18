package chess.ui;

import java.util.LinkedHashMap;
import java.util.Map;

import chess.ai.AI;
import chess.logic.ChessGame;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class SetAIMenu extends Menu {
	public SetAIMenu(
		String title, ChessGame chessGame, Map<String, AI> aiMap, boolean isWhite
	) {
		super(title);
		
		Map<String, AI> newMap = new LinkedHashMap<>();
		newMap.put("Human", null);
		newMap.putAll(aiMap);
		
		ToggleGroup toggleGroup = new ToggleGroup();
		for (Map.Entry<String, AI> entry : newMap.entrySet()) {
			RadioMenuItem newItem = new RadioMenuItem(entry.getKey());
			newItem.setToggleGroup(toggleGroup);
			newItem.setOnAction(e -> {
				chessGame.setAI(isWhite, entry.getValue());
				chessGame.reset();
			});
			
			if (chessGame.getAI(isWhite) == entry.getValue()) {
				newItem.setSelected(true);
			}
			getItems().add(newItem);
		}
	}
}
