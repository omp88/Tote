package by.epam.tote.dao;

import java.util.List;

import by.epam.tote.entity.Admin;
import by.epam.tote.entity.BetState;
import by.epam.tote.entity.Client;
import by.epam.tote.entity.FootballBet;
import by.epam.tote.entity.FootballEvent;
import by.epam.tote.entity.FootballResult;
import by.epam.tote.exception.DAOException;


public interface AdminDao {
	
	
	/**
	 * Find admin.
	 *
	 * @param login
	 * @return  admin
	 * @throws DAOException
	 */
	Admin findAdmin(String login) throws DAOException;
	
	
	/**
	 * Check login original.
	 *
	 * @param login the login
	 * @return true, if successful
	 * @throws DAOException
	 */
	boolean checkLoginOriginal(String login) throws DAOException;
	
	/**
	 * Adds the event.
	 *
	 * @param event
	 * @throws DAOException
	 */
	void addEvent(FootballEvent event) throws DAOException;
	
	/**
	 * Adds the result.
	 *
	 * @param result
	 * @throws DAOException
	 */
	void addResult(FootballResult result) throws DAOException;
	
	/**
	 * Update bet.
	 *
	 * @param state
	 * @param betId
	 * @throws DAOException
	 */
	void updateBet(BetState state, int betId) throws DAOException;
	
	/**
	 * Update event to done.
	 *
	 * @param eventId
	 * @throws DAOException
	 */
	public void updateEventToDone(int eventId) throws DAOException;
	
	/**
	 * Find clients.
	 *
	 * @return list
	 * @throws DAOException
	 */
	List<Client> findClients() throws DAOException;

	
	/**
	 * Delete bet.
	 *
	 * @param id
	 * @throws DAOException
	 */
	void deleteBet(int id) throws DAOException;
	
	/**
	 * Delete client.
	 *
	 * @param id
	 * @throws DAOException
	 */
	void deleteClient(int id) throws DAOException;
	
	/**
	 * Delete event.
	 *
	 * @param id
	 * @throws DAOException
	 */
	void deleteEvent(int id) throws DAOException;
	
	/**
	 * Find bets by event id.
	 *
	 * @param id
	 * @return list
	 * @throws DAOException
	 */
	List<FootballBet> findBetsByEventId(int id) throws DAOException;
	
	/**
	 * Check result id.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws DAOException
	 */
	boolean checkResultId(int id) throws DAOException;
	
	/**
	 * Check bet id.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws DAOException
	 */
	boolean checkBetId(int id) throws DAOException;
	
	/**
	 * Check client id.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws DAOException
	 */
	boolean checkClientId(int id) throws DAOException;
	
	/**
	 * Check event id.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws DAOException
	 */
	public boolean checkEventId(int id) throws DAOException;
	

}
