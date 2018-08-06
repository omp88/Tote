package by.epam.tote.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.tote.constant.SqlConstant;
import by.epam.tote.dao.AbstractDao;
import by.epam.tote.dao.AdminDao;
import by.epam.tote.entity.Admin;
import by.epam.tote.entity.BetCondition;
import by.epam.tote.entity.BetState;
import by.epam.tote.entity.Client;
import by.epam.tote.entity.FootballBet;
import by.epam.tote.entity.FootballEvent;
import by.epam.tote.entity.FootballResult;
import by.epam.tote.exception.DAOException;

public class AdminDaoImpl extends AbstractDao implements AdminDao {

	/** SQL Constants */
	private static final String SQL_FIND_ADMIN = "SELECT admin_id, login, password FROM totalizator.administrator WHERE login = ?;";

	private static final String SQL_FIND_ADMIN_LOGIN = "SELECT login FROM totalizator.administrator WHERE login = ?;";

	private static final String SQL_FIND_EVENT_ID = "SELECT event_id FROM totalizator.football_event where event_id = ?;";

	private static final String SQL_FIND_RESULT_ID = "SELECT football_event_id FROM totalizator.football_result WHERE football_event_id = ?;";

	private static final String SQL_FIND_BET_ID = "SELECT bet_id FROM totalizator.bet WHERE bet_id = ?;";

	private static final String SQL_FIND_CLIENT_ID = "SELECT client_id FROM totalizator.client WHERE client_id = ?;";

	private static final String SQL_FIND_CLIENTS = "SELECT client_id, first_name, last_name, email, login, password, passport_id, money_amount FROM totalizator.client;";

	private static final String SQL_FIND_BETS_BY_EVENT_ID = "SELECT bet_id, event_id, client_id, bet_condition, bet_amount, exp_team1_score, exp_team2_score, coeff, state FROM totalizator.bet WHERE event_id = ?;";

	private static final String SQL_DELETE_CLIENT_BY_ID = "DELETE FROM `totalizator`.`client` WHERE `client_id`=?;";

	private static final String SQL_DELETE_BET_BY_ID = "DELETE FROM `totalizator`.`bet` WHERE `bet_id`=?;";

	private static final String SQL_DELETE_EVENT_BY_ID = "DELETE FROM `totalizator`.`football_event` WHERE `event_id`=?;";

	private static final String SQL_ADD_EVENT = "INSERT INTO `totalizator`.`football_event` (`name`, `start`, `team1_name`, `team2_name`, `team1_win_coef`, `team2_win_coef`, `draw_coef`) VALUES (?,?,?,?,?,?,?);";

	private static final String SQL_ADD_RESULT = "INSERT INTO `totalizator`.`football_result` (`football_event_id`, `football_result`, `team1_score`, `team2_score`) VALUES (?,?,?,?);";

	private static final String SQL_UPDATE_BET_STATE = "UPDATE `totalizator`.`bet` SET `state`=? WHERE `bet_id`=?;";
	
	private static final String SQL_UPDATE_EVENT_STATE_TO_DONE = "UPDATE `totalizator`.`football_event` SET `state`='done' WHERE `event_id`=?;";

