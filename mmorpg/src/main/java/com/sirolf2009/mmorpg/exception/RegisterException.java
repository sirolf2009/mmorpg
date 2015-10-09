package com.sirolf2009.mmorpg.exception;

public class RegisterException extends MmorpgException {

	private static final long serialVersionUID = -1490435210947712944L;

	public RegisterException(String msg) {
		super(msg);
	}

	public RegisterException(Throwable cause) {
		super(cause);
	}

}
