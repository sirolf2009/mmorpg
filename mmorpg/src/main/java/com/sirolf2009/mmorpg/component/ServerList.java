package com.sirolf2009.mmorpg.component;

import com.sirolf2009.mmorpg.Mmorpg;
import com.sirolf2009.mmorpg.entity.Server;
import com.sirolf2009.mmorpg.entity.User;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ServerList  extends BorderedTitledPane {

	public ServerList(Stage stage, User user) {
		super("Servers", createContent(stage, user));
	}

	private static Node createContent(Stage stage, User user) {
		ListView<Server> list = new ListView<Server>();
		list.setPadding(new Insets(8, 8, 8, 8));
		list.setCellFactory(list2 -> new ServerCell(stage, user));
		list.setItems(FXCollections.observableArrayList(Mmorpg.database.getServers()));
		StackPane wrapper = new StackPane(list);
		StackPane.setMargin(list, new Insets(32, 16, 16, 16));
		updateLoop(list);
		return wrapper;
	}
	
	public static void updateLoop(ListView<Server> list) {
		Timeline updaterTask = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
				list.setItems(FXCollections.observableArrayList(Mmorpg.database.getServers()));
		    }
		}));
		updaterTask.setCycleCount(Timeline.INDEFINITE);
		updaterTask.play();
	}

	private static class ServerCell extends ListCell<Server> {
		
		private Stage stage;
		private User user;
		
		public ServerCell(Stage stage, User user) {
			this.stage = stage;
			this.user = user;
		}

		@Override
		protected void updateItem(final Server item, boolean empty) {
			super.updateItem(item, empty);
			if(item != null) {
				Label name = new Label("Name\t"+item.getName());
				Label level = new Label("Location\t"+item.getLocation());
				Label race = new Label("Connected\t"+item.getConnected_users());
				Label clazz = new Label("Maximum\t"+item.getMax_users());
				Button connect = new Button("Connect");
				Label address = new Label("address\t"+item.getAddress());
				setGraphic(new HBox(16, new VBox(name, level), new VBox(race, clazz), new VBox(address, connect)));
				
				connect.setOnMouseClicked(event -> {
					try {
						Mmorpg.database.joinServer(user, item);
						final Stage dialog = new Stage();
		                dialog.initModality(Modality.APPLICATION_MODAL);
		                dialog.initOwner(stage);
		                dialog.setTitle("SUCCES");
		                BorderPane pane = new BorderPane();
		                pane.setCenter(new Text("Connected!"));
		                Scene dialogScene = new Scene(pane, 200, 100);
		                dialog.setScene(dialogScene);
		                dialog.show();
					} catch (Exception e) {
						final Stage dialog = new Stage();
		                dialog.initModality(Modality.APPLICATION_MODAL);
		                dialog.initOwner(stage);
		                dialog.setTitle("ERROR");
		                BorderPane pane = new BorderPane();
		                pane.setCenter(new Text(e.getMessage()));
		                Scene dialogScene = new Scene(pane, 200, 100);
		                dialog.setScene(dialogScene);
		                dialog.show();
		                log.error(e);
					}
				});
			}
		}

	}

}