	/**
	 * Find admin.
	 *
	 * @param login
	 * @return admin
	 * @throws DAOException
	 */
	@Override
	public Admin findAdmin(String login) throws DAOException {

		Admin admin = null;
		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_ADMIN)) {

			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				admin = extractAdmin(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException("Error in findAdmin", e);
		}
		return admin;
	}

	/**
	 * Check login original.
	 *
	 * @param login
	 * @return true, if successful
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public boolean checkLoginOriginal(String login) throws DAOException {

		String result = null;
		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_ADMIN_LOGIN)) {

			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				result = resultSet.getString("login");
			}
			if (login.equals(result)) {
				return true;
			}
		} catch (SQLException e) {
			throw new DAOException("Error in checkLoginOriginal", e);
		}

		return false;
	}

	/**
	 * Adds the event.
	 *
	 * @param event
	 * @throws DAOException
	 */
	@Override
	public void addEvent(FootballEvent event) throws DAOException {

		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_ADD_EVENT)) {

			preparedStatement.setString(1, event.getFootballName());
			preparedStatement.setTimestamp(2, event.getStartTime());
			preparedStatement.setString(3, event.getTeam1Name());
			preparedStatement.setString(4, event.getTeam2Name());
			preparedStatement.setDouble(5, event.getTeam1WinCoef());
			preparedStatement.setDouble(6, event.getTeam2WinCoef());
			preparedStatement.setDouble(7, event.getDrawCoef());

			if (preparedStatement.executeUpdate() != 1) {
				throw new DAOException("Can't add a new event ");
			}
		} catch (SQLException e) {
			throw new DAOException("Error in addEvent", e);
		}
	}

	/**
	 * Adds the result.
	 *
	 * @param result
	 * @throws DAOException
	 */
	@Override
	public void addResult(FootballResult result) throws DAOException {

		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_ADD_RESULT)) {

			preparedStatement.setInt(1, result.getId());
			preparedStatement.setString(2, result.getType().toString().toLowerCase());
			preparedStatement.setInt(3, result.getTeam1Score());
			preparedStatement.setInt(4, result.getTeam2Score());

			if (preparedStatement.executeUpdate() != 1) {
				throw new DAOException("Can't add a new result ");
			}
		} catch (SQLException e) {
			throw new DAOException("Error in addResult", e);
		}
	}

	/**
	 * Update bet.
	 *
	 * @param state
	 * @param betId
	 * @throws DAOException
	 */
	@Override
	public void updateBet(BetState state, int betId) throws DAOException {

		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_UPDATE_BET_STATE)) {

			preparedStatement.setString(1, state.toString().toLowerCase());
			preparedStatement.setInt(2, betId);

			if (preparedStatement.executeUpdate() != 1) {
				throw new DAOException("Can't update bet with id " + betId);
			}
		} catch (SQLException e) {
			throw new DAOException("Error in updateBet", e);
		}
	}
	/**
	 * Update event to done.
	 *
	 * @param eventId
	 * @throws DAOException
	 */
	@Override
	public void updateEventToDone(int eventId) throws DAOException {
		
		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_UPDATE_EVENT_STATE_TO_DONE)) {
			
			preparedStatement.setInt(1, eventId);
			
			if (preparedStatement.executeUpdate() != 1) {
				throw new DAOException("Can't update event with id " + eventId);
			}
		} catch (SQLException e) {
			throw new DAOException("Error in updateEventToDone", e);
		}
	}

	/**
	 * Find clients.
	 *
	 * @return list
	 * @throws DAOException
	 */
	@Override
	public List<Client> findClients() throws DAOException {

		List<Client> clients = new ArrayList<>();

		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_CLIENTS)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Client client = extractClient(resultSet);
				clients.add(client);
			}
		} catch (SQLException e) {
			throw new DAOException("Error in findClients", e);
		}

		return clients;
	}

	/**
	 * Delete bet.
	 *
	 * @param id
	 * @throws DAOException
	 */
	@Override
	public void deleteBet(int id) throws DAOException {

		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_DELETE_BET_BY_ID)) {

			preparedStatement.setInt(1, id);

			if (preparedStatement.executeUpdate() != 1) {
				throw new DAOException("Cant delete bet");
			}
		} catch (SQLException e) {
			throw new DAOException("Error in deleteBet", e);
		}
	}

	/**
	 * Delete client.
	 *
	 * @param id
	 * @throws DAOException
	 */
	@Override
	public void deleteClient(int id) throws DAOException {

		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_DELETE_CLIENT_BY_ID)) {

			preparedStatement.setInt(1, id);

			if (preparedStatement.executeUpdate() != 1) {
				throw new DAOException("Cant delete client");
			}
		} catch (SQLException e) {
			throw new DAOException("Error in deleteClient", e);
		}
	}

	/**
	 * Delete event.
	 *
	 * @param id
	 * @throws DAOException
	 */
	@Override
	public void deleteEvent(int id) throws DAOException {

		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_DELETE_EVENT_BY_ID)) {

			preparedStatement.setInt(1, id);

			if (preparedStatement.executeUpdate() != 1) {
				throw new DAOException("Cant delete event");
			}
		} catch (SQLException e) {
			throw new DAOException("Error in deleteEvent", e);
		}
	}

	/**
	 * Find bets by event id.
	 *
	 * @param id
	 * @return list
	 * @throws DAOException
	 */
	@Override
	public List<FootballBet> findBetsByEventId(int id) throws DAOException {

		List<FootballBet> bets = new ArrayList<>();

		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_BETS_BY_EVENT_ID)) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				FootballBet bet = extractBet(resultSet);
				bets.add(bet);
			}
		} catch (SQLException e) {
			throw new DAOException("Error in findBetsByEventId", e);
		}

		return bets;
	}

	/**
	 * Check result id.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws DAOException
	 */
	@Override
	public boolean checkResultId(int id) throws DAOException {

		int result = 0;
		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_RESULT_ID)) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				result = resultSet.getInt(SqlConstant.FOOTBALL_EVENT_ID);
			}
			if (id == result) {
				return true;
			}
		} catch (SQLException e) {
			throw new DAOException("Error in checkResultId", e);
		}

		return false;
	}

	/**
	 * Check event id.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws DAOException
	 */
	@Override
	public boolean checkEventId(int id) throws DAOException {

		int result = 0;
		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_EVENT_ID)) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				result = resultSet.getInt(SqlConstant.EVENT_ID);
			}
			if (id == result) {
				return true;
			}
		} catch (SQLException e) {
			throw new DAOException("Error in checkResultId", e);
		}

		return false;
	}

	/**
	 * Check bet id.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws DAOException
	 */
	@Override
	public boolean checkBetId(int id) throws DAOException {

		int result = 0;
		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_BET_ID)) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				result = resultSet.getInt(SqlConstant.BET_ID);
			}
			if (id == result) {
				return true;
			}
		} catch (SQLException e) {
			throw new DAOException("Error in checkResultId", e);
		}

		return false;
	}

	/**
	 * Check client id.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws DAOException
	 */
	@Override
	public boolean checkClientId(int id) throws DAOException {

		int result = 0;
		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_CLIENT_ID)) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				result = resultSet.getInt(SqlConstant.CLIENT_ID);
			}
			if (id == result) {
				return true;
			}
		} catch (SQLException e) {
			throw new DAOException("Error in checkResultId", e);
		}

		return false;
	}

	/**
	 * Extract admin.
	 *
	 * @param set
	 * @return admin
	 * @throws SQLException
	 */
	private Admin extractAdmin(ResultSet set) throws SQLException {

		Admin admin = new Admin();
		admin.setId(set.getInt(SqlConstant.ADMIN_ID));
		admin.setLogin(set.getString(SqlConstant.LOGIN));
		admin.setHashedPassword(set.getString(SqlConstant.PASSWORD));
		return admin;
	}

	/**
	 * Extract client.
	 *
	 * @param set
	 * @return client
	 * @throws SQLException
	 */
	private Client extractClient(ResultSet set) throws SQLException {

		Client client = new Client();
		client.setId(set.getInt(SqlConstant.CLIENT_ID));
		client.setFirstName(set.getString(SqlConstant.FIRST_NAME));
		client.setLastName(set.getString(SqlConstant.LAST_NAME));
		client.setLogin(set.getString(SqlConstant.LOGIN));
		client.setEmail(set.getString(SqlConstant.EMAIL));
		client.setHashedPassword(set.getString(SqlConstant.PASSWORD));
		client.setPassportId(set.getString(SqlConstant.PASSPORT));
		client.setMoney(set.getBigDecimal(SqlConstant.MONEY_AMOUNT));
		return client;
	}

	/**
	 * Extract bet.
	 *
	 * @param set
	 * @return football bet
	 * @throws SQLException
	 */
	private FootballBet extractBet(ResultSet set) throws SQLException {

		FootballBet bet = new FootballBet();
		bet.setId(set.getInt(SqlConstant.BET_ID));
		bet.setEventId(set.getInt(SqlConstant.EVENT_ID));
		bet.setClientId(set.getInt(SqlConstant.CLIENT_ID));
		bet.setCondition(BetCondition.valueOf(set.getString(SqlConstant.BET_CONDITION).toUpperCase()));
		bet.setAmount(set.getBigDecimal(SqlConstant.BET_AMOUNT));
		bet.setTeam1ExpectedScore(set.getInt(SqlConstant.EXP_TEAM_1_SCORE));
		bet.setTeam2ExpectedScore(set.getInt(SqlConstant.EXP_TEAM_2_SCORE));
		bet.setCoeff(set.getDouble(SqlConstant.COEFF));
		bet.setState(BetState.valueOf(set.getString(SqlConstant.STATE).toUpperCase()));
		return bet;
	}

}
