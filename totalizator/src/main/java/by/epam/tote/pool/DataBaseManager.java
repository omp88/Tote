package by.epam.tote.pool;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.Driver;

    class DataBaseManager {

	

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger();

	/** The Constants  */
	private static final  String PROPERTIES_NAME = "database";
	
	private static final String CHARACTER_ENCODING = "characterEncoding";

	private static final String USE_SSL = "useSSL";

	private static final String PASSWORD = "password";

	private static final String USER = "user";
	
	/** The DataBase bundle. */
	private ResourceBundle dataBaseBundle;

	/**
	 * Instantiates a new data base manager.
	 */
	DataBaseManager() {
		
		try {
		dataBaseBundle = ResourceBundle.getBundle(PROPERTIES_NAME);
		}catch(MissingResourceException e) {
			LOGGER.fatal(e);
			throw new RuntimeException();
		}
		
	}

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	Properties getProperties() {

		Properties props = new Properties();

		props.put(USER, dataBaseBundle.getString(DBConst.DB_USERNAME.name()));
		props.put(PASSWORD, dataBaseBundle.getString(DBConst.DB_PASSWORD.name()));
		props.put(USE_SSL, dataBaseBundle.getString(DBConst.DB_USE_SSL.name()));
		props.put(CHARACTER_ENCODING, dataBaseBundle.getString(DBConst.DB_CHARACTER_ENCODING.name()));

		return props;
	}

	/**
	 * Gets the connection URL.
	 *
	 * @return the connection URL
	 */
	String getConnectionURL() {
		return dataBaseBundle.getString(DBConst.DB_URL.name());
	}

	/**
	 * Gets the min pool size.
	 *
	 * @return the min pool size
	 */
	int getMinPoolSize() {

		return Integer.parseInt(dataBaseBundle.getString(DBConst.DB_MIN_POOL_SIZE.name()));
	}
	
	/**
	 * Gets the max pool size.
	 *
	 * @return the max pool size
	 */
	int getMaxPoolSize() {
		
		return Integer.parseInt(dataBaseBundle.getString(DBConst.DB_MAX_POOL_SIZE.name()));
	}

	/**
	 * Register driver.
	 */
	void registerDriver() {

		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {

			LOGGER.fatal(e);
			throw new RuntimeException(e);

		}
	}

	/**
	 * Deregister drivers.
	 */
	void deregisterDrivers() {
		Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();

		while (drivers.hasMoreElements()) {
			java.sql.Driver driver = drivers.nextElement();

			try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				LOGGER.error("Cant deregister drivers", e);
			}
		}
	}

}
