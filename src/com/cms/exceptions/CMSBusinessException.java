package com.cms.exceptions;

public class CMSBusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	public CMSBusinessException() {
		super();
	}

	public CMSBusinessException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CMSBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public CMSBusinessException(String message) {
		super(message);
	}

	public CMSBusinessException(Throwable cause) {
		super(cause);
	}
}
