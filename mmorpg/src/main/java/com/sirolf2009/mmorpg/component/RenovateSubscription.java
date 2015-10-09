package com.sirolf2009.mmorpg.component;

import com.sirolf2009.mmorpg.Mmorpg;
import com.sirolf2009.mmorpg.Util;
import com.sirolf2009.mmorpg.entity.User;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class RenovateSubscription extends BorderedTitledPane {

	public static final String MONTH_1 = "1 month\t€5";
	public static final String MONTH_2 = "2 months\t€8";
	public static final String MONTH_3 = "3 months\t€10";
	public static final String YEAR = "1 year\t€35";

	public RenovateSubscription(User user, UserStats stats) {
		super("Renovate Subscription", createContent(user, stats));
	}
	
	public static Node createContent(User user, UserStats stats) {
		ComboBox<String> box = new ComboBox<String>(FXCollections.observableArrayList(MONTH_1, MONTH_2, MONTH_3, YEAR));
		Button submit = new Button("Submit");
		Label error = new Label();
		error.setTextFill(Color.RED);
		submit.setOnMouseClicked(event -> {
			try {
				Mmorpg.database.addMonths(user, Util.getMonthAmountFromDescription(box.getSelectionModel().getSelectedItem()));
				stats.update();
			} catch (Exception e) {
				error.setText(e.getMessage());
			}
		});
		return new HBox(box, submit, error);
	}

}
