package by.epam.tote.transaction;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tote.dao.AbstractDao;
import by.epam.tote.pool.ConnectionPool;
import by.epam.tote.pool.ProxyConnection;

public class TransactionManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger();

	/** The connection. */
	private ProxyConnection connection;

	/**
	 * Instantiates a new transaction manager with one dao.
	 *
	 * @param dao
	 *            
	 */
	public TransactionManager(AbstractDao dao) {

		this.connection = ConnectionPool.getInstance().retrieveConnection();
		dao.setProxy(connection);

	}
	/**
	 * Instantiates a new transaction manager with several daos.
	 *
	 * @param dao, dao
	 *            
	 */
	public TransactionManager(AbstractDao aDao, AbstractDao cDao) {
		
		this.connection = ConnectionPool.getInstance().retrieveConnection();
		aDao.setProxy(connection);
		cDao.setProxy(connection);
		
	}

	/**
	 * Commit.
	 */
	public void commit() {

		try {
			connection.commit();
		} catch (SQLException e) {

			LOGGER.error("Cant commit connection", e);
		}
	}

	/**
	 * Rollback.
	 */
	public void rollback() {

		try {
			connection.rollback();
		} catch (SQLException e) {

			LOGGER.error("Cant rollback connection", e);
		}
	}

	/**
	 * Sets the auto commit.
	 *
	 * @param autoCommit
	 */
	public void setAutoCommit(boolean autoCommit) {

		try {
			connection.setAutoCommit(autoCommit);
		} catch (SQLException e) {

			LOGGER.error("Cant set AutoCommit in connection", e);
		}
	}

	/**
	 * Close.
	 */
	
	public void close() {

		try {
			connection.close();

		} catch (SQLException e) {

			LOGGER.error("Cant close connection", e);
		}
	}

}
