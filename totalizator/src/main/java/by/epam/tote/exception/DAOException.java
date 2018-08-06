package by.epam.tote.exception;

public class DAOException extends Exception{

	/**
	 * Instantiates a new DAO exception.
	 */
	public DAOException() {
		super();
		
	}

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	public DAOException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param arg0 the arg 0
	 */
	public DAOException(String arg0) {
		super(arg0);
		
	}

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param arg0 the arg 0
	 */
	public DAOException(Throwable arg0) {
		super(arg0);
		
	}
	
	

}
