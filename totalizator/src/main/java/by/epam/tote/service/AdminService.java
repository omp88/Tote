package by.epam.tote.service;

import java.math.BigDecimal;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tote.dao.impl.AdminDaoImpl;
import by.epam.tote.dao.impl.ClientDaoImpl;
import by.epam.tote.entity.Admin;
import by.epam.tote.entity.BetCondition;
import by.epam.tote.entity.BetState;
import by.epam.tote.entity.Client;
import by.epam.tote.entity.FootballBet;
import by.epam.tote.entity.FootballEvent;
import by.epam.tote.entity.FootballResult;
import by.epam.tote.exception.DAOException;
import by.epam.tote.transaction.TransactionManager;
import by.epam.tote.util.PasswordSecurity;


public class AdminService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Find admin.
	 *
	 * @param login
	 * @param password
	 * @return the admin
	 */
	public Admin findAdmin(String login, String password) {

		Admin admin = null;
		AdminDaoImpl dao = new AdminDaoImpl();
		TransactionManager manager = new TransactionManager(dao);

		try {
			if(!dao.checkLoginOriginal(login)) {
				return admin;
			}
			admin = dao.findAdmin(login);
			boolean checkPassword = PasswordSecurity.checkPassword(password, admin.getHashedPassword());
			if (!checkPassword) {
				admin = null;
				LOGGER.debug("Admin password not correct");
			}
		} catch (DAOException e) {
			LOGGER.error("Cant find admin", e);
		} finally {
			manager.close();
		}
		return admin;
	}

	/**
	 * Creates the new event.
	 *
	 * @param event
	 */
	public void createNewEvent(FootballEvent event) {
		AdminDaoImpl dao = new AdminDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			dao.addEvent(event);
		} catch (DAOException e) {
			LOGGER.error("Cant create new football event", e);
		} finally {
			manager.close();
		}
	}

	/**
	 * Adds the event result.
	 *
	 * @param result
	 */
	public void addEventResult(FootballResult result) {

		AdminDaoImpl adminDao = new AdminDaoImpl();
		ClientDaoImpl clientDao = new ClientDaoImpl();
		TransactionManager manager = new TransactionManager(adminDao, clientDao);
		try {
			manager.setAutoCommit(false);
			checkResultId(adminDao, result.getId());
			adminDao.addResult(result);
			LOGGER.debug("result added to DB");
			calculateBets(adminDao, clientDao, result);
			adminDao.updateEventToDone(result.getId());
		} catch (DAOException e) {
			LOGGER.error("Cant add event result", e);
			manager.rollback();
		} finally {
			manager.setAutoCommit(true);
			manager.close();
		}
	}
	
	/**
	 * Checks if is event exist.
	 *
	 * @param id
	 * @return true, if is event exist
	 */
	public boolean  isEventExist(int id) {

		boolean result = false;
		AdminDaoImpl dao = new AdminDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			result = dao.checkEventId(id);
		} catch (DAOException e) {
			LOGGER.error("No event with this id", e);
		} finally {
			manager.close();
		}
		return result;
	}
	public boolean  isBetExist(int id) {
		
		boolean result = false;
		AdminDaoImpl dao = new AdminDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			result = dao.checkBetId(id);
		} catch (DAOException e) {
			LOGGER.error("No bet with this id", e);
		} finally {
			manager.close();
		}
		return result;
	}
	public boolean  isClientExist(int id) {
		
		boolean result = false;
		AdminDaoImpl dao = new AdminDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			result = dao.checkClientId(id);
		} catch (DAOException e) {
			LOGGER.error("No client with this id", e);
		} finally {
			manager.close();
		}
		return result;
	}

	/**
	 * Find clients.
	 *
	 * @return the list
	 */
	public List<Client> findClients() {

		List<Client> clients = null;
		AdminDaoImpl dao = new AdminDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			clients = dao.findClients();
		} catch (DAOException e) {
			LOGGER.error("Cant find clients", e);
		} finally {
			manager.close();
		}
		return clients;
	}

	/**
	 * Find client bets.
	 *
	 * @param clientId
	 * @return the list
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
	 * Find event bets.
	 *
	 * @param eventId
	 * @return the list
	 */
	public List<FootballBet> findEventBets(int eventId) {
		
		List<FootballBet> bets = null;
		AdminDaoImpl dao = new AdminDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			bets = dao.findBetsByEventId(eventId);
		} catch (DAOException e) {
			LOGGER.error("Cant find event bets", e);
		} finally {
			manager.close();
		}
		return bets;
	}
	
	/**
	 * Delete bet.
	 *
	 * @param betId
	 */
	public void deleteBet(int betId) {
		
		AdminDaoImpl dao = new AdminDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			dao.deleteBet(betId);
		} catch (DAOException e) {
			LOGGER.error("Cant delete bet", e);
		} finally {
			manager.close();
		}
	}
	
	/**
	 * Delete client.
	 *
	 * @param clientId
	 */
	public void deleteClient(int clientId) {
		
		AdminDaoImpl dao = new AdminDaoImpl();
		TransactionManager manager = new TransactionManager(dao);
		try {
			 dao.deleteClient(clientId);
		} catch (DAOException e) {
			LOGGER.error("Cant delete client", e);
		} finally {
			manager.close();
		}
	}
	
	/**
	 * Delete event.
	 *
	 * @param eventId
	 */
	public void deleteEvent(int eventId) {
		
		AdminDaoImpl adminDao = new AdminDaoImpl();
		ClientDaoImpl clientDao = new ClientDaoImpl();
		TransactionManager manager = new TransactionManager(adminDao, clientDao);
		try {
			manager.setAutoCommit(false);
			List<FootballBet> eventBets = adminDao.findBetsByEventId(eventId);
			nullifyBets(eventBets, clientDao, adminDao);
			adminDao.deleteEvent(eventId);
		} catch (DAOException e) {
			manager.rollback();
			LOGGER.error("Cant delete event", e);
		} finally {
			manager.setAutoCommit(true);
			manager.close();
		}
	}

	/**
	 * Check result id.
	 *
	 * @param dao
	 * @param id
	 * @throws DAOException
	 */
	private void checkResultId(AdminDaoImpl dao, int id) throws DAOException {

		boolean result = dao.checkResultId(id);
		if (result) {
			LOGGER.error("Result with this Id exists");
			throw new DAOException();
		}
		LOGGER.debug("id is available");
	}

	/**
	 * Calculate bets.
	 *
	 * @param adminDao
	 * @param clientDao
	 * @param footballResult
	 * @throws DAOException
	 */
	private void calculateBets(AdminDaoImpl adminDao, ClientDaoImpl clientDao, FootballResult footballResult) throws DAOException {

		List<FootballBet> bets = adminDao.findBetsByEventId(footballResult.getId());
		List<FootballBet> simpleBets = bets.stream()
				.filter(p -> p.getCondition().toString().equals(footballResult.getType().toString()))
				.collect(Collectors.toList());
		for (FootballBet bet : simpleBets) {
			executeSimpleWinBet(bet, clientDao, adminDao);
		}
		List<FootballBet> scoreBets = bets.stream()
				.filter(p -> p.getCondition().toString().equalsIgnoreCase(BetCondition.SCORE.toString()))
				.collect(Collectors.toList());
		for (FootballBet bet : scoreBets) {
			executeScoreBet(bet, clientDao, adminDao, footballResult);
		}
		bets.removeAll(simpleBets);
		bets.removeAll(scoreBets);
		for (FootballBet bet : bets) {
			executeLoseBets(bet, adminDao);
		}

	}

	
	/**
	 * Execute simple win bet.
	 *
	 * @param bet 
	 * @param clientDao 
	 * @param adminDao 
	 * @throws DAOException
	 */
	private void executeSimpleWinBet(FootballBet bet, ClientDaoImpl clientDao, AdminDaoImpl adminDao) throws DAOException {
		Client client = clientDao.findClientById(bet.getClientId());
		BigDecimal betAmount = bet.getAmount().multiply(BigDecimal.valueOf(bet.getCoeff()));
		BigDecimal addMoney = client.getMoney().add(betAmount);
		clientDao.updateClientMoney(client.getId(), addMoney);
		adminDao.updateBet(BetState.WIN, bet.getId());
	}

	/**
	 * Execute score bet.
	 *
	 * @param bet the bet
	 * @param clientDao
	 * @param adminDao
	 * @param result
	 * @throws DAOException
	 */
	private void executeScoreBet(FootballBet bet,ClientDaoImpl clientDao, AdminDaoImpl adminDao, FootballResult result) throws DAOException {

		if (bet.getTeam1ExpectedScore() == result.getTeam1Score()
				&& bet.getTeam2ExpectedScore() == result.getTeam2Score()) {

			executeSimpleWinBet(bet, clientDao, adminDao);
		} else {

			adminDao.updateBet(BetState.LOSE, bet.getId());
		}
	}

	/**
	 * Execute lose bets.
	 *
	 * @param bet
	 * @param dao
	 * @throws DAOException
	 */
	private void executeLoseBets(FootballBet bet, AdminDaoImpl dao) throws DAOException {

		dao.updateBet(BetState.LOSE, bet.getId());
	}
	
	/**
	 * Nullify bets.
	 *
	 * @param bets
	 * @param clientDao
	 * @param adminDao
	 * @throws DAOException
	 */
	private void nullifyBets(List<FootballBet> bets,ClientDaoImpl clientDao, AdminDaoImpl adminDao) throws DAOException {
		
		for(FootballBet bet : bets) {
			
			Client client = clientDao.findClientById(bet.getClientId());
			BigDecimal clientMoney = client.getMoney();
			BigDecimal clientNewMoney = clientMoney.add(bet.getAmount());
			clientDao.updateClientMoney(client.getId(), clientNewMoney);
			adminDao.deleteBet(bet.getId());
		}
		
	}

}
