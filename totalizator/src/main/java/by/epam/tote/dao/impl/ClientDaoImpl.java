package by.epam.tote.dao.impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tote.constant.SqlColumnConstant;
import by.epam.tote.dao.AbstractDao;
import by.epam.tote.dao.ClientDao;
import by.epam.tote.entity.BetCondition;
import by.epam.tote.entity.BetState;
import by.epam.tote.entity.Client;
import by.epam.tote.entity.FootballBet;
import by.epam.tote.entity.FootballEvent;
import by.epam.tote.exception.DAOException;

public class ClientDaoImpl extends AbstractDao implements ClientDao {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger();

	/** SQL Constants */
	private static final String SQL_FIND_FOOTBALL_EVENT = "SELECT event_id, name, start, team1_name, team2_name, team1_win_coef, team2_win_coef, draw_coef FROM football_event WHERE state = 'stand';";
	
	private static final String SQL_ADD_NEW_CLIENT = "INSERT INTO `totalizator`.`client` (`first_name`, `last_name`, `email`, `login`, `password`, `passport_id`) VALUES (?,?,?,?,?,?);";
	
	private static final String SQL_UPDATE_CLIENT_MONEY = "UPDATE `totalizator`.`client` SET `money_amount`=? WHERE `client_id`=?;";
	
	private static final String SQL_ADD_NEW_BET_WITH_SCORE = "INSERT INTO `totalizator`.`bet` (`event_id`, `client_id`, `bet_condition`, `bet_amount`, `exp_team1_score`, `exp_team2_score`, `coeff`, `state`) VALUES (?,?,?,?,?,?,?,?);";
	
	private static final String SQL_ADD_NEW_BET_WITHOUT_SCORE = "INSERT INTO `totalizator`.`bet` (`event_id`, `client_id`, `bet_condition`, `bet_amount`,  `coeff`, `state`) VALUES (?,?,?,?,?,?);";
	
	private static final String SQL_FIND_CLIENT_BY_LOGIN = "SELECT client_id, first_name,last_name, email, login, password, passport_id, money_amount FROM totalizator.client WHERE login = ?;";
	
	private static final String SQL_FIND_CLIENT_LOGIN = "SELECT login FROM totalizator.client WHERE login = ?;";
	
	private static final String SQL_FIND_CLIENT_BY_ID = "SELECT client_id, first_name,last_name, email, login, password, passport_id, money_amount FROM totalizator.client WHERE client_id = ?;";
	
	private static final String SQL_FIND_CLIENT_BETS_BY_ID = "SELECT bet_id, event_id, client_id, bet_condition, bet_amount, exp_team1_score, exp_team2_score, coeff, state FROM totalizator.bet WHERE client_id = ?;";
	
	private static final String SQL_FIND_DONE_CLIENT_BETS = "SELECT bet_id, event_id, client_id, bet_condition, bet_amount, exp_team1_score, exp_team2_score, coeff, state FROM totalizator.bet WHERE client_id = ? AND state != 'stand';";
	
	private static final String SQL_FIND_STAND_CLIENT_BETS = "SELECT bet_id, event_id, client_id, bet_condition, bet_amount, exp_team1_score, exp_team2_score, coeff, state FROM totalizator.bet WHERE client_id = ? AND state = 'stand';";
	
	private static final String SQL_FIND_EVENT_BY_ID = "SELECT event_id, name, start,team1_name, team2_name, team1_win_coef, team2_win_coef, draw_coef  FROM totalizator.football_event WHERE event_id = ?;";

