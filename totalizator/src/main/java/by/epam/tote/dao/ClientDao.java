package by.epam.tote.dao;

import java.math.BigDecimal;
import java.util.List;

import by.epam.tote.entity.Client;
import by.epam.tote.entity.FootballBet;
import by.epam.tote.entity.FootballEvent;
import by.epam.tote.exception.DAOException;

public interface ClientDao {
	
	/**
	 * Creates the new client.
	 *
	 * @param client
	 * @throws DAOException
	 */
	void createNewClient(Client client) throws DAOException;
	
	/**
	 * Creates the new bet with score.
	 *
	 * @param bet
	 * @throws DAOException
	 */
	void createNewBetWithScore(FootballBet bet) throws DAOException;
	
	/**
	 * Creates the new bet without score.
	 *
	 * @param bet
	 * @throws DAOException
	 */
	void createNewBetWithoutScore(FootballBet bet) throws DAOException;
	
	/**
	 * Find client.
	 *
	 * @param login
	 * @return client
	 * @throws DAOException
	 */
	Client findClient(String login) throws DAOException;
	
	/**
	 * Find client bets.
	 *
	 * @param id
	 * @return list
	 * @throws DAOException
	 */
	List<FootballBet> findClientBets(int id) throws DAOException;
	
	/**
	 * Find done client bets.
	 *
	 * @param id
	 * @return list
	 * @throws DAOException
	 */
	List<FootballBet> findDoneClientBets(int id) throws DAOException;
	
	/**
	 * Find stand client bets.
	 *
	 * @param id
	 * @return list
	 * @throws DAOException
	 */
	List<FootballBet> findStandClientBets(int id) throws DAOException;
	
	/**
	 * Check login original.
	 *
	 * @param login
	 * @return true, if successful
	 * @throws DAOException
	 */
	boolean checkLoginOriginal(String login) throws DAOException;
	
	/**
	 * Update client money.
	 *
	 * @param clientId
	 * @param money
	 * @throws DAOException
	 */
	void updateClientMoney(int clientId, BigDecimal money) throws DAOException;
	
	/**
	 * Find client by id.
	 *
	 * @param clientId
	 * @return client
	 * @throws DAOException
	 */
	Client findClientById(int clientId) throws DAOException;
	
	/**
	 * Find football event.
	 *
	 * @param id the id
	 * @return football event
	 * @throws DAOException
	 */
	FootballEvent findFootballEvent(int id) throws DAOException;
	
	/**
	 * Find football events.
	 *
	 * @return list
	 * @throws DAOException
	 */
	List<FootballEvent> findFootballEvents() throws DAOException;

}
