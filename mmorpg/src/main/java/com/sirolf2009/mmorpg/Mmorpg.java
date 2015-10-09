package com.sirolf2009.mmorpg;

import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sirolf2009.mmorpg.database.DatabaseManager;
import com.sirolf2009.mmorpg.database.HibernateDatabaseManager;
import com.sirolf2009.mmorpg.scene.SceneLogin;

import javafx.application.Application;
import javafx.stage.Stage;

public class Mmorpg extends Application {

	public static SessionFactory sessionFactory;
	public static DatabaseManager database;

	@Override
	public void start(Stage primaryStage) throws Exception {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		database = new HibernateDatabaseManager();
		
		primaryStage.setTitle("MMORPG");
		primaryStage.setScene(new SceneLogin(primaryStage));
		primaryStage.setWidth(500);
		primaryStage.setHeight(400);
		primaryStage.show();
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		launch(args);
		/*SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		com.sirolf2009.mmorpg.entity.Character character = new com.sirolf2009.mmorpg.entity.Character();
		character.setClass_type("Archer");
		character.setLevel(1);
		character.setName("sirolf2009");
		character.setRace("Undead");
		
		session.save(character);
		
		session.getTransaction().commit();
		factory.close();*/
	}

}
