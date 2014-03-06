package com.mgl.account.exception;

public class AccountAlreadyExists extends Exception {

	public AccountAlreadyExists() {
		super();
	}

	public AccountAlreadyExists(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AccountAlreadyExists(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountAlreadyExists(String message) {
		super(message);
	}

	public AccountAlreadyExists(Throwable cause) {
		super(cause);
	}

}
