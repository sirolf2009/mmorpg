package com.sirolf2009.mmorpg.exception;

public class LoginException extends MmorpgException {

	private static final long serialVersionUID = -6274801250842814420L;
	private String username;
	private String password;
	
	public LoginException(String username, String password) {
		super("Your username or password invalid");
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
