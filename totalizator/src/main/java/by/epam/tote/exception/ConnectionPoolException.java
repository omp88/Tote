package by.epam.tote.exception;


public class ConnectionPoolException extends Exception {

	/**
	 * Instantiates a new connection pool exception.
	 */
	public ConnectionPoolException() {
		super();
		
	}

	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	public ConnectionPoolException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param arg0 the arg 0
	 */
	public ConnectionPoolException(String arg0) {
		super(arg0);
		
	}

	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param arg0 the arg 0
	 */
	public ConnectionPoolException(Throwable arg0) {
		super(arg0);
		
	}

	
}
