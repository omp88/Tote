package by.epam.tote.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.router.Router;


/**
 * The Interface ActionCommand.
 */
public interface ActionCommand  {
	
	/**
	 * Execute command.
	 *
	 * @param  request
	 * @return  router
	 */
	Router execute(HttpServletRequest request);

}
