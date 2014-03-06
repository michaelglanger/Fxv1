package com.mgl.account.exception;

public class AccountUnknown extends Exception {

	public AccountUnknown() {
		super();
	}

	public AccountUnknown(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AccountUnknown(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountUnknown(String message) {
		super(message);
	}

	public AccountUnknown(Throwable cause) {
		super(cause);
	}

	
}
