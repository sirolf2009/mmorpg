package com.sirolf2009.mmorpg.scene;

import com.sirolf2009.mmorpg.Mmorpg;
import com.sirolf2009.mmorpg.entity.User;
import com.sirolf2009.mmorpg.exception.LoginException;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SceneLogin extends Scene {


	public SceneLogin(Stage stage) {
		this(stage, new BorderPane());
	}

	public SceneLogin(final Stage stage, BorderPane pane) {
		super(pane);
		pane.setPadding(new Insets(10,50,50,50));

		HBox hb = new HBox();
		hb.setPadding(new Insets(20,20,20,30));

		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20,20,20,20));
		gridPane.setHgap(5);
		gridPane.setVgap(5);

		Label lblUserName = new Label("Username");
		final TextField txtUserName = new TextField();
		Label lblPassword = new Label("Password");
		final PasswordField pf = new PasswordField();
		Button btnLogin = new Button("Login");
		Button btnRegister = new Button("Register");
		final Label lblMessage = new Label();

		gridPane.add(lblUserName, 0, 0);
		gridPane.add(txtUserName, 1, 0);
		gridPane.add(lblPassword, 0, 1);
		gridPane.add(pf, 1, 1);
		gridPane.add(btnLogin, 2, 0);
		gridPane.add(btnRegister, 2, 1);
		gridPane.add(lblMessage, 1, 2);

		Text text = new Text("Login");
		text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));

		hb.getChildren().add(text);

		btnLogin.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				try {
					User user = Mmorpg.database.login(txtUserName.getText().toString(), pf.getText().toString());
					stage.setScene(new SceneMain(stage, user));
				} catch (LoginException e) {
					lblMessage.setText(e.getMessage());
					lblMessage.setTextFill(Color.RED);
				}
			}
		});

		btnRegister.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				stage.setScene(new SceneRegister(stage));
			}
		});

		pane.setTop(hb);
		pane.setCenter(gridPane);  
	}

}
