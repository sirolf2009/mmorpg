package com.sirolf2009.mmorpg.scene;

import com.sirolf2009.mmorpg.component.AddMoney;
import com.sirolf2009.mmorpg.component.CharacterList;
import com.sirolf2009.mmorpg.component.CreateCharacter;
import com.sirolf2009.mmorpg.component.PurchaseSlots;
import com.sirolf2009.mmorpg.component.RenovateSubscription;
import com.sirolf2009.mmorpg.component.ServerList;
import com.sirolf2009.mmorpg.component.UserStats;
import com.sirolf2009.mmorpg.entity.User;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneMain extends MmorpgScene {

	public SceneMain(Stage stage, User user) {
		this(stage, user, new StackPane());
	}
	
	public SceneMain(Stage stage, User user, StackPane layout) {
		super(layout);
		stage.setMaximized(true);
		BorderPane border = new BorderPane();
		SplitPane main = new SplitPane();

		StackPane userManagement = new StackPane();
		userManagement.setPadding(new Insets(12));
		UserStats stats = new UserStats(user);
		userManagement.getChildren().addAll(new VBox(stats, new AddMoney(user, stats), new RenovateSubscription(user, stats), new PurchaseSlots(user, stats)));
		
		StackPane characterManagement = new StackPane();
		characterManagement.setPadding(new Insets(12));
		CharacterList list = new CharacterList(user);
		characterManagement.getChildren().addAll(new VBox(new CreateCharacter(user, stats, list), list, new ServerList(stage, user)));
		
		main.getItems().addAll(userManagement, characterManagement);
		border.setCenter(main);
		border.setTop(new FlowPane(8, 8, new Label("Logged in as "+user.getUser_name())));
		layout.getChildren().add(border);
	}

}
