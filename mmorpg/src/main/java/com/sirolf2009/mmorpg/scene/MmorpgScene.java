package com.sirolf2009.mmorpg.scene;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;

public abstract class MmorpgScene extends Scene {

	public MmorpgScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		init();
	}

	public MmorpgScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		init();
	}

	public MmorpgScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		init();
	}

	public MmorpgScene(Parent root, double width, double height) {
		super(root, width, height);
		init();
	}

	public MmorpgScene(Parent root, Paint fill) {
		super(root, fill);
		init();
	}

	public MmorpgScene(Parent root) {
		super(root);
		init();
	}
	
	public void init() {
		getStylesheets().add("mmorpg.css");
	}

}
