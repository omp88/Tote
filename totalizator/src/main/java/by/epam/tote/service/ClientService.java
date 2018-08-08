package by.epam.tote.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tote.dao.impl.ClientDaoImpl;
import by.epam.tote.entity.Client;
import by.epam.tote.entity.FootballBet;
import by.epam.tote.entity.FootballEvent;
import by.epam.tote.exception.DAOException;
import by.epam.tote.transaction.TransactionManager;
import by.epam.tote.util.PasswordSecurity;


public class ClientService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Find football events.
	 *
	 * @return the list
	 */
	public List<FootballEvent> findFootballEvents() {

		List<FootballEvent> footballEvents = null;
		ClientDaoImpl dao = new ClientDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			footballEvents = dao.findFootballEvents();
		} catch (DAOException e) {
			LOGGER.error("Cant find football events", e);
		} finally {
			manager.close();
		}
		return footballEvents;
	}

	/**
	 * Creates the new client.
	 *
	 * @param client
	 */
	public boolean createNewClient(Client client) {

		boolean result = false;
		ClientDaoImpl dao = new ClientDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			manager.setAutoCommit(false);
			checkLoginOriginal(dao, client.getLogin());
			dao.createNewClient(client);
			manager.commit();
			result = true;
		} catch (DAOException e) {
			manager.rollback();
			LOGGER.error("Cant create new client", e);
		} finally {
			manager.setAutoCommit(true);
			manager.close();
		}
		return result;
	}

	/**
	 * Adds the bet.
	 *
	 * @param bet
	 * @return the big decimal
	 */
	public BigDecimal addBet(FootballBet bet) {

		ClientDaoImpl dao = new ClientDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		BigDecimal newAmount = null;
		try {
			manager.setAutoCommit(false);
			checkBetTime(dao, bet.getEventId(), bet.getBetTime());
			LOGGER.debug(bet.getAmount() + " amount");
			newAmount = checkPayingCapacity(dao, bet.getClientId(), bet.getAmount());
			collectAmountOfBet(dao, bet.getClientId(), newAmount);
			if (bet.getCondition().toString() == "SCORE") {
				dao.createNewBetWithScore(bet);
			} else {
				dao.createNewBetWithoutScore(bet);
			}
			manager.commit();
		} catch (DAOException e) {
			manager.rollback();
			LOGGER.error("Cant add bet", e);
		} finally {
			manager.close();
		}
		return newAmount;
	}

	/**
	 * Find client.
	 *
	 * @param login
	 * @param password
	 * @return client
	 */
	public Client findClient(String login, String password) {

		Client client = null;
		ClientDaoImpl dao = new ClientDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			if(!dao.checkLoginOriginal(login)) {
				return client;
			}
			client = dao.findClient(login);
			boolean checkPassword = PasswordSecurity.checkPassword(password, client.getHashedPassword());
			if (!checkPassword) {
				client = null;
				LOGGER.debug("Client passwort not correct");
			}
		} catch (DAOException e) {
			LOGGER.error("Cant find client", e);
		} finally {
			manager.close();
		}
		return client;
	}
	
	/**
	 * Find clients by id.
	 *
	 * @param clientId
	 * @return client
	 */
	public Client findClientsById(int clientId) {

		Client client = null;
		ClientDaoImpl dao = new ClientDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			client = dao.findClientById(clientId);
		} catch (DAOException e) {
			LOGGER.error("Cant find client by Id", e);
		} finally {
			manager.close();
		}
		return client;
	}
	
	/**
	 * Update client money.
	 *
	 * @param clientId
	 * @param money
	 */
	public void updateClientMoney(int clientId, BigDecimal money) {
		
		ClientDaoImpl dao = new ClientDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			dao.updateClientMoney(clientId, money);
		} catch (DAOException e) {
			LOGGER.error("Cant update client money", e);
		} finally {
			manager.close();
		}
	}
	
	/**
	 * Find client bets.
	 *
	 * @param clientId
	 * @return list
	 */
	public List<FootballBet> findClientBets(int clientId) {

		List<FootballBet> bets = null;
		ClientDaoImpl dao = new ClientDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			bets = dao.findClientBets(clientId);
		} catch (DAOException e) {
			LOGGER.error("Cant find client bets", e);
		} finally {
			manager.close();
		}
		return bets;
	}
	
	/**
	 * Find done client bets.
	 *
	 * @param clientId
	 * @return list
	 */
	public List<FootballBet> findDoneClientBets(int clientId) {
		
		List<FootballBet> bets = null;
		ClientDaoImpl dao = new ClientDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			bets = dao.findDoneClientBets(clientId);
		} catch (DAOException e) {
			LOGGER.error("Cant find client bets", e);
		} finally {
			manager.close();
		}
		return bets;
	}
	
	/**
	 * Find stand client bets.
	 *
	 * @param clientId
	 * @return list
	 */
	public List<FootballBet> findStandClientBets(int clientId) {
		
		List<FootballBet> bets = null;
		ClientDaoImpl dao = new ClientDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			bets = dao.findStandClientBets(clientId);
		} catch (DAOException e) {
			LOGGER.error("Cant find client bets", e);
		} finally {
			manager.close();
		}
		return bets;
	}

	/**
	 * Find fooball event.
	 *
	 * @param id
	 * @return football event
	 */
	public FootballEvent findFooballEvent(int id) {

		FootballEvent event = null;
		ClientDaoImpl dao = new ClientDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			event = dao.findFootballEvent(id);
			LOGGER.debug(event.getFootballName() + "service");
		} catch (DAOException e) {
			LOGGER.error("Cant find football event", e);
		} finally {
			manager.close();
		}
		return event;
	}

	/**
	 * Check bet time.
	 *
	 * @param dao
	 * @param eventId
	 * @param betTime
	 * @throws DAOException
	 */
	private void checkBetTime(ClientDaoImpl dao, int eventId, Timestamp betTime) throws DAOException {

		FootballEvent event = dao.findFootballEvent(eventId);
		Timestamp startTime = event.getStartTime();
		if (betTime.after(startTime)) {
			LOGGER.error("betTime is not acceptable");
			throw new DAOException();
		}
		LOGGER.debug("1. betTime is acceptable");
	}

	/**
	 * Check paying capacity.
	 *
	 * @param dao
	 * @param clientId
	 * @param amount
	 * @return the big decimal
	 * @throws DAOException
	 */
	private BigDecimal checkPayingCapacity(ClientDaoImpl dao, int clientId, BigDecimal amount) throws DAOException {
		Client client = dao.findClientById(clientId);
		LOGGER.debug(client.getFirstName());
		int result = client.getMoney().compareTo(amount);
		LOGGER.debug(result + " result");
		if (result < 0) {
			LOGGER.error("Not enough money to bet");
			throw new DAOException();
		}
		LOGGER.debug("2. Enough money to bet");
		return client.getMoney().subtract(amount);
	}

	/**
	 * Collect amount of bet.
	 *
	 * @param dao
	 * @param clientId
	 * @param amount
	 * @throws DAOException
	 */
	private void collectAmountOfBet(ClientDaoImpl dao, int clientId, BigDecimal amount) throws DAOException {

		dao.updateClientMoney(clientId, amount);
		LOGGER.debug("3. Money subtracted");
	}

	/**
	 * Check login original.
	 *
	 * @param dao
	 * @param login
	 * @throws DAOException
	 */
	private void checkLoginOriginal(ClientDaoImpl dao, String login) throws DAOException {

		boolean result = dao.checkLoginOriginal(login);
		if (result) {
			LOGGER.error("login is not original");
			throw new DAOException();
		}
		LOGGER.debug("login is available");
	}

}
