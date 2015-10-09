package com.sirolf2009.mmorpg.database;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sirolf2009.mmorpg.Mmorpg;
import com.sirolf2009.mmorpg.Util;
import com.sirolf2009.mmorpg.entity.Character;
import com.sirolf2009.mmorpg.entity.Server;
import com.sirolf2009.mmorpg.entity.User;
import com.sirolf2009.mmorpg.exception.LoginException;
import com.sirolf2009.mmorpg.exception.MmorpgException;
import com.sirolf2009.mmorpg.exception.RegisterException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class HibernateDatabaseManager implements DatabaseManager {

	private SessionFactory sessionFactory;
	private Object lock;

	public HibernateDatabaseManager() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		lock = new Object();
	}

	@Override
	public User login(String username, String password) throws LoginException {
		synchronized (lock) {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query =  session.createQuery("FROM User WHERE user_name=? AND password=?");
			User user = (User)query.setString(0,username).setString(1, password).uniqueResult();
			session.getTransaction().commit();
			log.info("User Object: "+user);
			if(user != null) {
				return user;
			}
			throw new LoginException(username, password);
		}
	}

	@Override
	public void register(User user) throws RegisterException {
		save(user);
	}

	@Override
	public void addMoney(User user, double amount) {
		user.setBalance(user.getBalance() + amount);
		update(user);
	}

	@Override
	public void addMonths(User user, int amount) throws MmorpgException {
		double price = Util.getPriceForSubscription(amount);
		if(user.getBalance() < price) {
			throw new MmorpgException("Not enough money! Required: "+price);
		}
		user.setBalance(user.getBalance()-price);
		user.setLast_payment(new Date().toString());
		user.setMonths_payed(user.getMonths_payed()+amount);
		update(user);
	}

	@Override
	public void createCharacter(User user, Character character) throws MmorpgException {
		if(user.getCharacter_slots() <= 0) {
			throw new MmorpgException("Not enough character slots");
		}
		log.info("Creating new character "+character);
		try {
		transact(session -> {
			if(session.createSQLQuery("SELECT * FROM Owns WHERE name='"+character.getName()+"';").list().size() != 0) {
				throw new RuntimeException("This name is not available");
			}
			user.getCharacters().add(character);
			user.setCharacter_slots(user.getCharacter_slots()-1);
			session.update(user);
		});
		} catch(Exception e) {
			throw new MmorpgException(e.getMessage());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Server> getServers() {
		synchronized (lock) {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query =  session.createQuery("FROM Server");
			List<Server> servers = query.list();
			session.getTransaction().commit();
			return servers;
		}
	}

	@Override
	public void joinServer(User user, Server server) throws MmorpgException {
		if(server.getConnected_users() >= server.getMax_users()) {
			throw new MmorpgException("Server is full");
		}
		log.info("Joining server "+server);
		server.setConnected_users(server.getConnected_users()+1);
		update(server);
		transact(session -> {
			log.info(server.getAddress()+" "+user.getUser_name());
			boolean exists = Mmorpg.sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM stores WHERE address = ? AND user_name = ?").setString(0, server.getAddress()).setString(1, user.getUser_name()).list().size() == 1;
			if(!exists) {
				String query = "INSERT INTO stores(address, user_name) VALUES(?, ?);";
				log.trace(query);
				Mmorpg.sessionFactory.getCurrentSession().createSQLQuery(query)
				.setString(0, server.getAddress()).setString(1, user.getUser_name()).executeUpdate();
			}
		});
	}

	@Override
	public void addSlots(User user, int amount) throws MmorpgException {
		double price = Util.getPriceForSlots(amount);
		if(user.getBalance() < price) {
			throw new MmorpgException("Not enough money! Required: "+price);
		}
		user.setBalance(user.getBalance()-price);
		user.setLast_payment(new Date().toString());
		user.setCharacter_slots(user.getCharacter_slots()+amount);
	}

	private void save(Object object) {
		transact(session -> session.save(object));
	}

	private void update(Object object) {
		transact(session -> session.update(object));
	}

	private <T> void transact(Consumer<Session> consumer) {
		synchronized (lock) {
			Session session = Mmorpg.sessionFactory.getCurrentSession();
			session.beginTransaction();

			consumer.accept(session);

			session.getTransaction().commit();
		}
	}

}
