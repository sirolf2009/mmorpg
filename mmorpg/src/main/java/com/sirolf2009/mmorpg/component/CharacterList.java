package com.sirolf2009.mmorpg.component;

import com.sirolf2009.mmorpg.entity.Character;
import com.sirolf2009.mmorpg.entity.User;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CharacterList extends BorderedTitledPane {

	private static ListView<Character> list;
	private User user;

	public CharacterList(User user) {
		super("Characters", createContent(user));
		this.user = user;
		update();
	}

	private static Node createContent(User user) {
		list = new ListView<Character>();
		list.setPadding(new Insets(8, 8, 8, 8));
		list.setCellFactory(list -> new CharacterCell());
		StackPane wrapper = new StackPane(list);
		StackPane.setMargin(list, new Insets(32, 16, 16, 16));
		return wrapper;
	}

	public void update() {
		if(user.getCharacters() != null) {
			list.setItems(FXCollections.observableArrayList(user.getCharacters()));
		}
	}

	private static class CharacterCell extends ListCell<Character> {

		@Override
		protected void updateItem(Character item, boolean empty) {
			super.updateItem(item, empty);
			if(item != null) {
				Label name = new Label(item.getName());
				Label level = new Label("Level "+item.getLevel());
				Label race = new Label(item.getRace());
				Label clazz = new Label(item.getClass_type());
				setGraphic(new HBox(16, new VBox(name, level), new VBox(race, clazz)));
			}
		}

	}

}
