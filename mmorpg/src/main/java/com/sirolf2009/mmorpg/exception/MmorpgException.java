package com.sirolf2009.mmorpg.exception;

public class MmorpgException extends Exception {

	private static final long serialVersionUID = 5061124178010829082L;

	public MmorpgException() {
	}

	public MmorpgException(String message) {
		super(message);
	}

	public MmorpgException(Throwable cause) {
		super(cause);
	}

	public MmorpgException(String message, Throwable cause) {
		super(message, cause);
	}

	public MmorpgException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
