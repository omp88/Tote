package by.epam.tote.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tote.exception.ConnectionPoolException;


public class ConnectionPool {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger();

	/** The instance. */
	private static ConnectionPool instance;
	
	/** The pool is created. */
	private static AtomicBoolean poolIsCreated = new AtomicBoolean(false);
	
	/** The lock. */
	private static ReentrantLock lock = new ReentrantLock();
	
	/** The available connections. */
	private BlockingQueue<ProxyConnection> availableConnections;
	
	/** The working connections. */
	private LinkedList<ProxyConnection> workingConnections;
	
	/** The properties. */
	private Properties properties;
	
	/** The connection URL. */
	private String connectionURL;
	
	/** The database manager. */
	private DataBaseManager databaseManager;

	/**
	 * Instantiates a new connection pool.
	 */
	private ConnectionPool() {

		databaseManager = new DataBaseManager();
		databaseManager.registerDriver();

		int minPoolSize = databaseManager.getMinPoolSize();
		int maxPoolSize = databaseManager.getMaxPoolSize();
		properties = databaseManager.getProperties();
		connectionURL = databaseManager.getConnectionURL();

		availableConnections = new ArrayBlockingQueue<>(maxPoolSize);
		workingConnections = new LinkedList<>();

		for (int i = 0; i < minPoolSize; i++) {
			
			ProxyConnection proxyConnection = createConnection();
			availableConnections.offer(proxyConnection);
		}
		if (availableConnections.isEmpty()) {
			LOGGER.fatal("No connections in pool");
			throw new RuntimeException();
		}
	}

	/**
	 * Gets the single instance of ConnectionPool.
	 *
	 * @return single instance of ConnectionPool
	 */
	public static ConnectionPool getInstance() {
		if (!poolIsCreated.get()) {
			try {
				lock.lock();

				if (instance == null) {
					instance = new ConnectionPool();
					poolIsCreated.set(true);

					LOGGER.info("ConnectionPool initialized");
				}
			} finally {
				lock.unlock();
			}
		}

		return instance;
	}

	/**
	 * Creates the connection.
	 *
	 * @return the proxy connection
	 */
	private ProxyConnection createConnection() {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connectionURL, properties);
		} catch (SQLException e) {
			LOGGER.fatal("Connection hasn't been initialized!", e);
			throw new RuntimeException();
		}
		return new ProxyConnection(connection);
	}

	/**
	 * Retrieve connection.
	 *
	 * @return the proxy connection
	 */
	public ProxyConnection retrieveConnection() {
		ProxyConnection connection = null;

		if (availableConnections.isEmpty()) {
			connection = createConnection();
		} else {
			connection = availableConnections.poll();

		}
		workingConnections.offer(connection);
		return connection;
	}

	/**
	 * Putback connection.
	 *
	 * @param connection the connection
	 * @return true, if successful
	 * @throws ConnectionPoolException
	 */
	public boolean putbackConnection(ProxyConnection connection) throws ConnectionPoolException {

		boolean result = false;
		if (connection != null) {
			if (workingConnections.remove(connection)) {
				result = availableConnections.offer(connection);
			} else {
				throw new ConnectionPoolException("Problem with returning connection");
			}
		}
		return result;
	}

	/**
	 * Destroy.
	 */
	public void destroy() {
		try {
			
			for(int i = 0; i < availableConnections.size(); i++) {
				
				ProxyConnection proxy = availableConnections.take();
				proxy.realClose();
			}
			
		} catch (SQLException | InterruptedException e) {
			LOGGER.error(e);
		} 
		databaseManager.deregisterDrivers();
		LOGGER.info("ConnectionPool destroyed!");

	}

}
