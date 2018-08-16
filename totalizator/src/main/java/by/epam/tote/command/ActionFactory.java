package by.epam.tote.command;

/**
 * A factory for creating Action objects.
 */
public class ActionFactory {


	/**
	 * Define command.
	 *
	 * @param request
	 * @return the action command
	 */
	public ActionCommand defineCommand(String action) {

		ActionCommand current;

		if (action == null || action.isEmpty()) {
			return new EmptyCommand();
		}
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			current = new EmptyCommand();
		}
		return current;
	}

}
