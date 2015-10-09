package com.sirolf2009.mmorpg.scene;

import com.sirolf2009.mmorpg.Mmorpg;
import com.sirolf2009.mmorpg.entity.User;
import com.sirolf2009.mmorpg.exception.RegisterException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SceneRegister extends Scene {

	public SceneRegister(Stage stage) {
		this(stage, new StackPane());
	}

	public SceneRegister(final Stage stage, StackPane pane) {
		super(pane);
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10,50,50,50));

		HBox hb = new HBox();
		hb.setPadding(new Insets(20,20,20,30));

		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20,20,20,20));
		gridPane.setHgap(5);
		gridPane.setVgap(5);

		Label lblUserName = new Label("Username");
		final TextField txtUserName = new TextField();
		Label lblPassword = new Label("Password");
		final PasswordField txtPassword = new PasswordField();
		Label lblFirstName = new Label("First Name");
		final TextField txtFirstName = new TextField();
		Label lblLastName = new Label("Last Name");
		final TextField txtLastName = new TextField();
		Label lblIBAN = new Label("IBAN");
		final TextField txtIBAN = new TextField();
		Button btnRegister = new Button("Register");
		final Label lblMessage = new Label();

		gridPane.add(lblUserName, 0, 0);
		gridPane.add(txtUserName, 1, 0);
		gridPane.add(lblPassword, 0, 1);
		gridPane.add(txtPassword, 1, 1);
		gridPane.add(lblFirstName, 0, 2);
		gridPane.add(txtFirstName, 1, 2);
		gridPane.add(lblLastName, 0, 3);
		gridPane.add(txtLastName, 1, 3);
		gridPane.add(lblIBAN, 0, 4);
		gridPane.add(txtIBAN, 1, 4);
		gridPane.add(btnRegister, 3, 4);
		gridPane.add(lblMessage, 1, 5);

		Text text = new Text("Register");
		text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));

		hb.getChildren().add(text);

		btnRegister.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if(check(txtUserName, txtPassword, txtFirstName, txtLastName, txtIBAN)) {
					try {
						User user = new User();
						user.setUser_name(txtUserName.getText());
						user.setPassword(txtPassword.getText());
						user.setFirst_name(txtFirstName.getText());
						user.setLast_name(txtLastName.getText());
						user.setIban(txtIBAN.getText());
						user.setCharacter_slots(5);
						Mmorpg.database.register(user);
						stage.setScene(new SceneMain(stage, user));
					} catch (RegisterException e) {
						lblMessage.setText(e.getMessage());
					}
				} else {
					lblMessage.setText("Please enter all fields");
				}
			}

			public boolean check(TextField... textFields) {
				for(TextField textField : textFields) {
					if(!check(textField)) {
						return false;
					}
				}
				return true;
			}

			public boolean check(TextField textField) {
				if(textField.getText().isEmpty()) {
					return false;
				}
				return true;
			}
		});

		//Add HBox and GridPane layout to BorderPane Layout
		borderPane.setTop(hb);
		borderPane.setCenter(gridPane); 

		pane.getChildren().add(borderPane);
	}

}