	/**
	 * Creates the new client.
	 *
	 * @param client
	 * @throws DAOException
	 */
	@Override
	public void createNewClient(Client client) throws DAOException {

		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_ADD_NEW_CLIENT)) {

			preparedStatement.setString(1, client.getFirstName());
			preparedStatement.setString(2, client.getLastName());
			preparedStatement.setString(3, client.getEmail());
			preparedStatement.setString(4, client.getLogin());
			preparedStatement.setString(5, client.getPassword());
			preparedStatement.setString(6, client.getPassportId());

			if (preparedStatement.executeUpdate() != 1) {
				throw new DAOException("Can't add a new client ");
			}

		} catch (SQLException e) {
			throw new DAOException("Error in createNewClient", e);
		}

	}

	/**
	 * Creates the new bet with score.
	 *
	 * @param bet
	 * @throws DAOException
	 */
	@Override
	public void createNewBetWithScore(FootballBet bet) throws DAOException {

		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_ADD_NEW_BET_WITH_SCORE)) {

			preparedStatement.setInt(1, bet.getEventId());
			preparedStatement.setInt(2, bet.getClientId());
			preparedStatement.setString(3, bet.getCondition().toString().toLowerCase());
			preparedStatement.setBigDecimal(4, bet.getAmount());
			preparedStatement.setInt(5, bet.getTeam1ExpectedScore());
			preparedStatement.setInt(6, bet.getTeam2ExpectedScore());
			preparedStatement.setDouble(7, bet.getCoeff());
			preparedStatement.setString(8, bet.getState().toString().toLowerCase());

			if (preparedStatement.executeUpdate() != 1) {
				throw new DAOException("Can't add a new bet with score ");
			}

		} catch (SQLException e) {
			throw new DAOException("Error in createNewBetWithScore", e);
		}
	}

	/**
	 * Creates the new bet without score.
	 *
	 * @param bet
	 * @throws DAOException
	 */
	@Override
	public void createNewBetWithoutScore(FootballBet bet) throws DAOException {

		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_ADD_NEW_BET_WITHOUT_SCORE)) {

			preparedStatement.setInt(1, bet.getEventId());
			preparedStatement.setInt(2, bet.getClientId());
			preparedStatement.setString(3, bet.getCondition().toString().toLowerCase());
			preparedStatement.setBigDecimal(4, bet.getAmount());
			preparedStatement.setDouble(5, bet.getCoeff());
			preparedStatement.setString(6, bet.getState().toString().toLowerCase());

			if (preparedStatement.executeUpdate() != 1) {
				throw new DAOException("Can't add a new bet without score ");
			}

		} catch (SQLException e) {
			throw new DAOException("Error in createNewBetWithoutScore", e);
		}
	}

	/**
	 * Find client.
	 *
	 * @param login
	 * @return client
	 * @throws DAOException
	 */
	@Override
	public Client findClient(String login) throws DAOException {

		Client client = null;
		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_CLIENT_BY_LOGIN)) {

			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				client = extractClient(resultSet);
				LOGGER.debug(client.getEmail() + "dao level");
			}
		} catch (SQLException e) {
			throw new DAOException("Error in findClient", e);
		}

		return client;
	}

	/**
	 * Find client bets.
	 *
	 * @param id
	 * @return list
	 * @throws DAOException
	 */
	@Override
	public List<FootballBet> findClientBets(int id) throws DAOException {

		List<FootballBet> bets = new ArrayList<>();

		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_CLIENT_BETS_BY_ID)) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				FootballBet bet = extractBet(resultSet);
				bets.add(bet);
			}
		} catch (SQLException e) {
			throw new DAOException("Error in findClientBets", e);
		}

		return bets;
	}

	/**
	 * Find done client bets.
	 *
	 * @param id
	 * @return list
	 * @throws DAOException
	 */
	@Override
	public List<FootballBet> findDoneClientBets(int id) throws DAOException {

		List<FootballBet> bets = new ArrayList<>();

		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_DONE_CLIENT_BETS)) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				FootballBet bet = extractBet(resultSet);
				bets.add(bet);
			}
		} catch (SQLException e) {
			throw new DAOException("Error in findDoneClientBets", e);
		}

		return bets;
	}

	/**
	 * Find stand client bets.
	 *
	 * @param id
	 * @return list
	 * @throws DAOException
	 */
	@Override
	public List<FootballBet> findStandClientBets(int id) throws DAOException {

		List<FootballBet> bets = new ArrayList<>();

		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_STAND_CLIENT_BETS)) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				FootballBet bet = extractBet(resultSet);
				bets.add(bet);
			}
		} catch (SQLException e) {
			throw new DAOException("Error in findStandClientBets", e);
		}

		return bets;
	}

	/**
	 * Check login original.
	 *
	 * @param login
	 * @return true, if successful
	 * @throws DAOException
	 */
	@Override
	public boolean checkLoginOriginal(String login) throws DAOException {

		String result = null;
		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_CLIENT_LOGIN)) {

			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				result = resultSet.getString("login");
				LOGGER.debug(result + "dao level login");
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
	 * Update client money.
	 *
	 * @param clientId
	 * @param money
	 * @throws DAOException
	 */
	@Override
	public void updateClientMoney(int clientId, BigDecimal money) throws DAOException {
		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_UPDATE_CLIENT_MONEY)) {

			preparedStatement.setBigDecimal(1, money);
			preparedStatement.setInt(2, clientId);

			if (preparedStatement.executeUpdate() != 1) {
				throw new DAOException("Can't update money amount");
			}

		} catch (SQLException e) {
			throw new DAOException("Error in updateClientMoney", e);
		}
	}

	/**
	 * Find client by id.
	 *
	 * @param clientId
	 * @return client
	 * @throws DAOException
	 */
	@Override
	public Client findClientById(int clientId) throws DAOException {

		Client client = null;
		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_CLIENT_BY_ID)) {

			preparedStatement.setInt(1, clientId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				client = extractClient(resultSet);
				LOGGER.debug(client.getEmail() + "dao level");
			}
		} catch (SQLException e) {
			throw new DAOException("Error in updateClientMoney", e);
		}
		return client;
	}

	/**
	 * Find football event.
	 *
	 * @param id the id
	 * @return football event
	 * @throws DAOException
	 */
	@Override
	public FootballEvent findFootballEvent(int id) throws DAOException {

		FootballEvent event = null;
		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_EVENT_BY_ID)) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				event = extractFootballEvent(resultSet);
				LOGGER.debug(event.getFootballName() + "dao level");
			}
		} catch (SQLException e) {
			throw new DAOException("Error in findFootballEvent", e);
		}
		return event;
	}

	/**
	 * Find football events.
	 *
	 * @return list
	 * @throws DAOException
	 */
	@Override
	public List<FootballEvent> findFootballEvents() throws DAOException {

		List<FootballEvent> listFootballEvents = new ArrayList<>();

		try (PreparedStatement preparedStatement = getProxy().prepareStatement(SQL_FIND_FOOTBALL_EVENT)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				FootballEvent footballEvent = extractFootballEvent(resultSet);
				listFootballEvents.add(footballEvent);
			}
		} catch (SQLException e) {
			throw new DAOException("Error in updateClientMoney", e);
		}
		return listFootballEvents;
	}

	/**
	 * Extract football event.
	 *
	 * @param set
	 * @return football event
	 * @throws SQLException
	 */
	private FootballEvent extractFootballEvent(ResultSet set) throws SQLException {

		FootballEvent event = new FootballEvent();
		event.setId(set.getInt(SqlColumnConstant.EVENT_ID));
		event.setFootballName(set.getString(SqlColumnConstant.EVENT_NAME));
		event.setStartTime(set.getTimestamp(SqlColumnConstant.EVENT_START));
		event.setTeam1Name(set.getString(SqlColumnConstant.TEAM_1_NAME));
		event.setTeam2Name(set.getString(SqlColumnConstant.TEAM_2_NAME));
		event.setTeam1WinCoef(set.getDouble(SqlColumnConstant.TEAM_1_WIN_COEFF));
		event.setTeam2WinCoef(set.getDouble(SqlColumnConstant.TEAM_2_WIN_COEFF));
		event.setDrawCoef(set.getDouble(SqlColumnConstant.DRAW_COEF));
		return event;
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
		client.setId(set.getInt(SqlColumnConstant.CLIENT_ID));
		client.setFirstName(set.getString(SqlColumnConstant.FIRST_NAME));
		client.setLastName(set.getString(SqlColumnConstant.LAST_NAME));
		client.setLogin(set.getString(SqlColumnConstant.LOGIN));
		client.setEmail(set.getString(SqlColumnConstant.EMAIL));
		client.setHashedPassword(set.getString(SqlColumnConstant.PASSWORD));
		client.setPassportId(set.getString(SqlColumnConstant.PASSPORT));
		client.setMoney(set.getBigDecimal(SqlColumnConstant.MONEY_AMOUNT));
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
		bet.setId(set.getInt(SqlColumnConstant.BET_ID));
		bet.setEventId(set.getInt(SqlColumnConstant.EVENT_ID));
		bet.setClientId(set.getInt(SqlColumnConstant.CLIENT_ID));
		bet.setCondition(BetCondition.valueOf(set.getString(SqlColumnConstant.BET_CONDITION).toUpperCase()));
		bet.setAmount(set.getBigDecimal(SqlColumnConstant.BET_AMOUNT));
		bet.setTeam1ExpectedScore(set.getInt(SqlColumnConstant.EXP_TEAM_1_SCORE));
		bet.setTeam2ExpectedScore(set.getInt(SqlColumnConstant.EXP_TEAM_2_SCORE));
		bet.setCoeff(set.getDouble(SqlColumnConstant.COEFF));
		bet.setState(BetState.valueOf(set.getString(SqlColumnConstant.STATE).toUpperCase()));
		return bet;
	}
}
