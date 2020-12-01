package by.itacademy.newsproject.dao.pool;

import java.io.Serializable;

public class ConnectionPoolException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 4225536249130165542L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Exception e) {
		super(e);
	}

	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}
}