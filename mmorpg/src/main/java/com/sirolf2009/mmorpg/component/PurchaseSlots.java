package com.sirolf2009.mmorpg.component;

import com.sirolf2009.mmorpg.Mmorpg;
import com.sirolf2009.mmorpg.entity.User;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class PurchaseSlots extends BorderedTitledPane {

	public PurchaseSlots(User user, UserStats stats) {
		super("Purchase slots", createContent(user, stats));
	}

	public static Node createContent(User user, UserStats stats) {
		TextField amount = new TextField() {
		      @Override public void replaceText(int start, int end, String text) {
		        if (text.matches("[0-9]*")) {
		          super.replaceText(start, end, text);
		        }
		      }

		      @Override public void replaceSelection(String text) {
		        if (text.matches("[0-9]*")) {
		          super.replaceSelection(text);
		        }
		      }
		    };
			Label error = new Label();
			error.setTextFill(Color.RED);
		    Button submit = new Button("Submit");
		    submit.setOnMouseClicked(event -> {
		    	try {
		    		Mmorpg.database.addSlots(user, Integer.parseInt(amount.getText()));
		    		stats.update();
		    	} catch(Exception e) {
		    		error.setText(e.getMessage());
		    	}
		    });
		return new HBox(amount, submit, error);
	}

}
