package itat.zttc.shop.model;

public class ShopException extends RuntimeException {

	private static final long serialVersionUID = -7309874469719887493L;

	public ShopException() {
		super();
	}

	public ShopException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ShopException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShopException(String message) {
		super(message);
	}

	public ShopException(Throwable cause) {
		super(cause);
	}

}
