package com.sirolf2009.mmorpg.component;

import com.sirolf2009.mmorpg.Mmorpg;
import com.sirolf2009.mmorpg.entity.User;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AddMoney extends BorderedTitledPane {

	public AddMoney(User user, UserStats stats) {
		super("Add Money", createContent(user, stats));
	}

	public static Node createContent(final User user, UserStats stats) {
		HBox box = new HBox();
		TextField amount = new TextField();
		Label error = new Label();
		error.setTextFill(Color.RED);
		Button submit = new Button("Submit");
		submit.setOnMouseClicked(event -> {
			try {
				Mmorpg.database.addMoney(user, Double.parseDouble(amount.getText()));
				error.setText("");
				stats.update();
			} catch(Exception e) {
				error.setText(e.getClass()+": "+e.getMessage());
				log.error(e);
			}
		});
		box.getChildren().addAll(amount, submit, error);
		return box;
	}

}
