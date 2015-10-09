package com.sirolf2009.mmorpg.component;

import com.sirolf2009.mmorpg.entity.User;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class UserStats extends BorderedTitledPane {

	private static Label money;
	private static Label months;
	private static Label slots;
	private User user;

	public UserStats(User user) {
		super("Stats", createContent(user));
		this.user = user;
		update();
	}

	private static Node createContent(User user) {
		money = new Label();
		months = new Label();
		slots = new Label();
		return new FlowPane(16, 16, money, months, slots);
	}

	public void update() {
		money.setText("Money: €"+user.getBalance());
		months.setText("Playtime: "+user.getMonths_payed()+" months");
		slots.setText("Slots: "+user.getCharacter_slots());
	}

}
