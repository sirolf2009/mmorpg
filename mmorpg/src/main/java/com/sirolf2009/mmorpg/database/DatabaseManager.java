package com.sirolf2009.mmorpg.database;

import java.util.List;

import com.sirolf2009.mmorpg.entity.Character;
import com.sirolf2009.mmorpg.entity.Server;
import com.sirolf2009.mmorpg.entity.User;
import com.sirolf2009.mmorpg.exception.LoginException;
import com.sirolf2009.mmorpg.exception.MmorpgException;
import com.sirolf2009.mmorpg.exception.RegisterException;

public interface DatabaseManager {

	public User login(String username, String password) throws LoginException;
	public void register(User user) throws RegisterException;
	
	public void createCharacter(User user, Character character) throws MmorpgException;
	
	public List<Server> getServers();
	public void joinServer(User user, Server server) throws MmorpgException;

	public void addMoney(User user, double amount);
	public void addMonths(User user, int amount) throws MmorpgException;
	public void addSlots(User user, int amount) throws MmorpgException;
	
}
