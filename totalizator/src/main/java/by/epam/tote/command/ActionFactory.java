package by.epam.tote.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A factory for creating Action objects.
 */
public class ActionFactory {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Define command.
	 *
	 * @param request
	 * @return the action command
	 */
	public ActionCommand defineCommand(String action) {

		ActionCommand current;

		if (action == null || action.isEmpty()) {
			LOGGER.error("no command");
			return new EmptyCommand();
		}
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			LOGGER.error("no command", e);
			current = new EmptyCommand();
		}
		return current;
	}

}
