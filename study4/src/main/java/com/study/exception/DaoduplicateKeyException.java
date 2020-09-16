package com.study.exception;

public class DaoduplicateKeyException extends DaoException {

	private String key;
	
	public String getKey() {
		return key;
	}
	
	public DaoduplicateKeyException() {
	}

	public DaoduplicateKeyException(String message) {
		super(message);
	}
	
	public DaoduplicateKeyException(String message, String key) {
		super("key=[" + key +"], " + message);
		this.key = key;
	}

	public DaoduplicateKeyException(Throwable cause) {
		super(cause);
	}

	public DaoduplicateKeyException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DaoduplicateKeyException(String message, String key, Throwable cause) {
		super("key=[" + key +"], " + message, cause);
		this.key = key;
	}

	public DaoduplicateKeyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
