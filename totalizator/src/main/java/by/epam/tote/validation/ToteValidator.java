package by.epam.tote.validation;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tote.constant.ClientConstant;
import by.epam.tote.constant.EventConstant;

public class ToteValidator {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger();

	/** The Constant regex for validation */
	private static final String REGEX_CLIENT_NAME = "[A-z]{0,30}||[А-яЁё]{0,30}";

	private static final String REGEX_CLIENT_EMAIL = "[A-z0-9._%+-]+@[A-z0-9.-]+\\.[a-z]{2,3}";

	private static final String REGEX_CLIENT_LOGIN = ".{0,30}";

	private static final String REGEX_CLIENT_PASSWORD = ".{0,30}";

	private static final String REGEX_CLIENT_PASSPORT = "[0-9]{0,30}";

	private static final String REGEX_BET_AMMOUNT = "^\\s*(?=.*[1-9])\\d{1,15}(?:\\.\\d{1,2})?\\s*$";

	private static final String REGEX_BET_SCORE = "\\d{1,3}";

	private static final String REGEX_EVENT_NAME = ".{0,30}";

	private static final String REGEX_EVENT_START = "20(1[8-9]|[2-9][0-9])-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])T(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])";

	private static final String REGEX_EVENT_COEFF = "\\d{1,5}(,|.\\d{0,2})?";

	/**
	 * Validate clients parameters.
	 *
	 * @param clientsParams
	 * @return true, if successful
	 */
	public boolean validateClientsParameters(Map<String, String> clientsParams) {

		String firstName = clientsParams.get(ClientConstant.FIRST_NAME);
		LOGGER.debug(firstName);
		if (!firstName.matches(REGEX_CLIENT_NAME)) {
			return false;
		}
		String lastName = clientsParams.get(ClientConstant.LAST_NAME);
		LOGGER.debug(lastName);
		if (!lastName.matches(REGEX_CLIENT_NAME)) {
			return false;
		}
		String email = clientsParams.get(ClientConstant.EMAIL);
		LOGGER.debug(email);
		if (!email.matches(REGEX_CLIENT_EMAIL)) {
			return false;
		}
		String login = clientsParams.get(ClientConstant.LOGIN);
		LOGGER.debug(login);
		if (!login.matches(REGEX_CLIENT_LOGIN)) {
			return false;
		}
		String password = clientsParams.get(ClientConstant.PASSWORD);
		LOGGER.debug(password);
		if (!password.matches(REGEX_CLIENT_PASSWORD)) {
			return false;
		}
		String passportId = clientsParams.get(ClientConstant.PASSPORT);
		LOGGER.debug(passportId);
		if (!passportId.matches(REGEX_CLIENT_PASSPORT)) {
			return false;
		}
		return true;
	}

	/**
	 * Validate football bet amount.
	 *
	 * @param amount
	 * @return true, if successful
	 */
	public boolean validateFootballBetAmount(String amount) {

		if (!amount.matches(REGEX_BET_AMMOUNT)) {
			return false;
		}
		return true;
	}

	/**
	 * Validate money amount.
	 *
	 * @param amount
	 * @return true, if successful
	 */
	public boolean validateMoneyAmount(String amount) {

		if (!amount.matches(REGEX_BET_AMMOUNT)) {
			return false;
		}
		return true;
	}

	/**
	 * Validate football score.
	 *
	 * @param team1Score
	 * @param team2Score
	 * @return true, if successful
	 */
	public boolean validateFootballScore(String team1Score, String team2Score) {

		if (!team1Score.matches(REGEX_BET_SCORE)) {
			return false;
		}
		if (!team2Score.matches(REGEX_BET_SCORE)) {
			return false;
		}
		return true;
	}

	/**
	 * Validate football event.
	 *
	 * @param eventParams
	 * @return true, if successful
	 */
	public boolean validateFootballEvent(Map<String, String> eventParams) {

		String eventName = eventParams.get(EventConstant.EVENT_NAME);
		LOGGER.debug(eventName);
		LOGGER.debug(eventName.matches(REGEX_EVENT_NAME));
		if (!eventName.matches(REGEX_EVENT_NAME)) {
			return false;
		}

		String eventStart = eventParams.get(EventConstant.EVENT_START);
		LOGGER.debug(eventStart);
		if (!eventStart.matches(REGEX_EVENT_START)) {
			return false;
		}

		String team1Name = eventParams.get(EventConstant.TEAM_1_NAME);
		LOGGER.debug(team1Name);
		if (!team1Name.matches(REGEX_EVENT_NAME)) {
			return false;
		}

		String team2Name = eventParams.get(EventConstant.TEAM_2_NAME);
		LOGGER.debug(team2Name);
		if (!team2Name.matches(REGEX_EVENT_NAME)) {
			return false;
		}

		String team1WinCoeff = eventParams.get(EventConstant.TEAM_1_WIN_COEFF);
		LOGGER.debug(team1WinCoeff);
		if (!team1WinCoeff.matches(REGEX_EVENT_COEFF)) {
			return false;
		}

		String team2WinCoeff = eventParams.get(EventConstant.TEAM_2_WIN_COEFF);
		LOGGER.debug(team2WinCoeff);
		if (!team2WinCoeff.matches(REGEX_EVENT_COEFF)) {
			return false;
		}

		String drawCoeff = eventParams.get(EventConstant.DRAW_COEFF);
		LOGGER.debug(drawCoeff);
		if (!drawCoeff.matches(REGEX_EVENT_COEFF)) {
			return false;
		}
		return true;
	}

}
