package com.sirolf2009.mmorpg.component;

import com.sirolf2009.mmorpg.Mmorpg;
import com.sirolf2009.mmorpg.entity.User;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CreateCharacter extends BorderedTitledPane {

	public static String[] RACES = new String[] {
			"Werewolf", "Goblin", "Vampire", "Dwarf", "Orc", "Elv", "Human"
	};

	public static String[] CLASSES = new String[] {
			"Tank", "Sniper", "Support"
	};

	public CreateCharacter(User user, UserStats stats, CharacterList list) {
		super("Create a character", createContent(user, stats, list));
	}

	public static Node createContent(User user, UserStats stats, CharacterList list) {
		Label lblName = new Label("Name");
		TextField name = new TextField();
		Label lblRace = new Label("Race");
		ComboBox<String> race = new ComboBox<String>(FXCollections.observableArrayList(RACES));
		Label lblClass = new Label("Class");
		ComboBox<String> clazz = new ComboBox<String>(FXCollections.observableArrayList(CLASSES));
		Label error = new Label();
		error.setTextFill(Color.RED);
		Button submit = new Button("Submit");
		submit.setOnMouseClicked(event -> {
			try {
				com.sirolf2009.mmorpg.entity.Character character = new com.sirolf2009.mmorpg.entity.Character();
				character.setClass_type(clazz.getSelectionModel().getSelectedItem());
				character.setLevel(1);
				character.setName(name.getText());
				character.setRace(race.getSelectionModel().getSelectedItem());
				if(character.getName().isEmpty() || character.getRace() == null || character.getClass_type() == null) {
					error.setText("Please fill in all fields");
				} else {
					Mmorpg.database.createCharacter(user, character);
					list.update();
					error.setText("Succes!");
					stats.update();
					log.info("Created "+character);
				}
			} catch(Exception e) {
				error.setText(e.getMessage());
				log.error(e);
			}
		});

		GridPane grid = new GridPane();
		grid.add(lblName, 0, 0);
		grid.add(name, 1, 0);
		grid.add(lblRace, 0, 1);
		grid.add(race, 1, 1);
		grid.add(lblClass, 0, 2);
		grid.add(clazz, 1, 2);
		grid.add(submit, 2, 3);
		grid.add(error, 1, 4);
		return grid;
	}

}
